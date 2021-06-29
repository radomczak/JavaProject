package IO;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FolderMapper {

    private Map<File,Integer> folderStructure = null;

    private void mapFolder(String directoryPath) throws IOException {
        File file = new File(directoryPath);

        folderStructure = new LinkedHashMap<>();
        mapPathOfFile(file,-1);
        folderStructure.remove(file);
    }

    private void mapPathOfFile(File file, int count) {
        folderStructure.put(file,count);

        if(file.isDirectory()){
            String[] filesInDirectory = file.list();
            for(String fileName: filesInDirectory){
                mapPathOfFile(new File(file, fileName),count+1);
            }
        }
    }

    public void printFolderStructue() throws IOException {
        if(folderStructure == null) throw new IOException("Folder is not mapped yet");
        for(File file: folderStructure.keySet()){
            System.out.println(file.getAbsolutePath() + " - " + folderStructure.get(file));
        }
    }

    public Map<File, Integer> getFolderStructure(String directoryPath) throws IOException {
        mapFolder(directoryPath);
        sortFolderStructure();
        return folderStructure;
    }

    private void sortFolderStructure() {
        Map<File,Integer> sortedMap = new LinkedHashMap<>();
        int maxPriority = 0;

        for(int i : folderStructure.values()) {
            if(i > maxPriority) maxPriority=i;
        }
        for(int priority = 0; priority <= maxPriority; priority++) {
            for (File f : folderStructure.keySet()) {
                if (folderStructure.get(f) == priority) {
                    sortedMap.put(f, priority);
                }
            }
        }
        folderStructure = sortedMap;
    }
}
