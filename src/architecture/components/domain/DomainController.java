package architecture.components.domain;

import architecture.EntityBase;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;

import static architecture.model.NamesPlugin.DOMAIN;

/**
 * Created by alvaro on 15/07/2016.
 * Controller to create domain structure. Contains:
 * .  domain
 */
public class DomainController extends EntityBase {

    public DomainController(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static void createArchitecture(PsiDirectory parent) {

        // Create domain package
        createDirectory(parent, DOMAIN.toLowerCase());
    }
}
