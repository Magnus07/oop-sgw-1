package view;

import model.Literature;

import javax.swing.*;

public class LiteratureDlg extends Dlg {
    private JTextField textField2;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;

    public LiteratureDlg(){
        //setBounds(100,100,200,100);setContentPane(contentPane);
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
