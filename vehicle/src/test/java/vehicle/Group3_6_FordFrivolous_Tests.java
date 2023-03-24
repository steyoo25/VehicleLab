package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.*;

import org.junit.jupiter.api.Test;

public class Group3_6_FordFrivolous_Tests {
    @Test
    public void fordConstructorTest1() {
        FordFrivolous ford = new FordFrivolous();
        assertEquals(20.0, ford.getFuelCapacity(),  0.1, "Initial fuel capacity not correct.");
        assertEquals(20.0, ford.getFuelLevel(),  0.1, "The car should begin full.");
        assertEquals(23.6, ford.getMPG(), 0.1, "Initial mpg not correct.");
        assertEquals(0.0, ford.getMileage(), 0.1, "Default mileage should be zero.");
        assertEquals(ford.getFuelCapacity() * ford.getMPG(), ford.getRemainingRange(), 0.1,
                "Remaining range of car not correct at creation.");
        assertEquals("Ford", ford.getMake(), "make does not match");
        assertEquals("Frivolous", ford.getModel(), "model does not match");
        assertEquals("Ford Frivolous (0.0 mi)", ford.toString(), "toString does not match");
    }

    @Test
    public void fordMileageConstructorTest2() {
        FordFrivolous ford = new FordFrivolous(30.0);
        // assertEquals(23.6, ford.getFuelCapacity(),  0.1, "Initial fuel capacity not correct.");
        // assertEquals(ford.getFuelCapacity(), ford.getFuelLevel(), 0.1, "The car should begin full.");
        // assertEquals(20.0, ford.getMPG(), 0.1, "Initial mpg not correct.");
        assertEquals(30.0, ford.getMileage(), 0.1, "Default mileage should be zero.");
        assertEquals(ford.getFuelCapacity() * ford.getMPG(), ford.getRemainingRange(), 0.1,
                "Remaining range of car not correct at creation.");
        assertEquals("Ford", ford.getMake(), "make does not match");
        assertEquals("Frivolous", ford.getModel(), "model does not match");
        assertEquals("Ford Frivolous (30.0 mi)", ford.toString(), "toString does not match");
    }

    @Test
    public void fordDrivingTest3() {
        FordFrivolous ford = new FordFrivolous(49.7);

        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(-0.11);
        }, "Driving mileage cannot be negative.");
        
        ford.drive(0);
        assertEquals(49.7, ford.getMileage(), .1, "Mileage should not have changed.");

        ford.drive(30);
        assertEquals(79.7, ford.getMileage(), .1, "Mileage should be 30 after first drive.");

        ford.drive(200);
        assertEquals(279.7, ford.getMileage(), .1, "Mileage should be 230 after second drive.");
        assertEquals(472.0 - 230.0 , ford.getRemainingRange(), 0.1, "Remaining range should be 472.0-230.0=242.0.");

        assertFalse(ford.canDrive(242.1), "Should not be able to drive more than remaining range (242).");
        assertTrue(ford.canDrive(241), "Should be able to drive remaining range (242).");

        ford.drive(241);
        assertEquals(1, ford.getRemainingRange(), .1, "Mileage should be 1 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(1.5);
        }, "Cannot drive more than remaining range on gas level.");

        ford.drive(1);
        assertEquals(0, ford.getRemainingRange(), .1, "Remaining range should be 0 after fourth drive.");

        ford.refillTank();
        assertEquals(472.0, ford.getRemainingRange(), .1, "Remaining range should restart again as 20 * 23.6 after refill.");

        //assertFalse(ford.canFly(-1.0), "Should not be able to fly a negative distance (-110.0).");
        assertTrue(ford.canFly(0.0), "Should be able to fly no distance (0.0).");
        assertTrue(ford.canFly(1.0), "Should be able to fly a positive distance (100.0).");

        ford.fly(110.0);
        // assertEquals(23.6 - 16.5, ford.getFuelLevel(), "Fuel level is incorrect after flying.");
        assertEquals(521.7, ford.getMileage(), "Mileage is incorrect after flying. It should not have changed.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.refillTank(16.6);
        }, "Cannot refill to more than capacity.");

        ford.refillTank(0);
        // assertEquals(23.6-16.5, ford.getFuelLevel(), .1, "Fuel level should not have changed.");

        // ford.refillTank(16.5);
 //       assertEquals(23.6, ford.getFuelLevel(), .1, "Should be a valid amount of gas to add. Gas level should be back to full.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.driveAutonomously(-0.11);
        }, "Driving mileage cannot be negative.");
        ford.driveAutonomously(0);
       //  assertEquals(472.0, ford.getRemainingRange(), 0.1, "Remaining range should be 420 after autonomous drive 1 of distance 0.");
        ford.driveAutonomously(200);
      //  assertEquals(272.0, ford.getRemainingRange(), 0.1, "Remaining range should be 220 after autonomous drive 2 of distance 200.0");

        // List<Double> milesEachDay = Arrays.asList(5.7, 159.0, 201.3);
        // assertEquals(2, ford.roadTrip(milesEachDay), .1, "Should be able to drive first 2 days only.");

        // milesEachDay = Arrays.asList(53.0, 54.0, 0.2, 0.15, 0.01);
        // assertEquals(3, ford.roadTrip(milesEachDay), .1, "Should be able to drive first 3 days.");
    }
    
}
