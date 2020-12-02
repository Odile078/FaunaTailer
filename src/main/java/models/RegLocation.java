package models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void save(){
        try (Connection con=DB.sql2o.open()){
            String sql="INSERT INTO reglocations (name) VALUES (:name)";
            if(name.equals("")){
                throw new IllegalArgumentException("fill all fields");
            }
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static RegLocation find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT * FROM reglocations WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(RegLocation.class);
        }

    }
    public static List<RegLocation> all(){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT * FROM reglocations";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(RegLocation.class);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegLocation that = (RegLocation) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<RegSighting> getLocationSightings(){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT regsighting_id FROM reglocations_regsightings WHERE reglocation_id=:reglocation_id";
            List<Integer> sightings_ids=con.createQuery(sql)
                    .addParameter("reglocation_id",this.getId())
                    .executeAndFetch(Integer.class);
            List<RegSighting> sightings=new ArrayList<RegSighting>();

            for(Integer sighting_id:sightings_ids){
                String sightingsQuery="SELECT * FROM regsightings WHERE id=:regsighting_id";
                RegSighting sighting=con.createQuery(sightingsQuery)
                        .addParameter("regsighting_id",sighting_id)
                        .executeAndFetchFirst(RegSighting.class);
                sightings.add(sighting);

            }
            if(sightings.size()==0){
                throw new IllegalArgumentException("Location has no sighting");
            }
            else {return sightings;}


        }

    }


    public void delete(){
        try (Connection con=DB.sql2o.open()){
            String sql="DELETE FROM reglocations WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
    }



}
