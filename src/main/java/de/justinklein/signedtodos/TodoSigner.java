package de.justinklein.signedtodos;

import com.intellij.openapi.vfs.VirtualFile;
import de.justinklein.signedtodos.settings.PluginSettingsState;

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
              String signedLine = line + " " + getSignature();
              outputStream.write(signedLine.getBytes());
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

  private static String getName() {
    return PluginSettingsState.getInstance().userId;
  }

  private static String getDateString() {
    return "20.12.2023";
  }

  private static String getSignature() {
    return "(" + getName() + " " + getDateString() + ")";
  }
}
