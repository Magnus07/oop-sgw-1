package view;

import model.Literature;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Locale;

public class SearchByPublisherOrAuthorDlg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextArea textArea1;
    private DefaultMutableTreeNode node;

    public SearchByPublisherOrAuthorDlg(DefaultMutableTreeNode node) {

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

            Literature lit = (Literature) data;

            if (lit.publisher.toLowerCase().contains(textField1.getText().toLowerCase())) {
                result += lit + "\n";
                count++;
                continue;
            }
            if (lit.author.toLowerCase().contains(textField1.getText().toLowerCase())) {
                result += lit + "\n";
                count++;
                continue;
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
