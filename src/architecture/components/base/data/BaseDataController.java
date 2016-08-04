package architecture.components.base.data;

import architecture.components.base.BaseController;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.CACHE;
import static architecture.model.NamesPlugin.DATA;

/**
 * Created by alvaro on 15/07/2016.
 * Controller to create main data package. Contains:
 * .  data
 * .  |
 * .   -- api
 * .      |
 * .      -- BaseService.class
 * .   -- cache
 * .   -- model
 * .      |
 * .      -- DataSource.class
 * .      -- ServerResponse.class
 */
public class BaseDataController extends BaseController {

    private static PsiDirectory dataDirectory;

    public BaseDataController(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getDataDirectory() {
        return dataDirectory;
    }

    public static void setDataDirectory(PsiDirectory dataDirectory) {
        BaseDataController.dataDirectory = dataDirectory;
    }

    public static void create() {

        // Check if exists data package
        PsiDirectory packageResult = containsPackage(getMainDirectory(), DATA.toLowerCase());

        // Not exists
        if (packageResult == null) {
            // Create data package
            dataDirectory = createDirectory(getMainDirectory(), DATA.toLowerCase());
        } else {
            setDataDirectory(packageResult);
        }

        // Create api package with components
        ParentAPI.create();

        // Create cache package
        if (containsPackage(dataDirectory, CACHE.toLowerCase()) == null)
            createDirectory(dataDirectory, CACHE.toLowerCase());

        // Create model package with components
        ParentModel.create();

    }
}
