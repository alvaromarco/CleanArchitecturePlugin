package architecture.components.base.data;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 19/07/2016.
 * Create model package with DataSource and ServerResponse class
 */
public class ParentModel extends BaseDataController {

    public ParentModel(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static void create() {

        if (containsPackage(getDataDirectory(), MODEL.toLowerCase()) == null) { // Not contains
            // Create model package
            PsiDirectory modelDirectory = createDirectory(getDataDirectory(), MODEL.toLowerCase());

            // Create DataSource class + ServerResponse class
            Runnable runnable = () -> {
                JavaDirectoryService.getInstance().createClass(modelDirectory, DATA_SOURCE, DATA_SOURCE_TEMPLATE);
                JavaDirectoryService.getInstance().createClass(modelDirectory, SERVER_RESPONSE, BASE_SERVER_RESPONSE_TEMPLATE);
            };

            WriteCommandAction.runWriteCommandAction(getProject(), runnable);
        }

    }
}
