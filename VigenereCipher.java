public class VigenereCipher {
	static String VigenereEncrypt(String plaintext, String keyWord) {

		String encryptedWord = "";
		int counter = 0;
              if(Word(keyWord) == true) {
		for (int i = 0; i < plaintext.length(); i++) {
			char keyToUse = ' ';

			if ((plaintext.charAt(i) >= 'A' || plaintext.charAt(i) >= 'a')
					&& (plaintext.charAt(i) <= 'Z' || plaintext.charAt(i) <= 'z') || (plaintext.charAt(i) == ' '))

			{
				if (plaintext.charAt(i) != ' ') {
					if (plaintext.charAt(i) >= 'A' && plaintext.charAt(i) <= 'Z')

					{

						keyWord = keyWord.toUpperCase();
						keyToUse = keyWord.charAt((i - counter) % keyWord.length());

						if ((plaintext.charAt(i) + (keyToUse - 'A')) <= 'Z')
						{
							encryptedWord += (char) (plaintext.charAt(i) + (keyToUse - 'A'));
						}
						else
						{
							encryptedWord += (char) ((plaintext.charAt(i) + keyToUse) - 'A' - 26);
						}
					}

					else if (plaintext.charAt(i) >= 'a' && plaintext.charAt(i) <= 'z')

					{

						keyWord = keyWord.toLowerCase();
						keyToUse = keyWord.charAt((i - counter) % keyWord.length());

						if ((plaintext.charAt(i) + (keyToUse - 'a')) <= 'z')

						{
							encryptedWord += (char) (plaintext.charAt(i) + (keyToUse - 'a'));
						}
						else
						{
							encryptedWord += (char) ((plaintext.charAt(i) + (keyToUse)) - 'a' - 26);
						}
					}
				} else

				{
					encryptedWord += " ";
					counter++;
				}
			} else if ((plaintext.charAt(i) < 'A' || plaintext.charAt(i) < 'a')
					|| (plaintext.charAt(i) > 'Z' || plaintext.charAt(i) > 'z')) {
				encryptedWord += plaintext.charAt(i);
				counter++;
			}
		}
		return encryptedWord;
}
else
{
return("nuk mund te behej pasi qelesi permban karaktere te ndaluara");
}
	}

	static String VigenereDecrypt(String encryptedWord, String keyWord) {

		String decryptedWord = "";
		int counter = 0;
            if(Word(keyWord) == true) {
		for (int i = 0; i < encryptedWord.length(); i++) {
			char keyToUse = ' ';

			if ((encryptedWord.charAt(i) >= 'A' || encryptedWord.charAt(i) >= 'a')
					&& (encryptedWord.charAt(i) <= 'Z' || encryptedWord.charAt(i) <= 'z')
					|| (encryptedWord.charAt(i) == ' '))

			{
				if (encryptedWord.charAt(i) > ' ') {
					if (encryptedWord.charAt(i) >= 'A' && encryptedWord.charAt(i) <= 'Z')

					{

						keyWord = keyWord.toUpperCase();
						keyToUse = keyWord.charAt((i - counter) % keyWord.length());

						if ((encryptedWord.charAt(i) - (keyToUse - 'A')) >= 'A')

						{
							decryptedWord += (char) (encryptedWord.charAt(i) - (keyToUse - 'A'));

						}
						else

						{
							decryptedWord += (char) ('Z' + 1 + encryptedWord.charAt(i) - keyToUse);
						}

					}

					else if (encryptedWord.charAt(i) >= 'a' && encryptedWord.charAt(i) <= 'z')

					{

						keyWord = keyWord.toLowerCase();
						keyToUse = keyWord.charAt((i - counter) % keyWord.length());

						if ((encryptedWord.charAt(i) - (keyToUse - 'a')) >= 'a')

						{
							decryptedWord += (char) (encryptedWord.charAt(i) - (keyToUse - 'a'));
						}
						else
						{
							decryptedWord += (char) ('z' + 1 + encryptedWord.charAt(i) - keyToUse);

						}
					}
				} else

				{
					decryptedWord += " ";
					counter++;
				}
			} else if ((encryptedWord.charAt(i) < 'A' || encryptedWord.charAt(i) < 'a')
					|| (encryptedWord.charAt(i) > 'Z' || encryptedWord.charAt(i) > 'z')) {
				decryptedWord += encryptedWord.charAt(i);
				counter++;
			}
		}
		return decryptedWord;
}
else
{
return("nuk mund te behej pasi qelesi permban karaktere te ndaluara");
}
	}

	public static boolean Word(String Key) {
		int n = 0;
		for (int i = 0; i < Key.length(); i++) {
			if ((Key.charAt(i) >= 'a' && Key.charAt(i) <= 'z') || (Key.charAt(i) >= 'A' && Key.charAt(i) <= 'Z')) {
				continue;
			} else
				n++;
		}
		if (n > 0) {
			return false;
		} else
			return true;
	}

}
