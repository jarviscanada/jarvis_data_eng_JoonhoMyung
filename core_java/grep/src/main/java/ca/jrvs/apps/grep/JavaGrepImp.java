package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  @Override
  public void process() throws IOException {
      List<String> matchedLines = new ArrayList<>();

      for (File file : listFiles(getRootPath())){
          for (String line : readLines(file)){
              if (containsPattern(line)){
                matchedLines.add(line);
              }
          }
      }

      writeToFile(matchedLines);
  }

  @Override
  /**
   * Find all files from given rootDir and return it
   *
   * @param rootDir input dir address
   * @return files under the rootDir
   */
  public List<File> listFiles(String rootDir) {
    File files = new File(rootDir);
    File[] fileLists = files.listFiles();;
    List<File> totalFiles = new ArrayList<File>();

    if(fileLists != null) {
      for (File file : fileLists) {
        if (file.isFile()) {
          totalFiles.add(file);
        }
        if (file.isDirectory()) {
          totalFiles.addAll(listFiles(file.getAbsolutePath()));
        }
      }
    }

    return totalFiles;
  }

  @Override
  /**
   * Return every lines of text from file
   *
   * @param inputFile file to be read
   * @return lines
   * @throws IllegalArgumentException
   */
  public List<String> readLines(File inputFile)
      throws IllegalArgumentException, IOException {
    FileReader fr = new FileReader(inputFile);
    BufferedReader br = new BufferedReader(fr);
    List<String> fileTexts = new ArrayList<>();
    String line = null;

    while ((line = br.readLine()) != null){
        fileTexts.add(line);
    }

    return fileTexts;
  }

  @Override
  /**
   * check if a line matches with input regex patter
   *
   * @param line
   * @return boolean
   */
  public boolean containsPattern(String line) {
    boolean isContain = false;

    if (line.matches(getRegex())){
      isContain = true;
    }

    return isContain;
  }

  @Override
  /**
   * write input string to a output file address
   *
   * @param lines
   * @throws IOException
   */
  public void writeToFile(List<String> lines) throws IOException {
        FileWriter writer = new FileWriter(getOutFile());
        BufferedWriter buffer = new BufferedWriter(writer);

        for (String text : lines){
          buffer.write(text + "\n");
        }
        buffer.close();
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  public static void main(String[] args) {
    if (args.length != 3){
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try{
      javaGrepImp.process();
    } catch (Exception ex){
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }
}