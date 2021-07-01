package Tests;

import Arguments.ArgValidator;
import IO.FolderCopier;
import IO.FolderMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FolderCopierTest {

    FolderCopier copier = null;
    Set<String> paths = null;
    File file1 = null;
    File file2 = null;

    @Before
    public void setUp() {
        paths = populateStringSet();
        String[] args = {"C:\\T","C:\\T2","5"};
        file1 = new File(args[0]);
        file2 = new File(args[1]);
        file1.mkdir();
        file2.mkdir();

        for (String path : paths) {
            File f = new File(path);
            if(path.contains(".txt")) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else f.mkdir();
        }

        ArgValidator argValidator = null;
        try {
            argValidator = new ArgValidator(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        copier = new FolderCopier(argValidator);
    }

    private Set<String> populateStringSet() {
        Set<String> paths = new HashSet<>();
        String base = "C:\\T\\";
        for (int i = 0; i < 5; i++) {
            String s = base;
            if(i%2==0) {
                s = s.concat("Plik"+i+".txt");
            } else {
                s = s.concat("Folder"+i+"");
            }
            paths.add(s);
        }
        return paths;
    }

    @Test
    public void copyFolderTest() {
        copier.copyFolder();
        FolderMapper mapper = new FolderMapper();
        Map<File, Integer> folderStructure = null;
        try {
            folderStructure = mapper.getFolderStructure("C:\\T2");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(folderStructure != null);
        assertTrue(!folderStructure.isEmpty());
        assertEquals(5,folderStructure.size());

    }

    @After
    public void setAfter() {
        String[] files1ToDelete = file1.list();
        String[] files2ToDelete = file2.list();

        for (String p : files1ToDelete) {
            p = "C:\\T\\".concat(p);
            File f = new File(p);
            f.delete();
        }
        for (String p : files2ToDelete) {
            p = "C:\\T2\\".concat(p);
            File f = new File(p);
            f.delete();
        }
        file1.delete();
        file2.delete();
    }
}
