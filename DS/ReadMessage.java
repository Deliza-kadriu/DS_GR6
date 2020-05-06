
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class ReadMessage {
	public static void read(String message) throws IOException, NoSuchAlgorithmException, InvalidKeyException, ParserConfigurationException, NoSuchPaddingException, BadPaddingException, InvalidKeySpecException, IllegalBlockSizeException, InvalidAlgorithmParameterException, SAXException {
        if (message.endsWith(".txt"))
            dmetxt(message);
         else
            dpatxt(message);

}
	public static void dmetxt(String text) throws ParserConfigurationException, SAXException, IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException {
		
    
    File filePath= new File(text);
		if(filePath.exists()) {
			try {
			 StringBuilder contentBuilder = new StringBuilder();
	            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

	                String currentLine;
	                while ((currentLine = br.readLine()) != null) {
	                    contentBuilder.append(currentLine).append("\n");
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            String content = contentBuilder.toString();
	            String[] stringss = content.split("\\.");
	            String emri = decode64(stringss[0]);
	            String decryptedKey = decode64(rsaDec(emri, stringss[2]));
	            String iv = decode64(stringss[1]);
	            byte [] ivDes = iv.getBytes();
	            byte[] key = decryptedKey.getBytes();
	            String plain = decode64(stringss[3]);
                byte[] byteArray = Base64.getDecoder().decode(plain.getBytes());

	            String decodeemri = new String(emri);
                System.out.println("Marresi: " + decodeemri);
                SecretKey originalKey = new SecretKeySpec(key, 0, key.length, "DES");
                Cipher aesCipher = Cipher.getInstance("DES/CBC/PKCS5PADDING");
                aesCipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(ivDes));


                byte[] bytePlainText = aesCipher.doFinal(byteArray);
                String plainText = new String(bytePlainText);
                System.out.println("Mesazhi"+plainText);
                }
                
                catch(Exception e) {
                	System.out.println("error");
                }

		
	}}
	private static String decode64(String str) {
		
		byte[] decodedBytes = Base64.getDecoder().decode(str);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
	private static String rsaDec(String emri, String ky) throws ParserConfigurationException, SAXException, IOException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException {
		File file = new File("keys\\"+emri+".xml");
		if(file.exists()) {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            Base64.Decoder decoder = Base64.getDecoder();
            String iModulus =doc.getElementsByTagName("Modulus").item(0).getTextContent();
            byte[] modBytes = decoder.decode(iModulus.trim());
            BigInteger modules = new BigInteger(1, modBytes);
            String iExponentBytes =doc.getElementsByTagName("Exponent").item(0).getTextContent();
            byte [] exp = decoder.decode(iExponentBytes.trim());
            BigInteger iExponentBytes1 = new BigInteger(1,exp );
            String iPBytes=doc.getElementsByTagName("P").item(0).getTextContent();
            byte [] p = decoder.decode(iPBytes.trim());
            BigInteger iPBytes1 = new BigInteger(1,p );
            String iQBytes =doc.getElementsByTagName("Q").item(0).getTextContent();
            byte [] q = decoder.decode(iQBytes.trim());
            BigInteger iQBytes1= new BigInteger(1,q );
            String iDPBytes =doc.getElementsByTagName("DP").item(0).getTextContent();
            byte [] dp = decoder.decode(iDPBytes.trim());
            BigInteger iDPBytes1= new BigInteger(1,dp );
            String iDQBytes=doc.getElementsByTagName("DQ").item(0).getTextContent();
            byte [] dq = decoder.decode(iDQBytes.trim());
            BigInteger iDQBytes1= new BigInteger(1,dq );
            String iInverseQBytes=doc.getElementsByTagName("InverseQ").item(0).getTextContent();
            byte [] ib = decoder.decode(iInverseQBytes.trim());
            BigInteger iInverseQBytes1= new BigInteger(1,ib );
            String iDBytes=doc.getElementsByTagName("D").item(0).getTextContent();
            byte []  d= decoder.decode(iDBytes.trim());
            BigInteger iDBytes1= new BigInteger(1,d );
            

            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPrivateKeySpec privSpec = new RSAPrivateCrtKeySpec(modules,iExponentBytes1,iPBytes1,iQBytes1,iDPBytes1,iDQBytes1,iInverseQBytes1,iDBytes1);
            PrivateKey privKey = factory.generatePrivate(privSpec);


            final Cipher rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            rsa.init(Cipher.DECRYPT_MODE, privKey);
            rsa.update(ky.getBytes());
            return new String(rsa.doFinal());
		}
		return null;
	}
	public static void dpatxt(String message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, ParserConfigurationException, SAXException, IOException, InvalidAlgorithmParameterException {
		 try {
		String[] strings = message.split("\\.");
		 String emri = decode64(strings[0]);
		    String decryptedKey = decode64(rsaDec(emri, strings[2]));
		    String iv = decode64(strings[1]);
		    byte [] ivDes = iv.getBytes();
		    byte[] key = decryptedKey.getBytes();
		    String plain = decode64(strings[3]);
            byte[] byteArray = Base64.getDecoder().decode(plain.getBytes());
		    System.out.println("Marresi"+emri);

		    SecretKey originalKey = new SecretKeySpec(key, "DES");
	
		    Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		    desCipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(ivDes));
		    byte[] bytePlainText = desCipher.doFinal(byteArray);
		    String plainText = new String(bytePlainText);
		    System.out.println("Mesazhi: "+plainText);}
		    catch(Exception e) {
		    	System.out.println("error");
		    	System.out.println(e);
		    }
		  
	}
	
}
