package model;

import view.DepartmentDlg;
import view.Dlg;
import view.SubjectDlg;

import java.io.Serializable;

public abstract class CommonData implements Serializable {
    public String name;

    @Override
    public String toString() {
        return name;
    }

    public abstract Dlg showDialog(boolean b);

    public abstract Dlg showSonDialog();
}
