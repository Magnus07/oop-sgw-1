package model;

import view.Dlg;
import view.LiteratureDlg;

public class Literature extends CommonData{
    public String author;
    public Literature(String name, String author){
        this.name = name;this.author=author;
    }

    public Dlg showDialog(boolean b){
        LiteratureDlg d = new LiteratureDlg(this);
        d.setEditable(b);
        d.setVisible(true);
        return d;
    }

    public Dlg showSonDialog(){
        return null;
    }
}
