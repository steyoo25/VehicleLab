package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Group1_1ChevroletBirdTester {

    @Test
    public void BirdConstructor_Test() {
        ChevroletBird c = new ChevroletBird();
        assertEquals( 250, c.getMaxRange(), .01, "Verify max range is 250");
        assertEquals(c.getMaxRange(), c.getRemainingRange(), "Remaining range should equal max range when first created.");
        assertEquals(0, c.getMileage(),.01, "Verify mileage is 0");
        assertEquals("Chevrolet", c.getMake(), "Verify model is chevrolet");
        assertEquals("Bird", c.getModel(), "Verify make is Bird");
        assertEquals("Chevrolet Bird (0.0 mi)", c.toString(), "Verify toString");
    }

    @Test
    public void BirdDriving_Test() {
        ChevroletBird bird = new ChevroletBird(100);
        assertEquals(100, bird.getMileage(), "Verify mileage is 100");
        assertFalse(bird.checkWingsExtended(), "Verify wings are retracted since the car didn't fly yet");
        assertThrows(IllegalArgumentException.class, () -> {bird.drive(-1);}, "Verify IllegalArgumentException is thrown (Negative Distance).");
        //assertThrows(null, () -> {bird.drive(0);}, "Verify no exception is thrown though the car doesn't move.");
        assertThrows(IllegalArgumentException.class, () -> {bird.drive(251);}, "Verify IllegalArgumentException is thrown (Driving passed empty).");
        bird.drive(200);
        assertEquals(300, bird.getMileage(), .1, "Verify mileage is 300");
        assertEquals(50, bird.getRemainingRange(), .1, "Verify remaining range is 50 (250 - 200 = 50)");
        assertFalse(bird.canDrive(51), "Driving 51 should fail.");
        assertTrue(bird.canDrive(50), "Driving 50 should succeed.");
        bird.recharge();
        assertEquals(bird.getMaxRange(), bird.getRemainingRange(), .1, "Verify remaining range is 250 or max range");
        assertThrows(IllegalArgumentException.class, () -> {bird.fly(-1);}, "Verify IllegalArgumentException is thrown (Negative Distance).");
        //assertThrows(null, () -> {bird.fly(0);}, "Verify no exception is thrown though the car doesn't move.");
        assertThrows(IllegalArgumentException.class, () -> {bird.fly(251);}, "Verify IllegalArgumentException is thrown (flying passed empty).");
        bird.fly(200);
        assertTrue(bird.checkWingsExtended(), "Verify wings are extended since the car flew");
        assertEquals(300, bird.getMileage(), .1, "Verify mileage is still 300");
        assertEquals(50, bird.getRemainingRange(), .1, "Verify remaining range is 50 (250 - 200 = 50)");
        assertFalse(bird.canFly(51), "Flying 51 should fail.");
        assertTrue(bird.canFly(50), "flying 50 should succeed.");
        assertThrows(IllegalArgumentException.class, () -> {bird.canFly(-1);}, "Verify IllegalArgumentException is thrown (Negative Distance).");
        bird.drive(10);
        assertFalse(bird.checkWingsExtended(), "Verify wings are retracted since the car is driving");
        assertEquals(40, bird.getRemainingRange(), .1, "Verify remaining range is 40");
        

    }
}
