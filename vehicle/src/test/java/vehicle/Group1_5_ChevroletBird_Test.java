package vehicle;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Group1_5_ChevroletBird_Test {
    
    @Test
    public void chevyConstructor_Tests() {
        ChevroletBird c = new ChevroletBird();

        // (want, test/compare want, 0.1, message)

        assertFalse(c.checkWingsExtended(), "Wings should not be extended");
        assertThrows(IllegalArgumentException.class, () -> {new ChevroletBird(-1);}, "Cannot have a negative starting mileage");
//        assertEquals(true, new ChevroletBird(100), "Car should create");
        assertEquals(0, c.getMileage(), "Should return 100");
        assertEquals(250, c.getRemainingRange(), "Should return 250");
        assertFalse(c.checkWingsExtended(), "Should return False");
        assertFalse(c.canDrive(251), "Should return false");
        assertThrows(IllegalArgumentException.class, () -> {c.canDrive(-1);}, "Cannot drive negative miles");
        assertThrows(IllegalArgumentException.class, () -> {c.drive(-1);}, "Cannot drive negatuve miles");
        assertEquals(0, c.getMileage(), "Should return 0");
        assertEquals("Chevrolet Bird (0.0 mi)", c.toString(), "Correct toString is ChevroletBird(50)");
        assertEquals(250, c.getMaxRange(), "Should be returning 250");
//        assertEquals(200, c.getMaxRange(), "Should be returning 200");
        assertEquals("Bird", c.getModel(), "Should be returning Bird");
        assertEquals("Chevrolet", c.getMake(), "Should be returning, Chevrolet");
        assertThrows(IllegalArgumentException.class, () -> {c.drive(-1);}, "Cannot drive anything miles");
        assertTrue(c.canFly(250), "Should br returning true");
        assertFalse(c.canFly(251), "Cannot fly that much");
        assertThrows(IllegalArgumentException.class, () -> {c.fly(251);}, "Cannot drive that much");
        assertFalse(c.checkWingsExtended(), "Should be returning true");
        // drive should make wings equal false
        assertFalse(c.checkWingsExtended(), "Should be false because driving cannot have wings out");
       // assertEquals(51, c.getMileage(), "Should return 51 after drive");
       // assertEquals(0, c.getRemainingRange(), "Should be returning 0");


        
        



    }
}