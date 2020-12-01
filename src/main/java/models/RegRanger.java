package models;



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


}
