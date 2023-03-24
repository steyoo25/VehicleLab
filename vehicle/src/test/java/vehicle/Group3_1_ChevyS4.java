package vehicle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/*
 * canFly
 * fly
 */
public class Group3_1_ChevyS4 {
    @Test
    public void chevyCanFlyTest() {

        ChevroletBird bird = new ChevroletBird(0);

        assertTrue(bird.canFly(bird.getMileage()), "canFly should be false");
        assertThrows(IllegalArgumentException.class, () -> {
            bird.canFly(-1);
        }, "Flying mileage cannot be negative.");
    }

    @Test
    public void chevyFlyTest() {

        ChevroletBird bird = new ChevroletBird(0);

        bird.fly(bird.getMileage());
        assertThrows(IllegalArgumentException.class, () -> {
            bird.fly(-1);
        }, "Flying mileage cannot be negative.");
        assertThrows(IllegalArgumentException.class, () -> {
            bird.fly(bird.getRemainingRange() + 1);
        }, "Flying mileage cannot be over the remaining range of the car.");
    }
}
