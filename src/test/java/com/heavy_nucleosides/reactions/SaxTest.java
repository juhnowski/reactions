package com.heavy_nucleosides.reactions;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SaxTest {

    private static final String FILENAME = "/home/ilya/reactions/grants/2016/I20160105.xml";
    private static final String DIR = "/home/ilya/reactions_data"; // "/home/ilya/reactions_d1"; //

        public static void main(String[] args) {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            Map<String,String> err = new HashMap<>();
            try {

                SAXParser saxParser = factory.newSAXParser();
                PrintAllHandlerSax handler = new PrintAllHandlerSax();

                List<File> files = Stream.of(new File(DIR).listFiles())
                        .filter(file -> !file.isDirectory())
                        .map(File::getAbsoluteFile)
                        .collect(Collectors.toList());

                int total = files.size();
                for (int i=0; i < total; i++) {

                    File fn = files.get(i);
                    System.out.println(i + "/" + total + " fn="+ fn + "("+fn.length()+")");
                    try {
                        saxParser.parse(fn, handler);
                    } catch (Exception ex){
                        System.out.println(fn+"=>"+ex.getMessage());
                        err.put(fn.toString(),ex.getMessage());
                    }

                    System.gc();
                }


            } catch (ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }

            System.out.println("------------- parsing errors ----------------");
            err.forEach((k,v)->System.out.println(k+" => "+v));

    }
}
