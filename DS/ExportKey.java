
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class ExportKey {
	private static Document convertXMLFileToXMLDocument(String filePath) 
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	     
	    DocumentBuilder builder = null;
	    try
	    {
	        builder = factory.newDocumentBuilder();
	         
	        Document xmlDocument = builder.parse(new File(filePath));
	         
	        return xmlDocument;
	    } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    }
	    return null;
	}
	


}
//Metoden printKey dhe convertXMLFileToXMLDocument e kame marre te gatshme nga faqja :
//https://howtodoinjava.com/xml/xml-to-string-write-xml-file/
