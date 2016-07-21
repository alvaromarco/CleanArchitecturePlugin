package architecture.components.view;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.containers.HashMap;

import static architecture.model.NamesPlugin.ADAPTER;
import static architecture.model.NamesPlugin.ADAPTER_TEMPLATE;

/**
 * Created by alvaro on 14/07/2016.
 * Class to create adapter package and adapter class
 */
public class EntityAdapter extends ViewController {

    public EntityAdapter(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    /**
     * Create EntityAdapter.class
     */
    public static void create() {
        PsiDirectory adapterDirectory = createDirectory(getViewPackage(), ADAPTER.toLowerCase());

        String className = getEntityConfig().getEntityName() + ADAPTER;

        HashMap<String, String> varTemplate = new HashMap<>();
        varTemplate.put("PACKAGE_PROJECT", getPackageNameProject(getProjectDirectory()));
        varTemplate.put("LAYOUT_NAME", getEntityConfig().getEntityName().toLowerCase());


        Runnable runnable = () -> {
            JavaDirectoryService.getInstance().createClass(adapterDirectory, className, ADAPTER_TEMPLATE, false, varTemplate);
            try {
                createLayout(getPackageNameProject(adapterDirectory), className, ADAPTER);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);


    }

}
