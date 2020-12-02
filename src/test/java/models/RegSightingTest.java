package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegSightingTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        RegSighting testRegSighting = new RegSighting(1, 1,1);
        assertEquals(true, testRegSighting instanceof RegSighting);
    }

    @Test
    public void getlocation_id_SightinInstantiatesWithName_true() {
        RegSighting testRegSighting = new RegSighting(1, 1,1);
        assertEquals(1, testRegSighting.getRegLocation_id());
    }
    @Test
    public void getranger_id_sightingInstantiatesWithName_true() {
        RegSighting testRegSighting = new RegSighting(1, 1,1);
        assertEquals(1, testRegSighting.getRegRanger_id());
    }
    @Test
    public void getanimal_id_sightingInstantiatesWithName_true() {
        RegSighting testRegSighting = new RegSighting(1, 1, 1);
        assertEquals(1, testRegSighting.getRegAnimal_id());
    }

    @Test
    public void allInstancesAreSaved() {
        RegSighting testRegSighting = new RegSighting(1, 1, 1);
        RegSighting otherRegSighting=new RegSighting(-1,1,1);
        try {
            testRegSighting.save();
            otherRegSighting.save();
            assertTrue(RegSighting.find(testRegSighting.getId()).equals(testRegSighting));
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }

    @Test
    public void deleteSightingByID() {
        RegSighting testRegSighting = new RegSighting(1, 1, 1);
        testRegSighting.save();
        testRegSighting.delete();
        assertEquals(null,RegSighting.find(testRegSighting.getId()));

    }
    @Test
    public void deleteAll() {
        RegSighting testRegSighting = new RegSighting(1, 1, 1);
        RegSighting otherRegSightings=new RegSighting(1, 1,1);
        testRegSighting.save();
        otherRegSightings.save();
        RegSighting.deleteAll();

        assertEquals(0,RegSighting.all().size());

    }



}