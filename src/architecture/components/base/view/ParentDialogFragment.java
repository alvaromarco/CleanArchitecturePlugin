package architecture.components.base.view;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiDirectory;

import java.util.HashMap;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 15/07/2016.
 * Create parent dialog fragment class
 */
public class ParentDialogFragment extends BaseViewController {

    public ParentDialogFragment(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static void create() {

        // Create Parent Fragment class
        HashMap<String, String> varTemplate = new HashMap<>();

        varTemplate.put("PACKAGE_PRESENTER", getPackageNameProject(Presenter.getPresenterDirectory()));
        varTemplate.put("PRESENTER", PRESENTER);

        Runnable runnable = () -> JavaDirectoryService.getInstance().createClass(ParentFragment.getFragmentDirectory(), PARENT_DIALOG_FRAGMENT, BASE_DIALOG_FRAGMENT_TEMPLATE, false, varTemplate);
        WriteCommandAction.runWriteCommandAction(getProject(), runnable);
    }
}
