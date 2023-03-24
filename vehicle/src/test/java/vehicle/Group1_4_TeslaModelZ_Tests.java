package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Group1_4_TeslaModelZ_Tests {

    @Test
    public void teslaModelZTest1() {
        TeslaModelZ tesla = new TeslaModelZ(300, 70);
        assertEquals(70, tesla.getModelNum(), "Model number should be 70.");
        assertEquals("Z70", tesla.getModel(), "The string should be \"Z70\".");
        assertEquals("Tesla Z70 (300.0 mi)", tesla.toString(), "toString() does not match.");
        assertEquals(300, tesla.getMileage(), 0.1, "Default mileage should be 300.");
        assertEquals(340, tesla.getRemainingRange(), 0.1, "The remaining range of the car should be 340.");
        assertEquals(340, tesla.getMaxRange(), 0.1, "The max range of the car should be 340.");
    }   

    @Test
    public void teslaModelZTest2() {
        TeslaModelZ tesla = new TeslaModelZ(60);
        assertEquals(60, tesla.getModelNum(), "Model number should be 60.");
        assertEquals("Z60", tesla.getModel(), "The string should be \"Z60\".");
        assertEquals("Tesla Z60 (0.0 mi)", tesla.toString(), "toString() does not match.");
        assertEquals(0, tesla.getMileage(), 0.1, "Default mileage should be 0.");
        assertEquals(340, tesla.getRemainingRange(), 0.1, "The remaining range of the car should be 340.");
        assertEquals(340, tesla.getMaxRange(), 0.1, "The max range of the car should be 340.");
    }

    @Test
    public void teslaModelZTest3() {
        TeslaModelZ z = new TeslaModelZ(60);

        assertThrows(IllegalArgumentException.class, () -> {
            z.drive(-1);
        }, "Driving mileage cannot be negative");

        z.drive(0);
        assertEquals(0, z.getMileage(),.1, "Mileage should still be 0 after first drive.");
        
        z.drive(20);
        assertEquals(20, z.getMileage(), .1, "Mileage should be 20 after second drive.");
       
        z.drive(319);
        assertEquals(339, z.getMileage(),  .1, "Mileage should be 339 after third drive.");

        assertEquals(z.getMaxRange() - 339, z.getRemainingRange(), .1, "Remaining range of car should be 1.");

        assertThrows(IllegalArgumentException.class, () -> {
            z.drive(10);
        }, "Driving beyond empty should fail.");

        z.drive(.9);

        z.recharge();
        assertEquals(340, z.getRemainingRange(), "The remaining range of the car should be 340.");

        assertThrows(IllegalArgumentException.class, () -> {
            z.drive(z.getRemainingRange() + 1);
        }, "Driving beyond range should fail.");

        z.drive(150);
        assertEquals(489.9, z.getMileage(), .01, "Mileage should now be 489.9 after first drive since recharging.");

        assertThrows(IllegalArgumentException.class, () -> {
            z.drive(z.getMaxRange());
        }, "Driving beyond empty should fail.");

        assertFalse(z.canDrive(191), "The car should not be able to drive 191 more miles.");
        assertTrue(z.canDrive(190), "The car should be able to drive 190 more miles.");
    }
}
