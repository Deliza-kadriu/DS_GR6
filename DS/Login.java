import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import java.sql.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Login {

    public static void login(String name) throws Exception {

        System.out.println("Token: " + sign(name));
        boolean True = verifikosigniture(sign(name));
        if (True) {
            System.out.println("Ne rregull");
        }
        File tokens = new File("Tokens/" + name + ".txt");
        FileOutputStream fileOutputStream = new FileOutputStream(new File(String.valueOf(tokens)));
        byte[] bytes = sign(name).getBytes();
        fileOutputStream.write(bytes);

    }
    public static String sign(String name) throws Exception {

        File file = new File("keys/" + name + ".xml");

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();

        String moduluss = doc.getElementsByTagName("Modulus").item(0).getTextContent();
        String D = doc.getElementsByTagName("D").item(0).getTextContent();
        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduluss));
        BigInteger d = new BigInteger(Base64.getDecoder().decode(D));


        RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        
        
        
        long currentTimeMillis = System.currentTimeMillis() + 12*10^5;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy  HH:mm");
        Date resultdate = new Date(currentTimeMillis);
        String expiration_date = sdf.format(resultdate);
        String algoritmi = "{'alg':'HS256'," +
            "'typ':'JWT'" +
            "}";
        String text = "{'name':'" + name + "'," +
            "'expiration_date':'" + expiration_date + "'}";
        String algoritmi_b64 = Base64.getEncoder().encodeToString(algoritmi.getBytes());
        String text_b64 = Base64.getEncoder().encodeToString(text.getBytes());




        Signature privateSignature = Signature.getInstance("SHA1withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update((text).getBytes(UTF_8));

        byte[] signature = privateSignature.sign();

        String signi = Base64.getEncoder().encodeToString(signature);
        String komplet = algoritmi_b64 + "." + text_b64 + "." + signi;
        return komplet;


    }

    public static boolean verifikosigniture(String signature) throws Exception {
        String[] stringss = signature.split("\\.");
        ArrayList < String > aListNumberss = new ArrayList < String > (Arrays.asList(stringss));
        byte[] tedhenat = Base64.getDecoder().decode(aListNumberss.get(1));
        String data = new String(tedhenat);
        String nenshkrimi = signature.split("\\.")[2];

        String name = data.split("'")[3];



        File file = new File("keys/" + name + ".pub.xml");

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();

        String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponentAsString = doc.getElementsByTagName("Exponent").item(0).getTextContent();

        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduliS));
        BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentAsString));

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);


        Signature publicSignature = Signature.getInstance("SHA1withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update((data).getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(nenshkrimi);

        return publicSignature.verify(signatureBytes);
    }
    
    public static boolean verifiko(String e_dhena, String emri, String nenshkrimi) throws Exception {

    	File filePub = new File("keys/", emri + ".pub.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(filePub);
        doc.getDocumentElement().normalize();
    	
        String moduluss = doc.getElementsByTagName("Modulus").item(0).getTextContent();

        BigInteger modulus = new BigInteger(Base64.getDecoder().decode(moduluss));
        String exponentt = doc.getElementsByTagName("Exponent").item(0).getTextContent();
    	
        BigInteger exponent = new BigInteger(Base64.getDecoder().decode(exponentt));

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);


        Signature signaturePub = Signature.getInstance("SHA1withRSA");
        signaturePub.initVerify(publicKey);
        signaturePub.update((e_dhena).getBytes(UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(nenshkrimi);

        return signaturePub.verify(signatureBytes);

    }

    public static void statusi(String signature) throws Exception {
        boolean nenshkrim_valid = verifikosigniture(signature);
        if (nenshkrim_valid) {
            String[] stringss = signature.split("\\.");
            ArrayList < String > aListNumberss = new ArrayList < String > (Arrays.asList(stringss));
            byte[] tedhenat = Base64.getDecoder().decode(aListNumberss.get(1));
            String data = new String(tedhenat);
            String name = data.split("'")[3];
            String dataSkadimit = data.split("'")[7];
            long currentTimeMillis = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date resultdate = new Date(currentTimeMillis);
            String string = sdf.format(resultdate);


            int compareTO = string.compareTo(dataSkadimit);
            if (compareTO < 0) {
                gjeje(name);
                System.out.println("Valid: Po");
                System.out.println("Skadimi: " + dataSkadimit);

            } else System.out.println("Jo Valid");



        } else System.out.println("Jo Valid");
    }
    public static void gjeje(String username) throws Exception {


        try {
	        Connection MyConn = null;
			MyConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/databazasd","root","");
            String checkusername = "Select * from shfrytesuesit where emri='"+username+"'";
            Statement statement = MyConn.createStatement();
            ResultSet resultSet1 = statement.executeQuery(checkusername);
            if (resultSet1.next() == false) {
                System.out.println("Useri nuk ekziston");
            } else {

                System.out.println("User: "+username);
              }
            }


         catch (SQLException err) {
            System.out.println(err.getMessage());
        }

    }
    public static void verifiko(String emri,String pass) throws Exception {

        Connection MyConn = null;
		MyConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/databazasd","root","");
    	String query="Select Salti from shfrytesuesit where emri='"+ emri +"'";
    	Statement s1 = MyConn.createStatement();
        ResultSet rs1=s1.executeQuery(query);
        rs1.next();
        byte[] salt = new byte[8];
    	salt = rs1.getBytes(1);
        String pwHash=DsDatabase.get_SHA_256_SecurePassword(pass,salt);
        
        try {
            String verifiko="Select * from shfrytesuesit where emri='"+emri+"' and Passwordi='"+pwHash+"'";
            Statement s = MyConn.createStatement();
            ResultSet rs=s.executeQuery(verifiko);
            if (rs.next()==false){
                System.out.println("Gabim: Shfrytezuesi ose fjalekalimi i gabuar.");
                
            }
            else
            {
                Login.login(emri);
               // System.out.println("a");
            }
        }
        catch (SQLException err){
            System.out.println(err.getMessage());
        }
       
    }
    


}
