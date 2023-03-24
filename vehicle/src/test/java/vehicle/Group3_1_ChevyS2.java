package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/*
 * getRemainingRange
 * getMaxRange
 * recharge
 */

public class Group3_1_ChevyS2 {
    @Test
    public void getRemainingRangeTest() {
        ChevroletBird bird = new ChevroletBird(0);
        bird.drive(bird.getRemainingRange());
        // getRemainingRange
        //should this be 250 or 0?
        assertEquals(0, bird.getRemainingRange(), "Should have 0 miles of range");
        //
    }

    @Test
    public void getMaxRangeTest() {
        ChevroletBird bird = new ChevroletBird(0);
        bird.drive(bird.getRemainingRange());
        // getMaxRange
        assertEquals(250, bird.getMaxRange(), "Should be 250 miles");
    }

    @Test
    public void rechargeTest() {
        ChevroletBird bird = new ChevroletBird(0);
        bird.drive(bird.getRemainingRange());
        
        // recharge
        bird.recharge();
        assertEquals(250, bird.getMaxRange(), "Should be 250 miles");
    }
    
}
