package de.justinklein.signedtodos;

import com.intellij.openapi.vfs.newvfs.BulkFileListener;
import com.intellij.openapi.vfs.newvfs.events.VFileEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FileSystemListener implements BulkFileListener {


  @Override
  public void after(@NotNull List<? extends @NotNull VFileEvent> events) {
    BulkFileListener.super.after(events);
    System.out.println("FS-Event");
    for (VFileEvent event : events) {
      System.out.println("Path = " + event.getPath());
      System.out.println("is-save = " + event.isFromSave());
      if (event.isFromSave()) {
        TodoSigner.signTodosInFile(event.getFile());
      }
    }
  }
}
