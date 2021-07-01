package App;

import Arguments.ArgValidator;
import IO.FolderCopier;
/**
 * Klasa inicjuj�ca dzia�anie aplikacji
 * @author Rados�aw Popielarski
 * @author Jacek Tr�bicki
 */
public class App {
    public static void main(String[] args) {
    	// Wys�anie parametr�w do walidacji
        ArgValidator argValidator = null;
        try {
            argValidator = new ArgValidator(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Dane po poprawnej walidacje s� wysy�ane do kopiowania
        FolderCopier copier = new FolderCopier(argValidator);
        copier.copyFolder();
    }
}
