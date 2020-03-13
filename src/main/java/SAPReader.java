import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class SAPReader {
    public static String authorNameTester(File file) {
        String authorName = null;
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            String fileString = new String(bytes);
            Scanner scanner = new Scanner(fileString).useDelimiter("\n");
            while (scanner.hasNext()) {
                String line = scanner.next();
                if (line.startsWith("AUTHOR ")) {
                    authorName = line.substring("AUTHOR ".length());
                    break;
                }
                if (line.startsWith("TIME ")) break;
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authorName;
    }
}
