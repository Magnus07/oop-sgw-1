package model;

import model.CommonData;
import view.DepartmentDlg;
import view.Dlg;
import view.SubjectDlg;

public class Department extends CommonData {
    public String head;
    public short  floor;

    public Department(String name, String head, short floor){
        this.name = name;this.head=head;this.floor=floor;
    }

    public Dlg showDialog(boolean b){
        DepartmentDlg d = new DepartmentDlg(this);
        d.setEditable(b);
        d.setVisible(true);
        return d;
    }

    public Dlg showSonDialog(){
        SubjectDlg d = new SubjectDlg();
        d.setVisible(true);
        return d;
    }
}
