package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Group1_3_FordFrivolous {

    @Test
    public void fordConstructorTest1() {
        FordFrivolous ford = new FordFrivolous();
        assertEquals("Ford",ford.getMake(), "Make should be Ford");
        assertEquals("Frivolous",ford.getModel(), "Model should be Frivolous");
        assertEquals(0, ford.getMileage(), 0.1, "Default mileage should be zero.");
        assertEquals(20, ford.getFuelCapacity(),  0.1, "Initial fuel capacity not correct.");
        assertEquals(ford.getFuelCapacity(), ford.getFuelLevel(), 0.1, "The car should begin full.");
        assertEquals(23.6, ford.getMPG(), 0.1, "Initial mpg not correct.");
        assertEquals(ford.getFuelCapacity() * ford.getMPG(), ford.getRemainingRange(), 0.1,
                "Remaining range of car not correct at creation.");
        assertEquals("Ford Frivolous (0.0 mi)", ford.toString(), "toString does not match");
    }

    @Test
    public void fordConstructorTest2() {
        FordFrivolous ford = new FordFrivolous();

        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(-1);
        }, "Driving mileage cannot be negative.");

       assertTrue(ford.canDrive(30), "canDrive should be true");
       ford.drive(30);
        assertEquals(30, ford.getMileage(), .1, "Mileage should be 30 after first drive.");

        ford.drive(200);
        assertEquals(230, ford.getMileage(), .1, "Mileage should be 230 after second drive.");

        assertEquals(ford.getFuelCapacity() * ford.getMPG() - 230, ford.getRemainingRange(), .1,
                "Remaining range of car not correct after driving twice.");

        assertFalse(ford.canDrive(245), "Driving 245 should fail.");
        assertTrue(ford.canDrive(242), "Driving 242 should succeed.");

        ford.drive(242);
        assertEquals(472, ford.getMileage(), .1, "Mileage should be 472 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(5);
        }, "Driving beyond empty should fail.");
    }

    @Test
    public void fordConstructorTest3() {
        FordFrivolous ford = new FordFrivolous();
        assertThrows(IllegalArgumentException.class, () -> {
            ford.fly(-1);
        }, "Flying mileage cannot be negative.");

       assertTrue(ford.canFly(30), "canFly should be true");
       ford.fly(10);
        ford.fly(50); //472 - 180 = 292
        assertEquals(292, ford.getRemainingRange(), .1, "remaining range should be 292 after 2 flying sessions");

        assertFalse(ford.canFly(100), "Flying 100 should fail.");
        assertTrue(ford.canFly(97), "Driving 97 should succeed.");

        ford.fly(97);
        assertThrows(IllegalArgumentException.class, () -> {
            ford.fly(1);
        }, "Driving beyond empty should fail.");
        assertEquals(0, ford.getMileage(), .1, "mileage should remain at 0");
    }
    @Test
    public void fordConstructorTest4() {
        FordFrivolous ford = new FordFrivolous();

        assertThrows(IllegalArgumentException.class, () -> {
            ford.driveAutonomously(-1);
        }, "Auto driving mileage cannot be negative.");

        ford.driveAutonomously(30);
        assertEquals(30, ford.getMileage(), .1, "Mileage should be 60 after first auto drive.");

        ford.driveAutonomously(200);
        assertEquals(230, ford.getMileage(), .1, "Mileage should be 230 after second auto drive.");

        assertEquals(ford.getFuelCapacity() * ford.getMPG() - 460, ford.getRemainingRange(), .1,
                "Remaining range of car not correct after auto driving twice.");

        assertEquals(12, ford.getRemainingRange(), .1, "Remaining range should be 12 after second auto drive.");

        ford.driveAutonomously(6);
        assertEquals(236, ford.getMileage(), .1, "Mileage should be 236 after third auto drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(1);
        }, "Auto driving beyond empty should fail.");
    }
    @Test
    public void fordConstructorTest5() {
        FordFrivolous ford = new FordFrivolous(300);
        assertEquals("Ford Frivolous (300.0 mi)", ford.toString(), "toString does not match");
        assertEquals(300, ford.getMileage(), .1, "Mileage should be 300 after no driving.");
       ford.drive(30);
        assertEquals(330, ford.getMileage(), .1, "Mileage should be 330 after first drive.");

    }

}
