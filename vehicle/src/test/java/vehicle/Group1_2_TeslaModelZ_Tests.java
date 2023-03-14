package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class Group1_2_TeslaModelZ_Tests {

    @Test
    public void teslaConstructorTest1() {
        TeslaModelZ tesla = new TeslaModelZ(15, 33);
        assertEquals(tesla.getModelNum(), 33, 0.1, "The model number should be 33.");
        assertEquals(tesla.getModel(), "Z33", "getModel should be Z33");
        
        tesla.drive(10);

        assertThrows(IllegalArgumentException.class, () -> {
            tesla.drive(340);
        }, "340 miles should be out of range");

        assertEquals(tesla.getMileage(), 25, "mileage not correct");
        
        assertEquals(tesla.toString(), "Tesla Z33 (25.0 mi)", "toString does not match");
    }

    @Test
    public void teslaConstructorTest2() {
        TeslaModelZ tesla = new TeslaModelZ(33);
        tesla.drive(10);

        // 6
        assertFalse(tesla.canDrive(340));
        
        // 7
        assertThrows(IllegalArgumentException.class, () -> {
            tesla.drive(-10);
        }, "Driving mileage cannt be negative"); 
        
        // 8
        assertEquals(tesla.getMaxRange(), 340, 0.01, "getMaxRange() should return 340");

        // 9
        tesla.recharge();
        assertEquals(tesla.getRemainingRange(), tesla.getMaxRange(), 0.01, "recharge() should recharge the current char back to the max charge");

        // 10
        assertEquals(tesla.getMake(), "Tesla", "getMake() should return \"Tesla\"");
    }
}
