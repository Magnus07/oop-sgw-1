package model;

import view.Dlg;
import view.LiteratureDlg;
import view.SubjectDlg;

public class Subject extends CommonData{
    public String tutor;
    public Subject(String name, String tutor){
        this.name = name;this.tutor=tutor;
    }

    public Dlg showDialog(boolean b){
        SubjectDlg d = new SubjectDlg(this);
        d.setEditable(b);
        d.setVisible(true);
        return d;
    }

    public Dlg showSonDialog(){
        LiteratureDlg d = new LiteratureDlg();
        d.setVisible(true);
        return d;
    }
}
