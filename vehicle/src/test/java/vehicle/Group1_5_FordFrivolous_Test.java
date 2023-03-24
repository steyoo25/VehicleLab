package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertThrows;



import org.junit.jupiter.api.Test;

public class Group1_5_FordFrivolous_Test {
    
    @Test
    public void fordConstructor_Tests() {
        FordFrivolous f = new FordFrivolous();

//        assertEquals(0, f.getMPG(), 0.1, "Initial MPG should equal 0");
        assertEquals(20, 0.1,  f.getFuelCapacity(), "Max capacity shoulkd be 20 gallons");
        assertTrue(f.canFly(30), "Should return 30");
        assertThrows(IllegalArgumentException.class, () -> {new FordFrivolous(-1);}, "Cannot have negative");
        assertEquals(0, f.getMileage(), "Should return 0");
        assertEquals(23.6, f.getMPG(), "Should be returning 23.6");
        assertEquals(20, f.getFuelCapacity(), "Should be returning 20");
        assertEquals(20, f.getFuelLevel(), "Should be returning 20");
        assertFalse(f.canDrive(475), "Cannot drive that much");
        assertThrows(IllegalArgumentException.class, () -> {f.canDrive(-1);}, "Cannot drive negative");
        //assertTrue(f.drive(0), "Should return true/work");
        assertEquals("Ford Frivolous (0.0 mi)", f.toString(), "Should be returning Ford Frivolous");
 //       assertEquals(123.6, f.getMileage(), "Should be returning 123.6");
        assertEquals(20, f.getFuelLevel(), "Should be returning 20");
        //assertEquals(20, f.refillTank(), "Should be returning 20");
        assertEquals("Ford", f.getMake(), "Shoulr be retuning Ford");
        assertEquals("Frivolous", f.getModel(), "Should be returning Frivolous");
        assertFalse(f.canFly(158), "Should be false");
        assertTrue(f.canFly(157), "Should be returning true");
        //assertFalse(f.driveAutonomously(-1), "Should return fals ebecause negative");
    }
}