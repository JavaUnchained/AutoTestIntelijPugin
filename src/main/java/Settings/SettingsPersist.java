package Settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Класс позволяет хранить переменные из {@link SettingState} в XML
 *
 * Доступ к хранимому в стиле Read Write Lock. Поэтому все операции записи осущетсвляются через отдельный поток:
 *  ApplicationManager.getApplication().runWriteAction(new Runnable() { @Override public void run() {}});
 */
@State(
        name = "Settings",
        storages = {@Storage("settings-value-demo.xml")}
)
public class SettingsPersist implements PersistentStateComponent<SettingState> {

    private SettingState settingState = new SettingState();

    @Nullable
    @Override
    public SettingState getState() {
        return settingState;
    }

    @Override
    public void loadState(@NotNull final SettingState state) {
        settingState = state;
    }

    public PersistentStateComponent<SettingState> getInstance() {
        return ServiceManager.getService(SettingsPersist.class);
    }

}
