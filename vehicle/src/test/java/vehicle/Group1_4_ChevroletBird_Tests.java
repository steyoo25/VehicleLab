package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Group1_4_ChevroletBird_Tests {

    @Test
    public void ChevroletConstructorTest1() {
        ChevroletBird chevy = new ChevroletBird(100);
        assertEquals(100, chevy.getMileage(), 0.1, "Default mileage should be 100.");
        assertFalse(chevy.checkWingsExtended(), "Wings should be not be extended.");
        assertEquals("Chevrolet Bird (100.0 mi)", chevy.toString(), "toString() does not match.");
        assertEquals(250, chevy.getRemainingRange(), 0.1, "The remaining range of the car should be 250.");
        assertEquals(250, chevy.getMaxRange(), 0.1, "The max range of the car should be 250.");
    }

    @Test
    public void ChevroletConstructorTest2() {
        ChevroletBird chevy = new ChevroletBird();
        assertFalse(chevy.checkWingsExtended(), "Wings should not be extended.");
        assertEquals(0, chevy.getMileage(), 0.1, "Default mileage should be 0.");
        assertEquals("Chevrolet Bird (0.0 mi)", chevy.toString(), "toString() does not match.");
        assertEquals(250, chevy.getRemainingRange(), 0.1, "The remaining range of the car should be 250.");
        assertEquals(250, chevy.getMaxRange(), 0.1, "The max range of the car should be 250.");
    }

    @Test
    public void ChevroletConstructorTest3() {
        ChevroletBird chevy = new ChevroletBird(100);

        assertFalse(chevy.checkWingsExtended(), "Wings should not be extended.");

        assertThrows(IllegalArgumentException.class, () -> {
            chevy.drive(-1);
        }, "Driving mileage cannot be negative");

        chevy.drive(0);
        assertEquals(100, chevy.getMileage(),.1, "Mileage should be still be 100 and no exception has been thrown.");

        chevy.drive(50);        
        assertEquals(150, chevy.getMileage(), .1, "Mileage should now be 50");

        chevy.drive(199);
        assertEquals(349, chevy.getMileage(), .1, "Mileage should now be 349");

        assertEquals(chevy.getMaxRange() - 249, chevy.getRemainingRange(), .1, "Remaining range of car should be 1.");

        assertThrows(IllegalArgumentException.class, () -> {
            chevy.drive(10);
        }, "You cannot drive pass empty.");

        chevy.drive(.9);
    
        chevy.recharge();
        assertEquals(250, chevy.getRemainingRange(), "The remaining range of the car should be 250.");

        assertThrows(IllegalArgumentException.class, () -> {
            chevy.drive(chevy.getRemainingRange() + 1);
        }, "Driving beyond range should fail.");

        chevy.drive(150);
        assertEquals(499.9, chevy.getMileage(), .1, "Mileage should now be 499.9");

        assertThrows(IllegalArgumentException.class, () -> {
            chevy.drive(chevy.getMaxRange());
        }, "Driving beyond range should fail.");

        assertFalse(chevy.canDrive(101), "The car should not be able to drive 101 more miles.");
        assertTrue(chevy.canDrive(100), "The car should be able to drive 100 more miles.");
    }
}