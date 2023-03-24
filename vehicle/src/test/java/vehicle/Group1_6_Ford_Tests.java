package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Group1_6_Ford_Tests {

    @Test
    public void FordConstructorTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FordFrivolous(-1);
        }, "Creating a Ford with a negative starting mileage should throw error!");

        FordFrivolous f1 = new FordFrivolous(10);

        assertEquals("Frivolous", f1.getModel(), "Model should be Frivolous");
        assertEquals("Ford Frivolous (10.0 mi)", f1.toString(), "To String method of F1");
        assertEquals(10.0, f1.getMileage(), 1, "Mileage should be 10");
        assertEquals("Ford", f1.getMake(), "Make of the car should be Ford");

        FordFrivolous f2 = new FordFrivolous(0);

        assertEquals("Frivolous", f2.getModel(), "Model should be Frivolous");
        assertEquals("Ford Frivolous (0.0 mi)", f2.toString(), "To String method of F1");
        assertEquals(0.0, f2.getMileage(), 1, "Mileage should be 10");
        assertEquals("Ford", f2.getMake(), "Make of the car should be Ford");
    }
    
    @Test
    public void FordDrivingTest() {
        FordFrivolous f1 = new FordFrivolous(0);

        assertThrows(IllegalArgumentException.class, () -> {
            f1.canDrive(-1);
        }, "Can drive cannot be negative!");

        assertThrows(IllegalArgumentException.class, () -> {
            f1.drive(-2);
        }, "Cannot drive a negative amount!");

        f1.drive(0);
        f1.drive(27);
        f1.drive(240);

        assertEquals(205, f1.getRemainingRange(), .1, "Remaining range should be 205 after 3rd drive");

        assertFalse(f1.canDrive(206), "Can drive returns false if miles is above remaining range"); 
        assertTrue(f1.canDrive(205), "Should be able to drive remaining range");       
        assertFalse(f1.canDrive(205.1), "Can drive returns false if miles is above remaining range"); 
        assertFalse(f1.canDrive(300), "Can drive returns false if miles is above remaining range"); 

        f1.drive(205);

        assertFalse(f1.canDrive(.1), "Can drive returns false if miles is above remaining range"); 

        assertThrows(IllegalArgumentException.class, () -> {
            f1.drive(.1);
        }, "Cannot drive an amount over the remaining range!");

        assertEquals(0, f1.getRemainingRange(), .1, "Remaining range should be 0.");
    }

    @Test
    public void FordRoadTripTest() {
        FordFrivolous f1 = new FordFrivolous();

        assertThrows(IllegalArgumentException.class, () -> {
            f1.roadTrip(Arrays.asList(-3.0,4.0,7.0,2.0,0.0));
        }, "Illegal argument exception should be thrown.");

        assertEquals(6, f1.roadTrip(Arrays.asList(30.0,40.0,60.0,70.0,80.0, 90.0)), .1, "Road trip should return 6");

        f1.refillTank();

        assertEquals(6, f1.roadTrip(Arrays.asList(40.0,70.0,80.0,20.0,90.0,100.0,1000.0,30.0)), .1, "Road trip should return 6");

        assertEquals(0, f1.roadTrip(new ArrayList<Double>()), .1, "Road trip should return 0 with empty array");

        assertEquals(0, f1.roadTrip(Arrays.asList(473.0)), .1, "Road trip should return 0");

        assertTrue(f1.canDrive(72), "Should be able to go remaining range");

        assertFalse(f1.canDrive(73), "Cannot go above remaining range");

    }

    @Test
    public void FordGasPoweredCarTest() {
        FordFrivolous f1 = new FordFrivolous();

        assertEquals(23.6, f1.getMPG(), 0.1, "MPG for Ford Frivilous should be 23.6");
        assertEquals(20, f1.getFuelLevel(), 0.1, "Ford should start on a full tank");
        assertEquals(20, f1.getFuelCapacity(), 0.1, "Ford should have a 20 gallon tank");

        f1.drive(23.6);
        assertEquals(19, f1.getFuelLevel(), 0.1, "Ford fuel level should be 19 after first drive");
        f1.refillTank();
        assertEquals(20, f1.getFuelLevel(), 0.1, "Fuel level should be max after refill tank with no arguments");

        f1.drive(236);
        assertEquals(10, f1.getFuelLevel(), 0.1, "Ford fuel level should be 10 after second drive");

        assertThrows(IllegalArgumentException.class, () -> {
            f1.refillTank(-1);
        }, "Refill tank should throw error with negative argument");

        assertThrows(IllegalArgumentException.class, () -> {
            f1.refillTank(30);
        }, "Refill tank should throw error with an amount exceding the amount needed for a full tank");

        assertThrows(IllegalArgumentException.class, () -> {
            f1.refillTank(10.1);
        }, "Refill tank should throw error with an amount exceding the amount needed for a full tank");

        f1.refillTank(5);

        assertEquals(15, f1.getFuelLevel(), 0.1, "Ford fuel level should be 15 after refilling 5 gallons");
    }

    @Test
    public void FordFlyingTest() {
        FordFrivolous f1 = new FordFrivolous();

        assertThrows(IllegalArgumentException.class, () -> {
            f1.canFly(-1);
        }, "Illegal argument exception should be thrown.");

        f1.drive(1);

        assertFalse(f1.canFly(158), "Cannot fly more then remaining range");

        assertTrue(f1.canFly(157), "It can fly remaining range");

        assertThrows(IllegalArgumentException.class, () -> {
            f1.fly(-1);
        }, "Cannot fly a negative amount");

        assertThrows(IllegalArgumentException.class, () -> {
            f1.fly(158);
        }, "Cannot fly a negative amount");

        f1.fly(157);

        assertEquals(1, f1.getMileage(), .1, "You only increase mileage for driving");

        assertEquals(0, f1.getRemainingRange(), .1, "Remaining range should be 0.");
    }

    @Test
    public void FordSelfDrivingTest() {
        FordFrivolous f = new FordFrivolous();
        assertThrows(IllegalArgumentException.class, () -> {
            f.canFly(-1);
        }, "canFly mileage cannot be negative.");

        f.drive(1);
        assertFalse(f.canFly(158), "Should not be able to fly 158 miles, as 158 * 3 = 474 > 471 (the remaining range).");
        assertTrue(f.canFly(157), "Should be able to drive 157 miles.");

        assertThrows(IllegalArgumentException.class, () -> {
            f.fly(-1);
        }, "Cannot fly a negative mileage.");
        assertThrows(IllegalArgumentException.class, () -> {
            f.fly(158);
        }, "Cannot fly 158 miles, as 158 * 3 = 474 > 471 (the remaining range).");

        f.fly(157);
        assertEquals(1, f.getMileage(), 0.1, "Mileage should be 1 after flying 157 miles since flying does not affect the odometer.");
        assertEquals(0, f.getRemainingRange(), 0.01, "Should not be able to drive any further, as 1 + (157 * 3) = 472 (the range on a full tank).");
    }
}
