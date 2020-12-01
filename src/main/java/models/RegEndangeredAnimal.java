package models;

public class RegEndangeredAnimal extends RegAnimal{
    public String health;
    public String age;

    public static final String HEALTH_HEALTHY="healthy";
    public static final String HEALTH_ILL="ill";
    public static final String HEALTH_OKAY="okay";

    public static final String AGE_NEWBORN="newborn";
    public static final String AGE_YOUNG="young";
    public static final String AGE_ADULT="adult";

    public static final String ANIMAL_TYPE="endangered";
    public RegEndangeredAnimal(String name,String type,String health,String age) {
        super(name,type);
        this.type=type;
        this.health=health;
        this.age=age;

    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }
}
