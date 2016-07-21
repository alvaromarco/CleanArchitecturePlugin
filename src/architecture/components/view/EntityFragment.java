package architecture.components.view;

import architecture.components.base.view.ParentFragment;
import architecture.components.base.view.Presenter;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.containers.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 14/07/2016.
 */
public class EntityFragment extends ViewController {

    public EntityFragment(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    /**
     * Create package fragment and EntityFragment.class
     */
    public static void create() {

        // Create fragment directory
        PsiDirectory fragmentDirectory = createDirectory(getViewPackage(), FRAGMENT.toLowerCase());

        // Create fragment class
        String className = getEntityConfig().getEntityName() + FRAGMENT;

        HashMap<String, String> varTemplate = new HashMap<>();
        varTemplate.put("PACKAGE_PROJECT", getPackageNameProject(getProjectDirectory()));
        varTemplate.put("LAYOUT_NAME", getEntityConfig().getEntityName().toLowerCase());
        varTemplate.put("PACKAGE_BASE_FRAGMENT", getPackageNameProject(ParentFragment.getFragmentDirectory()));
        varTemplate.put("BASE_FRAGMENT", PARENT_FRAGMENT);
        varTemplate.put("PACKAGE_BASE_PRESENTER", getPackageNameProject(Presenter.getPresenterDirectory()));
        varTemplate.put("BASE_PRESENTER", PRESENTER);

        if (EntityPresenter.getPresenterDirectory() != null && getEntityConfig().isContainsPresenter()) {  // With presenter or not
            varTemplate.put("PACKAGE_PRESENTER", getPackageNameProject(EntityPresenter.getPresenterDirectory()));
            varTemplate.put("PRESENTER", getEntityConfig().getEntityName() + PRESENTER);
        }

        Runnable runnable = () -> {
            JavaDirectoryService.getInstance().createClass(fragmentDirectory, className, FRAGMENT_TEMPLATE, false, varTemplate);
            try {
                createLayout(getPackageNameProject(fragmentDirectory), className, FRAGMENT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);

    }

}
