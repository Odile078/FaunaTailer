package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class RegAnimal implements DatabaseManagement{
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

    public void save(){
        if(this.name.equals("")||this.type.equals("")||this.name.equals(null)||this.type.equals(null)){
            throw new IllegalArgumentException("Fill all the fields");
        }
        try (Connection con=DB.sql2o.open()){


            String sql ="INSERT INTO reganimals (name,type) VALUES (:name,:type)";

            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }

    }
    public static RegAnimal find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql= "SELECT * FROM reganimals WHERE id=:id";
            RegAnimal animal=  con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(RegAnimal.class);
            return animal;

        }

    }
    public static List<RegAnimal> all(){
        try (Connection con=DB.sql2o.open()) {
            String sql ="SELECT * FROM reganimals";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(RegAnimal.class);

        }
    }
    public void update(int id,String type,String health,String age) {
        try (Connection con = DB.sql2o.open()) {
            if (type.equals("")) {
                throw new IllegalArgumentException("All fields must be filled");
            }
            if (type == "endangered") {
                if (health.equals("") || age.equals("")) {
                    throw new IllegalArgumentException("All fields must be filled");
                }
                String sql = "UPDATE reganimals SET type=:type,health=:health,age=:age WHERE id=:id";
                con.createQuery(sql)
                        .addParameter("type", type)
                        .addParameter("health", health)
                        .addParameter("age", age)
                        .addParameter("id", this.id)
                        .executeUpdate();
            } else {

                String sql = "UPDATE reganimals SET type=:type,health=:health,age=:age WHERE id=:id";
                con.createQuery(sql)
                        .addParameter("type", type)
                        .addParameter("health", "")
                        .addParameter("age", "")
                        .addParameter("id", this.id)
                        .executeUpdate();
            }

        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegAnimal regAnimal = (RegAnimal) o;
        return id == regAnimal.id &&
                Objects.equals(name, regAnimal.name) &&
                Objects.equals(type, regAnimal.type) &&
                Objects.equals(health, regAnimal.health) &&
                Objects.equals(age, regAnimal.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, health, age);
    }

    public void delete(){
        try (Connection con=DB.sql2o.open()){
            String sql = "DELETE FROM reganimals WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();

        }
    }
    public static void deleteAll() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM reganimals";
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
