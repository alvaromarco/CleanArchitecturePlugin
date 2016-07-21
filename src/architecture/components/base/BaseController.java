package architecture.components.base;

import architecture.EntityBase;
import architecture.components.base.data.BaseDataController;
import architecture.components.base.domain.BaseDomainController;
import architecture.components.base.view.BaseViewController;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.*;

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

    public static void generateBaseArchitecture(PsiDirectory parent) {
        // Create main package
        mainDirectory = createDirectory(parent, MAIN.toLowerCase());

        // Create data package
        if (!containsPackage(getProjectDirectory(), DATA.toLowerCase()))
            BaseDataController.create();

        // Create domain package
        if (!containsPackage(getProjectDirectory(), DOMAIN.toLowerCase()))
            BaseDomainController.create();

        // Create view package
        if (!containsPackage(getProjectDirectory(), VIEW.toLowerCase()))
            BaseViewController.create();
    }
}
