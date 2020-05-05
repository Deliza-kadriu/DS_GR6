
public class DSgr6 {
	public static void main(String[] args) {

		if (args[0].equals("Caesar")) {
			try {
				if (args[1].equals("encrypt")) {
					CaesarCipher.encrypt(args[3], Integer.parseInt(args[2]));
				} else if (args[1].equals("decrypt")) {
					CaesarCipher.decrypt(args[3], Integer.parseInt(args[2]));
				} else if (args[1].equals("bruteforce")) {
					System.out.println("Dekriptimet me BruteForce:");
					CaesarCipher.bruteForce(args[3]);
				} else {
					System.out.println("Shkrimi nuk eshte i sakte te metoda e Caesaar metoda eshte gabim duhet te jete si ne vazhdim  :");
					System.out.println("Pozita1:Kodi, Pozita2:Metoda, Pozita3:Celesi\\path, Pozita4:Teksti");
				}
			} catch (Exception e) {

				System.out.println(
						"Nuk keni dhene argumente te mjaftueshme per te realizuar ndonje kod. Shkrimi i sakte i argumenteve duhet te jete si ne vijim:");
				System.out.println("Pozita1:Kodi, Pozita2:Metoda, Pozita3:Celesi\\path, Pozita4:Teksti");
			}
		} 
		else if (args[0].equals("create-user")) {
			try {
				if (args[1].matches("[A-Za-z0-9_]+")) {
                                        CreateUser.generateKey(args[1]);
                                }
                                 else {
                System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
                                  }

			} catch (Exception e) {

				System.out.println("Nuk keni dhene argumente valide.");
				System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
			}
		}
		else if (args[0].equals("delete-user")) {
			try {
				if (args[1].matches("[A-Za-z0-9_]+")) {
                                        DeleteUser.fshij(args[1]);
                                }
                                 else {
                System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
                                  }

			} catch (Exception e) {

				System.out.println("Nuk keni dhene argumente valide.");
				System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
			}
		}
		else if (args[0].equals("Vigenere")) {
			try {
				if (args[1].equals("decrypt")) {
					System.out.println(
							"Dekriptimi sipas Kodit Vigenere: " + VigenereCipher.VigenereDecrypt(args[3], (args[2])));
				} else if (args[1].equals("encrypt")) {
					System.out.println(
							"Encriptimi sipas Kodit Vigenere:" + VigenereCipher.VigenereEncrypt(args[3], (args[2])));
				}

				else {
					System.out.println("Shkrimi nuk eshte i sakte te metoda e Vigenere metoda eshte gabim duhet te jete si ne vazhdim  : ");
					System.out.println("Pozita1:Kodi, Pozita2:Metoda, Pozita3:Celesi\\path, Pozita4:Teksti");
				}
			} catch (Exception e) {

				System.out.println(
						"Nuk keni dhene argumente te mjaftueshme per te realizuar ndonje kod. Shkrimi i sakte i argumenteve duhet te jete si ne vijim:");
				System.out.println("Pozita1:Kodi, Pozita2:Metoda, Pozita3:Celesi\\path, Pozita4:Teksti");
			}
		} else if (args[0].equals("Beale")) {
			try {
				if (args[1].equals("encrypt")) {
					System.out.println("Enkriptimi sipas Kodit Beale: ");
					BealeCipher.encrypt(args[2], args[3]);
				}
				else if (args[1].equals("decrypt")) {
					System.out.println("Dekriptimi sipas Kodit Beale: ");
					BealeCipher.decrypt(args[2], args[3]);
					
				}

				else {
					System.out.println("Shkrimi nuk eshte i sakte te metoda e Beale metoda eshte gabim duhet te jete si ne vazhdim  : ");
					System.out.println("Pozita1:Kodi, Pozita2:Metoda, Pozita3:Celesi\\path, Pozita4:Teksti");
				}
			} catch (Exception e) {

				System.out.println(
						"Nuk keni dhene argumente te mjaftueshme per te realizuar ndonje kod. Shkrimi i sakte i argumenteve duhet te jete si ne vijim:");
				System.out.println("Pozita1:Kodi, Pozita2:Metoda, Pozita3:Celesi\\path, Pozita4:Teksti");
			}

		}

		else {

			System.out.println("Keni nje gabim ne sintakse. Shkrimi adekuat i argumenteve eshte : ");
			System.out.println("Kodet: Caesar, Vigenere,Beale");
			System.out.println("Metodat : encrypt, decrypt, bruteforce");
			System.out.println(
					"Celesi/path: Te metoda e caesarit eshte numer , te vigenere eshte fjale kurse te beale eshte path");
			System.out.println("Teksti: Tekst i cfaredoeshem tek metoda e bealit duhet te jete nje varg i numave i ndare me presje p.sh: 14,10,10,54");
			System.exit(1);
		}
	}
}
