package de.justinklein.signedtodos.settings;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class PluginSettingsComponent {
  private final JPanel myMainPanel;
  private final JBTextField myUserNameText = new JBTextField();

  public PluginSettingsComponent() {
    myMainPanel = FormBuilder.createFormBuilder()
      .addLabeledComponent(new JBLabel("Enter the name used to Sign your TODOs"), myUserNameText, 1, true)
      .addComponentFillVertically(new JPanel(), 0)
      .getPanel();
  }

  public JPanel getPanel() {
    return myMainPanel;
  }

  public JComponent getPreferredFocusedComponent() {
    return myUserNameText;
  }

  @NotNull
  public String getUserNameText() {
    return myUserNameText.getText();
  }

  public void setUserNameText(@NotNull String newText) {
    myUserNameText.setText(newText);
  }
}
