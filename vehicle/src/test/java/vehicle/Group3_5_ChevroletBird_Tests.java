package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class Group3_5_ChevroletBird_Tests {
    @Test
    public void birdNoMileageConstructor() {
        ChevroletBird bird = new ChevroletBird();
        assertEquals(0, bird.getMileage(), 0.1, "Default mileage should be zero.");
        assertEquals(250, bird.getRemainingRange(),  0.1, "Initial charge not correct.");
        assertEquals(bird.getMaxRange(), bird.getRemainingRange(), 0.1, "The car should begin full.");
        assertEquals("Chevrolet Bird (0.0 mi)", bird.toString(), "toString does not match");
    }

    @Test
    public void birdMileageConstructor() {
        ChevroletBird bird = new ChevroletBird(500);
        assertEquals(500, bird.getMileage(), 0.1, "Default mileage should be 500.");
        bird.drive(0);
        assertEquals(500, bird.getMileage(), 0.1, "Mileage should not have changed.");
        assertEquals(250, bird.getRemainingRange(),  0.1, "Initial charge should be 250.");
        assertEquals("Chevrolet", bird.getMake(), "Should return 'Chevrolet' as the make.");
        assertEquals("Bird", bird.getModel(), "Should return 'Bird' as the model.");
        assertEquals("Chevrolet Bird (500.0 mi)", bird.toString(), "toString does not match");
        assertFalse(bird.checkWingsExtended(), "Should begin as closed.");
    }

    @Test
    public void birdDriving() {
        ChevroletBird bird = new ChevroletBird();
        assertThrows(IllegalArgumentException.class, () -> {
            bird.drive(-1);
        }, "Driving mileage cannot be negative.");

        bird.drive(0);
        assertTrue(bird.canDrive(0), "Should not throw any errors.");
        assertEquals(0, bird.getMileage(), .1, "Mileage should not change.");
        assertEquals(bird.getMaxRange(), bird.getRemainingRange(), .1, "Range should be max.");

        
        bird.drive(30);
        assertEquals(30, bird.getMileage(), .1, "Mileage should be 30 after first drive.");

        bird.drive(200);
        assertEquals(230, bird.getMileage(), .1, "Mileage should be 230 after second drive.");

        assertEquals(250 - 230, bird.getRemainingRange(), .1,
                "Remaining range of car not correct after driving twice.");

        assertFalse(bird.canDrive(21), "Driving 21 should fail.");
        assertTrue(bird.canDrive(19), "Driving 19 should succeed.");

        bird.drive(19);
        assertEquals(249, bird.getMileage(), .1, "Mileage should be 249 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            bird.drive(2);
        }, "Driving beyond empty should fail.");

        bird.drive(1);
        assertEquals(0, bird.getRemainingRange(), 0.1, "Range should be now empty.");
    }

    @Test
    public void birdWingsExtended(){
        ChevroletBird bird = new ChevroletBird();
        assertFalse(bird.checkWingsExtended(), "Wings should start as closed.");

        bird.drive(1);
        assertFalse(bird.checkWingsExtended(), "Wings should stay closed to drive.");

        bird.fly(1);
        assertTrue(bird.checkWingsExtended(), "Wings should extend to fly.");

        bird.drive(1);
        assertFalse(bird.checkWingsExtended(), "Wings should close again to drive.");
    }

    @Test
    public void birdFlying() {
        ChevroletBird bird = new ChevroletBird();
        assertThrows(IllegalArgumentException.class, () -> {
            bird.fly(-1);
        }, "Flying cannot be negative.");

        bird.fly(0);
        assertTrue(bird.canFly(0), "Should not throw any errors.");
        assertEquals(0, bird.getMileage(), .1, "Mileage should not change.");
        assertEquals(bird.getMaxRange(), bird.getRemainingRange(), .1, "Range should be max.");

        
        bird.fly(1);
        assertEquals(0, bird.getMileage(), .1, "Mileage should not change.");
        assertEquals(249, bird.getRemainingRange(), .1, "Range should be 249.");

        assertFalse(bird.canFly(250), "Flying should fail.");
        assertTrue(bird.canFly(249), "Flying should succeed.");

        bird.fly(248);
        assertEquals(0, bird.getMileage(), .1, "Mileage should still be 0.");

        assertThrows(IllegalArgumentException.class, () -> {
            bird.fly(2);
        }, "Flying beyond empty should fail.");

        bird.fly(1);
        assertEquals(0, bird.getRemainingRange(), 0.1, "Range should be now empty.");
    }
}
