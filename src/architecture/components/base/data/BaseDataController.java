package architecture.components.base.data;

import architecture.components.base.BaseController;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.*;

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

    public static void create() {

        // Create data package
        dataDirectory = createDirectory(getMainDirectory(), DATA.toLowerCase());

        // Create api package with components
       /* if (!containsPackage(dataDirectory, API.toLowerCase()))*/
            ParentAPI.create();

        // Create cache package
        /*if (!containsPackage(dataDirectory, CACHE.toLowerCase()))*/
            createDirectory(dataDirectory, CACHE.toLowerCase());

        // Create model package with components
        /*if (!containsPackage(dataDirectory, MODEL.toLowerCase()))*/
            ParentModel.create();

    }
}
