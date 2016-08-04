package architecture.components.base.domain;

import architecture.components.base.BaseController;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 15/07/2016.
 * Controller to create main domain package. Contains:
 * .  domains
 * .  |
 * .   -- UseCase.class
 */
public class BaseDomainController extends BaseController {

    public BaseDomainController(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static void create() {
        // Check if exists domain package
        PsiDirectory packageResult = containsPackage(getMainDirectory(), DOMAIN.toLowerCase());

        if (packageResult == null) { // Not exists
            // Create domain package
            PsiDirectory domainDirectory = createDirectory(getMainDirectory(), DOMAIN.toLowerCase());

            // Create UseCase.java
            Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(domainDirectory, USE_CASE_BASE, BASE_USE_CASE_TEMPLATE);
            WriteCommandAction.runWriteCommandAction(getProject(), runnable);

        } else { // Exists
            if (packageResult.findFile(USE_CASE_BASE + ".java") == null) { // Not contains UseCase.java

                // Create UseCase.java
                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(packageResult, CONSTANTS, BASE_UTIL);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }
        }


    }
}
