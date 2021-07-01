package Tests;

import IO.FolderMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class FolderMapperTest {

    Set<String> folder = new HashSet<>();
    FolderMapper mapper = null;
    File file1 = null;
    File file2 = null;
    File file3 = null;
    File file4 = null;

    @Before
    public void setUp() {
        file1 = new File("C:\\T");
        file2 = new File("C:\\T\\1.txt");
        file3 = new File("C:\\T\\2.txt");
        file4 = new File("C:\\T\\Inner");
        file1.mkdir();
        try {
            file2.createNewFile();
            file3.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file4.mkdir();
        folder.add(file1.getAbsolutePath());
        folder.add(file2.getAbsolutePath());
        folder.add(file3.getAbsolutePath());
        folder.add(file4.getAbsolutePath());

        mapper = new FolderMapper();
    }

    @Test
    public void mapFolderTest() {
        Map<File, Integer> folderStructure = null;
        try {
            folderStructure = mapper.getFolderStructure("C:\\T");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(folderStructure != null);
        assertTrue(!folderStructure.isEmpty());
        assertEquals(3,folderStructure.size());

        for(File f : folderStructure.keySet()) {
            if(folder.contains(f.getAbsolutePath())) {}
            else fail();
        }

    }

    @After
    public void setAfter() {
        file1.delete();
        file2.delete();
        file3.delete();
        file4.delete();
    }
}
