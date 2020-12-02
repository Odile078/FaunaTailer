package models;

import models.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {

        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/faunatailer_test", "odile", "123");
        //DB.sql2o = new Sql2o("jdbc:postgresql://ec2-54-237-135-248.compute-1.amazonaws.com:5432/dcnhd9b7ukgv4i", "dnmnmclytjrzjd", "e598c69fe2b7345f8d835d57fb9b0db1c29557fe458996c64e104b468a5e9067");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimalsQuery = "DELETE FROM reganimals *;";
            String deleteRangersQuery = "DELETE FROM regrangers *;";
            String deleteLocationsQuery = "DELETE FROM reglocations *;";
            String deleteSightingsQuery = "DELETE FROM regsightings *;";
            String deleteJoinsRangersQuery = "DELETE FROM regrangers_regsightings *;";
            String deleteJoinsLocationsQuery = "DELETE FROM reglocations_regsightings *;";
            con.createQuery(deleteAnimalsQuery).executeUpdate();
            con.createQuery(deleteRangersQuery).executeUpdate();
            con.createQuery(deleteLocationsQuery).executeUpdate();
            con.createQuery(deleteSightingsQuery).executeUpdate();
            con.createQuery(deleteJoinsRangersQuery).executeUpdate();

            con.createQuery(deleteJoinsLocationsQuery).executeUpdate();
        }
    }

}