package IO;

import Arguments.ArgValidator;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Klasa kopiuj¹ca pliki
 * @author Rados³aw Popielarski
 * @author Jacek Trêbicki
 */
public class FolderCopier {

	// Deklaracja atrybutów
    public static Set<File> destinationFilesDone = new HashSet<>();
    private Map<File, Integer> folderStructure = null;
    // Deklaracja mapy (struktury) kopiowanych plików
    private FolderMapper mapper = new FolderMapper();
    private String sourcePath;
    private String destinationPath;
    private int numberOfThreads;

    /**
     * Kontruktor przypisuj¹cy wartoœci do atrybutów
     * @param argValidator - parametry po walidacji
     */
    public FolderCopier(ArgValidator argValidator) {
        this.sourcePath = argValidator.getSourcePath();
        this.destinationPath = argValidator.getDestinationPath();
        this.numberOfThreads = argValidator.getNumberOfThreads();
    }

    /**
     * Funkcja kopiuj¹ca. Nie zwraca ¿adnej wartoœci
     */
    public void copyFolder() {
        try {
            folderStructure = mapper.getFolderStructure(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Pliki wymagane do wczeœniejszego skopiowania
        Set<File> required;
        ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);
        for(File f : folderStructure.keySet()) {
            required = checkAndGetRequired(folderStructure.get(f));
            String filePath = f.getAbsolutePath().replace(sourcePath,destinationPath);
            es.execute(new CopyThread(required,f,filePath));
        }
        es.shutdown();
    }

    /**
     * Funkcja sprawdzaj¹ca i zwracaj¹ca wymagane pliki do wczeœniejszego skopiowania
     * @param i - poziom zagnie¿dzenia
     * @return - HashSet przechowuj¹cy wymagane pliki do wczeœniejszego skopiowania
     */
    private Set<File> checkAndGetRequired(int i) {
        Set<File> required = new HashSet<>();
        for(File f : folderStructure.keySet()) {
            if(folderStructure.get(f)<i) {
                required.add(f);
            }
        }
        return required;
    }
}
