package architecture.components.base.view;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

import java.util.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 15/07/2016.
 * Class to create activity package and ParentActivity class
 */
public class ParentActivity extends BaseViewController {

    private static PsiDirectory activityDirectory;

    public ParentActivity(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getActivityDirectory() {
        return activityDirectory;
    }

    public static void setActivityDirectory(PsiDirectory activityDirectory) {
        ParentActivity.activityDirectory = activityDirectory;
    }

    public static void create() {

        // Check if exists activity package
        PsiDirectory packageResult = containsPackage(getViewDirectory(), ACTIVITY.toLowerCase());

        // Not exists
        if (packageResult == null) {

            // Create activity package
            activityDirectory = createDirectory(getViewDirectory(), ACTIVITY.toLowerCase());

            // Create ParentActivity.class
            HashMap<String, String> varTemplate = new HashMap<>();

            varTemplate.put("PACKAGE_PROJECT", getPackageNameProject(getProjectDirectory()));
            varTemplate.put("PACKAGE_PRESENTER", getPackageNameProject(Presenter.getPresenterDirectory()));
            varTemplate.put("PRESENTER", PRESENTER);

            Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(activityDirectory, PARENT_ACTIVITY, BASE_ACTIVITY_TEMPLATE, false, varTemplate);
            WriteCommandAction.runWriteCommandAction(getProject(), runnable);
        } else { // Exists
            setActivityDirectory(packageResult);

            if (activityDirectory.findFile(PARENT_ACTIVITY + ".java") == null) { // Not contains ParentActivity.java
                // Create ParentActivity.java
                HashMap<String, String> varTemplate = new HashMap<>();

                varTemplate.put("PACKAGE_PROJECT", getPackageNameProject(getProjectDirectory()));
                varTemplate.put("PACKAGE_PRESENTER", getPackageNameProject(Presenter.getPresenterDirectory()));
                varTemplate.put("PRESENTER", PRESENTER);

                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(activityDirectory, PARENT_ACTIVITY, BASE_ACTIVITY_TEMPLATE, false, varTemplate);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }
        }
    }
}
