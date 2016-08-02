package architecture.components.view;

import architecture.EntityBase;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import static architecture.model.NamesPlugin.*;

/**
 * Created by alvaro on 15/07/2016.
 * Controller to create view structure. Contains:
 * .  view
 * .  |
 * .   -- activity
 * .      |
 * .      -- EntityActivity.class
 * .   -- fragment
 * .      |
 * .      -- EntityFragment.class
 * .   -- presenter
 * .      |
 * .      -- EntityPresenter.class
 * .   -- adapter
 * .      |
 * .      -- EntityAdapter.class
 */
public class ViewController extends EntityBase {

    private static PsiDirectory viewPackage;

    public ViewController(Project project, PsiDirectory mainDirectory) {
        super(project, mainDirectory);
    }

    public static PsiDirectory getViewPackage() {
        return viewPackage;
    }

    public static void createArchitecture(PsiDirectory parent) {
        viewPackage = createDirectory(parent, VIEW.toLowerCase());

        // Creation presenter package and components
        if (getEntityConfig().isContainsPresenter() && !containsPackage(viewPackage, PRESENTER.toLowerCase()))
            EntityPresenter.create();

        // Creation activity package and components
        if (getEntityConfig().isContainsActivity() && !containsPackage(viewPackage, ACTIVITY.toLowerCase()))
            EntityActivity.create();

        // Create fragment package and components
        if (getEntityConfig().isContainsFragment() && !containsPackage(viewPackage, FRAGMENT.toLowerCase()))
            EntityFragment.create();

        // Create adapter package and components
        if (getEntityConfig().isContainsAdapter() && !containsPackage(viewPackage, ADAPTER.toLowerCase()))
            EntityAdapter.create();

    }

    public static void createLayout(String directory, String className, String type) throws Exception {
        final String INDENT_SPACE = "{http://xml.apache.org/xslt}indent-amount";
        final String nsUri = "http://www.w3.org/2000/xmlns/";
        final String androidUri = "http://schemas.android.com/apk/res/android";
        final String toolsUri = "http://schemas.android.com/tools";

        VirtualFile parent = getResPackage(); // Res package in android project
        VirtualFile layoutDirectory = parent.findChild("layout");

        VirtualFile newXmlFile = layoutDirectory.createChildData(null, type.toLowerCase() + "_" + getEntityConfig().getEntityName().toLowerCase() + ".xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();

        Element root = doc.createElement("RelativeLayout");
        root.setAttributeNS(nsUri, "xmlns:android", androidUri);
        root.setAttributeNS(nsUri, "xmlns:tools", toolsUri);
        root.setAttribute("android:id", "@+id/container");
        root.setAttribute("android:layout_width", "match_parent");
        root.setAttribute("android:layout_height", "match_parent");
        root.setAttribute("tools:context", directory + "." + className);
        doc.appendChild(root);

        OutputStream os = newXmlFile.getOutputStream(null);
        PrintWriter out = new PrintWriter(os);

        StringWriter writer = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(INDENT_SPACE, "4");
        transformer.transform(new DOMSource(doc), new StreamResult(writer));

        out.println(writer.getBuffer().toString());
        out.close();

    }

}
