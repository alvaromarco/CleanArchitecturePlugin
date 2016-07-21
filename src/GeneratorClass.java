import architecture.EntityBase;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.externalSystem.model.project.ExternalConfigPathAware;
import com.intellij.openapi.project.Project;
import com.intellij.platform.templates.ArchivedTemplatesFactory;
import com.intellij.psi.PsiDirectory;

/**
 * Created by alvaro on 30/06/2016.
 */
public class GeneratorClass extends AnAction {

    public GeneratorClass() {
        super("Generator Class");
    }

    @Override
    public void actionPerformed(AnActionEvent event) {

        Project project = event.getData(PlatformDataKeys.PROJECT);

        Object nav = event.getData(CommonDataKeys.NAVIGATABLE); // Get where the user click to create clean architecture

        PsiDirectory projectDirectory = null;
        if (nav instanceof PsiDirectory)
            projectDirectory = (PsiDirectory) nav;

        //PathManager.getConfigPath()

        new FormCreation(new EntityBase(project, projectDirectory)); // Launch the form
    }
}
