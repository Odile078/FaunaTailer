package models;


import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RegRanger {
    private int id;
    public String name;
    public String badge_id ;
    public String phone;

    public RegRanger (String name, String badge_id, String phone){
        this.name = name;
        this.badge_id =badge_id;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBadge_id() {
        return badge_id;
    }

    public String getPhone() {
        return phone;
    }

    public void save(){
        try (Connection con=DB.sql2o.open()){
            String sql="INSERT INTO regrangers (name,badge_id,phone) VALUES (:name,:badge_id,:phone)";
            if(name.equals("")||badge_id.equals("")||phone.equals("")){
                throw new IllegalArgumentException("fill all fields");
            }
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("badge_id",this.badge_id)
                    .addParameter("phone",this.phone)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static RegRanger find(int id){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT * FROM regrangers WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(RegRanger.class);
        }

    }
    public static List<RegRanger> all(){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT * FROM regrangers";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(RegRanger.class);

        }

    }
    public void update(int id,String name,String phone){
        try (Connection con=DB.sql2o.open()){
            String sql="UPDATE regrangers SET name=:name,phone=:phone WHERE id=:id";
            if(name.equals("")||phone.equals("")){
                throw new IllegalArgumentException("fill all the fields");
            }
            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .addParameter("name",name)
                    .addParameter("phone",phone)
                    .executeUpdate();

        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegRanger regRanger = (RegRanger) o;
        return id == regRanger.id &&
                Objects.equals(name, regRanger.name) &&
                Objects.equals(badge_id, regRanger.badge_id) &&
                Objects.equals(phone, regRanger.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, badge_id, phone);
    }

    public void delete(){
        try (Connection con=DB.sql2o.open()){
            String sql="DELETE FROM regrangers WHERE id=:id";

            con.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeUpdate();
        }
    }


    public List<RegSighting> getRangerSightings(){
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT regsighting_id FROM regrangers_sightings WHERE regranger_id=:regranger_id";
            List<Integer> sightings_ids=con.createQuery(sql)
                    .addParameter("regranger_id",this.getId())
                    .executeAndFetch(Integer.class);
            List<RegSighting> sightings=new ArrayList<RegSighting>();

            for(Integer sighting_id:sightings_ids){
                String sightingsQuery="SELECT * FROM regsightings WHERE id=:sighting_id";
                RegSighting sighting=con.createQuery(sightingsQuery)
                        .addParameter("regsighting_id",sighting_id)
                        .executeAndFetchFirst(RegSighting.class);
                sightings.add(sighting);

            }
            if(sightings.size()==0){
                throw new IllegalArgumentException("Ranger has no sighting");
            }
            else {return sightings;}


        }

    }

}
