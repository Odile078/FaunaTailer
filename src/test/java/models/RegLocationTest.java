package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegLocationTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void location_instantiatesCorrectly_true() {
        RegLocation testLocation = new RegLocation("ZoneA");
        assertEquals(true, testLocation instanceof RegLocation);
    }

    @Test
    public void getName_locationInstantiatesWithName() {
        RegLocation testLocation = new RegLocation("ZoneA");
        assertEquals("ZoneA", testLocation.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void EndangeredAnimal_throwsExceptionIfNameTypeHealthAgeCanNotBeEmpty(){
        RegLocation testLocation = new RegLocation("");

        if(testLocation.name.equals("")){
            testLocation.save();
        }
    }

    @Test
    public void save_insertsObjectIntoDatabase_locationAndNamecanNotBeEmpty() {
        RegLocation testLocation = new RegLocation("ZoneA");
        testLocation.save();
        assertTrue(RegLocation.all().get(0).equals(testLocation));
    }

    @Test
    public void all_returnsAllInstancesOfLocation_true() {
        RegLocation firstLocation = new RegLocation("ZoneA");
        firstLocation.save();
        RegLocation secondLocation = new RegLocation("ZoneB");
        secondLocation.save();
        assertEquals(true, RegLocation.all().get(0).equals(firstLocation));
        assertEquals(true, RegLocation.all().get(1).equals(secondLocation));
    }

    @Test
    public void delete_deletesLocation_true() {
        RegLocation testLocation = new RegLocation("ZoneA");
        testLocation.save();
        testLocation.delete();
        assertEquals(0, RegLocation.all().size());
    }

    @Test
    public void save_assignsIdToObject() {
        RegLocation testLocation = new RegLocation("ZoneA");
        testLocation.save();
        RegLocation savedLocation = RegLocation.all().get(0);
        assertEquals(testLocation.getId(), savedLocation.getId());
    }

    @Test
    public void find_returnsPersonWithSameId_secondPerson() {
        RegLocation firstLocation = new RegLocation("ZoneA");
        firstLocation.save();
        RegLocation secondLocation = new RegLocation("ZoneB");
        secondLocation.save();
        assertEquals(RegLocation.find(secondLocation.getId()), secondLocation);
    }



    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        RegLocation firstLocation = new RegLocation("ZoneA");
        RegLocation anotherLocation = new RegLocation("ZoneA");
        assertTrue(firstLocation.equals(anotherLocation));
    }



}