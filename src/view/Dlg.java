package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class Dlg extends JDialog {
    protected JPanel contentPane;
    protected JButton buttonOK;
    protected JButton buttonCancel;
    protected JTextField textField1;
    protected boolean ok;

    public Dlg(){

        setModal(true);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    protected void onOK() {
        // add your code here
        dispose();
    }

    protected void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public abstract Object createObject() throws Exception;

    public void setEditable(boolean b){
        buttonOK.setVisible(b);

        if(b)
            buttonCancel.setText("Cancel");
        else
            buttonCancel.setText("Exit");
        for (Component c: contentPane.getComponents())
            c.setEnabled(b);
    }
}
