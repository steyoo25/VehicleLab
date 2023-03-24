package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Group3_5_FordFrivolous_Tests {

    @Test
    public void fordConstructorTest1() {
        FordFrivolous ford = new FordFrivolous();
        assertEquals(0, ford.getMileage(), 0.1, "Default mileage should be zero.");
        assertEquals(20, ford.getFuelCapacity(),  0.1, "Initial fuel capacity not correct.");
        assertEquals(ford.getFuelCapacity(), ford.getFuelLevel(), 0.1, "The car should begin full.");
        assertEquals(23.6, ford.getMPG(), 0.1, "Initial mpg not correct.");
        assertEquals(ford.getFuelCapacity() * ford.getMPG(), ford.getRemainingRange(), 0.1,
                "Remaining range of car not correct at creation.");
        ford.refillTank();
        assertEquals(ford.getFuelCapacity(), ford.getFuelLevel(), "Fuel capacity not maximum.");
        assertEquals("Ford Frivolous (0.0 mi)", ford.toString(), "toString does not match");
        assertEquals("Ford", ford.getMake(), "Make does not match Ford");
        assertEquals("Frivolous", ford.getModel(), "Model does not match Frivolous");
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

        assertFalse(ford.canDrive(243), "Driving 243 should fail.");
        assertTrue(ford.canDrive(242), "Driving 242 should succeed.");

        ford.drive(242);
        assertEquals(472, ford.getMileage(), .1, "Mileage should be 472 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(5);
        }, "Driving beyond empty should fail.");
    }

    @Test
    public void fordConstructorTest3() {
        FordFrivolous ford = new FordFrivolous(500);
        assertEquals(500.0, ford.getMileage(), 0.1, "Mileage should be 500.0");
        assertEquals(20, ford.getFuelCapacity(),  0.1, "Initial fuel capacity not correct.");
        assertEquals(ford.getFuelCapacity(), ford.getFuelLevel(), 0.1, "The car should begin full.");
        assertEquals(23.6, ford.getMPG(), 0.1, "Initial mpg not correct.");
        assertEquals(ford.getFuelCapacity() * ford.getMPG(), ford.getRemainingRange(), 0.1,
                "Remaining range of car not correct at creation.");

        ford.refillTank();
        assertEquals(ford.getFuelCapacity(), ford.getFuelLevel(), "Fuel capacity not maximum.");
        assertEquals("Ford Frivolous (500.0 mi)", ford.toString(), "toString does not match");
    
        assertTrue(ford.canDrive(30), "canDrive should be true");
        ford.drive(30);
        assertEquals(530, ford.getMileage(), .1, "Mileage should be 530 after first drive.");

        ford.drive(200);
        assertEquals(730, ford.getMileage(), .1, "Mileage should be 230 after second drive.");

        assertEquals(ford.getFuelCapacity() * ford.getMPG() - 230, ford.getRemainingRange(), .1,
                "Remaining range of car not correct after driving twice.");

        assertFalse(ford.canDrive(243), "Driving 243 should fail.");
        assertTrue(ford.canDrive(242), "Driving 242 should succeed.");

        ford.drive(242);
        assertEquals(972, ford.getMileage(), .1, "Mileage should be 972 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.drive(5);
        }, "Driving beyond empty should fail.");
    }

    @Test
    public void fordConstructorTest4() {
        FordFrivolous ford = new FordFrivolous();
        assertThrows(IllegalArgumentException.class, () -> {
            ford.driveAutonomously(-1);
        }, "Driving beyond empty should fail.");

        ford.driveAutonomously(45);
        assertEquals(45, ford.getMileage(), .1, "Mileage should be 45 after first drive.");
        assertEquals(472-90, ford.getRemainingRange(), 0.1,"Remaining range should be subtracted by double the amount");
        ford.driveAutonomously(191);
        assertEquals(236, ford.getMileage(), .1, "Mileage should be 236.");
        assertEquals(0, ford.getRemainingRange(), 0.1,"Remaining range should be 0");

        ford.refillTank(10);
        assertEquals(236, ford.getRemainingRange(), 0.1,"Remaining range should be 472");
        ford.driveAutonomously(300);
        assertEquals(0, ford.getRemainingRange(), 0.1,"Remaining range should be 0");
        //fixed this literally a minute after my internet cut out
    }

    @Test
    public void fordConstructorTest5() {
        FordFrivolous ford = new FordFrivolous();
        assertThrows(IllegalArgumentException.class, () -> {
            ford.fly(-1);
        }, "Flying negative should fail.");

        ford.fly(0);
        assertEquals(0, ford.getMileage(), .1, "Mileage should not change when flying.");
        assertEquals(472, ford.getRemainingRange(), 0.1,"Remaining range should stay the same.");

        ford.fly(1);
        assertEquals(0, ford.getMileage(), .1, "Mileage should not change when flying.");
        assertEquals(472-3, ford.getRemainingRange(), 0.1,"Remaining range should be subtracted by triple to amount. 469.");

        assertFalse(ford.canFly(157), "Driving 157 should fail.");
        assertTrue(ford.canFly(156), "Driving 156 should succeed.");
        assertFalse(ford.canFly(300), "Driving 300 should fail.");


        ford.fly(156);
        assertEquals(0, ford.getMileage(), .1, "Mileage should be 0.");
        assertEquals(1, ford.getRemainingRange(), 0.1,"Remaining range should be 1");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.fly(5);
        }, "Flying beyond possible should fail.");

        assertThrows(IllegalArgumentException.class, () -> {
            ford.fly(1);
        }, "Flying beyond possible should fail.");
    }
}