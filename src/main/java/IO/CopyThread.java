package IO;

import java.io.File;
import java.nio.file.Files;
import java.util.Set;

public class CopyThread implements Runnable {

    Set<File> required = null;
    File file = null;
    String destination = null;

    public CopyThread(Set<File> required, File file,String destination) {
        this.required = required;
        this.file = file;
        this.destination = destination;
    }

    @Override
    public void run() {
        while (!FolderCopier.destinationFilesDone.containsAll(required)) {}
        try {
            if(file.isDirectory()) {
                Files.createDirectory(new File(destination).toPath());
            } else {
               Files.copy(file.toPath(),new File(destination).toPath());
            }
            FolderCopier.destinationFilesDone.add(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
