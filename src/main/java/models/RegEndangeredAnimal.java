package models;

import org.sql2o.Connection;

import java.util.Objects;

public class RegEndangeredAnimal extends RegAnimal implements DatabaseManagement{
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
    @Override
    public void save() {
        if(this.name.equals("")||this.type.equals("")||this.health.equals("")||this.age.equals("")){
            throw new IllegalArgumentException("Fill all the fields");
        }
        try (Connection con=DB.sql2o.open()){


            String sql ="INSERT INTO reganimals (name,type,health,age) VALUES (:name,:type,:health,:age)";

            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .addParameter("health",this.health)
                    .addParameter("age",this.age)
                    .executeUpdate()
                    .getKey();
        }

    }

    public static RegEndangeredAnimal find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql= "SELECT * FROM reganimals WHERE id=:id";
            RegEndangeredAnimal animal=  con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(RegEndangeredAnimal.class);
            return animal;

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RegEndangeredAnimal that = (RegEndangeredAnimal) o;
        return Objects.equals(health, that.health) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), health, age);
    }
}
