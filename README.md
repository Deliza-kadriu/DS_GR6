# DS_GR6

-Komanda Vigenere: Eshte metode e cila kryen enkriptimin duke pranuar dy parametra String, mesazhin qe deshirojme ta enkriptojme: 
String plaintext dhe qelesin e enkriptimit: String keyWord. Kjo komande kerkon qe qelesi te kete gjatesine e plaintext prandaj kemi perdorur
funksionin GenerateKey i cili ben perseritjen e shkronjave te qelesit te dhene derisa arrine gjatesine e barabarte me plaintext. 
Pastaj kemi krijuar nje forloop e cila kalon neper secilin karakter te plaintext-it dhe percakton distancen mes shkronjave perkatese dhe 
tregon se per sa do te levize shkronja per te arritur ne shkronjen e cila del si rezultat i enkriptimit, logjika per fitimin e karakterit 
te enkriptuar eshte bere duke gjetur distancen mes dy shkronjave: keyWord.chatAt(i-c)-'A' dhe pastaj kjo diference t'i shtohet 
plaintext.charAt(i) , kur rezultati eshte me i madh se 'Z' perdorim logjiken e modulos, hapat e njejte (me pershtatje) kalohen edhe kur 
mesazhi permbane shkronja te vogla.Forloop-i permban disa kushtezimeve IF te cilat kontrollojne nese plaintext-i i dhene permbane karaktere 
tjera perveq shkronjave te alfabetit, me kete rast kemi krijuar nje numerues int c=0  i cili rritet ne qofte se plaintext-i permbane 
hapesira ' ' , numer apo ndonje simbol/karakter tjeter jashte rangut te shkronjave me q'rast ato karaktere vetem i pershkruhen tekstit te 
enkriptuar ne dalje ne pozitat perkatese. Duhet cekur se kemi bere edhe funksionin i cili kontrollon nese qelesi permbane ndonje karakter 
jasht shkronjave me q'rast programi na tregon se kemi perdorur karaktere te ndaluara dhe nuk lejon qe te kaloj programi ne enkriptim fare. 
Nenkomanda e dekriptimit perdore te njejtin qeles qe eshte perdorur per enkriptim dhe permbane te njejtat kushte, vetem pershtatja e formules per dekriptim dallon pasi ketu kemi zbritje e jo mbledhje.
Referencat: Per gjenerimin e qelesit: www.geeksforgeeks.org 

-Komanda Beale :
encrypt(String path, String encryptWord):
Eshte metode e cila kryen enkriptimin duke perdorur dy parametra hyres te cilet jane shtegun se ku ndodhet libri qe deshirojm te enkriptojme prej tij: String path dhe fjalen te cilen deshirojme ta enkriptojme: String encryptWord. Si fillim e kemi shkruajtur kodin qe na lejon te lexojm path-in i cili nese nuk mundet me gjet dokumentin e kthen nje error Couldn't find the file.Dokumentin qe e lexon e kthen ne shkronja te vogla qe me qene me e sakte. Krijojme nje for loop ku i = 0 deri sa eshte gjatesia e encryptWord ku per secilen shkronje te encryptWord do te veproje si ne vijim. Do te krijohen dy numerues ( count, t ) te barabarte me zero.
Komanda : “for (String st : s.split("\\s+"))” bene ndarjen e fjaleve brenda dokumentit dhe per secilen fjale count do te rritet per nje.String ch eshte encryptWord.charAt(i) i konvertuar ne string dhe nese ky karakter eshte numer ateher ai do te injorohet ose do te 
kthehet si space.Kemi thene se nese st fillon me ch ateher te nderpritet kerkimi perndryshe te rritet t per nje. Komanda if (t < count) eshte komande e cila kthen karakteret te cilat e permbushin kushtin se ato ndodhen brenda librit. 
decrypt(String path, String decryptWord):
Ngjashem si metoda e enkriptimit eshte realizua edhe kjo e dekriptimit e cila e merr path dhe 
decryptWord. DecryptWord e ndajme ne pjes permes space ku secilen pjese e shikojm a jane numra
permes metodes boolean isNumeric nese nuk eshte numer ateher ate pjese nuk e deshifrojme dhe 
vazhdojm te deshifrojm pjeset e tjera ku merr karakterni e pare qe gjendet te fjala me ate 
numer.
Referencat: Metoden isNumeric https://www.baeldung.com/java-check-string-number

-Komanda Caesar :
encrypt(String plaintext, int Key):
Eshte metode e cila kryen enkriptimin duke perdorur dy parametra hyres te cilet jane 
plaintext-i dhe Key numri per te cilin zvendoset secila shkronje e plaintext-it. Kemi marr 
nje for loop e cila shkon neper secilen shkronje te plaintext-it dhe e shikon nese eshte shkonje
e madhe, e vogel ose space. Nese shkronja eshte e madhe perdoret formula :
'A' + ((int) plaintext.charAt(i) - 'A' + Key) % 26,
nese shkronja eshte e vogel perdoret formula :
'a' + ((int) plaintext.charAt(i) - 'a' + Key) % 26,
ndersa nese eshte space ateher vetem pershkruhet.
Ngjashem me enkriptimin krijohet edhe ajo e dekriptimit vetem se formules nuk i shtohet qelsi po
i heket ai gjithashtu i shtohet edhe numri 26 per shkak qe te del vlera pozitive.
Gjithashtu edhe bruteForce eshte e ngjashme me metoden enkript vetem ktu Key merr vlera prej 1 deri ne 25.
Referencat:https://www.w3schools.com/ https://www.geeksforgeeks.org/java/, Libri :Introduction to Java programming
