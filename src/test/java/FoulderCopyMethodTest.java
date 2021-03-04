import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoulderCopyMethodTest {

    File source = new File("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\ReadWriteFiles\\data");
    File destination = new File("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\DirectoryCopy\\data");



    @BeforeEach
    public void setUp() throws IOException {
        FileUtils.deleteDirectory(new File("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\DirectoryCopy\\data"));
    }

    //Apache Commons IO (NIO FileChannel)

    @Test
    public void whenCopyFoulderUsingApacheCommonsIO() throws IOException {

        long expected = 9826;

        FileUtils.copyDirectory(source, destination);

        long actual = FileUtils.sizeOfDirectory(destination);

        assertEquals(expected, actual);
    }


    @Test
    public void whenCopyFoulderUsingMyFileCopyVisitor() throws IOException {

        long expected = 9826;

        Files.walkFileTree(Paths.get("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\ReadWriteFiles\\data"),
                new MyFileCopyVisitor(Paths.get("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\ReadWriteFiles\\data"),
                        Paths.get("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\DirectoryCopy\\data")));

        long actual = FileUtils.sizeOfDirectory(destination);

        assertEquals(expected, actual);
    }

}
