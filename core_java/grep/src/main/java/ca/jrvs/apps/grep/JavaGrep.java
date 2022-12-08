package ca.jrvs.apps.grep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

  /**
   *
   * @throws IOException
   */
  void process() throws IOException;

  /**
   *
   * @param rootDir
   * @return
   */
  List<File> listFiles(String rootDir);

  /**
   *
   * @param inputFile
   * @return
   */
  List<String> readLines(File inputFile) throws IOException;

  /**
   *
   * @param line
   * @return
   */
  boolean containsPattern(String line);

  /**
   *
   * @param lines
   * @throws IOException
   */
  void writeToFile(List<String> lines) throws IOException;

  String getRootPath();

  void setRootPath (String rootPath);

  String getRegex();

  void setRegex(String regex);

  String getOutFile();

  void setOutFile(String outFile);
}