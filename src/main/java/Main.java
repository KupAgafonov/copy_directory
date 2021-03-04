import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 09_FilesAndNetwork/FoulderCopy/data
 * C:/MyDocum/MyProjects/java_basics/09_FilesAndNetwork/FoulderCopy/src/main/resources/data
 * C:/Users/kupag/Desktop/ТестКорона
 * C:/Users/kupag/Desktop/Test
 */

public class Main {
    public static void main(String[] args) {

        final Logger LOGGER = LogManager.getLogger(Main.class);
        final Marker PRINT_MARKER = MarkerManager.getMarker("PRINT");
        final Marker DONE_MARKER = MarkerManager.getMarker("DONE");
        final Marker EXCEPTION_MARKER = MarkerManager.getMarker("EXCEPTION");

        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            System.out.println("Выберете папку :");
            String from = scanner.nextLine().trim();
            System.out.println("Куда скопировать :");
            String to = scanner.nextLine().trim();
            try {
                Path source = Paths.get(from);
                Path destination = Paths.get(to);

                Files.walkFileTree(source, new CopyFileVisitor(destination));

                LOGGER.info(DONE_MARKER, " new paths directory " + to);
                LOGGER.info(PRINT_MARKER, " OK COPY !");

            } catch (Exception ex) {
                LOGGER.error(EXCEPTION_MARKER, (Object) ex);
                ex.printStackTrace();
            }
        }
    }
}

