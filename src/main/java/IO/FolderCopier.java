package IO;

import Arguments.ArgValidator;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FolderCopier {

    public static Set<File> destinationFilesDone = new HashSet<>();
    private Map<File, Integer> folderStructure = null;
    private FolderMapper mapper = new FolderMapper();
    private String sourcePath;
    private String destinationPath;
    private int numberOfThreads;

    public FolderCopier(ArgValidator argValidator) {
        this.sourcePath = argValidator.getSourcePath();
        this.destinationPath = argValidator.getDestinationPath();
        this.numberOfThreads = argValidator.getNumberOfThreads();
    }

    public void copyFolder() {
        try {
            folderStructure = mapper.getFolderStructure(sourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set<File> required;
        ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);
        for(File f : folderStructure.keySet()) {
            required = checkAndGetRequired(folderStructure.get(f));
            String filePath = f.getAbsolutePath().replace(sourcePath,destinationPath);
            es.execute(new CopyThread(required,f,filePath));
        }
        es.shutdown();
    }

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
