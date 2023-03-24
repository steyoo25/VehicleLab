package vehicle;

// created by Jacob Berger and Julia Thompson

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Group3_3_FordFrivolous_Tests {

    @Test
    public void fordConstructorTest1() {
        FordFrivolous ford = new FordFrivolous();
        assertEquals(ford.getMileage(), 0,0.1,"Default mileage should be zero.");
        assertEquals(ford.getFuelCapacity(), 20, 0.1, "Initial fuel capacity not correct.");
        assertEquals(ford.getFuelLevel(), ford.getFuelCapacity(), 0.1, "The car should begin full.");
        assertEquals(ford.getMPG(), 23.6, 0.1, "Initial mpg not correct.");
        assertEquals(ford.getRemainingRange(), ford.getFuelCapacity() * ford.getMPG(), 0.1,
                "Remaining range of car not correct at creation.");
        assertEquals(ford.toString(), "Ford Frivolous (0.0 mi)", "toString does not match");
    }

    @Test
    public void fordConstructorTest2() {
        FordFrivolous ford = new FordFrivolous(10);
        assertEquals(ford.getMileage(), 10,0.1,"Default mileage should be ten.");
        assertEquals(ford.getFuelCapacity(), 20, 0.1, "Initial fuel capacity not correct.");
        assertEquals(ford.getFuelLevel(), ford.getFuelCapacity(), 0.1, "The car should begin full.");
        assertEquals(ford.getMPG(), 23.6, 0.1, "Initial mpg not correct.");
        assertEquals(ford.getRemainingRange(), ford.getFuelCapacity() * ford.getMPG(), 0.1,
                "Remaining range of car not correct at creation.");
        
        assertThrows(IllegalArgumentException.class, () -> {
                FordFrivolous ford2 = new FordFrivolous(-1);
        }, "Driving mileage cannot be negative.");
    }

    @Test
    public void fordDrivingTest1() {
        FordFrivolous ford = new FordFrivolous();
        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(-1);
        }, "Driving mileage cannot be negative.");
        ford.drive(0);
        assertEquals(ford.getMileage(), 0,0.1,"Should not move when driving 0 miles.");
        ford.drive(30);
        assertEquals(ford.getMileage(), 30, .1, "Mileage should be 30 after first drive.");
        ford.drive(200);
        assertEquals(ford.getMileage(), 230, .1, "Mileage should be 230 after second drive.");
        assertEquals(ford.getRemainingRange(), 242,0.1,"There should be 242 miles of gass in the car after second drive.");
        assertEquals(ford.canDrive(243), false,"Driving 243 should fail.");
        assertEquals(ford.canDrive(242), true,"Driving 242 should succeed.");
        ford.drive(242);
        assertEquals(ford.getMileage(), 472,0.1,"Mileage should be 472 after third drive.");
        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(-1);
        }, "Driving beyond empty should fail.");
        ford.refillTank();
        assertEquals(ford.getFuelLevel(), ford.getFuelCapacity(),0.1,"Tank should refill.");
        assertThrows(IllegalArgumentException.class, () -> {
            ford.refillTank(10);
        }, "Cannot overfill tank.");
        assertThrows(IllegalArgumentException.class, () -> {
            ford.refillTank(-1);
        }, "Cannot refill negative gas.");
        assertEquals(ford.getFuelLevel(), ford.getFuelCapacity(),"Fuel level should match fuel capacity.");
        assertThrows(IllegalArgumentException.class, () -> {
            ford.driveAutonomously(-1);
        }, "Cannot refill negative gas.");
        ford.driveAutonomously(248);
        assertEquals(ford.getMileage(),708,0.1,"Mileage should be 708 after driveAutonomously.");
        ford.refillTank();
        assertThrows(IllegalArgumentException.class, () -> {
            ford.fly(158);
        }, "Flying 158 should fail.");
        assertThrows(IllegalArgumentException.class, () -> {
            ford.fly(-1);
        }, "Flying -1 should fail.");
        ford.fly(157);
        assertEquals(ford.getMileage(),708,0.1,"Mileage should be 865 after driveAutonomously.");
    }
}
