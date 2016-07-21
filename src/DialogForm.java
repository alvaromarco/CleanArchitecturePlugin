import com.intellij.openapi.application.PathManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;

/**
 * Created by alvaro on 21/07/2016.
 */
public class DialogForm extends JFrame {

    private JPanel rootPanel;
    private Project project;

    public DialogForm(Project project) {
        super("DialogForm");

        this.project = project;



        String configPath = PathManager.getConfigPath();


        VirtualFile file = LocalFileSystem.getInstance().findFileByPath(configPath);

        VirtualFile templatesFolder = null; // folder with templates in android (dest)

        for (VirtualFile children : file.getChildren()) {
            if (children.getName().equals("fileTemplates")) {
                templatesFolder = children;
                break;
            }
        }

        if (templatesFolder != null) { // Copy templates files in template folder

        }





    }
}
