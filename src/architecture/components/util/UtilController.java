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
        /**
         * If packageResult is null: The project not contains package util, create util package
         * else packageResult isn't null: The project contains package util and set the package
         **/
        PsiDirectory packageResult = containsPackage(parent, UTIL.toLowerCase());

        if (packageResult == null) {
            // Create util package
            utilPackage = createDirectory(parent, UTIL.toLowerCase());

            // Create Constants class
            Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(utilPackage, CONSTANTS, BASE_UTIL);
            WriteCommandAction.runWriteCommandAction(getProject(), runnable);
        } else { // Get user util package
            setUtilPackage(packageResult);

            PsiFile file = utilPackage.findFile(CONSTANTS + ".java");
            if (file == null) {
                // Create Constants class
                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(utilPackage, CONSTANTS, BASE_UTIL);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }

           /* PsiClass[] psiClasses = JavaDirectoryService.getInstance().getClasses(utilPackage);

            boolean containsUtils = false;
            for (int i = 0; i< psiClasses.length; i++){
                if (psiClasses[i].getName().equals(CONSTANTS)){
                    containsUtils = true;
                    break;
                }
            }

            if (!containsUtils){
                // Create Constants class
                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(utilPackage, CONSTANTS, BASE_UTIL);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }*/


        }


    }
}
