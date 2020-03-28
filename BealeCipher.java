import javax.swing.JOptionPane;
import java.io.*;
import java.nio.file.*; 

public class BealeCipher {
	 public static void encrypt(String path,String encryptWord) {
		 try {
    	      File file = new File(path);
    	      if (!file.exists())
    	        throw new FileNotFoundException();
    	    
    	      
    	        String s1 = new String(Files.readAllBytes(Paths.get(path)));
    	        String s =  s1.toLowerCase();
    	       for(int i=0;i<encryptWord.length();i++) {
	     	    	int count=0;
	     	    	int t = 0,n = 0;
	     	    	
	     	    	for(String st : s.split("\\s+")){
	     	    		
	     	    			n++;
	     	    			count++;
	     	    			String ch=Character.toString(encryptWord.charAt(i)).replaceAll("\\d+"," ");
	     	    		
	     	    	    if(st.startsWith(ch.toLowerCase())){
	     	    	    	
	     	    	        break;
	     	    	    }
	     	    	    else {
	     	    	    	t++;
	     	    	    }
	     	    	    }	     	    	
    	        	if(t < n) {
    		    	System.out.print(count+" ");
    	    	}
    	        	}
    	
    	     
    	    } catch (Exception e) {
    	      System.err.println(e.getMessage());
    	    } 

	 }
	 public static void decrypt(String path,String decryptWord) {
			 try {
	    	      File file = new File(path);
	    	      if (!file.exists())
	    	        throw new FileNotFoundException();
	    	    
	    	      
	    	        String s1 = new String(Files.readAllBytes(Paths.get(path)));
	    	        String s =  s1.toLowerCase();
	    	    	   int count=0;
	    	    		for (String st1 :s.split(" ")) {
	    	    			count++;
	    	    		}
	    	    		for(String st : decryptWord.split("\\s+")) {
	    	    			Boolean bool = isNumeric(st);
	    	    			int n;
	    	    			if(bool) {
	    	    		 n = Integer.parseInt(st);}
	    	    			else {
	    	    				n=-1;
	    	    			}
	    	    		if(n<count && n>0 && bool) {
	    	    		char ch = (s.split("\\s+")[n-1]).charAt(0);
	    	    		System.out.print(ch);
	    	    		}
	    	    		else {
	    	    			continue;
	    	    		}
	    	    		}
	    	
	    	     
	    	    } catch (Exception e) {
	    	      System.err.println(e.getMessage());
	    	    } 

		 
	 }

	 //Classen isNumeric e kam marr te gatshme nga faqja : https://www.baeldung.com/java-check-string-number
		public static boolean isNumeric(String strNum) {
		    if (strNum == null) {
		        return false;
		    }
		    try {
		        double d = Double.parseDouble(strNum);
		    } catch (NumberFormatException nfe) {
		        return false;
		    }
		    return true;
		}
	    public static void main(String[] args) 
	    { 	     	
	     	    String[] options = new String[] {"1", "2"};
	     	    int response = JOptionPane.showOptionDialog(null, "Choose 1 for encryption and 2 for decryption","",
	     	        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	     	        null, options, options[0]);
	     	   String path = JOptionPane.showInputDialog("Provide a path.");
	     	  	     	
	     	if(response == 0) {
	    	String encryptWord = JOptionPane.showInputDialog("Provide a word you want to encrypt.");
		     	
	     	System.out.println(path);
	     	System.out.println(encryptWord);
	     	encrypt(path, encryptWord);
	     	} 
	    	if(response == 1) {
		    	String decryptWord = JOptionPane.showInputDialog("Provide a word you want to decrypt.");
			     	
		     	System.out.println(path);
		     	System.out.println(decryptWord);
		     	decrypt(path, decryptWord);
		     	} 
	     	
	     	
	    }
}