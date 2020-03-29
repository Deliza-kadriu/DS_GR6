public class VigenereCipher {

static String generateKey(String plaintext, String keyWord) 
{ 
    int x = plaintext.length(); 
  
    for (int i = 0; ; i++) 
    { 
        if (x == i) 
            i = 0; 
        if (keyWord.length() == plaintext.length()) 
            break; 
        keyWord+=(keyWord.charAt(i)); 
    } 
    return keyWord; 
} 
	static String VigenereEncrypt(String plaintext, String keyWord){
	    
	    String encryptedWord="";
		int c=0;
	    for(int i=0; i<plaintext.length(); i++){

	        if (( plaintext.charAt(i) >= 'A' || plaintext.charAt(i) >= 'a') && (plaintext.charAt(i) <= 'Z' || plaintext.charAt(i) <= 'z') || (plaintext.charAt(i) == ' '))
		            {
		                if (plaintext.charAt(i) != ' ')
		                {	                    
		                    if (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z')
		                    {
                             			keyWord = keyWord.toUpperCase();
		                        if ((plaintext.charAt(i) + (keyWord.charAt(i-c) - 'A')) <= 'Z')
		                        {
		                            encryptedWord += (char) (plaintext.charAt(i) + (keyWord.charAt(i-c) - 'A'));
		                        }
		                        else
		                        {
		                            encryptedWord += (char) ((plaintext.charAt(i) + keyWord.charAt(i-c) ) - 'A'-26);
		                        }
		                    }
		                    else if (plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z')                        
		                    {
                             			 keyWord = keyWord.toLowerCase();
		                        if ((plaintext.charAt(i) + (keyWord.charAt(i-c) - 'a')) <= 'z')
		                        {
		                            encryptedWord += (char) (plaintext.charAt(i) + (keyWord.charAt(i-c) - 'a'));
		                        }
		                        else
		                        {
		                            encryptedWord += (char) ((plaintext.charAt(i) + (keyWord.charAt(i-c) - 'a')) - 26);
		                        }
		                    }
		                }
		                else
		                {
		                    encryptedWord += " ";
		                    c++;
		                }
		            }
		            else if ((plaintext.charAt(i) < 'A' || plaintext.charAt(i) < 'a') || (plaintext.charAt(i) > 'Z' || plaintext.charAt(i) > 'z'))
		            {
		                encryptedWord += plaintext.charAt(i);
		                c++;
		            }
		        }
		        return encryptedWord;
		    }
	
	 static String VigenereDecrypt(String encryptedWord,String keyWord){
	    
	    String decryptedWord="";
	    int c = 0;

	    for(int i=0; i<encryptedWord.length(); i++){
	     	        
	        if (( encryptedWord.charAt(i) >= 'A' || encryptedWord.charAt(i) >= 'a') && (encryptedWord.charAt(i) <= 'Z' || encryptedWord.charAt(i) <= 'z') || (encryptedWord.charAt(i) == ' '))
		            {
		                if (encryptedWord.charAt(i) > ' ')
		                {
		                    if (encryptedWord.charAt(i) >= 'A' && encryptedWord.charAt(i) <= 'Z')
		                    {
                                keyWord = keyWord.toUpperCase();
		                        if ((encryptedWord.charAt(i) - (keyWord.charAt(i-c) - 'A')) >= 'A')
		                        {
		                            decryptedWord += (char)(encryptedWord.charAt(i) - (keyWord.charAt(i-c) - 'A'));
		                        }
		                        else
		                        {
		                            decryptedWord += (char)('Z' + 1 + encryptedWord.charAt(i) - keyWord.charAt(i-c) );
		                        }
		                    }
		                    else if (encryptedWord.charAt(i) >= 'a' && encryptedWord.charAt(i) <= 'z')
		                    {
                                keyWord = keyWord.toLowerCase();
		                        if ((encryptedWord.charAt(i) - (keyWord.charAt(i-c) - 'a')) >= 'a')
		                        {
		                            decryptedWord += (char) (encryptedWord.charAt(i) - (keyWord.charAt(i-c) - 'a'));
		                        }
		                        else
		                        {
		                            decryptedWord += (char) ('z' + 1 + encryptedWord.charAt(i) - keyWord.charAt(i-c) );
		                        }
		                    }
		                }
		                else
		                {
		                    decryptedWord += " ";
		                    c++;
		                }
		            }
		            else if ((encryptedWord.charAt(i) < 'A' || encryptedWord.charAt(i) < 'a') || (encryptedWord.charAt(i) > 'Z' || encryptedWord.charAt(i) > 'z'))
		            {
		                decryptedWord += encryptedWord.charAt(i);
		                c++;
		            }
		        }
	        return decryptedWord;
	}

	 public static boolean Word(String Key) {
		 int n=0;
		 for(int i = 0;i<Key.length();i++) {
		    	if((Key.charAt(i) >= 'a' && Key.charAt(i) <= 'z') || (Key.charAt(i) >= 'A' && Key.charAt(i) <= 'Z')) {
		    		continue;
		    	}
		    	else 
		    		n++;
		    }
		 if(n > 0) {
			 return false;
		 }
		 else 
			 return true;
	 }
}
