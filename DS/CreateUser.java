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
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
			
            KeyPair keyP = kpg.generateKeyPair();
			
            PublicKey publicK = keyP.getPublic();
            PrivateKey privateK = keyP.getPrivate();
            } 
      catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown : " + e);
            }
    }
	public static void PrivateKeyXML(PrivateKey privateKey, String name) throws Exception {
        try {
            File file = new File("c://RSAKeys");
	    file.mkdir();
	    File filePriv = new File(file.getPath()+"//"+ name +".xml");

	    if (filePriv.exists()){
		    System.out.print("Gabim: Celesi " + name + " ekziston paraprakisht!");
	    }		
	    else if(!filePriv.exists()){
                KeyFactory keyF = KeyFactory.getInstance("RSA");
                RSAPrivateCrtKeySpec keyS = keyF.getKeySpec(privateKey, RSAPrivateCrtKeySpec.class);

                //Kthimi nga BigInteger ne Base64
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
}
