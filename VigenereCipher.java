	static String VigenereEncrypt(String plaintext, String keyWord){
	    
	    String encryptedWord="";
	    .

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
