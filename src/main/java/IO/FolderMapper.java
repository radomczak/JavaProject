package IO;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * Klasa mapuj¹ca strukturê kopiowanego katalogu
 * @author Rados³aw Popielarski
 * @author Jacek Trêbicki
 */
public class FolderMapper {
	// Deklaracja atrybutów
    private Map<File,Integer> folderStructure = null;

    /**
     * Funkcja odwzorowuj¹ca budowê katalogu
     * @param directoryPath - œcie¿ka mapowanego katalogu
     * @throws IOException
     */
    private void mapFolder(String directoryPath) throws IOException {
    	// Tworzenie pliku ze œcie¿ki
        File file = new File(directoryPath);

        // Mapowanie budowy folderu
        folderStructure = new LinkedHashMap<>();
        // Zagnie¿d¿enie zaczyna siê od -1, bo to poziom wskazanego w parametrze folderu do kopiowania
        mapPathOfFile(file,-1);
        // Usuniêcie wskazanego folderu w parametrze do kopiowania
        folderStructure.remove(file);
    }

    /**
     * Funckja rekurencyjna badaj¹ca zagnie¿d¿enie we wskazanym pliku
     * @param file - badany plik
     * @param count - poziom zagnie¿d¿enia pliku w folderze
     */
    private void mapPathOfFile(File file, int count) {
    	// Dodanie pliku i jej poziomy do listy
        folderStructure.put(file,count);

        // Jeœli plik jest folderem wykonywana jest rekurencja badaj¹ca jego zagnie¿d¿enie
        if(file.isDirectory()){
            String[] filesInDirectory = file.list();
            for(String fileName: filesInDirectory){
                mapPathOfFile(new File(file, fileName),count+1);
            }
        }
    }

    /**
     * Funkcja wyœwietlaj¹ca strukturê wskazanego w parametrze folderu (plik i jego poziom zagnie¿d¿enia)
     * @throws IOException
     */
    public void printFolderStructue() throws IOException {
        if(folderStructure == null) throw new IOException("Folder is not mapped yet");
        for(File file: folderStructure.keySet()){
            System.out.println(file.getAbsolutePath() + " - " + folderStructure.get(file));
        }
    }

    /**
     * Funkcja zwracaj¹ca mapê (strukturê folderu)
     * @param directoryPath - œcie¿ka katalogu do zwrócenia
     * @return - struktura folderu w postaci mapy przechowuj¹cej plik i poziom jego zagnie¿d¿enia
     * @throws IOException
     */
    public Map<File, Integer> getFolderStructure(String directoryPath) throws IOException {
        mapFolder(directoryPath);
        sortFolderStructure();
        return folderStructure;
    }

    /**
     * Funckja sortuj¹ca pliki po poziomie zgnie¿d¿enia
     */
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
