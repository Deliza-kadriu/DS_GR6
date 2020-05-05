import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Files;
public class DeleteUser {

    public static void fshij(String name) {
       File filePriv = new File("keys/",name  + ".xml");
       File filePub = new File("keys/",name + ".pub.xml");
		
         try {
                if (filePub.exists() && filePriv.exists()) {
                    filePriv.delete();
                    System.out.println("Eshte larguar celesi privat " + name + ".xml'"); 
                    filePub.delete();
                    System.out.println("Eshte larguar celesi publik " + name + ".pub.xml'");   
                }
                else if  (filePub.exists() && !filePriv.exists()){
                    filePub.delete();
                    System.out.println("Eshte larguar celesi publik " + name + ".pub.xml'");
                }
                else if  (!filePub.exists() && filePriv.exists()){
                    filePriv.delete();
                    System.out.println("Eshte larguar celesi privat " + name + ".xml'");
                }
                else {
                    System.out.println("Gabim: Celesi '"+ name +"' nuk ekziston");
                }
    } catch (Exception e) {

        System.out.println("Exception thrown");
    }
    }
}
