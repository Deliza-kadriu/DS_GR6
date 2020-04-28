public class CaesarCipher {
	public static void encrypt(String plaintext, int Key) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < plaintext.length(); i++) {
			if (Character.isLetter(plaintext.charAt(i))) {
				if (Character.isUpperCase(plaintext.charAt(i))) {
					char ch = (char) ('A' + ((int) plaintext.charAt(i) - 'A' + Key) % 26);
					result.append(ch);
				} else if (plaintext.charAt(i) == ' ') {
					char ch = ' ';
					result.append(ch);
				} else {
					char ch = (char) ('a' + ((int) plaintext.charAt(i) - 'a' + Key) % 26);
					result.append(ch);
				}
			} else {
				result.append(plaintext.charAt(i));
			}
		}

		System.out.println("Fjala pas enkriptimit eshte : " + result);
	}
	public static void decrypt(String decryptText, int Key) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < decryptText.length(); i++) {
			if (Character.isLetter(decryptText.charAt(i))) {
				if (Character.isUpperCase(decryptText.charAt(i))) {
					char ch = (char) ('A' + ((int) decryptText.charAt(i) - 'A' - Key + 26) % 26);
					result.append(ch);
				} else if (decryptText.charAt(i) == ' ') {
					char ch = ' ';
					result.append(ch);
				} else {
					char ch = (char) ('a' + ((int) decryptText.charAt(i) - 'a' - Key + 26) % 26);
					result.append(ch);
				}
			} else {
				result.append(decryptText.charAt(i));
			}
		}
		System.out.println("Fjala pas dekriptimit eshte : " + result);

	}

	public static void bruteForce(String decryptText) {
		StringBuffer result = new StringBuffer();
		for (int j = 1; j < 26; j++) {
			for (int i = 0; i < decryptText.length(); i++) {
				if (Character.isLetter(decryptText.charAt(i))) {
					if (Character.isUpperCase(decryptText.charAt(i))) {
						char ch = (char) ('A' + (((int) decryptText.charAt(i) - 'A' - j + 26) % 26));
						result.append(ch);
					} else if (decryptText.charAt(i) == ' ') {
						char ch = ' ';
						result.append(ch);
					} else {
						char ch = (char) ('a' + (((int) decryptText.charAt(i) - 'a' - j + 26) % 26));
						result.append(ch);
					}
				} else {
					result.append(decryptText.charAt(i));
				}
			}

			System.out.println("Shift key  +" + j + " " + result);
			result.delete(0, result.length());
		}

	}
}
