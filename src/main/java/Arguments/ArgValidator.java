package Arguments;

import java.io.File;
import java.io.IOException;

public class ArgValidator {
    private String sourcePath;
    private String destinationPath;
    private int numberOfThreads;

    public ArgValidator(String[] arguments) throws Exception {
        if(arguments.length == 0) throw new Exception("No arguments");
        String source = arguments[0];
        String destionation = arguments[1];
        String nThreads = arguments[2];
        int i;
        if(!(new File(source).exists()))
            throw new IOException("Invalid source path");
        if(!(new File(destionation).exists()))
            throw new IOException("Invalid destination path");
        try{
            i = Integer.parseInt(nThreads);
        }catch (Exception ex) {
            throw new Exception("Invalid number of threads");
        }

        this.sourcePath = source;
        this.destinationPath = destionation;
        this.numberOfThreads = i;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }
}
