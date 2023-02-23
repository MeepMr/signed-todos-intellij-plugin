package de.justinklein.signedtodos.settings;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class PluginSettingsConfigurable implements Configurable {
  private PluginSettingsComponent mySettingsComponent;

  @Nls(capitalization = Nls.Capitalization.Title)
  @Override
  public String getDisplayName() {
    return "Signed TODOs";
  }

  @Override
  public JComponent getPreferredFocusedComponent() {
    return mySettingsComponent.getPreferredFocusedComponent();
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    mySettingsComponent = new PluginSettingsComponent();
    return mySettingsComponent.getPanel();
  }

  @Override
  public boolean isModified() {
    PluginSettingsState settings = PluginSettingsState.getInstance();
    return !mySettingsComponent.getUserNameText().equals(settings.userId);
  }

  @Override
  public void apply() {
    PluginSettingsState settings = PluginSettingsState.getInstance();
    settings.userId = mySettingsComponent.getUserNameText();
  }

  @Override
  public void reset() {
    PluginSettingsState settings = PluginSettingsState.getInstance();
    mySettingsComponent.setUserNameText(settings.userId);
  }

  @Override
  public void disposeUIResources() {
    mySettingsComponent = null;
  }
}
