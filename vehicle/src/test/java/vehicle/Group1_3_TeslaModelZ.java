package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class Group1_3_TeslaModelZ {
    @Test
    public void TeslaConstructor_Test1() {
        TeslaModelZ t = new TeslaModelZ(70);

        assertEquals(0, t.getMileage(), 0.01, "No arg constructor initial mileage should be 0");
        assertEquals("Z70", t.getModel(), "getModel wrong");
        assertEquals("Tesla", t.getMake(), "getMake wrong");
        assertEquals(340, t.getRemainingRange(), 0.01, "Initial remaining range should be 340");
        assertEquals(340, t.getMaxRange(), 0.01, "maxRange incorrect");
        assertEquals(70, t.getModelNum(), "modelNum incorrect");
        assertEquals("Tesla Z70 (0.0 mi)", t.toString(), "toString wrong");
    }

    @Test
    public void TeslaConstructor_Test2() {
        TeslaModelZ t = new TeslaModelZ(10, 70);

        assertThrows(IllegalArgumentException.class, () -> {
            t.drive(-1);
        }, "Driving mileage cannot be negative.");

        assertEquals(10, t.getMileage(), .01, "starting mileage incorrect");

        assertTrue(t.canDrive(30), "canDrive should be true");
        t.drive(30);
        assertEquals(40, t.getMileage(), .1, "Mileage should be 40 after first drive.");

        t.drive(200);
        assertEquals(240, t.getMileage(), .1, "Mileage should be 240 after second drive.");

        assertFalse(t.canDrive(111), "Driving 111 should fail.");
        assertTrue(t.canDrive(110), "Driving 110 should succeed.");

        t.drive(110);
        assertEquals(350, t.getMileage(), .1, "Mileage should be 350 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            t.drive(5);
        }, "Driving beyond empty should fail.");

        t.recharge();
        assertEquals(340, t.getRemainingRange(), 0.01, "Recharge should allow vehicle to drive 340 miles");

        assertThrows(IllegalArgumentException.class, () -> {
            t.driveAutonomously(-1);
        }, "Miles to drive cannot be negative.");

        t.driveAutonomously(10);
        assertEquals(360, t.getMileage(), .01, "mileage should be 360 after autonomous driving");
        assertEquals(330, t.getRemainingRange(), .01, "remaining range after drive should be 330");

        t.driveAutonomously(340);
        assertEquals(690, t.getMileage(), .01, "mileage should be 690 after autonomous driving");
        assertEquals(0, t.getRemainingRange(), .01, "remainimg range after autonomously driving fully should be 0");

    }

}
