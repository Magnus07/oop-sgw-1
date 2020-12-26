package model;

import view.DepartmentDlg;
import view.Dlg;
import view.DlgFaculty;

public class Faculty extends CommonData{
    public String head;

    public Faculty(String name, String head){
        this.name = name;this.head = head;
    }

    public Dlg showDialog(boolean b){
        DlgFaculty d = new DlgFaculty(this);
        d.setEditable(b);
        d.setVisible(true);
        return d;
    }

    public Dlg showSonDialog(){
        DepartmentDlg d = new DepartmentDlg();
        d.setVisible(true);
        return d;
    }
}
