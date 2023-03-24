package vehicle;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
 * Need to test Car methods:
 * canDrive
 * toString
 * getMileage
 * getMake
 * getModel
 * roadTrip
 */

public class Group3_1_ChevyS1 {
    @Test
    public void canDriveTest() {
        ChevroletBird bird = new ChevroletBird(0);
        double range = bird.getRemainingRange();

        assertTrue(bird.canDrive(range), "You should be able to drive this amount");
        assertFalse(bird.canDrive(range + 1), "You should not be able to drive this amount");
        assertThrows(IllegalArgumentException.class, () -> {bird.canDrive(-1);}, "Should have exception here");
    }

    @Test
    public void toStringTest() {
        ChevroletBird bird = new ChevroletBird(0);
        assertEquals("Chevrolet Bird (0.0 mi)", bird.toString(), "this is the toString test");
    }

    @Test
    public void getMileageTest() {
        ChevroletBird bird = new ChevroletBird(0);

        assertEquals(0, bird.getMileage(), "Your mileage should be 0");
    }

    @Test
    public void getMakeTest() {
        ChevroletBird bird = new ChevroletBird(0);

        assertEquals("Chevrolet", bird.getMake(), "Your make should be Chevrolet");
    }

    @Test
    public void getModelTest() {
        ChevroletBird bird = new ChevroletBird(0);

        assertEquals("Bird", bird.getModel(), "Your model should be Bird");

    }

    @Test
    public void roadTripTest() {
        ChevroletBird bird = new ChevroletBird(0);
        double range = bird.getRemainingRange();

        List<Double> roadtrip = new ArrayList<Double>();
        roadtrip.add(range);
        roadtrip.add(1.0);

        assertEquals(1, bird.roadTrip(roadtrip), "you drive full range 1st day, unable to drive second day");

        bird.recharge();
        List<Double> roadtrip2 = new ArrayList<Double>();
        roadtrip2.add(range);
        roadtrip2.add(-1.0);

        assertThrows(IllegalArgumentException.class, () -> {bird.roadTrip(roadtrip2);}, "Should have exception here");
    }
}
