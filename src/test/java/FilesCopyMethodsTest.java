import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesCopyMethodsTest {

    File source = new File("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\ReadWriteFiles\\data\\file.txt");
    File destination = new File("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\DirectoryCopy\\data\\file.txt");

    //IO_Stream
    private static void copyFileUsingIOStream(File source, File destination) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    //NIO FileChannel
    private static void copyFileUsingNIOFileChannel(File source, File destination) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destChannel = new FileOutputStream(destination).getChannel();
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }

    //Apache Commons IO (NIO FileChannel)
    private static void copyFileUsingApacheCommonsIO(File source, File destination) throws IOException {
        FileUtils.copyFile(source, destination);
    }

    //Files
    private static void copyFileUsingFiles(File source, File destination) throws IOException {
        Files.copy(source.toPath(), destination.toPath());
    }


    @BeforeEach
    public void setUp() throws IOException {
        Files.delete(Paths.get("C:\\MyDocum\\MyProjects\\java_basics\\09_FilesAndNetwork\\DirectoryCopy\\data\\file.txt"));
    }


    @Test
    public void whenCopyFoulderUsingIOStream() throws IOException {
        long expected = 3890;

        copyFileUsingIOStream(source, destination);

        long actual = FileUtils.sizeOf(destination);

        assertEquals(expected, actual);
    }


    @Test
    public void whenCopyFoulderUsingNIOFileChannel() throws IOException {
        long expected = 3890;

        copyFileUsingNIOFileChannel(source, destination);

        long actual = FileUtils.sizeOf(destination);

        assertEquals(expected, actual);
    }


    @Test
    public void whenCopyFoulderUsingApacheCommonsIO() throws IOException {
        long expected = 3890;

        copyFileUsingApacheCommonsIO(source, destination);

        long actual = FileUtils.sizeOf(destination);

        assertEquals(expected, actual);
    }


    @Test
    public void whenCopyFoulderUsingFilesClass() throws IOException {
        long expected = 3890;

        copyFileUsingFiles(source, destination);

        long actual = FileUtils.sizeOf(destination);

        assertEquals(expected, actual);
    }
}
