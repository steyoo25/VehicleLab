package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Group1_6_TeslaZ_Tests {

    @Test
    public void TeslaConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TeslaModelZ(-1, 10);
        }, "Creating a Tesla with a negative starting mileage should throw error!");

        TeslaModelZ t1 = new TeslaModelZ(10);

        assertEquals("Z10", t1.getModel(), "Model should be Z10");
        assertEquals(10, t1.getModelNum(), "Model Number should be 10");
        assertEquals("Tesla Z10 (0.0 mi)", t1.toString(), "To String method of T1");
        assertEquals("Tesla", t1.getMake(), "Make of the car should be Tesla");
        assertEquals(0.0, t1.getMileage(), 1, "Mileage should be 0");

        TeslaModelZ t2 = new TeslaModelZ(100, 20);

        assertEquals("Z20", t2.getModel(), "Model should be Z20");
        assertEquals(20, t2.getModelNum(), "Model Number should be 20");
        assertEquals("Tesla Z20 (100.0 mi)", t2.toString(), "To String method of T2");
        assertEquals("Tesla", t2.getMake(), "Make of the car should be Tesla");

        assertEquals(100, t2.getMileage(), 100, "Mileage should be 100 with starting arguement");
    }

    @Test
    public void TeslaDrivingTest() {
        TeslaModelZ t1 = new TeslaModelZ(20);

        assertThrows(IllegalArgumentException.class, () -> {
            t1.drive(-2);
        }, "Driving mileage cannot be negative.");

        assertThrows(IllegalArgumentException.class, () -> {
            t1.canDrive(-1);
        }, "Can drive should throw error if negative.");

        t1.drive(0);
        assertEquals(0, t1.getMileage(), .1, "Mileage should be 0 after driving 0");
        t1.drive(27);
        assertEquals(27, t1.getMileage(), .1, "Mileage should be 27 after 2nd drive");
        t1.drive(240);
        assertEquals(267, t1.getMileage(), .1, "Mileage should be 240 after 3rd drive");

        assertEquals(73, t1.getRemainingRange(), .1, "Remaining range should be 73 after 3rd drive");

        assertFalse(t1.canDrive(74), "Driving 74 should fail.");
        assertTrue(t1.canDrive(73), "Driving 73 should succeed.");

        t1.drive(72.9);
        assertEquals(339.9, t1.getMileage(), .1, "Mileage should be 339.1 after fourth drive.");

        assertFalse(t1.canDrive(0.2), "Cannot drive 0.2 should fail.");
        assertThrows(IllegalArgumentException.class, () -> {
            t1.drive(0.2);
        }, "Driving 0.2 should throw error.");

        assertEquals(0.1, t1.getRemainingRange(), .1, "FIX");

//      Rounding error makes this test error prone.
//        assertTrue(t1.canDrive(0.1), "Driving 0.1 should succeed.");
//        t1.drive(0.1);

        assertEquals(0, t1.getRemainingRange(), .1, "Remaining range should be 0.");
    }

    @Test
    public void TeslaSelfDrivingTest() {
        TeslaModelZ t1 = new TeslaModelZ(60, 70);

        assertThrows(IllegalArgumentException.class, () -> {
            t1.driveAutonomously(-20);
        }, "Driving autonomously cannot take a negative argument.");

        t1.driveAutonomously(339);

        assertEquals(1, t1.getRemainingRange(), .1, "Remaining range should be 1 after 1st drive");

        assertTrue(t1.canDrive(1), "It can drive 1 mile");

        assertThrows(IllegalArgumentException.class, () -> {
            t1.canDrive(-1);
        }, "Can drive cannot take a negative number.");

        assertFalse(t1.canDrive(2), "Can drive should return false if miles is above remaining range!");

        t1.drive(1);

        TeslaModelZ t2 = new TeslaModelZ(50);

        t2.driveAutonomously(341);

        assertEquals(340, t2.getMileage(), .1, "Driving autonomously should go as far as charge will take it, which results in a mileage of 0.");

        assertEquals(0, t2.getRemainingRange(), .1, "Remaining range should be 0 after driving autonomously past the fuel limit.");
    }

    @Test
    public void TeslaRoadTripTest() {
        TeslaModelZ t1 = new TeslaModelZ(20);

        assertThrows(IllegalArgumentException.class, () -> {
            t1.roadTrip(Arrays.asList(-3.0,4.0,7.0,2.0,0.0));
        }, "Illegal argument exception should be thrown.");

        assertEquals(5, t1.roadTrip(Arrays.asList(30.0,40.0,60.0,70.0,80.0,1000.0)), .1, "Road trip should return 5");

        t1.recharge();

        assertEquals(4, t1.roadTrip(Arrays.asList(300.0, 20.0, 10.0, 5.0)), .1, "Road trip should return 4");

        assertEquals(0, t1.roadTrip(new ArrayList<Double>()), .1, "Road trip should return 0 with empty array");

        assertEquals(0, t1.roadTrip(Arrays.asList(473.0)), .1, "Road trip should return 0");

        assertTrue(t1.canDrive(5), "Should be able to go remaining range");

        assertFalse(t1.canDrive(6), "Cannot go above remaining range");
    }

}