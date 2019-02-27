package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {

  public static void pack(String sourceDirPackage) throws IOException {
    System.out.println("Working Directory = " + System.getProperty("user.dir"));
    Path outputFile = Paths.get("out/" + sourceDirPackage + ".zip");
    Files.deleteIfExists(outputFile);
    Files.createFile(outputFile);
    try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(outputFile))) {
      Path pp = Paths.get("src/main/java/" + sourceDirPackage);
      Files.walk(pp)
           .filter(path -> !Files.isDirectory(path))
           .forEach(path -> {
             ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
             try {
               zs.putNextEntry(zipEntry);
               Files.copy(path, zs);
               zs.closeEntry();
             } catch (IOException e) {
               System.err.println(e);
             }
           });
    }
  }

}
