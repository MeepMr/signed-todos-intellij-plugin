package de.justinklein.signedtodos;

import com.intellij.openapi.vfs.VirtualFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoSigner {
  static void signTodosInFile(VirtualFile file) {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      if (fileContainsTodos(file)) {
        var outputStream = file.getOutputStream(null);
        bufferedReader.lines().forEach(line -> {
          try {
            if (lineContainsTodos(line) && !lineEndsWithSignature(line)) {
              System.out.println("Test");
              var test = line + " (jklein 20.12.2023)";
              outputStream.write(test.getBytes());
            } else {
              outputStream.write(line.getBytes());
            }
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
        outputStream.flush();
        outputStream.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static boolean lineContainsTodos(String line) {
    return line.contains("// TODO");
  }

  private static boolean fileContainsTodos(VirtualFile file) throws IOException {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      return bufferedReader.lines().anyMatch(TodoSigner::lineContainsTodos);
    }
  }

  private static boolean lineEndsWithSignature(String line) {
    return line.matches("(.*[(])(.+[ ][0-9]{1,2}[.][0-9]{1,2}[.][0-9]{2,4}[)].*)");
  }
}
