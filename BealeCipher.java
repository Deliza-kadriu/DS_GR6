
import java.io.*;
import java.nio.file.*;

public class BealeCipher {
	public static void encrypt(String path, String plaintext) {
		try {
			File file = new File(path);
			if (!file.exists())
				throw new FileNotFoundException();

			String s1 = new String(Files.readAllBytes(Paths.get(path)));
			String s = s1.toLowerCase();
			for (int i = 0; i < plaintext.length(); i++) {
				int count = 0;
				int t = 0, n = 0;

				for (String st : s.split("\\s+")) {

					count++;
					String ch = Character.toString(plaintext.charAt(i)).replaceAll("\\d+", " ");

					if (st.startsWith(ch.toLowerCase())) {

						break;
					} else {
						t++;
					}
				}
				if (t < count) {
					System.out.print(count + " ");
				}
			}

		} catch (Exception e) {
			System.out.println("Couldn't find the file.");
			System.exit(0);
		}

	}

	public static void decrypt(String path, String decryptWord) {
		try {
			File file = new File(path);
			if (!file.exists())
				throw new FileNotFoundException();

			String s1 = new String(Files.readAllBytes(Paths.get(path)));
			String s = s1.toLowerCase();
			int count = 0;
			for (String st1 : s.split(" ")) {
				count++;
			}
			for (String st : decryptWord.split(",")) {
				Boolean bool = isNumeric(st);
				int n;
				if (bool) {
					n = Integer.parseInt(st);
				} else {
					n = -1;
				}
				if (n < count && n > 0 && bool) {
					char ch = (s.split("\\s+")[n - 1]).charAt(0);
					System.out.print(ch);
				} else {
					continue;
				}
			}

		} catch (Exception e) {
			System.out.println("Couldn't find the letter.");
			System.exit(0);
		}

	}

	// metoden isNumeric e kam marr te gatshme nga faqja :
	// https://www.baeldung.com/java-check-string-number
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

}
