package models;

public class RegLocation {
    private int id;
    public String name;

    public RegLocation(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }


}
