package App;

import Arguments.ArgValidator;
import IO.FolderCopier;

public class App {
    public static void main(String[] args) {
        ArgValidator argValidator = null;
        try {
            argValidator = new ArgValidator(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        FolderCopier copier = new FolderCopier(argValidator);
        copier.copyFolder();
    }
}
