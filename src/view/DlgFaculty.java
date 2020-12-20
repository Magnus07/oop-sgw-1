package view;

import model.Faculty;

import javax.swing.*;

public class DlgFaculty extends Dlg {
    private JTextField textField2;

    public DlgFaculty(){
        setBounds(100,100,100,100);
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
