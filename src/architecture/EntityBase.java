package architecture;

import architecture.components.base.BaseController;
import architecture.components.data.DataController;
import architecture.components.domain.DomainController;
import architecture.components.util.UtilController;
import architecture.components.view.ViewController;
import architecture.model.EntityConfig;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.MAIN;

/**
 * Created by alvaro on 14/07/2016.
 */
public class EntityBase {

    private static Project project;
    private static PsiDirectory projectDirectory;

    private static EntityConfig entityConfig;

    // Variables to create a directory
    private static PsiDirectory directoryCreated;
    private static Runnable runnable;

    public EntityBase(Project project, PsiDirectory projectDirectory) {
        this.project = project;
        this.projectDirectory = projectDirectory;
    }

    public static Project getProject() {
        return project;
    }

    public static EntityConfig getEntityConfig() {
        return entityConfig;
    }

    public static PsiDirectory getProjectDirectory() {
        return projectDirectory;
    }


    /**
     * Create clean architecture: base, view, data and domain packages
     */
    public void create(EntityConfig entityConfig) {
        this.entityConfig = entityConfig;
        PsiDirectory parent = projectDirectory; // Main package about the project
        parent = createDirectory(parent, this.entityConfig.getEntityName().toLowerCase());


        UtilController.createArchitecture(projectDirectory);


        /*if (!containsPackage(projectDirectory, MAIN.toLowerCase()))*/
            BaseController.generateBaseArchitecture(projectDirectory);

        ViewController.createArchitecture(parent);

        DataController.createArchitecture(parent);

        DomainController.createArchitecture(parent);

    }

    /**
     * Find the directory from user click to create new entity
     *
     * @param sourceDirectory source directory to search
     * @param namePackage     name about package that you search
     * @return
     */
    public static PsiDirectory containsPackage(PsiDirectory sourceDirectory, String namePackage) {
        PsiDirectory mPackage = null;

        // Find from project directory to subdirectories
        for (PsiDirectory iterator : sourceDirectory.getSubdirectories()) {
            if (iterator.getName().equals(namePackage))
                mPackage = iterator;
        }

        return mPackage;
    }


    /**
     * Create package in the directory specific with a name
     *
     * @param parent      package when create a subpackage
     * @param packageName package name
     * @return
     */
    public static PsiDirectory createDirectory(PsiDirectory parent, String packageName) {
        directoryCreated = null;

        // Search subpackage with the same name
        for (PsiDirectory dir : parent.getSubdirectories()) {
            if (dir.getName().equalsIgnoreCase(packageName)) {
                directoryCreated = dir;
                break;
            }
        }

        // When the search not found a package with the same name, create a subdirectory
        if (directoryCreated == null) {
            runnable = () -> directoryCreated = parent.createSubdirectory(packageName);
            WriteCommandAction.runWriteCommandAction(project, runnable);
        }
        return directoryCreated;
    }

    /**
     * Method that return the complete package name for a component in clean architecture
     *
     * @param directory directory to find the parents
     * @return example: com.digio.project.entityName.view.activity
     */
    public static String getPackageNameProject(PsiDirectory directory) {
        PsiDirectory directoryIterator = directory;
        String packageName = directory.getName();

        while (!directoryIterator.getName().equals("com")) {
            directoryIterator = directoryIterator.getParentDirectory();
            packageName = directoryIterator.getName() + "." + packageName;
        }

        return packageName;

    }

    /**
     * Get res package in project
     *
     * @return res package
     */
    public static VirtualFile getResPackage() {
        PsiDirectory iterator = projectDirectory;

        // Search main directory in project
        while (!iterator.getName().equals("main")) {
            iterator = iterator.getParentDirectory();
        }

        VirtualFile main = iterator.getVirtualFile();

        // Search res directory in main directory
        return main.findChild("res");
    }
}
