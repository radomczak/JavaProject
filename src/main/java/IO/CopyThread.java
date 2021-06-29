package IO;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;
/**
 * Klasa obs³uguj¹ca w¹tki
 * @author Rados³aw Popielarski
 * @author Jacek Trêbicki
 */
public class CopyThread implements Runnable {
	// Deklaracja atrybutów
    Set<File> required = null;
    File file = null;
    String destination = null;

    /**
     * Kostruktor przypisuj¹cy wartoœci do atrybutów 
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
     * Metoda obs³uguj¹ca pracê w¹tków
     * @Override
     */
    public void run() {
    	// Czeka dopóki s¹ inne (wymagane) pliki do skopiowania
        while (!FolderCopier.destinationFilesDone.containsAll(required)) {}
        try {
        	// Jeœli plik jest folderem tworzony jest nowy folder we wskazanej lokalizacji. W przeciwnym razie kopiowany jest plik do wskazanej lokazlizacji
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
