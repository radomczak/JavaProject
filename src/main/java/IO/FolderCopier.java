package IO;

import Arguments.ArgValidator;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Klasa kopiuj�ca pliki
 * @author Rados�aw Popielarski
 * @author Jacek Tr�bicki
 */
public class FolderCopier {

	// Deklaracja atrybut�w
    public static Set<File> destinationFilesDone = new HashSet<>();
    private Map<File, Integer> folderStructure = null;
    // Deklaracja mapy (struktury) kopiowanych plik�w
    private FolderMapper mapper = new FolderMapper();
    private String sourcePath;
    private String destinationPath;
    private int numberOfThreads;

    /**
     * Kontruktor przypisuj�cy warto�ci do atrybut�w
     * @param argValidator - parametry po walidacji
     */
    public FolderCopier(ArgValidator argValidator) {
        this.sourcePath = argValidator.getSourcePath();
        this.destinationPath = argValidator.getDestinationPath();
        this.numberOfThreads = argValidator.getNumberOfThreads();
    }

    /**
     * Funkcja kopiuj�ca. Nie zwraca �adnej warto�ci
     */
    public void copyFolder() {
        try {
            folderStructure = mapper.getFolderStructure(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Pliki wymagane do wcze�niejszego skopiowania
        Set<File> required;
        ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);
        for(File f : folderStructure.keySet()) {
            required = checkAndGetRequired(folderStructure.get(f));
            String filePath = f.getAbsolutePath().replace(sourcePath,destinationPath);
            es.execute(new CopyThread(required,f,filePath));
        }
        es.shutdown();
        try {
            es.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funkcja sprawdzaj�ca i zwracaj�ca wymagane pliki do wcze�niejszego skopiowania
     * @param i - poziom zagnie�dzenia
     * @return - HashSet przechowuj�cy wymagane pliki do wcze�niejszego skopiowania
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
