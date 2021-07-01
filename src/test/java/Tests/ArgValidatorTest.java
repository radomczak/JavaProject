package Tests;

import Arguments.ArgValidator;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;


public class ArgValidatorTest {

    @Test
    public void constructorTest() throws Exception {
        File file = new File("C:\\T");
        File file2 = new File("C:\\T2");
        file.mkdir();
        file2.mkdir();

        String[] arguments = {"C:\\T","C:\\T2","10"};
        ArgValidator argValidator = new ArgValidator(arguments);
        assertEquals("C:\\T",argValidator.getSourcePath());
        assertEquals("C:\\T2",argValidator.getDestinationPath());
        assertEquals(10,argValidator.getNumberOfThreads());

        String[] arguments2 = {"zxcvxzcxz:\\T","C:\\T2","10"};
        assertThrows(Exception.class,() -> {
            new ArgValidator(arguments2);
        });

        file.delete();
        file2.delete();
    }
}
//
//import static org.junit.jupiter.api.Assertions.assertThrows;