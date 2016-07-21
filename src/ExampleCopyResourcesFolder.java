import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * Created by alvaro on 21/07/2016.
 */
public class ExampleCopyResourcesFolder extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        String configPath = PathManager.getConfigPath();

        VirtualFile file = LocalFileSystem.getInstance().findFileByPath(configPath);

        Project project = event.getData(PlatformDataKeys.PROJECT);






        new ExampleCopyResourcesFolderForm(project);
    }
}
