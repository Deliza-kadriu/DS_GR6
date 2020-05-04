import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.math.BigInteger;
import java.util.Base64;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CreateUser {

    public static void generateKey(String name) throws Exception {
        try {
	    //Gjenerimi i RSA celesave 
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
			
            KeyPair keyP = kpg.generateKeyPair();
			
            PublicKey publicK = keyP.getPublic();
            PrivateKey privateK = keyP.getPrivate();
	    
            PrivateKeyXML(privateK, name);
            PublicKeyXML(publicK, name);
            } 
      catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown : " + e);
            }
    }
	public static void PrivateKeyXML(PrivateKey privateKey, String name) throws Exception {
        try {
            File filePriv = new File("keys/", name + ".xml");

	    if (filePriv.exists()){
		    System.out.print("Gabim: Celesi '" + name + "' ekziston paraprakisht!");
	    }		
	    else if(!filePriv.exists()){
                KeyFactory keyF = KeyFactory.getInstance("RSA");
                RSAPrivateCrtKeySpec keyS = keyF.getKeySpec(privateKey, RSAPrivateCrtKeySpec.class);

                //Marrja dhe kthimi i elementeve te RSA celesit nga BigInteger ne Base64
                BigInteger mod = keyS.getModulus();
                String modstr = new String(Base64.getEncoder().encodeToString(mod.toByteArray()));

                BigInteger exp = keyS.getPublicExponent();
                String expstr = new String(Base64.getEncoder().encodeToString(exp.toByteArray()));

                BigInteger prP = keyS.getPrimeP();
                String prPstr = new String(Base64.getEncoder().encodeToString(prP.toByteArray()));

                BigInteger prQ = keyS.getPrimeQ();
                String prQstr = new String(Base64.getEncoder().encodeToString(prQ.toByteArray()));

                BigInteger DPp = keyS.getPrimeExponentP();
                String DPpstr = new String(Base64.getEncoder().encodeToString(DPp.toByteArray()));

                BigInteger DQq = keyS.getPrimeExponentQ();
                String DQqstr = new String(Base64.getEncoder().encodeToString(DQq.toByteArray()));

                BigInteger inversii = keyS.getCrtCoefficient();
                String inversiistr = new String(Base64.getEncoder().encodeToString(inversii.toByteArray()));
                
                BigInteger de = keyS.getPrivateExponent();
                String destr = new String(Base64.getEncoder().encodeToString(de.toByteArray()));
		 
		//Krijimi i dokumentit dhe ruajtja e elementeve te RSA celesit ne te
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

                Document document = documentBuilder.newDocument();
                Element root = document.createElement("RSAKeyValue");
                document.appendChild(root);
		    
                Element moduli = document.createElement("Modulus");
                moduli.appendChild(document.createTextNode(modstr));
                root.appendChild(moduli);
		    
                Element exponenti = document.createElement("Exponent");
                exponenti.appendChild(document.createTextNode(expstr));
                root.appendChild(exponenti);
		    
                Element p = document.createElement("P");
                p.appendChild(document.createTextNode(prPstr));
                root.appendChild(p);
		    
                Element q = document.createElement("Q");
                q.appendChild(document.createTextNode(prQstr));
                root.appendChild(q);
		    
                Element dp = document.createElement("DP");
                dp.appendChild(document.createTextNode(DPpstr));
                root.appendChild(dp);
		    
                Element dq = document.createElement("DQ");
                dq.appendChild(document.createTextNode(DQqstr));
                root.appendChild(dq);
		    
                Element inversi = document.createElement("InverseQ");
                inversi.appendChild(document.createTextNode(inversiistr));
                root.appendChild(inversi);
		    
                Element d = document.createElement("D");
                d.appendChild(document.createTextNode(destr));
                root.appendChild(d);

	        //Transformimi ne xml file 
						
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource domSource = new DOMSource(document);
                StreamResult streamResult = new StreamResult(filePriv);
                transformer.transform(domSource, streamResult);

                System.out.println("Eshte krijuar celesi privat 'keys/" + name + ".xml'");
            } 
        
        } catch (NoSuchAlgorithmException e) {

            System.out.println("Exception thrown : " + e);
        }
}
	public static void PublicKeyXML(PublicKey publicKey, String name) throws Exception {

        try {
        File filePub = new File("keys/", name + ".pub.xml");

            if (filePub.exists()){
            	System.out.print("");
            	}
		
	       else if(!filePub.exists()){

                KeyFactory keyF = KeyFactory.getInstance("RSA");
                RSAPublicKeySpec keyS = keyF.getKeySpec(publicKey, RSAPublicKeySpec.class);

		//Marrja dhe kthimi i elementeve te RSA celesit nga BigInteger ne Base64
                BigInteger mod = keyS.getModulus();
                String modstr = new String(Base64.getEncoder().encodeToString(mod.toByteArray()));

                BigInteger exp = keyS.getPublicExponent();
                String expstr = new String(Base64.getEncoder().encodeToString(exp.toByteArray()));

                //Krijimi i dokumentit dhe ruajtja e elementeve te RSA celesit ne te
                DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
                Document document = documentBuilder.newDocument();

                Element roott = document.createElement("RSAKeyValue");
                document.appendChild(roott);
                
                Element moduluss = document.createElement("Modulus");
                moduluss.appendChild(document.createTextNode(modstr));
                roott.appendChild(moduluss);
                
                Element exponentt = document.createElement("Exponent");
                exponentt.appendChild(document.createTextNode(expstr));
                roott.appendChild(exponentt);

                //Transformimi ne xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                DOMSource domSource = new DOMSource(document);

                StreamResult streamResult = new StreamResult(filePub);
                transformer.transform(domSource, streamResult);
                System.out.println("Eshte krijuar celesi publik 'keys/" + name + ".xml'");
            } 
        
        } 
        catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown : " + e);
        }
}
}
// Referencat ne README.md
