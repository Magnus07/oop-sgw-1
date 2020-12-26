package view;

import model.Department;

import javax.swing.*;

public class DepartmentDlg extends Dlg {
    private JTextField textField2;
    private JPanel contentPane;
    private JTextField textField1;
    private JButton buttonOK;
    private JButton buttonCancel;

    public DepartmentDlg(){
        setBounds(100,100,100,100);setContentPane(contentPane);

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
        return new Department(name, head);
    }

    public DepartmentDlg(Object data){
        this();
        Department f = (Department) data;
        textField1.setText(f.name);
        textField2.setText(f.head);
    }
}
