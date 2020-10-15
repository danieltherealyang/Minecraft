package Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String readFile(String file) {
        Path path = Paths.get(file);
        String content = null;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
