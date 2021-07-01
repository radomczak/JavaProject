package IO;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;
/**
 * Klasa obs�uguj�ca w�tki
 * @author Rados�aw Popielarski
 * @author Jacek Tr�bicki
 */
public class CopyThread implements Runnable {
	// Deklaracja atrybut�w
    Set<File> required = null;
    File file = null;
    String destination = null;

    /**
     * Kostruktor przypisuj�cy warto�ci do atrybut�w 
     * @param required - pliki wymagane do skopiowania
     * @param file - plik do skopiowania
     * @param destination - miejsce docelowe dla pliku
     */
    public CopyThread(Set<File> required, File file,String destination) {
        this.required = required;
        this.file = file;
        this.destination = destination;
    }

    
    /**
     * Metoda obs�uguj�ca prac� w�tk�w
     * @Override
     */
    public void run() {
    	// Czeka dop�ki s� inne (wymagane) pliki do skopiowania
        while (!FolderCopier.destinationFilesDone.containsAll(required)) {}
        try {
        	// Je�li plik jest folderem tworzony jest nowy folder we wskazanej lokalizacji. W przeciwnym razie kopiowany jest plik do wskazanej lokazlizacji
            if(file.isDirectory()) {
                Files.createDirectory(new File(destination).toPath());
            } else {
               Files.copy(file.toPath(),new File(destination).toPath());
            }
            // Do obiektu FolderCopier dodawana jest informacja o skopiowanym pliku/folderze
            FolderCopier.destinationFilesDone.add(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
