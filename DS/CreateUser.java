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
}
