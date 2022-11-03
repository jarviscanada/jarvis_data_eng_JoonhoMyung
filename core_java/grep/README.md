# Introduction
This application implements Linux grep command which 
searches for a string in groups of files with matching regex
pattern and returns the string of lines that contains the regex pattern.


This application was built using Maven and implemented the grep command 
using two different ways. The first program used core java and the second 
program used lambda and stream API. Also, FileReader/FileWriter and 
BufferedReader/BufferWriter I/O library was used to interact with data.

Lastly, the docker image of this application was built and deployed 
on DockerHub to facilitate distribution of the application.


# Quick Start
How to use your apps?

First, prepare three arguments which is going to be used for running the program

- `regex`: regex pattern to search
- `rootPath`: where to search regex pattern
- `outFile`: where to save output of application

### Using Jar file:
```
mvn clean package
java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.[nameOfClassYouWantToRun] [regex] [rootPath] [outFile]
```

### Using Docker image
```
docker pull m3996m/grep
docker build -t m3996m/grep .
docker run --rm -v `pwd`/data:/data -v `pwd`/log:/log m3996m/grep [regex] [rootPath] [outFile]
```


# Implemenation
The application searches through files line by line with regex pattern and 
writes matching lines to given outFile address.

## Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue
The `OutOfMemoryError` exception can happen due to lack of heap space availability in JVM. 
When the matching lines from inputFiles is extremely large and if it exceeds
the heap memory set by JVM, exception returns.

1. Allocate more heap memory
    - Since this exception occurred by lack of heap space availability,
      allocating more memory in heap memory by setting max heap size with
      `-Xmx` option would resolve this problem
    - Ex. `java -Xmx256m JavaGrepImp.java`

2. Use Stream API
    - Using Stream API allows us to perform operations on large inputs of data.
      Since stream doesn't store or make any changes on data, it just
      takes input from collections and process collections of objects.

# Test
The application was tested by inputting sample data. Sample data includes
regex patter, input files path and output filename path. Various arguments 
were test on different text files by comparing it to expected output.


# Deployment
This application was dockerized by packaging java app with uber/fat.jar
file. The docker image was uploaded to DockerHub by using `docker push`
and It can be accessed by URL or pulling it from command line
- https://hub.docker.com/repository/docker/m3996m/grep
- docker pull m3996m/grep

# Improvement
- Implement GUI for users
- Implement details about the output of application like line number.
- Implement program which is a better memory efficient application.