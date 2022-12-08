package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

      JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
      javaGrepLambdaImp.setRegex(args[0]);
      javaGrepLambdaImp.setRootPath(args[1]);
      javaGrepLambdaImp.setOutFile(args[2]);

      try {
        javaGrepLambdaImp.process();
      } catch (Exception ex){
        ex.printStackTrace();
      }

  }

  @Override
  /**
   * Overrides process() method from super class JavaGrepImp.jav
   * to implement using lambda and stream APIs
   *
   * @throws IOException
   */
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();

    listStreamFiles(getRootPath()).forEach(file -> {
      readStreamLines(file).forEach(line -> {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      });
    });

    writeToFile(matchedLines);
  }

  /**
   * To optimize memory usage, create new method to return stream
   *
   * @param rootDir
   * @return Stream<File>
   */
  public Stream<File> listStreamFiles(String rootDir){
    File files = new File(rootDir);
    File[] fileLists = files.listFiles();
    Stream<File> stream;
    List<File> totalFiles = new ArrayList<File>();

    if(fileLists != null){
        Arrays.stream(fileLists).forEach(file -> {
          if (file.isFile()) {
             totalFiles.add(file);
          }
          if (file.isDirectory()) {
            totalFiles.addAll(listFiles(file.getAbsolutePath()));
          }
        });
    }

    stream = listToStream(totalFiles);

    return stream;
  }

  private static <T> Stream<T> listToStream (List<T> list) {
    return list.stream();
  }

  /**
   * To optimize memory usage, create new method to return stream
   *
   * @param inputFile
   * @return Stream<String>
   * @throws IllegalArgumentException
   */
  public Stream<String> readStreamLines(File inputFile) throws IllegalArgumentException {
    try {
      FileReader fr = new FileReader(inputFile);
      BufferedReader br = new BufferedReader(fr);

      return br.lines();

    } catch (IOException e){
      throw new RuntimeException(e);
    }
  }

}