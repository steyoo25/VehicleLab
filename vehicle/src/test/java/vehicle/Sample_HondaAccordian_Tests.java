package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Sample_HondaAccordian_Tests {

    @Test
    public void hondaConstructorTest1() {
        HondaAccordian honda = new HondaAccordian(2018);
        assertEquals(honda.getMileage(), 0, 0.1, "Default mileage should be zero.");
        assertEquals(honda.getFuelCapacity(), 14.5, 0.1, "Initial fuel capacity not correct.");
        assertEquals(honda.getFuelLevel(), honda.getFuelCapacity(), 0.1, "The car should begin full.");
        assertEquals(honda.getMPG(), 33.2, 0.1, "Initial mpg not correct.");
        assertEquals(honda.getRemainingRange(), honda.getFuelCapacity() * honda.getMPG(), 0.1,
                "Remaining range of car not correct at creation.");
        assertEquals(honda.toString(), "2018 Honda Accordian (0.0 mi)", "toString does not match");
    }

    @Test
    public void hondaConstructorTest2() {
        HondaAccordian honda = new HondaAccordian(2018);

        assertThrows(IllegalArgumentException.class, () -> {
            honda.drive(-1);
        }, "Driving mileage cannot be negative.");

       assertTrue(honda.canDrive(30), "canDrive should be true");
       honda.drive(30);
        assertEquals(honda.getMileage(), 30, .1, "Mileage should be 30 after first drive.");

        honda.drive(200);
        assertEquals(honda.getMileage(), 230, .1, "Mileage should be 230 after second drive.");

        assertEquals(honda.getRemainingRange(), honda.getFuelCapacity() * honda.getMPG() - 230, .1,
                "Remaining range of car not correct after driving twice.");

        assertFalse(honda.canDrive(252), "Driving 252 should fail.");
        assertTrue(honda.canDrive(251), "Driving 251 should succeed.");

        honda.drive(251);
        assertEquals(honda.getMileage(), 481, .1, "Mileage should be 481 after third drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            honda.drive(5);
        }, "Driving beyond empty should fail.");
    }
}
