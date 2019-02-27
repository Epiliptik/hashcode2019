package template;

import util.Zipper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class MainTemplate {

  public static void main(String[] args) throws Throwable {
    Zipper.pack("template");

    List<String> fileA = toList("a_example.in");
    List<String> fileB = toList("b_small.in");
    List<String> fileC = toList("c_medium.in");
    List<String> fileD = toList("d_big.in");

    toFile("a_example.out", fileA);
    toFile("b_small.out", fileB);
    toFile("c_medium.out", fileC);
    toFile("d_big.out", fileD);
  }

  private static List<String> toList(String fileName) throws IOException, URISyntaxException {
    Path file = new File(MainTemplate.class.getResource(fileName).toURI()).toPath();
    return Files.readAllLines(file);
  }

  private static void toFile(String name, List<String> lines) throws IOException {
    Files.write(Paths.get("out/" + name),
                lines,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE);
  }
}
