package models;

public class RegAnimal {
    public int id;
    public String name;
    public String type;
    public String health;
    public String age;

    public static final String ANIMAL_TYPE = "safe";

    public RegAnimal(String name, String  type){
        this.name = name;
        this.type = ANIMAL_TYPE;
        this.health = "";
        this.age ="";
    }
    public String getName() {
        return name;
    }

    public String getType(){
        return  type;
    }

    public String getHealth() {

        return health;
    }

    public String getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
}
