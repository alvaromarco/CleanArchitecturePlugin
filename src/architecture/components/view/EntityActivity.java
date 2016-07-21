package architecture.components.view;

import architecture.components.base.view.ParentActivity;
import architecture.components.base.view.Presenter;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.containers.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 14/07/2016.
 * Class to create activity package and Entity
 */
public class EntityActivity extends ViewController {

    public EntityActivity(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    /**
     * Create package activity and EntityActivity.class
     */
    public static void create() {

        // Create activity directory
        PsiDirectory activityDirectory = createDirectory(getViewPackage(), ACTIVITY.toLowerCase());

        // Create activity class
        String className = getEntityConfig().getEntityName() + ACTIVITY;

        HashMap<String, String> varTemplate = new HashMap<>();
        varTemplate.put("PACKAGE_PROJECT", getPackageNameProject(getProjectDirectory()));
        varTemplate.put("LAYOUT_NAME", getEntityConfig().getEntityName().toLowerCase());


        if (ParentActivity.getActivityDirectory() != null) { // With Parent Activity
            varTemplate.put("PACKAGE_BASE_ACTIVITY", getPackageNameProject(ParentActivity.getActivityDirectory()));
            varTemplate.put("BASE_ACTIVITY", PARENT_ACTIVITY);
        }

        if (Presenter.getPresenterDirectory() != null) { // With Parent Presenter
            varTemplate.put("PACKAGE_BASE_PRESENTER", getPackageNameProject(Presenter.getPresenterDirectory()));
            varTemplate.put("BASE_PRESENTER", PRESENTER);
        }

        if (EntityPresenter.getPresenterDirectory() != null && getEntityConfig().isContainsPresenter()) { // With presenter
            varTemplate.put("PACKAGE_PRESENTER", getPackageNameProject(EntityPresenter.getPresenterDirectory()));
            varTemplate.put("PRESENTER", getEntityConfig().getEntityName() + PRESENTER);
        }

        Runnable runnable = () -> {
            JavaDirectoryService.getInstance().createClass(activityDirectory, className, ACTIVITY_TEMPLATE, false, varTemplate);
            try {
                createLayout(getPackageNameProject(activityDirectory), className, ACTIVITY);
            } catch (Exception e) {
                e.printStackTrace();
            }

        };
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);

    }

}
