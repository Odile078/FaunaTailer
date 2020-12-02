package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegRangerTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void ranger_instantiatesCorrectly_true() {
        RegRanger testRegRanger = new RegRanger("Annita","R1","+0784455678");
        assertEquals(true, testRegRanger instanceof RegRanger);
    }
    @Test
    public void getName_rangerInstantiatesWithName() {
        RegRanger testRegRanger = new RegRanger("Annita","R1","+0784455678");
        assertEquals("Annita", testRegRanger.getName());
    }
    @Test
    public void getbadge_rangerInstantiatesWithbadge() {
        RegRanger testRegRanger = new RegRanger("Annita","R1","+0784455678");
        assertEquals("R1", testRegRanger.getBadge_id());
    }
    @Test
    public void getphone_rangerInstantiatesWithphone() {
        RegRanger testRegRanger = new RegRanger("Annita", "R1", "+0784455678");
        assertEquals("+0784455678", testRegRanger.getPhone());
    }

    @Test(expected = IllegalArgumentException.class)
    public void EndangeredAnimal_throwsExceptionIfNameTypeHealthAgeCanNotBeEmpty(){
        RegRanger testRegRanger = new RegRanger("Annita", "", "+0784455678");

        if(testRegRanger.name.equals("")||testRegRanger.badge_id.equals("")||testRegRanger.phone.equals("")){
            testRegRanger.save();
        }
    }

    @Test
    public void save_insertsObjectIntoDatabase_locationAndNamecanNotBeEmpty() {
        RegRanger testRegRanger = new RegRanger("Annita", "R2", "+0784455678");
        testRegRanger.save();
        assertTrue(RegRanger.all().get(0).equals(testRegRanger));
    }

    @Test
    public void all_returnsAllInstancesOfRanger_true() {
        RegRanger firstRegRanger = new RegRanger("Annita", "R1", "+0784455678");
        firstRegRanger.save();
        RegRanger secondRegRanger = new RegRanger("Ange","R2","+0784458678");
        secondRegRanger.save();
        assertEquals(true, RegRanger.all().get(0).equals(firstRegRanger));
        assertEquals(true, RegRanger.all().get(1).equals(secondRegRanger));
    }

    @Test
    public void delete_deletesLocation_true() {
        RegRanger testRegRanger = new RegRanger("Annita", "R1", "+0784455678");
        testRegRanger.save();
        testRegRanger.delete();
        assertEquals(0, RegRanger.all().size());
    }
    @Test
    public void save_assignsIdToObject() {
        RegRanger testRegRanger = new RegRanger("Annita", "R1", "+0784455678");
        testRegRanger.save();
        RegRanger savedRanger = RegRanger.all().get(0);
        assertEquals(testRegRanger.getId(), savedRanger.getId());
    }

    @Test
    public void find_returnsPersonWithSameId_secondRanger() {
        RegRanger firstRegRanger = new RegRanger("Annita", "R1", "+0784455678");
        firstRegRanger.save();
        RegRanger secondRegRanger = new RegRanger("Anne","R2","+0784455878");
        secondRegRanger.save();
        assertEquals(RegRanger.find(secondRegRanger.getId()), secondRegRanger);
    }

    @Test
    public void equals_returnsTrueIfNameAreSame_true() {
        RegRanger firstRegRanger = new RegRanger("Annita", "R1", "+0784455678");
        RegRanger anotherRegRanger = new RegRanger("Annita","R1","+0784455678");
        assertTrue(firstRegRanger.equals(anotherRegRanger));
    }


}