package models;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RegAnimalTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void RegAnimal_instantiatesCorrectly_true() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals(true, testRegAnimal instanceof RegAnimal);
    }
    @Test
    public void RegAnimal_instantiatesWithName_String() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("Monkey", testRegAnimal.getName());
    }
    @Test
    public void RegAnimal_instantiatesWithType_StringAndIsTheAssignedConstant() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("safe", testRegAnimal.getType());
    }
    @Test
    public void RegAnimal_instantiatesWithHealth_StringAndIsTheAssignedvalue() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("", testRegAnimal.getHealth());
    }
    @Test
    public void RegAnimal_instantiatesWithAge_StringAndIsTheAssignedvalue() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("", testRegAnimal.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Animal_throwsExceptionIfNameTypeCanNotBeEmpty(){
        RegAnimal testRegAnimal = new RegAnimal("","safe");

        if(testRegAnimal.name.equals("")||testRegAnimal.type.equals("")||testRegAnimal.name.equals(null)||testRegAnimal.type.equals(null)){
            testRegAnimal.save();
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void Animal_throwsExceptionIfTypeIsEndangeredCanNotBeEmpty(){
        RegAnimal testRegAnimal = new RegAnimal("","safe");
        if(testRegAnimal.type.equals("endangered")){

        }

        if(testRegAnimal.name.equals("")||testRegAnimal.type.equals("")||testRegAnimal.name.equals(null)||testRegAnimal.type.equals(null)){
            testRegAnimal.save();
        }
    }


    @Test
    public void save_assignsIdToObject() {
        RegAnimal testRegAnimal=new RegAnimal("Monkey","");
        testRegAnimal.save();
        RegAnimal savedRegAnimal = RegAnimal.all().get(0);
        assertEquals(testRegAnimal.getId(), savedRegAnimal.getId());
    }


    @Test
    public void save_assignsNameToObjectAndCanNotBeEmpty() {
        RegAnimal testRegAnimal=new RegAnimal("Monkey","");
        testRegAnimal.save();
        RegAnimal savedRegAnimal = RegAnimal.all().get(0);
        assertEquals(testRegAnimal.getName(), savedRegAnimal.getName());
    }

    @Test
    public void allInstancesAreSaved() {
        RegAnimal testRegAnimal=new RegAnimal("Monkey","safe");
        testRegAnimal.save();
        assertFalse(RegAnimal.all().get(0).equals(testRegAnimal));
    }


    @Test
    public void ensureEntryIsUpdatedCorrectlyAndNonCanBeEmpty() {
        RegAnimal testRegAnimal=new RegAnimal("Monkey","");
        RegAnimal otherRegAnimal=testRegAnimal;
        testRegAnimal.save();
        try {
            testRegAnimal.update(testRegAnimal.getId(),"endangered","ill","newborn");
            RegAnimal updatedAnimal=  RegAnimal.find(testRegAnimal.getId());
            assertEquals(updatedAnimal.getId(),otherRegAnimal.getId());
            assertNotEquals(updatedAnimal.getHealth(),otherRegAnimal.getHealth());
        }catch (IllegalArgumentException e){

        }
    }

    @Test
    public void findByIdReturnsCorrectInfo() {
        RegAnimal testRegAnimal=new RegAnimal("Monkey","");
        testRegAnimal.save();
        RegAnimal foundRegAnimal= RegAnimal.find(testRegAnimal.getId());
        assertFalse(foundRegAnimal.equals(testRegAnimal));
    }

    @Test
    public void deleteById() {
        RegAnimal testAnimal=new RegAnimal("Monkey","");
        testAnimal.save();
        testAnimal.delete();
        assertEquals(null,RegAnimal.find(testAnimal.getId()));
    }

    @Test
    public void deleteAllEntries() {
        RegAnimal testAnimal=new RegAnimal("Monkey","");
        RegAnimal otherAnimal=new RegAnimal("Giraffe","");
        testAnimal.save();
        otherAnimal.save();
        RegAnimal.deleteAll();
        List<RegAnimal> animals=RegAnimal.all();
        assertEquals(0,animals.size());
    }


}