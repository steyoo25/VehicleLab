package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Group1_1HondaAccordianTester {
    
    @Test
    public void HondaConstructor_Test() {
        HondaAccordian h = new HondaAccordian(2018);
        assertEquals(0, h.getMileage(), 0.1, "Verify Mileage equals 0");
        assertEquals(14.5, h.getFuelCapacity(), 0.1, "Verify fuel capacity equals 14.5");
        assertEquals(14.5, h.getFuelLevel(), .01, "Verify Fuel Level matches fuel capacity");
        assertEquals(h.getFuelCapacity(), h.getFuelLevel(), 0.1, "The car should begin full.");
        assertEquals(33.2, h.getMPG(),  .1, "Verify mpg equals 33.2");
        assertEquals(481.4, h.getRemainingRange(), .1, "Verify remaining range matches fuel level * mpg");
        assertEquals("2018 Honda Accordian (0.0 mi)", h.toString(), "Verify toString matches '2018 Honda Accordian (0.0 mi)'");
        assertThrows(IllegalArgumentException.class, () -> {new HondaAccordian(-1, 2018);}, "Starting mileage cannot be negative.");
        HondaAccordian h2 = new HondaAccordian(10.5, 2018);
        assertEquals(10.5, h2.getMileage(), 0.01, "Verify mileage is 10.5");
        assertEquals("Honda", h2.getMake(), "Verify make of the car is Honda");
        assertEquals("Accordian", h2.getModel(), "Verify model of the car is Accordian");

    }

    @Test
    public void HondaDriving_test() {
        HondaAccordian honda = new HondaAccordian(2018);
        assertEquals( 33.2, honda.getMPG(),.1, "Verify 33.2 is returned.");
        assertEquals( 14.5,honda.getFuelCapacity(), .1, "Verify 14.5 is returned.");
        assertThrows(IllegalArgumentException.class, () -> {honda.drive(-1);}, "Verify IllegalArgumentException is thrown (Negative Distance).");
        // assertThrows(null, () -> {honda.drive(0);}, "Verify no exception is thrown though the car doesn't move.");
        honda.drive(230);
        assertEquals( 230, honda.getMileage(),.01, "Verify mileage is now 230");
        assertEquals( 251.4,honda.getRemainingRange(), .1, "Verify it is now the full tank range (481.4) minus 230= 251.4");
        assertEquals( 7.57, honda.getFuelLevel(), .1, "Verify it is now the full tank (14.5) minus (230 miles / 33.2 mpg) = 7.57");
        assertFalse(honda.canDrive(252), "Driving 252 should fail.");
        assertTrue(honda.canDrive(251), "Driving 251 should succeed.");
        honda.drive(251);
        assertEquals( 481, honda.getMileage(), .1, "Verify mileage is now 481");
        assertThrows(IllegalArgumentException.class, () -> {honda.drive(.5);}, "Verify an IllegalArgumentException is thrown (Driving passed empty).");
        //assertThrows(null, () -> {honda.drive(.4);}, "Verify no exception is thrown");
        assertThrows(IllegalArgumentException.class, () -> {honda.refillTank(-1);}, "Verify an IllegalArgumentException is thrown (Negative refill value).");
        honda.refillTank(7.5);
        assertEquals( 7.5, honda.getFuelLevel(), .1, "Verify fuel level is now 7.5");
        honda.refillTank(3);
        assertEquals(10.5, honda.getFuelLevel(), .1, "Verify fuel level is now 10.5");
        honda.refillTank();
        assertEquals(14.5, honda.getFuelLevel(), .1, "Verify fuel level is now 14.5");
        assertThrows(IllegalArgumentException.class, () -> {honda.refillTank(1);}, "Verify an IllegalArgumentException is thrown (overfill).");
        assertThrows(IllegalArgumentException.class, () -> {honda.refillTank(.5);}, "Verify an IllegalArgumentException is thrown (overfill).");
    }
}