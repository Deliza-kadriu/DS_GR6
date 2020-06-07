import java.io.Console;
import java.util.Scanner;

public class ds {
	public static void main(String[] args) throws Exception {
		Scanner input=new Scanner(System.in);
		Console console = System.console();
		if (args[0].equals("Caesar")) {
			try {
				if (args[1].equals("encrypt")) {
					CaesarCipher.encrypt(args[3], Integer.parseInt(args[2]));
				} else if (args[1].equals("decrypt")) {
					CaesarCipher.decrypt(args[3], Integer.parseInt(args[2]));
				} else if (args[1].equals("bruteforce")) {
					System.out.println("Dekriptimet me BruteForce:");
					CaesarCipher.bruteForce(args[2]);
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
			 char [] passwordi = console.readPassword("Jepni fjalekalimin: ");
               		 String password = String.valueOf(passwordi);
               		  if (password.matches("^(?=.*[a-zA-Z])(?=.*\\d).{6,}$")) {
                    	  char [] perserit = console.readPassword("Perserit fjalekalimin: ");
                    	  String perseritja = String.valueOf(perserit);
                    	   if (password.equals(perseritja)) {
                           CreateUser.generateKey(args[1], password);
                         } else
                           System.out.println("Gabim: Fjalekalimet nuk perputhen");
                       } else{
                        System.out.println("Gabim: Fjalekalimi duhet te permbaje se paku nje numer ose simbol.");
                     }
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
					DsDatabase.fshijNgaDB(args[1]);
                                }
                                 else {
                			System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
                                  }

			} catch (Exception e) {

				System.out.println("Nuk keni dhene argumente valide.");
				System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
			}
		}
		else if (args[0].equals("export-key")) {
			try {
				if (args.length==3){
					ExportKey.export_pub_priv1(args[2],args[1]);
			    }
			    	else if (args.length==4){
					ExportKey.export_pub_priv(args[2],args[1],args[3]);
			    }
			    	else{
					System.out.println("Argumentet nuk jane ne rregull");
					System.exit(1);
			    }                 

			} catch (Exception e) {

				System.out.println("Nuk keni dhene argumente valide.");
				System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
				}
			}
		else if(args[0].equalsIgnoreCase("import-key")){
			try {
			    if (args.length == 3 ) {
				    ImportKey.import_Key(args[1], args[2]);
			    }
			    else {
				System.out.println("Argumentet nuk jane ne rregull");
				System.exit(1);
			    }
				}catch(Exception e) {
					System.out.println("Nuk keni dhene argumente valide.");

			    }
			}
		
		else if (args[0].equals("write-message")) {
			try {
				if (args.length == 4) {
	                if (args[3].length() > 20) {
	                    WriteMessage.write_messageSender(args[1], args[2], args[3]);
					} else
	                    WriteMessage.writemessage(args[1], args[2], args[3]);
	                    }
			else if(args.length==3){
				WriteMessage.writemessagee(args[1], args[2]);
			}
                        else {
			System.out.println("Parametrat jane dhene gabim.");
			}
			} catch (Exception e) {

				System.out.println("Nuk keni dhene argumente valide.");
				System.out.println("Argumenti i dyte mund te permbaje vetem karakteret: shkronja, numra ose underline");
				System.out.print(e);
			}
			}
		
		else if (args[0].equalsIgnoreCase("read-message")) {
			try{
	            if (args.length == 2) {
	                ReadMessage.read(args[1]);
	           
	            } else {
	                System.out.println("Argumentet nuk jane ne rregull");
	                System.exit(1);
	            }
			}
			catch (Exception e){
			System.out.println("Nuk keni dhene argumente valide.");
			}
	        }
		else if (args[0].equalsIgnoreCase("login")) {
            if (args.length != 2) {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);
            } else {

                char[] passwordi = console.readPassword("Jepni fjalekalimin: ");
                String password = String.valueOf(passwordi);
                Login.verifiko(args[1], password);
                //Login.login(args[1]);
            }
        } else if (args[0].equalsIgnoreCase("status")) {
            if (args.length != 2) {
                System.out.println("Argumentet nuk jane ne rregull");
                System.exit(1);
            } else {
                Login.statusi(args[1]);
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

			System.out.println("Keni gabime ne sintakse");
			System.exit(1);
		}
	}
}
