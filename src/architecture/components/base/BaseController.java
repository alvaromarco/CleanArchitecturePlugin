package architecture.components.base;

import architecture.EntityBase;
import architecture.components.base.data.BaseDataController;
import architecture.components.base.domain.BaseDomainController;
import architecture.components.base.view.BaseViewController;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.MAIN;

/**
 * Created by alvaro on 15/07/2016.
 * Controller to create main structure. Contains:
 * .  main
 * .  |
 * .   -- data
 * .   -- domain
 * .   -- view
 */
public class BaseController extends EntityBase {

    private static PsiDirectory mainDirectory;

    public BaseController(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getMainDirectory() {
        return mainDirectory;
    }

    public static void setMainDirectory(PsiDirectory mainDirectory) {
        BaseController.mainDirectory = mainDirectory;
    }

    public static void generateBaseArchitecture(PsiDirectory parent) {

        // Check if exists main package
        PsiDirectory packageResult = containsPackage(parent, MAIN.toLowerCase());

        // Not exists
        if (packageResult == null) {
            // Create main package
            mainDirectory = createDirectory(parent, MAIN.toLowerCase());
        } else {  // Exists

            // Set user main package
            setMainDirectory(packageResult);
        }

        // Create data package
        BaseDataController.create();

        // Create domain package
        BaseDomainController.create();

        // Create view package
        BaseViewController.create();
    }
}
