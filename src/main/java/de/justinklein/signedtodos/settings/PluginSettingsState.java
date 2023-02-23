package de.justinklein.signedtodos.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
  name = "de.justinklein.signedtodos.settings.PluginSettingsState",
  storages = @Storage("SdkSettingsPlugin.xml")
)
public class PluginSettingsState implements PersistentStateComponent<PluginSettingsState> {
  public String userId = "Your Name Here :-)";

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
