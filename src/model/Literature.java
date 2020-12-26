package model;

import view.Dlg;
import view.LiteratureDlg;

public class Literature extends CommonData{
    public String author;
    public int    year;
    public String publisher;


    public Literature(String name, String author, int year, String publisher){
        this.name = name;this.author=author;this.year = year; this.publisher = publisher;
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

    @Override
    public String toString() {
        return "Literature{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}
