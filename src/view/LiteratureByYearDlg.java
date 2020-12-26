package view;

import com.sun.source.tree.Tree;
import model.Literature;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.xml.transform.Result;
import java.awt.event.*;
import java.util.Enumeration;

public class LiteratureByYearDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextArea textArea1;
    private JLabel resultLabel;
    private DefaultMutableTreeNode node;

    public LiteratureByYearDlg(DefaultMutableTreeNode node) {
        setBounds(100,100,400,200);

        setVisible(true);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.node = node;
    }

    private void onOK() {
        Enumeration<TreeNode> enm = node.postorderEnumeration();

        String result = "Result:\n";
        int count = 0;

        while (enm.hasMoreElements()){
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) enm.nextElement();
            Object data = currentNode.getUserObject();

            if (!(data instanceof Literature)) continue;

            if (((Literature)data).year == Integer.valueOf(textField1.getText())) {
                result += (Literature) data + "\n";
                count++;
            }
        }

        result+="\nTotal found: " + String.valueOf(count);

        textArea1.setText(result);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
