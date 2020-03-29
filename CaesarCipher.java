
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

		System.out.println("Fjala pas kriptimit eshte : " + result);
	}

