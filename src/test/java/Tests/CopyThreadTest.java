package Tests;//import static org.junit.Assert.*;

import IO.CopyThread;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CopyThreadTest {

    File file = new File("C:\\T");
    File file2 = new File("C:\\T2");

    @Before
    public void setUp() {
        file.mkdir();
    }

    @After
    public void setAfter() {
        file.delete();
        file2.delete();
    }

    @Test
    public void runTest() {
        Set<File> required = new HashSet<>();
        File file = new File("C:\\T");
        String destination = "C:\\T2";

        CopyThread c = new CopyThread(required,file,destination);
        c.run();
        assertTrue(file2.exists());
    }

}
