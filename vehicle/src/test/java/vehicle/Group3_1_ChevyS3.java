package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/*
 * Both constructors
 * checkWingExtended
 * drive
 */
public class Group3_1_ChevyS3 {
    @Test
    public void chevyConstructorTest() {
        ChevroletBird chevy = new ChevroletBird(34);
        assertEquals(34, chevy.getMileage(), "Bad constructor");

        assertThrows(IllegalArgumentException.class, () -> {
        new ChevroletBird(-1);
        }, "Starting Mileage cannot be negative.");
    }

    @Test
    public void chevyCheckWingExtendedTest() {
        ChevroletBird bird = new ChevroletBird(0);

        assertFalse(bird.checkWingsExtended(), "checkWingsExtended should be false");
    }

    @Test
    public void chevyDriveTest() {
        ChevroletBird bird = new ChevroletBird(0);

        assertFalse(bird.checkWingsExtended(), "checkWingsExtended should be false");
        // copy and paste honda accordian drive method

    }

}
