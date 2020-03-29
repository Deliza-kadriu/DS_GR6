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
Nenkomanda e dekriptimit perdore te njejtin qeles qe eshte perdorur per enkriptim dhe permbane te njejtat kushte, vetem pershtatja e formules 
per dekriptim dallon pasi ketu kemi zbritje e jo mbledhje.
Referencat: Per gjenerimin e qelesit: www.geeksforgeeks.org , ndihmese ne kod: www.dreamincode.net
