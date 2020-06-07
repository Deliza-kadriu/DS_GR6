
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
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
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

				  KeySpec myKeySpec = new DESKeySpec(key);
                    SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                    SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);

	            String decodeemri = new String(emri);
                System.out.println("Marresi: " + decodeemri);
				
               	Cipher decryptCipher = Cipher.getInstance("DES");
                    decryptCipher.init(Cipher.DECRYPT_MODE, celesi);
                    //   byte[] utf8 = Base64.getDecoder().decode(aListNumbers.get(3));
                    byte[] dec = decryptCipher.doFinal(byteArray);
                    String decryptedWord = new String(dec);
                    System.out.println("Mesazhi:" + decryptedWord);

                }
                
                catch(Exception e) {
                	System.out.println("error");
                	System.out.print(e);
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
            doc.getDocumentElement().normalize();
            String iModulus =doc.getElementsByTagName("Modulus").item(0).getTextContent();
            
            BigInteger modules = new BigInteger(decode64(iModulus).getBytes());
            String iDBytes=doc.getElementsByTagName("D").item(0).getTextContent();
            
            BigInteger iDBytes1= new BigInteger(decode64(iDBytes).getBytes());
            

            KeyFactory factory = KeyFactory.getInstance("RSA");
            RSAPrivateKeySpec privSpec = new RSAPrivateKeySpec(modules,iDBytes1);
            PrivateKey privKey = factory.generatePrivate(privSpec);



			
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privKey);
            byte[] key = cipher.doFinal(decode64(ky).getBytes());
            return new String(key);
            
		}
		return null;
	}
	public static void dpatxt(String message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, ParserConfigurationException, SAXException, IOException, InvalidAlgorithmParameterException {
		 try {
			String[] strings = message.split("\\.");
			if (strings.length==4){
		 	String emri = decode64(strings[0]);
		 	System.out.print(emri);
		    String decryptedKey = decode64(rsaDec(emri, strings[2]));
		    String iv = decode64(strings[1]);
		    byte [] ivDes = iv.getBytes();
		    System.out.print(iv); 
		    System.out.print(ivDes); 
		    System.out.println(decryptedKey);
		 
		    byte[] key = decryptedKey.getBytes();
		    
		    String plain = decode64(strings[3]);
            byte[] byteArray = Base64.getDecoder().decode(plain.getBytes());
		    System.out.println("Marresi"+emri);

		    SecretKey originalKey = new SecretKeySpec(key, "DES");
	
		    Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		    desCipher.init(Cipher.DECRYPT_MODE, originalKey, new IvParameterSpec(ivDes));
		    byte[] bytePlainText = desCipher.doFinal(byteArray);
		    String plainText = new String(bytePlainText);
		    System.out.println("Mesazhi: "+plainText);
		 }
			else{
                try {

                    byte[] emri = Base64.getDecoder().decode(strings[0]);
                    String ivi = strings[1];
                    String encrypti = strings[3];
                    byte [] sender=Base64.getDecoder().decode(strings[4]);

                    String senderName=new String(sender);

                    byte[] decodedBytesKey = Base64.getDecoder().decode(strings[2]);



                    String decodeemri = new String(emri);
                    System.out.println("Marresi: " + decodeemri);


                    File file = new File("keys/", decodeemri + ".xml");
                    if (file.exists()) {
                        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                        Document doc = docBuilder.parse(file);
                        doc.getDocumentElement().normalize();

                        String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
                        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
                        String D = doc.getElementsByTagName("D").item(0).getTextContent();
                        BigInteger d = new BigInteger(Base64.getDecoder().decode(D));

                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
                        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);


                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
                        byte[] key = cipher.doFinal(decodedBytesKey);

                        KeySpec myKeySpec = new DESKeySpec(key);
                        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
                        SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec);


                        String a = strings[3];
                        byte[] byteArray = Base64.getDecoder().decode(a.getBytes());


                        Cipher decryptCipher = Cipher.getInstance("DES");
                        decryptCipher.init(Cipher.DECRYPT_MODE, celesi);
                        byte[] dec = decryptCipher.doFinal(byteArray);
                        String decryptedWord = new String(dec);
                        System.out.println("Mesazhi:" + decryptedWord);
                        System.out.println("Derguesi: "+senderName);
                        String nenshkrimi=strings[5];
                        File file2=new File("keys/",senderName+".pub.xml");
                        if (file2.exists())
                        {
                          boolean verifikimi=Login.verifiko(encrypti,senderName,nenshkrimi);
                           if (verifikimi==true)
                            System.out.println("Nenshkrimi: valid");
                            else System.out.println("Nenshkrimi: jovalid");
                        }
                        else System.out.println("Mungon celesi publik '"+senderName+"'");

                    } else {
                        System.out.println("Gabim: Celesi privat '" + file + "' nuk ekziston.");
                    }

                } catch (Exception e) {

                }
            }	
		 }
		 
		    catch(Exception e) {
		    	System.out.println("error");
		    	System.out.println(e);
		    }
		  
	}
	
}
