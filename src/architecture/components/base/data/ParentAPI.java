package architecture.components.base.data;

import architecture.components.util.UtilController;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.containers.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 19/07/2016.
 * Create api package with BaseService class
 */
public class ParentAPI extends BaseDataController {

    private static PsiDirectory apiDirectory;

    public ParentAPI(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getApiDirectory() {
        return apiDirectory;
    }

    public static void create() {

        // Create api package
        apiDirectory = createDirectory(getDataDirectory(), API.toLowerCase());

        // Create BaseService
        HashMap<String, String> varTemplate = new HashMap<>();
        varTemplate.put("PACKAGE_UTIL", getPackageNameProject(UtilController.getUtilPackage()));
        varTemplate.put("CONSTANTS", CONSTANTS);

        Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(apiDirectory, BASE_SERVICE, BASE_SERVICE_TEMPLATE, false, varTemplate);
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);

    }
}
