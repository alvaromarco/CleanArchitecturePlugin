package architecture.components.data;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.CACHE;
import static architecture.model.NamesPlugin.CACHE_TEMPLATE;

/**
 * Created by alvaro on 15/07/2016.
 * Create cache directory and entity cache class
 */
public class EntityCache extends DataController {

    private static PsiDirectory cacheDirectory;

    public EntityCache(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getCacheDirectory() {
        return cacheDirectory;
    }

    public static void create() {

        // Create cache package
        cacheDirectory = createDirectory(getDataPackage(), CACHE.toLowerCase());

        // Create entity cache class
        String className = getEntityConfig().getEntityName() + CACHE;

        Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(cacheDirectory, className, CACHE_TEMPLATE);
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);
    }
}
