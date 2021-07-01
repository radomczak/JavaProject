package IO;

import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * Klasa mapuj�ca struktur� kopiowanego katalogu
 * @author Rados�aw Popielarski
 * @author Jacek Tr�bicki
 */
public class FolderMapper {
	// Deklaracja atrybut�w
    private Map<File,Integer> folderStructure = null;

    /**
     * Funkcja odwzorowuj�ca budow� katalogu
     * @param directoryPath - �cie�ka mapowanego katalogu
     * @throws IOException
     */
    private void mapFolder(String directoryPath) throws IOException {
    	// Tworzenie pliku ze �cie�ki
        File file = new File(directoryPath);

        // Mapowanie budowy folderu
        folderStructure = new LinkedHashMap<>();
        // Zagnie�d�enie zaczyna si� od -1, bo to poziom wskazanego w parametrze folderu do kopiowania
        mapPathOfFile(file,-1);
        // Usuni�cie wskazanego folderu w parametrze do kopiowania
        folderStructure.remove(file);
    }

    /**
     * Funckja rekurencyjna badaj�ca zagnie�d�enie we wskazanym pliku
     * @param file - badany plik
     * @param count - poziom zagnie�d�enia pliku w folderze
     */
    private void mapPathOfFile(File file, int count) {
    	// Dodanie pliku i jej poziomy do listy
        folderStructure.put(file,count);

        // Je�li plik jest folderem wykonywana jest rekurencja badaj�ca jego zagnie�d�enie
        if(file.isDirectory()){
            String[] filesInDirectory = file.list();
            for(String fileName: filesInDirectory){
                mapPathOfFile(new File(file, fileName),count+1);
            }
        }
    }

    /**
     * Funkcja zwracaj�ca map� (struktur� folderu)
     * @param directoryPath - �cie�ka katalogu do zwr�cenia
     * @return - struktura folderu w postaci mapy przechowuj�cej plik i poziom jego zagnie�d�enia
     * @throws IOException
     */
    public Map<File, Integer> getFolderStructure(String directoryPath) throws IOException {
        mapFolder(directoryPath);
        sortFolderStructure();
        return folderStructure;
    }

    /**
     * Funckja sortuj�ca pliki po poziomie zgnie�d�enia
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
