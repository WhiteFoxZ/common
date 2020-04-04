// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileInputStream;
import javax.servlet.ServletContext;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.log4j.Logger;

public class QueryXMLParser
{
    private static Logger log;
    
    static {
        QueryXMLParser.log = Logger.getLogger((Class)QueryXMLParser.class);
    }
    
    public static void getQuery(String path) throws SAXException, IOException, ParserConfigurationException {
        if (path == null) {
            path = "C:\\Users\\LEESR\\Downloads\\people.xml";
        }
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.parse(new File(path));
        final NodeList list = doc.getElementsByTagName("query1");
        for (int i = 0; list.item(i) != null; ++i) {
            final Element element = (Element)list.item(i);
            final String contents = element.getTextContent();
            QueryXMLParser.log.debug((Object)(String.valueOf(i) + " " + contents));
        }
    }
    
    public static String getQuery(final Class c, final String fileName, final String nodeName) throws SAXException, IOException, ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.parse(c.getResourceAsStream(fileName));
        final NodeList list = doc.getElementsByTagName(nodeName);
        int i = 0;
        String contents = "";
        while (list.item(i) != null) {
            final Element element = (Element)list.item(i);
            contents = element.getTextContent();
            ++i;
        }
        return contents;
    }
    
    public static String getQuery(final ServletContext con, final String path, final String fileName, final String nodeName) throws SAXException, IOException, ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final File f = new File(String.valueOf(con.getRealPath("/")) + "/WEB-INF/classes/" + path + "/" + fileName);
        final FileInputStream fis = new FileInputStream(f);
        final Document doc = builder.parse(fis);
        final NodeList list = doc.getElementsByTagName(nodeName);
        int i = 0;
        String contents = "";
        while (list.item(i) != null) {
            final Element element = (Element)list.item(i);
            contents = element.getTextContent();
            ++i;
        }
        return contents;
    }
    
    public static void getXXX(final String fileName, final String nodeName) throws SAXException, IOException, ParserConfigurationException {
        final FileInputStream f = new FileInputStream(fileName);
        final InputStreamReader ir = new InputStreamReader(f);
        final StringBuffer sb = new StringBuffer("");
        final char[] s = new char[1025];
        String a = null;
        while (ir.read(s) > -1) {
            a = new String(s, 0, s.length);
            QueryXMLParser.log.debug((Object)a);
            if (a.contentEquals("<activity")) {
                QueryXMLParser.log.debug((Object)"************");
            }
        }
        QueryXMLParser.log.debug((Object)sb.toString());
    }
    
    public static void main(final String[] args) {
    }
}
