package view;

import model.Literature;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LiteratureDlg extends Dlg {
    private JTextField textField2;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;

    public LiteratureDlg(){
        setBounds(100,100,400,200);
        setContentPane(contentPane);
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
        String author = textField2.getText();
        int year = Integer.valueOf(textField3.getText());
        String publisher = textField4.getText();
        return new Literature(name, author, year, publisher);
    }

    public LiteratureDlg(Object data){
        this();
        Literature f = (Literature) data;
        textField1.setText(f.name);
        textField2.setText(f.author);
        textField3.setText(String.valueOf(f.year));
        textField4.setText(f.publisher);
    }
}
