package Arguments;

import java.io.File;
import java.io.IOException;
/**
 * Klasa waliduj¹ca parametry
 * @author Rados³aw Popielarski
 * @author Jacek Trêbicki
 */
public class ArgValidator {
    private String sourcePath;
    private String destinationPath;
    private int numberOfThreads;

    /**
     * Konstruktor waliduj¹cy parametry
     * Nie s¹ zwracane ¿adne dane. W przypadku nipoprawnje walidacji s¹ rzucane wyj¹tki
     * @param arguments - parametry podane podczas uruchamiania aplikacji
     * @throws Exception - rzucone wyj¹tki z informacj¹ o b³êdzie
     */
    public ArgValidator(String[] arguments) throws Exception {
    	// Sprawdzanie, czy zosta³y podane atrybuty, jeœli nie zostanie rzucony wyj¹tek
        if(arguments.length == 0) throw new Exception("No arguments");
        // Przypisanie parametrów do zmiennych
        String source = arguments[0];
        String destionation = arguments[1];
        String nThreads = arguments[2];
        int i;
        // Sprawdzanie ka¿dego parametru. Jeœli któryœ jest niepoprawny zostanie rzucoony wyj¹tek
        if(!(new File(source).exists()))
            throw new IOException("Invalid source path");
        if(!(new File(destionation).exists()))
            throw new IOException("Invalid destination path");
        try{
            i = Integer.parseInt(nThreads);
        }catch (Exception ex) {
            throw new Exception("Invalid number of threads");
        }
        
        // Po poprawnej walidacji zmienne przypisywane s¹ do argumentów
        this.sourcePath = source;
        this.destinationPath = destionation;
        this.numberOfThreads = i;
    }
    /**
     * Zwróæ œcie¿kê sk¹d bed¹ kopiowane pliki
     * @return
     */
    public String getSourcePath() {
        return sourcePath;
    }
    
    /**
     * Zwróæ œcie¿kê dok¹d bêd¹ kopiowane pliki
     * @return
     */
    public String getDestinationPath() {
        return destinationPath;
    }

    /**
     * Zwróæ liczbê przydzielonych w¹tków
     * @return
     */
    public int getNumberOfThreads() {
        return numberOfThreads;
    }
}
