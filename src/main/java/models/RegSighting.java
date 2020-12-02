package models;

import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class RegSighting {
    private int id;
    private int reglocation_id;
    private int regranger_id;
    private int reganimal_id;
    private Date date= new Date();
    private Timestamp registered;

    public RegSighting(int reglocation_id, int regranger_id, int reganimal_id) {
        this.reglocation_id = reglocation_id;
        this.regranger_id = regranger_id;
        this.reganimal_id = reganimal_id;
        this.registered = new Timestamp(date.getTime());

    }

    public int getId() {
        return id;
    }

    public int getRegLocation_id() {
        return reglocation_id;
    }

    public int getRegRanger_id() {
        return regranger_id;
    }

    public int getRegAnimal_id() {
        return reganimal_id;
    }

    public Timestamp getTime() {
        return registered;
    }

    public void save(){

        if(this.reganimal_id==-1||this.reglocation_id==-1||this.regranger_id==-1){
            throw new IllegalArgumentException("fill all the fields");
        }
        try (Connection con=DB.sql2o.open()){
            String sql= "INSERT INTO regsightings (reganimal_id,regranger_id,reglocation_id,registered) VALUES (:reganimal_id,:regranger_id," +
                    ":reglocation_id,:registered)";
            String joinRanger="INSERT INTO regrangers_regsightings (regranger_id,regsighting_id) VALUES (:regranger_id,:regsighting_id)";
            String joinLocation="INSERT INTO reglocations_regsightings (reglocation_id,regsighting_id) VALUES (:reglocation_id," +
                    ":regsighting_id)";

            this.id=(int) con.createQuery(sql,true)
                    .addParameter("reganimal_id",this.reganimal_id)
                    .addParameter("regranger_id",this.regranger_id)
                    .addParameter("reglocation_id",this.reglocation_id)
                    .addParameter("registered",this.registered)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();

            con.createQuery(joinRanger).addParameter("regranger_id",this.getRegRanger_id()).addParameter("regsighting_id",
                    this.getId()).executeUpdate();
            con.createQuery(joinLocation).addParameter("reglocation_id",this.getRegLocation_id()).addParameter("regsighting_id",
                    this.id).executeUpdate();

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegSighting that = (RegSighting) o;
        return id == that.id &&
                reglocation_id == that.reglocation_id &&
                regranger_id == that.regranger_id &&
                reganimal_id == that.reganimal_id &&
                Objects.equals(date, that.date) &&
                Objects.equals(registered, that.registered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reglocation_id, regranger_id, reganimal_id, date, registered);
    }

    public static List<RegSighting> all(){
        try (Connection con =DB.sql2o.open()){
            String sql=("SELECT * FROM regsightings");
            return con.createQuery(sql)
                    .executeAndFetch(RegSighting.class);


        }
    }

    public static RegSighting find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT * FROM regsightings WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(RegSighting.class);

        }
    }
    public void delete(){
        try (Connection con=DB.sql2o.open()){
            String sql="DELETE FROM regsightings WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }

    }
    public static void deleteAll(){
        try (Connection con=DB.sql2o.open()){
            String sql="DELETE FROM regsightings";
            con.createQuery(sql)
                    .executeUpdate();
        }

    }


}
