package architecture.components.util;

import architecture.EntityBase;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;

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

    public static void setUtilPackage(PsiDirectory utilPackage) {
        UtilController.utilPackage = utilPackage;
    }

    public static void createArchitecture(PsiDirectory parent) {

        // Check if exists util package
        PsiDirectory packageResult = containsPackage(parent, UTIL.toLowerCase());

        // Not exists
        if (packageResult == null) {

            // Create util package
            utilPackage = createDirectory(parent, UTIL.toLowerCase());

            // Create Constants.java
            Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(utilPackage, CONSTANTS, BASE_UTIL);
            WriteCommandAction.runWriteCommandAction(getProject(), runnable);
        } else { // Exists

            // Set user util package
            setUtilPackage(packageResult);

            if (utilPackage.findFile(CONSTANTS + ".java") == null) { // Not contains Constants.java

                // Create Constants class
                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(utilPackage, CONSTANTS, BASE_UTIL);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }

        }


    }
}
