package view;

import model.Subject;

import javax.swing.*;

public class SubjectDlg extends Dlg {
    private JTextField textField2;
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton buttonOK;
    private JTextField textField1;

    public SubjectDlg(){
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
        String tutor = textField2.getText();
        return new Subject(name, tutor);
    }

    public SubjectDlg(Object data){
        this();
        Subject f = (Subject) data;
        textField1.setText(f.name);
        textField2.setText(f.tutor);
    }
}
