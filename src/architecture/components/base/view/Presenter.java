package architecture.components.base.view;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

import java.util.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 15/07/2016.
 * Create presenter package and presenter + presenter implementation class
 */
public class Presenter extends BaseViewController {
    private static PsiDirectory presenterDirectory;

    public Presenter(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getPresenterDirectory() {
        return presenterDirectory;
    }

    public static void setPresenterDirectory(PsiDirectory presenterDirectory) {
        Presenter.presenterDirectory = presenterDirectory;
    }

    public static void create() {

        // Check if exists view package
        PsiDirectory packageResult = containsPackage(getViewDirectory(), PRESENTER.toLowerCase());

        if (packageResult == null) { // Not exists
            // Create Presenter Directory
            presenterDirectory = createDirectory(getViewDirectory(), PRESENTER.toLowerCase());

            // Create Presenter.java and PresenterImp.java
            HashMap<String, String> varTemplate = new HashMap<>();

            varTemplate.put("PRESENTER", PRESENTER);

            Runnable runnable = () -> {
                JavaDirectoryService.getInstance().createClass(presenterDirectory, PRESENTER, BASE_PRESENTER);
                JavaDirectoryService.getInstance().createClass(presenterDirectory, PRESENTER_IMPL, BASE_PRESENTER_IMPL, false, varTemplate);
            };

            WriteCommandAction.runWriteCommandAction(getProject(), runnable);
        } else { // Exists
            setPresenterDirectory(packageResult);

            if (presenterDirectory.findFile(PRESENTER + ".java") == null) { // Not contains Presenter.java
                // Create Presenter.java
                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(presenterDirectory, PRESENTER, BASE_PRESENTER);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }

            if (presenterDirectory.findFile(PRESENTER_IMPL + ".java") == null) { // Not contains PresenterImpl.java
                // Create PresenterImpl.class
                HashMap<String, String> varTemplate = new HashMap<>();
                varTemplate.put("PRESENTER", PRESENTER);

                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(presenterDirectory, PRESENTER_IMPL, BASE_PRESENTER_IMPL, false, varTemplate);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }

        }


    }
}
