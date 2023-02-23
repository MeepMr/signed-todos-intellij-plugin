package de.justinklein.signedtodos.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PluginSettingsState implements PersistentStateComponent<PluginSettingsState> {
  public String userId = "John Q. Public";
  
  public static PluginSettingsState getInstance() {
    return ApplicationManager.getApplication().getService(PluginSettingsState.class);
  }

  @Nullable
  @Override
  public PluginSettingsState getState() {
    return this;
  }

  @Override
  public void loadState(
    @NotNull PluginSettingsState state
  ) {
    XmlSerializerUtil.copyBean(state, this);
  }

}
