package com.heavy_nucleosides.reactions;

import com.heavy_nucleosides.model.Reaction;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.impl.ODocument;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;

public class PrintAllHandlerSax extends DefaultHandler {
    private static final String REACTION = "reaction";
    private static final String SOURCE = "source";
    private static final String DOCUMENT_ID = "documentId";
    private static final String HEADING_TEXT = "headingText";
    private static final String PARAGRAPH_NUM = "paragraphNum";
    private static final String PARAGRAPH_TEXT = "paragraphText";
    private static final String REACTION_SMILES = "reactionSmiles";
    private static final String PRODUCT_LIST = "productList";
    private static final String PRODUCT = "product";
    private static final String MOLECULE = "molecule";
    private static final String NAME = "name";
    private static final String IDENTIFIER = "identifier";
    private static final String CML_SMILES = "cml:smiles";
    private static final String SMILES = "smiles";
    private static final String CML_INCHI = "cml:inchi";
    private static final String INCHI = "inchi";
    private static final String ID = "id";

    private static final String REACTANT_LIST = "reactantList";
    private static final String REACTANT = "reactant";
    private static final String ROLE = "role";
    private static final String COUNT = "count";
    private static final String AMOUNT = "amount";
    private static final String PROPERTY_TYPE = "propertyType";
    private static final String NORMALIZED_VALUE = "normalizedValue";
    private static final String VALUE = "value";
    private static final String ENTITY_TYPE = "entityType";

    private static final String SPECTATOR_LIST = "spectatorList";
    private static final String SPECTATOR = "spectator";

    private static final String REACTION_ACTION_LIST = "reactionActionList";
    private static final String REACTION_ACTION = "reactionAction";
    private static final String PHRASE_TEXT = "phraseText";
    private static final String PARAMETER = "parameter";
    private static final String CHEMICAL = "chemical";
    private static final String ACTION = "action";

    private StringBuilder currentValue = new StringBuilder();
    private Attributes currentAttributes;

    private OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
    private ODatabaseSession db;
    private ODocument reaction;
    private ODocument source;
    private ODocument productList;
    private ODocument product;
    private ODocument molecule;
    private ODocument reactantList;
    private ODocument reactant;
    private ODocument amount;
    private ODocument spectatorList;
    private ODocument spectator;
    private ODocument reactionActionList;
    private ODocument reactionAction;
    private ODocument chemical;

    private String objName="";
    private String qNameStr="";
    private String cmlStr="";

    private Attributes moleculeAttributes;
    private Attributes identifierAttributes;
    private Attributes reactantAttributes;
    private Attributes amountAttributes;
    private Attributes spectatorAttributes;
    private Attributes reactionActionAttributes;

    @Override
    public void startDocument() {
        db = orient.open("reactions", "root", "1");
        System.out.println("db open");
    }

