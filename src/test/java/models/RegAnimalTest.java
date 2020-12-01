package models;

import org.junit.Rule;
import org.junit.Test;

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
    public void Animal_instantiatesWithName_String() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("Monkey", testRegAnimal.getName());
    }
    @Test
    public void Animal_instantiatesWithType_StringAndIsTheAssignedConstant() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("safe", testRegAnimal.getType());
    }
    @Test
    public void Animal_instantiatesWithHealth_StringAndIsTheAssignedvalue() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("", testRegAnimal.getHealth());
    }
    @Test
    public void Animal_instantiatesWithAge_StringAndIsTheAssignedvalue() {
        RegAnimal testRegAnimal = new RegAnimal("Monkey","safe");
        assertEquals("", testRegAnimal.getAge());
    }

}