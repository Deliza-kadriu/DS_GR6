import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.security.SecureRandom;

public class DsDatabase{
 		
		public static void shtoNeDB(String name, String pass) throws NoSuchAlgorithmException{
		      	String url="jdbc:mysql://localhost:3306/databazasd";
            		String namee="root";
          		String passwordd="";
		    try{
			Connection MyConn= DriverManager.getConnection(url, namee, passwordd);
			String query="Select * from shfrytesuesit where emri='"+ name +"'";
             		Statement s = MyConn.createStatement();
 			ResultSet rs=s.executeQuery(query);
               		if (rs.next()==false){
                 	 byte[] salt = getSalt();
               		 String passHash = get_SHA_256_SecurePassword(pass, salt);
               		 String sql="Insert into shfrytesuesit (emri, passwordi, salti)"+ "VALUES ('"+ name +"','"+ passHash +"','"+ salt +"')";
             	   	 PreparedStatement stmt = MyConn.prepareStatement(sql);
               		 stmt.executeUpdate(sql);
               		 System.out.println("Eshte krijuar shfrytezuesi " + name);
               	        }
                        else
                         System.out.print("");
		     }
		catch (SQLException err){
              	    System.out.println(err.getMessage());
		}
		}
	
		public static void fshijNgaDB(String name){
           	 String url="jdbc:mysql://localhost:3306/databazasd";
           	 String namee="root";
           	 String passwordd="";
		try {
                Connection MyConn= DriverManager.getConnection(url, namee, passwordd);
                String delete = "Delete from shfrytesuesit where emri='" + name + "'";
                PreparedStatement stmt = MyConn.prepareStatement(delete);
                stmt.executeUpdate(delete);
            }
            catch (SQLException err){
                System.out.println(err.getMessage());
            }
            }

	    public static byte[] getSalt() throws NoSuchAlgorithmException{
       		 SecureRandom sr = new SecureRandom();
       		 byte[] salt = new byte[16];
       		 sr.nextBytes(salt);
       		 return salt;
   		}

   	public static String get_SHA_256_SecurePassword(String passwordToHash, byte[] salt){
     	String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.reset();
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }
        
}
