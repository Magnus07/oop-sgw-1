package view;

import model.Faculty;

import javax.swing.*;

public class DlgFaculty extends Dlg {
    private JTextField textField2;
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton buttonOK;
    private JTextField textField1;

    public DlgFaculty(){
        setBounds(100,100,400,200);setContentPane(contentPane);

        buttonOK.addActionListener(e -> {
            ok=true;
            setVisible(false);
        });
        buttonCancel.addActionListener(e -> {
            ok = false;
            setVisible(false);
        });
    }

    @Override
    public Object createObject() throws Exception {
        if (!ok) return null;
        String name = textField1.getText();
        String head = textField2.getText();
        return new Faculty(name, head);
    }

    public DlgFaculty(Object data){
        this();
        Faculty f = (Faculty) data;
        textField1.setText(f.name);
        textField2.setText(f.head);
    }
}
