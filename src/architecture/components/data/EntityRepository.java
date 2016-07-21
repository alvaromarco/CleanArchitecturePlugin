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
 */
public class EntityRepository extends DataController {

    public EntityRepository(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static void create() {

        // Create Repository package
        PsiDirectory repositoryDirectory = createDirectory(getDataPackage(), REPOSITORY.toLowerCase());

        // Create DataSource package
        PsiDirectory dataSourceDirectory = createDirectory(repositoryDirectory, DATASOURCE.toLowerCase());

        // Create Cloud Entity Data Source class
        String cloudEntityDataSourceName = CLOUD + getEntityConfig().getEntityName() + DATASOURCE;

        HashMap<String, String> cloudTemplate = new HashMap<>();
        cloudTemplate.put("PACKAGE_SERVICE", getPackageNameProject(EntityAPI.getApiDirectory()));
        cloudTemplate.put("SERVICE", getEntityConfig().getEntityName() + SERVICE);

        // Create Disk Entity Data Source class
        String diskEntityDataSourceName = DISK + getEntityConfig().getEntityName() + DATASOURCE;

        HashMap<String, String> diskTemplate = new HashMap<>();
        diskTemplate.put("PACKAGE_BASE_SERVICE", getPackageNameProject(ParentAPI.getApiDirectory()));
        diskTemplate.put("BASE_SERVICE", BASE_SERVICE);
        diskTemplate.put("PACKAGE_CACHE", getPackageNameProject(EntityCache.getCacheDirectory()));
        diskTemplate.put("CACHE", getEntityConfig().getEntityName() + CACHE);

        // Create Data Store Factory class
        String dataStoreFactoryName = getEntityConfig().getEntityName() + DATASTOREFACTORY;

        HashMap<String, String> dataStoreFactoryTemplate = new HashMap<>();
        dataStoreFactoryTemplate.put("PACKAGE_ENTITY_SERVICE", getPackageNameProject(EntityAPI.getApiDirectory()));
        dataStoreFactoryTemplate.put("ENTITY_SERVICE", getEntityConfig().getEntityName() + SERVICE);
        dataStoreFactoryTemplate.put("PACKAGE_ENTITY_CACHE", getPackageNameProject(EntityCache.getCacheDirectory()));
        dataStoreFactoryTemplate.put("ENTITY_CACHE", getEntityConfig().getEntityName() + CACHE);
        dataStoreFactoryTemplate.put("PACKAGE_CLOUD_ENTITY_DATA_SOURCE", getPackageNameProject(dataSourceDirectory));
        dataStoreFactoryTemplate.put("CLOUD_ENTITY_DATA_SOURCE", cloudEntityDataSourceName);
        dataStoreFactoryTemplate.put("PACKAGE_DISK_ENTITY_DATA_SOURCE", getPackageNameProject(dataSourceDirectory));
        dataStoreFactoryTemplate.put("DISK_ENTITY_DATA_SOURCE", diskEntityDataSourceName);

        // Create Repository class
        String repositoryName = getEntityConfig().getEntityName() + REPOSITORY;

        HashMap<String, String> repositoryTemplate = new HashMap<>();
        repositoryTemplate.put("DATA_STORE_FACTORY", getEntityConfig().getEntityName() + DATASTOREFACTORY);


        Runnable repositoryRunnable = () -> {
            JavaDirectoryService.getInstance().createClass(dataSourceDirectory, cloudEntityDataSourceName, CLOUD_ENTITY_DATA_SOURCE_TEMPLATE, false, cloudTemplate);
            JavaDirectoryService.getInstance().createClass(dataSourceDirectory, diskEntityDataSourceName, DISK_ENTITY_DATA_SOURCE_TEMPLATE, false, diskTemplate);
            JavaDirectoryService.getInstance().createClass(repositoryDirectory, dataStoreFactoryName, DATA_STORE_FACTORY_TEMPLATE, false, dataStoreFactoryTemplate);
            JavaDirectoryService.getInstance().createClass(repositoryDirectory, repositoryName, REPOSITORY_TEMPLATE, false, repositoryTemplate);
        };

        WriteCommandAction.runWriteCommandAction(getProject(), repositoryRunnable);

    }
}
