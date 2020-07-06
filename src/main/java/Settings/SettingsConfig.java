package Settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.options.SearchableConfigurable;
import com.sun.istack.Nullable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/* Класс берёт GUI для настроек и обрабатывает все операции пользователя с ним*/
public class SettingsConfig implements SearchableConfigurable {
    private SettingsForm mGUI;
    private PersistentStateComponent<SettingState> mState;

    @Nullable
    @Override
    public JComponent createComponent() {
        mGUI = new SettingsForm();
        mGUI.createUI();
        return mGUI.getRootPanel();
    }

    public SettingsConfig() {
        mState = new SettingsPersist().getInstance();
    }

    @Override
    public @org.jetbrains.annotations.Nullable Runnable enableSearch(String option) {
        return null;
    }

    @Override
    public void reset() {
        mGUI.reset();
    }

    @Override
    public boolean isModified() {
        return mGUI.isModified();
    }

    @Override
    public void apply() {
        mGUI.apply();
    }

    @Override
    public void disposeUIResources() {
        mGUI = null;
    }

    @Override
    public @NotNull String getId() {
        return "1";
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return  "AutoTest Generator";
    }
}
