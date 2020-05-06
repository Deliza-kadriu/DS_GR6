import org.w3c.dom.Document;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;
import org.xml.sax.SAXException;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.spec.DESedeKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class WriteMessage {

public static void writemessage(String emri, String mesazhi, String fajlli) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, KeyStoreException, InvalidKeySpecException, ParserConfigurationException, SAXException, InvalidAlgorithmParameterException {
	File filePub = new File("keys/", emri + ".pub.xml");  

    if (!filePub.exists()){
        System.out.println("Gabim: Celesi publik "+emri+ " nuk ekziston.\n");
    }else {
        
       String emrii = encode64(emri);

	SecureRandom sr = new SecureRandom();
        byte[] ivD = new byte[8]; 
        sr.nextBytes(ivD);
   
        String iv = encode64(Arrays.toString(ivD));
        String Dess = encode64(DESenkriptimi(mesazhi));
        String Rsaa = encode64(RSAenkriptimi(emri));
        String ciphertext = emrii+"."+iv+"."+Rsaa+"."+Dess;
        PrintWriter writer = new PrintWriter(fajlli, StandardCharsets.UTF_8);
        writer.println(ciphertext);
        writer.close();
        System.out.println("Mesazhi i enkriptuar u ruajt ne fajllin " + fajlli + " .");

    }
    }

public static void writemessagee(String emri, String mesazhi) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IOException, ParserConfigurationException, SAXException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
File filePub = new File("keys/", emri + ".pub.xml");
if (!filePub.exists()){
        System.out.println("Gabim: Celesi publik "+emri+ " nuk ekziston.\n");
    }else {
    String emrii= encode64(emri);
	
    SecureRandom sr = new SecureRandom();
    byte[] ivD = new byte[8]; 
    sr.nextBytes(ivD);
   
    String iv = encode64(Arrays.toString(ivD));
    String Dess=encode64(DESenkriptimi(mesazhi));
    String Rsaa = encode64(RSAenkriptimi(emri));
    String ciphertext = emrii+"."+iv+"."+Rsaa+"."+Dess;
    System.out.println(ciphertext);
    }
}
public static String encode64(String str){
    return Base64.getEncoder().encodeToString(str.getBytes());
}
public static String DESenkriptimi(String mesazhi) throws InvalidKeySpecException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
    Random keyy = new Random();
    byte[] keys = new byte[8];
    keyy.nextBytes(keys);
    KeySpec myKeySpec = new DESKeySpec(keys);
    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

    SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);

    Cipher cipher = Cipher.getInstance("DES");
    cipher.init(Cipher.ENCRYPT_MODE, celesi);
    byte[] utf8 = mesazhi.getBytes("UTF8");
    byte[] enc = cipher.doFinal(utf8);
    enc = Base64.getEncoder().encode(enc);
    String encryptedWord = Base64.getEncoder().encodeToString(enc);

    return encryptedWord;
}
public static String RSAenkriptimi(String emri) throws InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, IOException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, SAXException, ParserConfigurationException {

File filePub = new File("keys/", emri + ".pub.xml");

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document doc = documentBuilder.parse(filePub);
    doc.getDocumentElement().normalize();
	
    String moduluss = document.getElementsByTagName("Modulus").item(0).getTextContent();
    String exponentt = document.getElementsByTagName("Exponent").item(0).getTextContent();
	
    BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduluss));
    BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentt));

    Random keyy = new Random();
    byte[] keys = new byte[8];
    keyy.nextBytes(keys);
    KeySpec myKeySpec = new DESKeySpec(keys);
    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
    SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);
	
    
    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey pubKey = keyFactory.generatePublic(keySpec);


 	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] encryptedKEY = cipher.doFinal(celesi.getEncoded());
	String encryptedKEYY= Base64.getEncoder().encodeToString(encryptedKEY);
	return encryptedKEYY;
}
}
