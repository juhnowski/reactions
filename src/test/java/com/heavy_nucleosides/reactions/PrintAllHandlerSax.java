package com.heavy_nucleosides.reactions;

import com.heavy_nucleosides.model.Reaction;
import com.orientechnologies.orient.core.db.ODatabaseSession;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
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


    static OrientDB orient = new OrientDB("remote:localhost", OrientDBConfig.defaultConfig());
    static ODatabaseSession db;
    static ODocument reaction;
    static ODocument source;
    static ODocument productList;
    static ODocument product;
    static ODocument molecule;
    static ODocument reactantList;
    static ODocument reactant;
    static ODocument amount;
    static ODocument spectatorList;
    static ODocument spectator;
    static ODocument reactionActionList;
    static ODocument reactionAction;
    static ODocument chemical;

    static String objName="";

    @Override
    public void startDocument() {
        db = orient.open("reactions", "root", "1");
        System.out.println("db open");
    }

    @Override
    public void endDocument() {
        db.save(reaction);
        db.close();
        System.out.println("db close");
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


        switch(qName) {
            case REACTION:
                objName = REACTION;
                reaction = new ODocument(REACTION);
                source = new ODocument(SOURCE);
                productList = new ODocument(PRODUCT_LIST);
                reactantList = new ODocument(REACTANT_LIST);
                spectatorList = new ODocument(SPECTATOR_LIST);
                reactionActionList = new ODocument(REACTION_ACTION_LIST);

                reaction.field(SOURCE, source);
                reaction.field(PRODUCT_LIST, productList);
                reaction.field(REACTANT_LIST, reactantList);
                reaction.field(SPECTATOR_LIST, spectatorList);
                reaction.field(REACTION_ACTION_LIST, reactionActionList);
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
                objName = PRODUCT;
                product = new ODocument(PRODUCT);
                productList.field(PRODUCT, product);
                break;
            case MOLECULE:
                molecule = new ODocument(MOLECULE);
                molecule.field(ID, attributes.getValue(ID));
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
            case NAME:
                molecule.field(NAME, currentValue.toString());
                break;
            case IDENTIFIER:
                if (objName.equals(PRODUCT)){
                    if (attributes.getValue(0).equals(CML_SMILES)){
                        product.field(SMILES, currentValue.toString());
                    } else {
                        product.field(INCHI, currentValue.toString());
                    }
                    break;
                }
                if (objName.equals(SPECTATOR)){
                    if (attributes.getValue(0).equals(CML_SMILES)){
                        spectator.field(SMILES, currentValue.toString());
                    } else {
                        spectator.field(INCHI, currentValue.toString());
                    }
                    break;
                }
            case REACTANT:
                objName = REACTANT;
                reactant = new ODocument(REACTANT);
                reactant.field(ROLE, attributes.getValue(ROLE));
                reactant.field(COUNT, attributes.getValue(COUNT));
                reactantList.field(REACTANT, reactant);
                break;
            case AMOUNT:
                amount = new ODocument(AMOUNT);
                amount.field(PROPERTY_TYPE, attributes.getValue(PROPERTY_TYPE));
                amount.field(NORMALIZED_VALUE, attributes.getValue(NORMALIZED_VALUE));
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
                objName = SPECTATOR;
                spectator = new ODocument(SPECTATOR);
                spectator.field(ROLE, attributes.getValue(ROLE));
                spectatorList.field(SPECTATOR, spectator);
                break;
            case REACTION_ACTION:
                objName = REACTION_ACTION;
                reactionAction = new ODocument(REACTION_ACTION);
                reactionAction.field(ACTION, attributes.getValue(ROLE));
                reactionActionList.field(REACTION_ACTION, reactionAction);
                break;
            case PHRASE_TEXT:
                reactionAction.field(PHRASE_TEXT, currentValue.toString());
                break;
            case CHEMICAL:
                break;
        }
//        if (objName.equals(qName)) {
//
//        } else {
//
//        }
//
//        if (qName.equalsIgnoreCase("staff")) {
//            // get tag's attribute by name
//            String id = attributes.getValue("id");
//            System.out.printf("Staff id : %s%n", id);
//        }
//
//        if (qName.equalsIgnoreCase("salary")) {
//            // get tag's attribute by index, 0 = first attribute
//            String currency = attributes.getValue(0);
//            System.out.printf("Currency :%s%n", currency);
//        }

    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {

        System.out.printf("End Element : %s%n", qName);

        if (qName.equalsIgnoreCase("name")) {
            System.out.printf("Name : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("role")) {
            System.out.printf("Role : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("salary")) {
            System.out.printf("Salary : %s%n", currentValue.toString());
        }

        if (qName.equalsIgnoreCase("bio")) {
            System.out.printf("Bio : %s%n", currentValue.toString());
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
