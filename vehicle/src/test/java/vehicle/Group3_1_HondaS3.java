package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


/*
 * toString
 * both constructors
 */

public class Group3_1_HondaS3 {

    @Test
    public void testConstructor() {
        HondaAccordian honda = new HondaAccordian(34, 2018);
        assertEquals(34, honda.getMileage(), "Bad constructor");

    //     assertThrows(IllegalArgumentException.class, () -> {
    //     new HondaAccordian(-1);
    //     }, "Starting Mileage cannot be negative.");
    }

    @Test
    public void testToString() {
        HondaAccordian honda = new HondaAccordian(2018);
        String toString = honda.toString();
        assertEquals("2018 Honda Accordian (0.0 mi)", toString, "Bad toString()");
    }

}
 