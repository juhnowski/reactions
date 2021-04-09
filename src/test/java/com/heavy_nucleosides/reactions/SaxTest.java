package com.heavy_nucleosides.reactions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class SaxTest {

    private static final String FILENAME = "/home/ilya/reactions/grants/2016/I20160105.xml";

        public static void main(String[] args) {

            SAXParserFactory factory = SAXParserFactory.newInstance();

            try {

                SAXParser saxParser = factory.newSAXParser();

                PrintAllHandlerSax handler = new PrintAllHandlerSax();
                saxParser.parse(FILENAME, handler);

            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

    }
}
