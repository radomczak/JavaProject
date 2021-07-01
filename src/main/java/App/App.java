package App;

import Arguments.ArgValidator;
import IO.FolderCopier;
/**
 * Klasa inicjuj¹ca dzia³anie aplikacji
 * @author Rados³aw Popielarski
 * @author Jacek Trêbicki
 */
public class App {
    public static void main(String[] args) {
    	// Wys³anie parametrów do walidacji
        ArgValidator argValidator = null;
        try {
            argValidator = new ArgValidator(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Dane po poprawnej walidacje s¹ wysy³ane do kopiowania
        FolderCopier copier = new FolderCopier(argValidator);
        copier.copyFolder();
    }
}
