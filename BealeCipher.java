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

	    public static void main(String[] args) 
	    { 
	     	String path = JOptionPane.showInputDialog("Provide a path.");
	     	String encryptWord = JOptionPane.showInputDialog("Provide a word you want to encrypt.");
	     	System.out.println(path);
	     	System.out.println(encryptWord);
	     	encrypt(path, encryptWord);
	     	 
	     	
	    }
}
