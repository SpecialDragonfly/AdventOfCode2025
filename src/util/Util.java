package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Collectors;

public class Util {
    public static Vector<String> readFile(String file) {
        try {
            return Files.lines(Paths.get(file)).collect(Collectors.toCollection(Vector<String>::new));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Vector<>();
    }

    public static String getFileAsLine(String file) {
        Vector<String> lines = Util.readFile(file);
        StringBuilder sb = new StringBuilder();
        lines.forEach(sb::append);

        return sb.toString();
    }
}
