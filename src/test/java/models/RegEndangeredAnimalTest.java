package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegEndangeredAnimalTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void EndangeredAnimal_instantiatesCorrectly_true() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals(true, testRegEndangeredAnimal instanceof RegEndangeredAnimal);
    }

    @Test
    public void EndangeredAnimall_instantiatesWithName_String() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals("Monkey", testRegEndangeredAnimal.getName());
    }

    @Test
    public void EndangeredAnimall_instantiatesWithType_String() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals("endangered", testRegEndangeredAnimal.getType());
    }
    @Test
    public void EndangeredAnimall_instantiatesWithHealth_String() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey","endangered","healthy","newborn");
        assertEquals("healthy", testRegEndangeredAnimal.getHealth());
    }
    @Test
    public void EndangeredAnimall_instantiatesWithAge_String() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey", "endangered", "healthy", "newborn");
        assertEquals("newborn", testRegEndangeredAnimal.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void EndangeredAnimal_throwsExceptionIfNameTypeHealthAgeCanNotBeEmpty(){
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey", "endangered", "healthy", "");

        if(testRegEndangeredAnimal.name.equals("")||testRegEndangeredAnimal.type.equals("")||testRegEndangeredAnimal.health.equals("")||testRegEndangeredAnimal.age.equals("")||testRegEndangeredAnimal.name.equals(null)||testRegEndangeredAnimal.type.equals(null)){
            testRegEndangeredAnimal.save();
        }
    }

    @Test
    public void save_assignsIdToObject() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey", "endangered", "healthy", "newborn");
        testRegEndangeredAnimal.save();
        RegAnimal savedEndangeredAnimal = RegAnimal.all().get(0);
        assertEquals(testRegEndangeredAnimal.getId(), savedEndangeredAnimal.getId());
    }

    @Test
    public void save_assignsNameToObjectAndCanNotBeEmpty() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey", "endangered", "healthy", "newborn");
        testRegEndangeredAnimal.save();
        RegAnimal savedEndangeredAnimal = RegAnimal.all().get(0);
        assertEquals(testRegEndangeredAnimal.getName(), savedEndangeredAnimal.getName());
    }
    @Test
    public void save_assignsHealthToObjectAndCanNotBeEmpty() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey", "endangered", "healthy", "newborn");
        testRegEndangeredAnimal.save();
        RegAnimal savedEndangeredAnimal = RegAnimal.all().get(0);
        assertEquals(testRegEndangeredAnimal.getHealth(), savedEndangeredAnimal.getHealth());
    }
    @Test
    public void save_assignsAgeToObjectAndCanNotBeEmpty() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey", "endangered", "healthy", "newborn");
        testRegEndangeredAnimal.save();
        RegAnimal savedEndangeredAnimal = RegAnimal.all().get(0);
        assertEquals(testRegEndangeredAnimal.getAge(), savedEndangeredAnimal.getAge());
    }

    @Test
    public void deleteById() {
        RegEndangeredAnimal testRegEndangeredAnimal = new RegEndangeredAnimal("Monkey", "endangered", "healthy", "newborn");
        testRegEndangeredAnimal.save();
        testRegEndangeredAnimal.delete();
        assertEquals(null,RegAnimal.find(testRegEndangeredAnimal.getId()));
    }


}