package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/*
 * Need to test methods:
 * getMPG
 * getFuelCapacity
 * getFuelLevel
 * refillTank (both no param and when passed double)
 * drive
 * getRemainingRange
 */

public class Group3_1_HondaS2 {
    @Test
    public void driveTest() {
        HondaAccordian honda = new HondaAccordian(70, 2018);
        double range = honda.getRemainingRange();
        honda.drive(range);
        assertEquals(range + 70, honda.getMileage(),  "Mileage should be a full drive after first drive.");

        assertThrows(IllegalArgumentException.class, () -> {
            honda.drive(5);
        }, "Driving beyond empty should fail.");
    }

    @Test
    public void getMPGTest() {
        HondaAccordian honda = new HondaAccordian(70, 2018);

        assertEquals(33.2, honda.getMPG(), "Should be 33.2 MPG.");
    }

    @Test
    public void getFuelLevelTest() {
        HondaAccordian honda = new HondaAccordian(2018);

        assertEquals(14.5, honda.getFuelLevel(), "Should be 14.5 Gallons.");
        honda.drive(honda.getRemainingRange());

        assertEquals(0, honda.getFuelLevel(), "Should be 0 Gallons.");
    }

    @Test
    public void refillTankTest() {
        HondaAccordian honda = new HondaAccordian(2018);
        honda.drive(honda.getRemainingRange());

        assertEquals(14.5, honda.getFuelCapacity(), "Should be 14.5 Gallons");
    }

    @Test
    public void getRemainingRangeTest() {
        HondaAccordian honda = new HondaAccordian(2018);
        honda.drive(honda.getRemainingRange());

        assertEquals(0, honda.getRemainingRange(), "Should be 0 miles of range left.");
    }
}
