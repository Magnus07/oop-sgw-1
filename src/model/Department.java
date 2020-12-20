package model;

import model.CommonData;

public class Department extends CommonData {
    public String head;
    public Department(String name, String head){
        this.name = name;this.head=head;
    }
}
