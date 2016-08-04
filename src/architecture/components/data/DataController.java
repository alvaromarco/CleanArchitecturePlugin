package architecture.components.data;

import architecture.EntityBase;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.DATA;

/**
 * Created by alvaro on 15/07/2016.
 * Controller to create data structure. Contains:
 * .  data
 * .  |
 * .   -- api
 * .      |
 * .      -- EntityService.class
 * .   -- cache
 * .      |
 * .      -- EntityCache.class
 * .   -- model
 * .   -- repository
 * .      |
 * .      -- datasource
 * .         |
 * .         -- CloudEntityDataSource.class
 * .         -- DiskEntityDataSource.class
 * .      -- EntityDataStoreFactory.class
 * .      -- EntityRepository.class
 */
public class DataController extends EntityBase {

    private static PsiDirectory dataPackage;

    public DataController(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getDataPackage() {
        return dataPackage;
    }

    public static void createArchitecture(PsiDirectory parent) {
        dataPackage = createDirectory(parent, DATA.toLowerCase());

        // Create api package with components
        EntityAPI.create();

        // Create cache package with components
        EntityCache.create();

        // Create model package
        EntityModel.create();

        // Create repository package with components
        EntityRepository.create();

    }

}
