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
    private static PsiDirectory modelDirectory;

    public ParentModel(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getModelDirectory() {
        return modelDirectory;
    }

    public static void setModelDirectory(PsiDirectory modelDirectory) {
        ParentModel.modelDirectory = modelDirectory;
    }

    public static void create() {

        PsiDirectory packageResult = containsPackage(getDataDirectory(), MODEL.toLowerCase());

        if (packageResult == null) { // Not contains model package
            // Create model package
            modelDirectory = createDirectory(getDataDirectory(), MODEL.toLowerCase());

            // Create DataSource.java + ServerResponse.java
            Runnable runnable = () -> {
                JavaDirectoryService.getInstance().createClass(modelDirectory, DATA_SOURCE, DATA_SOURCE_TEMPLATE);
                JavaDirectoryService.getInstance().createClass(modelDirectory, SERVER_RESPONSE, BASE_SERVER_RESPONSE_TEMPLATE);
            };

            WriteCommandAction.runWriteCommandAction(getProject(), runnable);
        } else { // Contains model package

            // Set user model package
            setModelDirectory(packageResult);

            if (modelDirectory.findFile(DATA_SOURCE + ".java") == null) { // Not contains DataSource.java

                // Create DataSource.java
                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(modelDirectory, DATA_SOURCE, DATA_SOURCE_TEMPLATE);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }

            if (modelDirectory.findFile(DATA_SOURCE + ".java") == null) { // Not contains DataSource.java

                // Create ServerResponse.java
                Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(modelDirectory, SERVER_RESPONSE, BASE_SERVER_RESPONSE_TEMPLATE);
                WriteCommandAction.runWriteCommandAction(getProject(), runnable);
            }
        }

    }
}
