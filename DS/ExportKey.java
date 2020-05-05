
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
	public static void printKeyAndWriteKey(Document xmlDocument, File fileName)
	    {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer;
	        try {
	            transformer = tf.newTransformer();
	            FileOutputStream outStream = new FileOutputStream(new File(String.valueOf(fileName))); 
	            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
	            
	           } 
	        catch (TransformerException e) 
	        {
	            e.printStackTrace();
	        }
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }	 
	private static void printKey(Document xmlDocument, String fileName) {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer;
	        try {
	            transformer = tf.newTransformer();
	             
	            StringWriter writer = new StringWriter();
	            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
	            String xmlString = writer.getBuffer().toString();   
	            System.out.println(xmlString);          
	             
	            FileOutputStream outStream = new FileOutputStream(new File(fileName)); 
	            transformer.transform(new DOMSource(xmlDocument), new StreamResult(outStream));
	        }
	        catch (TransformerException e) 
	        {
	            e.printStackTrace();
	        }
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }
	    }


}
//Metoden printKey dhe convertXMLFileToXMLDocument e kame marre te gatshme nga faqja :
//https://howtodoinjava.com/xml/xml-to-string-write-xml-file/
