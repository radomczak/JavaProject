package Arguments;

import java.io.File;
import java.io.IOException;
/**
 * Klasa waliduj�ca parametry
 * @author Rados�aw Popielarski
 * @author Jacek Tr�bicki
 */
public class ArgValidator {
    private String sourcePath;
    private String destinationPath;
    private int numberOfThreads;

    /**
     * Konstruktor waliduj�cy parametry
     * Nie s� zwracane �adne dane. W przypadku nipoprawnje walidacji s� rzucane wyj�tki
     * @param arguments - parametry podane podczas uruchamiania aplikacji
     * @throws Exception - rzucone wyj�tki z informacj� o b��dzie
     */
    public ArgValidator(String[] arguments) throws Exception {
    	// Sprawdzanie, czy zosta�y podane atrybuty, je�li nie zostanie rzucony wyj�tek
        if(arguments.length == 0) throw new Exception("No arguments");
        // Przypisanie parametr�w do zmiennych
        String source = arguments[0];
        String destionation = arguments[1];
        String nThreads = arguments[2];
        int i;
        // Sprawdzanie ka�dego parametru. Je�li kt�ry� jest niepoprawny zostanie rzucoony wyj�tek
        if(!(new File(source).exists()))
            throw new IOException("Invalid source path");
        if(!(new File(destionation).exists()))
            throw new IOException("Invalid destination path");
        try{
            i = Integer.parseInt(nThreads);
        }catch (Exception ex) {
            throw new Exception("Invalid number of threads");
        }
        
        // Po poprawnej walidacji zmienne przypisywane s� do argument�w
        this.sourcePath = source;
        this.destinationPath = destionation;
        this.numberOfThreads = i;
    }
    /**
     * Zwr�� �cie�k� sk�d bed� kopiowane pliki
     * @return
     */
    public String getSourcePath() {
        return sourcePath;
    }
    
    /**
     * Zwr�� �cie�k� dok�d b�d� kopiowane pliki
     * @return
     */
    public String getDestinationPath() {
        return destinationPath;
    }

    /**
     * Zwr�� liczb� przydzielonych w�tk�w
     * @return
     */
    public int getNumberOfThreads() {
        return numberOfThreads;
    }
}
