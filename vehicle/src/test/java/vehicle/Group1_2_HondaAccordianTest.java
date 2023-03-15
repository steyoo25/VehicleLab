package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class Group1_2_HondaAccordianTest {

    @Test
    public void hondaTests() {
        assertThrows(IllegalArgumentException.class, () -> {
            HondaAccordian h = new HondaAccordian(-30.0, 2019);
        },
        "Constructor should not expect a negative mile");

        HondaAccordian h = new HondaAccordian(30.0, 2017);

        assertEquals(14.5, h.getFuelCapacity(), 0.01, "Fuel cap should be 14.5");

        assertThrows(IllegalArgumentException.class, () -> {
            h.refillTank(14.6);
        },
                "Cant refill more than 14.5");

        assertEquals(h.getMPG(), 33.2, 0.01, "mpg should be 33.2");

        h.drive(10);

        assertEquals("2017 Honda Accordian (40.0 mi)", h.toString(), "toString doesnt match");

        assertEquals(471.4, h.getRemainingRange(), 0.01, "should be 471.4");

        assertEquals(14.1987, h.getFuelLevel(), 0.01, "should be around 14.198");

        ArrayList<Double> arr = new ArrayList<Double>();
        arr.add(400.0);
        arr.add(70.0);
        arr.add(1.5);
        arr.add(1.0);
        assertEquals(2, h.roadTrip(arr), "should be 2");
    }
}
