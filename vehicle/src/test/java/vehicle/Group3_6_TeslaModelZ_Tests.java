package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Group3_6_TeslaModelZ_Tests {

    @Test
    public void teslaConstructorTest1() {
        TeslaModelZ tesla = new TeslaModelZ(70);
        assertEquals(0, tesla.getMileage(), 0.1, "Default mileage should be zero.");
        assertEquals(340, tesla.getMaxRange(),  0.1, "Max range is incorrect.");
        assertEquals(tesla.getMaxRange(), tesla.getRemainingRange(), 0.1, "The car should begin at full charge.");
        assertTrue(tesla.getMake().equals("Tesla"), "Car's make should be \"Tesla\".");
        // assertTrue(tesla.getModel().equals("Z"), "Car's model should be \"Z\".");
        assertTrue(tesla.toString().equals("Tesla Z70 (0.0 mi)"), "Incorrect toString.");
    }

    @Test
    public void teslaConstructorTest2() {
        TeslaModelZ tesla = new TeslaModelZ(30.0, 70);
        assertEquals(30.0, tesla.getMileage(), 0.1, "Default mileage should be 30.0.");
        assertEquals(340, tesla.getMaxRange(),  0.1, "Max range is incorrect.");
        assertEquals(tesla.getMaxRange(), tesla.getRemainingRange(), 0.1, "The car should begin at full charge.");
        assertTrue(tesla.getMake().equals("Tesla"), "Car's make should be \"Tesla\".");
//        assertTrue(tesla.getModel().equals("Z"), "Car's model should be \"Z\".");
        assertTrue(tesla.toString().equals("Tesla Z70 (30.0 mi)"), "Incorrect toString.");
    }

    @Test
    public void teslaDrivingTest() {
        TeslaModelZ tesla = new TeslaModelZ(70);
        assertThrows(IllegalArgumentException.class, () -> {
            tesla.drive(-1);
        }, "Driving mileage cannot be negative.");
        
        tesla.drive(0);
        assertEquals(0, tesla.getMileage(), .1, "Mileage should not have changed.");

        tesla.drive(30);
        assertEquals(30, tesla.getMileage(), .1, "Mileage should be 30 after first drive.");

        tesla.drive(200);
        assertEquals(230, tesla.getMileage(), .1, "Mileage should be 230 after second drive.");

        assertEquals(110, tesla.getRemainingRange(), 0.1, "Remaining range should be 340-230=110.");

        assertFalse(tesla.canDrive(111), "Should not be able to drive more than remaining range (110).");
        assertTrue(tesla.canDrive(110), "Should be able to drive remaining range (110).");

        tesla.drive(109);
        assertEquals(1, tesla.getRemainingRange(), .1, "Mileage should be 1 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            tesla.drive(1.5);
        }, "Cannot drive more than remaining range on charge.");

        tesla.drive(1);
        assertEquals(0, tesla.getRemainingRange(), .1, "Remaining range should be 0 after fourth drive.");

        tesla.recharge();
        assertEquals(340, tesla.getRemainingRange(), 0.1, "Remaining range should be 340 after recharge.");

        assertThrows(IllegalArgumentException.class, () -> {
            tesla.driveAutonomously(-1);
        }, "Cannot drive negative distances.");

        tesla.driveAutonomously(200);
        assertEquals(140, tesla.getRemainingRange(), 0.1, "Remaining range should be 140 after autonomous drive 1.");

        tesla.driveAutonomously(200);
        assertEquals(0, tesla.getRemainingRange(), 0.1, "Remaining range should be 0 after autonomous drive 2.");
    
        tesla.recharge();

        List<Double> milesEachDay = Arrays.asList(5.7, 159.0, 201.3);
        assertEquals(2, tesla.roadTrip(milesEachDay), .1, "Should be able to drive first 2 days only.");

        milesEachDay = Arrays.asList(53.0, 54.0, 40.2, 30.15, 0.01);
        assertEquals(3, tesla.roadTrip(milesEachDay), .1, "Should be able to drive first 3 days.");
    }
}
