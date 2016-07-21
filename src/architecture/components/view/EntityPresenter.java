package architecture.components.view;

import architecture.components.base.view.Presenter;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.containers.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 14/07/2016.
 * Class to create presenter package and presenter class
 */
public class EntityPresenter extends ViewController {
    private static PsiDirectory presenterDirectory;

    public EntityPresenter(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getPresenterDirectory() {
        return presenterDirectory;
    }

    /**
     * Create EntityPresenter.class
     */
    public static void create() {

        // Create presenter directory
        presenterDirectory = createDirectory(getViewPackage(), PRESENTER.toLowerCase());

        // Create presenter class
        String className = getEntityConfig().getEntityName() + PRESENTER;

        HashMap<String, String> varTemplate = new HashMap<>();
        varTemplate.put("PACKAGE_PRESENTER_IMPL", getPackageNameProject(Presenter.getPresenterDirectory()));
        varTemplate.put("PRESENTER_IMPL", PRESENTER_IMPL);

        Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(presenterDirectory, className, PRESENTER_TEMPLATE, false, varTemplate);
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);
    }
}
