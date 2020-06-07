import org.w3c.dom.Document;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;

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
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public static void writemessage(String emrii, String mesazhi, String fajlli) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, KeyStoreException, InvalidKeySpecException, ParserConfigurationException, SAXException, InvalidAlgorithmParameterException {
	File filePub = new File("keys/", emrii + ".pub.xml");  

    if (!filePub.exists()){
        System.out.println("Gabim: Celesi publik "+emrii+ " nuk ekziston.\n");
    }else {
        
       String emri = encode64(emrii);

	SecureRandom sr = new SecureRandom();
        byte[] ivD = new byte[8]; 
        sr.nextBytes(ivD);
   
        String iv = encode64(Arrays.toString(ivD));
        String Dess=DESenkriptimi(mesazhi);
        String[] parts = Dess.split("-");
        String Rsaa = RSAenkriptimi(emrii,parts[1].replaceAll("-", "").getBytes());
        String ciphertext = emri+"."+iv+"."+Rsaa+"."+parts[0].replaceAll("-", "");
        PrintWriter writer = new PrintWriter(fajlli, StandardCharsets.UTF_8);
        writer.println(ciphertext);
        writer.close();
        System.out.println("Mesazhi i enkriptuar u ruajt ne fajllin " + fajlli + " .");

    }
    }
public static void write_messageSender(String emrii, String message, String sender) throws Exception {
    File file = new File("keys/" + emrii + ".pub.xml"); 
    if (file.exists()) {
        byte[] bytes = emrii.getBytes("UTF-8");
        String emri = Base64.getEncoder().encodeToString(bytes);

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();
        SecureRandom sr = new SecureRandom();

        byte[] iv = new byte[8]; 
        sr.nextBytes(iv);

        String ivi = Base64.getEncoder().encodeToString(iv);

        Random keyi = new Random();
        byte[] keys = new byte[8];
        keyi.nextBytes(keys);
        KeySpec myKeySpec = new DESKeySpec(keys);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

        SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


        String moduliS =  doc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponentAsString =  doc.getElementsByTagName("Exponent").item(0).getTextContent();

        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
        BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentAsString));

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] encryptedKe = cipher.doFinal(celesi.getEncoded());
        String encryptedKey = Base64.getEncoder().encodeToString(encryptedKe);
        Cipher encryptedcipher = Cipher.getInstance("DES");
        encryptedcipher.init(Cipher.ENCRYPT_MODE, celesi);
        byte[] utf8 = message.getBytes("UTF8");
        byte[] enc = encryptedcipher.doFinal(utf8);
        enc = Base64.getEncoder().encode(enc);
        String encryptedWord = new String(enc);
        System.out.println(enc);

        String[] stringss = sender.split("\\.");
        byte[] tedhenat = Base64.getDecoder().decode(stringss[1]);
        String data = new String(tedhenat);
        String nenshkrimi = sender.split("\\.")[2];

        String name = data.split("'")[3];

        String dataSkadimit = data.split("'")[7];
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date resultdate = new Date(currentTimeMillis);
        String string = sdf.format(resultdate);
        String name_b64 = Base64.getEncoder().encodeToString(name.getBytes());

        File file1 = new File("keys/" + name + ".xml");

        DocumentBuilderFactory docFactory1 = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder1 = docFactory1.newDocumentBuilder();
        Document doc1 = docBuilder1.parse(file1);
        doc1.getDocumentElement().normalize();

        String moduliSp = doc1.getElementsByTagName("Modulus").item(0).getTextContent();
        String D = doc1.getElementsByTagName("D").item(0).getTextContent();

        BigInteger modulusp = new BigInteger(Base64.getDecoder().decode(moduliSp));
        BigInteger d = new BigInteger(Base64.getDecoder().decode(D));


        RSAPrivateKeySpec keySpec1 = new RSAPrivateKeySpec(modulusp, d);
        KeyFactory keyFactory1 = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory1.generatePrivate(keySpec1);

        Signature privateSignature = Signature.getInstance("SHA1withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update((encryptedWord).getBytes("UTF_8"));

        byte[] signature = privateSignature.sign();

        String signi = Base64.getEncoder().encodeToString(signature);




        if (Login.verifikosigniture(sender)) {


            int compareTO = string.compareTo(dataSkadimit);
            if (compareTO < 0) {
                boolean has = Login.gjeje(name);
                if (has == true) {
                    System.out.println(emri + "." + ivi + "." + encryptedKey + "." + encryptedWord + "." + name_b64 + "." + signi);
                } else
                    System.out.println("Tokeni nuk eshte valid");
            } else
                System.out.println("Tokeni nuk eshte valid");

        } else {
            System.out.println("Tokeni nuk eshte valid");
        }




    } else {
        System.out.println("Gabim:Qelesi publik '" + emrii + "' nuk ekziston");
    }
}

public static void writemessagee(String emrii, String mesazhi) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, IOException, ParserConfigurationException, SAXException, InvalidAlgorithmParameterException, UnsupportedEncodingException {
File filePub = new File("keys/", emrii + ".pub.xml");
if (!filePub.exists()){
        System.out.println("Gabim: Celesi publik "+emrii+ " nuk ekziston.\n");
    }else {
    byte[] bytes = emrii.getBytes("UTF-8");
    String emri = Base64.getEncoder().encodeToString(bytes);

    SecureRandom sr = new SecureRandom();
    byte[] ivD = new byte[8]; 
    sr.nextBytes(ivD);
   
    String ivi = Base64.getEncoder().encodeToString(ivD);
	String Dess=DESenkriptimi(mesazhi);
    String[] parts = Dess.split("-");
    String Rsaa = RSAenkriptimi(emrii,parts[1].replaceAll("-", "").getBytes());
    String ciphertext = emri+"."+ivi+"."+Rsaa+"."+parts[0].replaceAll("-", "");
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

    Cipher encryptedcipher = Cipher.getInstance("DES");
            encryptedcipher.init(Cipher.ENCRYPT_MODE, celesi);
            byte[] utf8 = mesazhi.getBytes("UTF8");
            byte[] enc = encryptedcipher.doFinal(utf8);
            enc = Base64.getEncoder().encode(enc);
            String encryptedWord = new String(enc);

    return encryptedWord+"-"+keys;
}
public static String RSAenkriptimi(String emri,byte[] keys) throws InvalidKeySpecException, UnsupportedEncodingException, InvalidKeyException, IOException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, SAXException, ParserConfigurationException {

File filePub = new File("keys/", emri + ".pub.xml");

    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document doc = documentBuilder.parse(filePub);
    doc.getDocumentElement().normalize();
	
    String moduluss = ((org.w3c.dom.Document) doc).getElementsByTagName("Modulus").item(0).getTextContent();
    String exponentt = ((org.w3c.dom.Document) doc).getElementsByTagName("Exponent").item(0).getTextContent();
	
    BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduluss));
    BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentt));

    
    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey pubKey = keyFactory.generatePublic(keySpec);
    
	KeySpec myKeySpec = new DESKeySpec(keys);
    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");

    SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


 	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encryptedKe = cipher.doFinal(celesi.getEncoded());
            String encryptedKey = Base64.getEncoder().encodeToString(encryptedKe);

			return encryptedKey;
}
}
