package architecture.components.data;

import architecture.components.base.data.ParentAPI;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.containers.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 15/07/2016.
 * Create api package and entity service class
 */
public class EntityAPI extends DataController {

    private static PsiDirectory apiDirectory;

    public EntityAPI(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getApiDirectory() {
        return apiDirectory;
    }

    public static void create() {

        // Create API package
        apiDirectory = createDirectory(getDataPackage(), API.toLowerCase());

        // Create service class
        String className = getEntityConfig().getEntityName() + SERVICE;

        HashMap<String, String> varTemplate = new HashMap<>();
        varTemplate.put("PACKAGE_BASE_SERVICE", getPackageNameProject(ParentAPI.getApiDirectory()));
        varTemplate.put("BASE_SERVICE", BASE_SERVICE);

        Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(apiDirectory, className, SERVICE_TEMPLATE, false, varTemplate);
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);

    }

}
