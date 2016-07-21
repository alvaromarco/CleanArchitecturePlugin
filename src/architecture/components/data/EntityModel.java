package architecture.components.data;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.MODEL;

/**
 * Created by alvaro on 15/07/2016.
 * Create model package
 */
public class EntityModel extends DataController {
    public EntityModel(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static void create() {

        // Create Model package
        createDirectory(getDataPackage(), MODEL.toLowerCase());
    }
}