    @Override
    public void endDocument() {
        long reactionCnt = db.getClass(REACTION).count();
        long sourceCnt = db.getClass(SOURCE).count();
        long productListCnt = db.getClass(PRODUCT_LIST).count();
        long productCnt = db.getClass(PRODUCT).count();
        long moleculeCnt = db.getClass(MOLECULE).count();
        long reactantListCnt = db.getClass(REACTANT_LIST).count();
        long reactantCnt = db.getClass(REACTANT).count();
        long amountCnt = db.getClass(AMOUNT).count();
        long spectatorListCnt = db.getClass(SPECTATOR_LIST).count();
        long spectatorCnt = db.getClass(SPECTATOR).count();
        long reactionActionListCnt = db.getClass(REACTION_ACTION_LIST).count();
        long reactionActionCnt = db.getClass(REACTION_ACTION).count();
        long chemicalCnt = db.getClass(CHEMICAL).count();

        db.close();

        System.out.println("-------------- STATISTIC -------------");
        System.out.format("%s:      %d%n",REACTION, reactionCnt);
        System.out.format("%s:      %d%n",SOURCE, sourceCnt);
        System.out.format("%s:      %d%n",PRODUCT_LIST, productListCnt);
        System.out.format("%s:      %d%n",PRODUCT, productCnt);
        System.out.format("%s:      %d%n",MOLECULE, moleculeCnt);
        System.out.format("%s:      %d%n",REACTANT_LIST, reactantListCnt);
        System.out.format("%s:      %d%n",REACTANT, reactantCnt);
        System.out.format("%s:      %d%n",AMOUNT, amountCnt);
        System.out.format("%s:      %d%n",SPECTATOR_LIST, spectatorListCnt);
        System.out.format("%s:      %d%n",SPECTATOR, spectatorCnt);
        System.out.format("%s:      %d%n",REACTION_ACTION_LIST, reactionActionListCnt);
        System.out.format("%s:      %d%n",REACTION_ACTION, reactionActionCnt);
        System.out.format("%s:      %d%n",CHEMICAL, chemicalCnt);
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        qName = qName.replace("dl:","");
        System.out.printf("Start Element : qName=%s%n",qName);
        currentAttributes = attributes;

        switch(qName) {
            case REACTION:
                objName = REACTION;
                reaction = new ODocument(REACTION);
                source = new ODocument(SOURCE);
                productList = new ODocument(PRODUCT_LIST);
                reactantList = new ODocument(REACTANT_LIST);
                spectatorList = new ODocument(SPECTATOR_LIST);
                reactionActionList = new ODocument(REACTION_ACTION_LIST);
                break;
            case SOURCE:
                objName = SOURCE;
                break;
            case PRODUCT:
                product = new ODocument(PRODUCT);
                objName = PRODUCT;
                break;
            case MOLECULE:
                molecule = new ODocument(MOLECULE);
                moleculeAttributes = attributes;
                break;
            case IDENTIFIER:
                identifierAttributes = attributes;
                break;
            case REACTANT:
                objName = REACTANT;
                reactantAttributes = attributes;
                reactant = new ODocument(REACTANT);
                break;
            case AMOUNT:
                amountAttributes = attributes;
                amount = new ODocument(AMOUNT);
                break;
            case SPECTATOR:
                objName = SPECTATOR;
                spectatorAttributes = attributes;
                spectator = new ODocument(SPECTATOR);
                break;
            case REACTION_ACTION:
                objName = REACTION_ACTION;
                reactionActionAttributes = attributes;
                reactionAction = new ODocument(REACTION_ACTION);
                break;
            case CHEMICAL:
                objName = CHEMICAL;
                chemical = new ODocument(CHEMICAL);
                break;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {

        qName = qName.replace("dl:","");
        System.out.printf("Start Element : qName=%s%n",qName);


        switch(qName) {
            case REACTION:
                reaction.field(SOURCE, source);
                reaction.field(PRODUCT_LIST, productList);
                reaction.field(REACTANT_LIST, reactantList);
                reaction.field(SPECTATOR_LIST, spectatorList);
                reaction.field(REACTION_ACTION_LIST, reactionActionList);
                db.save(reaction);
                System.out.println("reaction appended");
                break;
            case SOURCE:
                objName = SOURCE;
                break;
            case DOCUMENT_ID:
                if (objName.equals(SOURCE)){
                    source.field(DOCUMENT_ID,currentValue.toString());
                }
                break;
            case HEADING_TEXT:
                if (objName.equals(SOURCE)){
                    source.field(HEADING_TEXT,currentValue.toString());
                }
                break;
            case PARAGRAPH_NUM:
                if (objName.equals(SOURCE)){
                    source.field(PARAGRAPH_NUM,currentValue.toString());
                }
                break;
            case PARAGRAPH_TEXT:
                source.field(PARAGRAPH_TEXT,currentValue.toString());
                break;
            case REACTION_SMILES:
                reaction.field(REACTION_SMILES,currentValue.toString());
                break;
            case PRODUCT:
                productList.field(PRODUCT, product);
                break;
            case MOLECULE:
                for (int index = 0; index < moleculeAttributes.getLength(); index++) {
                    qNameStr = moleculeAttributes.getLocalName(index);
                    if(qNameStr.equals(ID)) {
                        molecule.field(ID, moleculeAttributes.getValue(ID));
                    }
                }

                if (objName.equals(PRODUCT)){
                    product.field(MOLECULE, molecule);
                    break;
                }
                if (objName.equals(REACTANT)){
                    reactant.field(MOLECULE, molecule);
                    break;
                }
                if (objName.equals(SPECTATOR)){
                    spectator.field(MOLECULE, molecule);
                    break;
                }
                if (objName.equals(CHEMICAL)){
                    chemical.field(MOLECULE, molecule);
                    break;
                }
            case NAME:
                molecule.field(NAME, currentValue.toString());
                break;
            case IDENTIFIER:
                if (objName.equals(PRODUCT)){
                    for (int index = 0; index < identifierAttributes.getLength(); index++) {
                        qNameStr = identifierAttributes.getLocalName(index);
                        if (qNameStr.equals(CML_SMILES) ){
                            product.field(SMILES, currentValue.toString());
                        }
                        if (qNameStr.equals(CML_INCHI) ){
                            product.field(INCHI, currentValue.toString());
                        }
                    }
                    break;
                }
                if (objName.equals(SPECTATOR)){
                    for (int index = 0; index < identifierAttributes.getLength(); index++) {
                        qNameStr = identifierAttributes.getLocalName(index);
                        if (qNameStr.equals(CML_SMILES) ){
                            spectator.field(SMILES, currentValue.toString());
                        }
                        if (qNameStr.equals(CML_INCHI) ){
                            spectator.field(INCHI, currentValue.toString());
                        }
                    }
                    break;
                }
                if (objName.equals(REACTION_ACTION)){
                    for (int index = 0; index < identifierAttributes.getLength(); index++) {
                        qNameStr = identifierAttributes.getLocalName(index);
                        if (qNameStr.equals(CML_SMILES) ){
                            reactionAction.field(SMILES, currentValue.toString());
                        }
                        if (qNameStr.equals(CML_INCHI) ){
                            reactionAction.field(INCHI, currentValue.toString());
                        }
                    }
                    break;
                }
            case REACTANT:
                for (int index = 0; index < reactantAttributes.getLength(); index++) {
                    qNameStr = reactantAttributes.getLocalName(index);
                    if(qNameStr.equals(ROLE)) {
                        reactant.field(ROLE, reactantAttributes.getValue(index));
                    }
                    if(qNameStr.equals(COUNT)) {
                        reactant.field(COUNT, reactantAttributes.getValue(index));
                    }
                }
                reactantList.field(REACTANT, reactant);
                break;
            case AMOUNT:
                for (int index = 0; index < amountAttributes.getLength(); index++) {
                    qNameStr = amountAttributes.getLocalName(index).replace("dl:","");
                    if (qNameStr.equals(PROPERTY_TYPE)){
                        amount.field(PROPERTY_TYPE, amountAttributes.getValue(index));
                    }
                    if (qNameStr.equals(NORMALIZED_VALUE)){
                        amount.field(NORMALIZED_VALUE, amountAttributes.getValue(index));
                    }
//
//                    System.out.print(amountAttributes.getLocalName(index) + " => "
//                            + amountAttributes.getValue(index));
                }
                amount.field(VALUE, currentValue.toString());
                if (objName.equals(REACTANT)){
                    reactant.field(AMOUNT, amount);
                    break;
                }
                if (objName.equals(SPECTATOR)){
                    spectator.field(AMOUNT, amount);
                    break;
                }
            case ENTITY_TYPE:
                if (objName.equals(REACTANT)){
                    reactant.field(ENTITY_TYPE, amount);
                    break;
                }
                if (objName.equals(SPECTATOR)){
                    spectator.field(ENTITY_TYPE, amount);
                    break;
                }
                break;
            case SPECTATOR:
                for (int index = 0; index < spectatorAttributes.getLength(); index++) {
                    qNameStr = spectatorAttributes.getLocalName(index);
                    if (qNameStr.equals(ROLE)){
                        spectator.field(ROLE, spectatorAttributes.getValue(index));
                    }
                }
                spectatorList.field(SPECTATOR, spectator);
                break;
            case REACTION_ACTION:
                for (int index = 0; index < spectatorAttributes.getLength(); index++) {
                    qNameStr = spectatorAttributes.getLocalName(index);
                    if (qNameStr.equals(ACTION)){
                        reactionAction.field(ACTION, reactionActionAttributes.getValue(index));
                    }
                }
                reactionActionList.field(REACTION_ACTION, reactionAction);
                break;
            case PHRASE_TEXT:
                reactionAction.field(PHRASE_TEXT, currentValue.toString());
                break;
            case CHEMICAL:
                reactionActionList.field(CHEMICAL, chemical);
                break;
        }

    }

    // http://www.saxproject.org/apidoc/org/xml/sax/ContentHandler.html#characters%28char%5B%5D,%20int,%20int%29
    // SAX parsers may return all contiguous character data in a single chunk,
    // or they may split it into several chunks
    @Override
    public void characters(char ch[], int start, int length) {

        // The characters() method can be called multiple times for a single text node.
        // Some values may missing if assign to a new string

        // avoid doing this
        // value = new String(ch, start, length);

        // better append it, works for single or multiple calls
        currentValue.append(ch, start, length);

    }
}
