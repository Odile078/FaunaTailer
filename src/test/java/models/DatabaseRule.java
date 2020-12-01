package models;

import models.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/faunatailer_test", "odile", "123");
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