package architecture.components.base.view;

import architecture.components.base.BaseController;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.VIEW;

/**
 * Created by alvaro on 15/07/2016.
 * Controller to create main data package. Contains:
 * .  view
 * .  |
 * .   -- activity
 * .      |
 * .      -- ParentActivity.class
 * .   -- fragment
 * .      |
 * .      -- ParentFragment.class
 * .      -- ParentDialogFragment.class
 * .   -- presenter
 * .      |
 * .      -- Presenter.class
 * .      -- PresenterImp.class
 * .   -- adapter
 * .      |
 * .      -- Adapter.class
 */
public class BaseViewController extends BaseController {

    private static PsiDirectory viewDirectory;

    public BaseViewController(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getViewDirectory() {
        return viewDirectory;
    }

    /**
     * Create view structure. Contains: activity, fragment, dialogFragment, presenter and adapter
     */
    public static void create() {

        // Create view package
        viewDirectory = createDirectory(getMainDirectory(), VIEW.toLowerCase());

        // Create Presenter
        Presenter.create();

        // Create Parent Activity
        ParentActivity.create();

        // Create Parent Fragment
        ParentFragment.create();

        // Create Dialog Parent Fragment
        ParentDialogFragment.create();

    }
}
