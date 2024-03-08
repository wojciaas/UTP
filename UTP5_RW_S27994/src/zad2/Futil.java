package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Futil {

    public static void processDir(String dirName, String resultFileName) {
        final List<String> filesContent = new ArrayList<>();
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(resultFileName), StandardCharsets.UTF_8);
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    BufferedReader reader = Files.newBufferedReader(file, Charset.forName("Cp1250"));
                    if (file.toString().endsWith(".txt")) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            filesContent.add(line);
                        }
                    }
                    reader.close();
                    return FileVisitResult.CONTINUE;
                }
            });
            for (String line : filesContent) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
