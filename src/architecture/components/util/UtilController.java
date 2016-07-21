package architecture.components.util;

import architecture.EntityBase;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 20/07/2016.
 * Controller to create util structure. Contains:
 * .  util
 * .  |
 * .   -- Constants.class
 */
public class UtilController extends EntityBase {

    private static PsiDirectory utilPackage;

    public UtilController(Project project, PsiDirectory projectDirectory) {
        super(project, projectDirectory);
    }

    public static PsiDirectory getUtilPackage() {
        return utilPackage;
    }

    public static void createArchitecture(PsiDirectory parent) {

        // Create util package
        utilPackage = createDirectory(parent, UTIL.toLowerCase());

        // Create Constants class
        Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(utilPackage, CONSTANTS, BASE_CONSTANTS);
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);


    }
}
