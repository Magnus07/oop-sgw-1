package view;

import model.Literature;

import javax.swing.*;

public class LiteratureDlg extends Dlg {
    private JTextField textField2;

    public LiteratureDlg(){
        setBounds(100,100,100,100);
    }

    @Override
    public Object createObject() throws Exception {
        if (!ok) return null;
        String name = textField1.getText();
        String author = textField2.getText();
        return new Literature(name, author);
    }

    public LiteratureDlg(Object data){
        this();
        Literature f = (Literature) data;
        textField1.setText(f.name);
        textField2.setText(f.author);
    }
}
