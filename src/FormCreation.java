import architecture.EntityBase;
import architecture.model.EntityConfig;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by alvaro on 04/07/2016.
 */
public class FormCreation extends JFrame {
    private JPanel rootPanel;
    private JTextField inputEntity;
    private JCheckBox containsActivity;
    private JCheckBox containsFragment;
    private JCheckBox containsAdapter;
    private JCheckBox containsPresenter;
    private JButton confirmButton;


    public FormCreation(EntityBase entityBase) {
        super("Clean Architecture Generator");

        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        confirmButton.addActionListener(e -> {

            if (containsActivity.isSelected() && containsFragment.isSelected()) { // Select both (Activity and Fragment)
                JOptionPane.showMessageDialog(rootPanel, "Only select activity or fragment");

            } else if (!containsActivity.isSelected() && !containsFragment.isSelected()) { // Not select Activity and Fragment
                JOptionPane.showMessageDialog(rootPanel, "You need to select activity or fragment");

            } else if (!inputEntity.getText().isEmpty()) { // Input not empty
                if (Character.isUpperCase(inputEntity.getText().charAt(0))) { // OK

                    EntityConfig entityConfig = new EntityConfig(inputEntity.getText(),
                            containsActivity.isSelected(),
                            containsFragment.isSelected(),
                            containsPresenter.isSelected(),
                            containsAdapter.isSelected());

                    entityBase.create(entityConfig);
                    dispose();

                } else { // Input lowercase
                    JOptionPane.showMessageDialog(rootPanel, "Please write the entity name with the first letter uppercase");
                }
            } else { // Input empty
                JOptionPane.showMessageDialog(rootPanel, "The entity name is empty!!");
            }

        });
    }
}
