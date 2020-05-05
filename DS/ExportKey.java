
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
	
		 public static void printKey(Document xmlDocument)
	    {
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer;
	        try {
	            transformer = tf.newTransformer();
	           
	            StringWriter writer = new StringWriter();
	            transformer.transform(new DOMSource(xmlDocument), new StreamResult(writer));
	            String xmlString = writer.getBuffer().toString();   
	            System.out.println(xmlString);          
	            
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
	 
	public static void export_pub_priv(String name, String pri_pub,String newFile) {
		try {
			File private_key = new File("keys/", name + ".xml");
			File public_key = new File("keys/", name + ".pub.xml");
			
			if (pri_pub.equalsIgnoreCase("public")) {
				
				if (public_key.exists()) {
					Document xmlDoc = convertXMLFileToXMLDocument(String.valueOf(public_key));
					
					File pubnew =new File("keys/",newFile);
					pubnew.createNewFile();
					public_key.delete();
					printKeyAndWriteKey(xmlDoc,pubnew);
					System.out.println("Celesi publik u ruajt ne fajllin \"" + newFile + "\"");
					
				}
				else {
					
					System.out.println("Gabim : Qelesi publik \"" + name + "\" nuk ekziston");
				
				}
			} 
			
			else if (pri_pub.equalsIgnoreCase("private")) {
				
				if (private_key.exists()) {
					Document xmlDoc = convertXMLFileToXMLDocument(String.valueOf(private_key));
					
					File pubnew =new File("keys/",newFile);
					pubnew.createNewFile();
					private_key.delete();
					printKeyAndWriteKey(xmlDoc,pubnew);
					System.out.println("Celesi private u ruajt ne fajllin \"" + newFile + "\"");
				} 
				else {
					
					System.out.println("Gabim : Qelesi privat \"" + name + "\" nuk ekziston");

				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	public static void export_pub_priv1(String name, String pri_pub) {
		try {
			File private_key = new File("keys/", name + ".xml");
			File public_key = new File("keys/", name + ".pub.xml");
			
			if (pri_pub.equalsIgnoreCase("public")) {
				
				if (public_key.exists()) {
					Document xmlDoc = convertXMLFileToXMLDocument(String.valueOf(public_key));	
					printKey(xmlDoc);
					
					
				}
				else {
					
					System.out.println("Gabim : Qelesi publik \"" + name + "\" nuk ekziston");
				
				}
			} 
			
			else if (pri_pub.equalsIgnoreCase("private")) {
				
				if (private_key.exists()) {
					Document xmlDoc = convertXMLFileToXMLDocument(String.valueOf(private_key));
					printKey(xmlDoc);
					
				} 
				else {
					
					System.out.println("Gabim : Qelesi privat \"" + name + "\" nuk ekziston");

				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}


}
//referencat gjenden ne readme 
