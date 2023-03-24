package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

// when starting mileage is 10. 
public class Group3_2TeslaModelZTesters{
    @Test
    public void TeslaModelZTest1() {
        // creating object and getting model + num
        TeslaModelZ tesla = new TeslaModelZ(10, 5);
        assertEquals(5, tesla.getModelNum(), 0, "Model Number should be five.");
        assertEquals("Z5", tesla.getModel(), "getModel should evaluate to Z5.");
        assertEquals(String.format("Tesla Z5 (10.0 mi)"), tesla.toString(), "toString should evaluate to Tesla Z5 (10 mi)");
        

        // testing drive
        assertTrue(tesla.canDrive(0), "canDrive should be true");
        tesla.drive(0); 
        assertEquals(10, tesla.getMileage(), .1, "Mileage should be 10 after the first drive.");

        assertTrue(tesla.canDrive(5), "canDrive should be true.");
        tesla.drive(5); 
        assertEquals(15, tesla.getMileage(), .1, "Mileage should be 15 after second drive.");

        assertThrows(IllegalArgumentException.class, () -> { 
            tesla.drive(-1); 
        },    "Driving mileage cannot be negative.");

        // current mileage: 15. available fuel:335 (340-5)
        assertTrue(tesla.canDrive(335), "canDrive should be true");
        tesla.drive(335); 
        assertEquals(350, tesla.getMileage(), .1, "Mileage should be 350 after third drive.");

        // current mileage: 350
        assertFalse(tesla.canDrive(1), "Driving 1 should fail."); 
        
        // getRemainingRange
        assertEquals(0, tesla.getRemainingRange(), .1, "Remaining Range should be equal to 0.");

        // getMaxRange
        assertEquals(340, tesla.getMaxRange(), .1, "getMaxRange should return 340. Does not change anything."); 

        // recharge
        tesla.recharge(); 
        assertEquals(340, tesla.getRemainingRange(), .1, "getRemainingRange should return 340."); 

        // canDrive
        assertThrows(IllegalArgumentException.class, () -> {
            tesla.canDrive(-1);
        },    "Cannot drive negative miles."); 

        assertTrue(tesla.canDrive(0), "canDrive should return true."); 
        assertTrue(tesla.canDrive(340), "canDrive should return true.");
        assertFalse(tesla.canDrive(341), "canDrive should return false."); 

        // toString
        assertEquals("Tesla Z5 (350.0 mi)", tesla.toString(), "toString should evaluate to <Tesla Z5> (<15.0> mi)"); 

        // getMileage
        assertEquals(350, tesla.getMileage(), .1, "Mileage should be 350."); 

        // getMake
        assertEquals("Tesla", tesla.getMake(), "getMake should return 'Tesla'.");

        // getModel 
        assertEquals("Z5", tesla.getModel(), "getModel should return Z5.");
        
        // more of canDrive
        assertTrue(tesla.canDrive(340), "canDrive should return true."); 

        assertTrue(tesla.canDrive(0), "canDrive should return true.");
        
        // roadTrip 
        List<Double> milesEachDay = new ArrayList<>();
        milesEachDay.add(10.0); 
        milesEachDay.add(200.0);
        milesEachDay.add(100.0);
        milesEachDay.add(400.0);

        assertEquals(3, tesla.roadTrip(milesEachDay));

        milesEachDay.clear();
        milesEachDay.add(-10.0); 
        milesEachDay.add(200.0);
        milesEachDay.add(100.0);
        milesEachDay.add(400.0);

        assertThrows(IllegalArgumentException.class, () -> {
            tesla.roadTrip(milesEachDay);
        }, "Car cannot drive negative miles");
        
        milesEachDay.clear();
        milesEachDay.add(1.0); 
        milesEachDay.add(2.0);
        milesEachDay.add(3.0);
        milesEachDay.add(4.0);

        assertEquals(tesla.roadTrip(milesEachDay), 4);

        // self-driving
        tesla.driveAutonomously(340); 
        assertEquals(690, tesla.getMileage(), .1, "getMileage should return 690 after running driveAutonomously for 340 miles.");

        assertThrows(IllegalArgumentException.class, () -> {
            tesla.driveAutonomously(-1); 
           },       "Cannot drive negative miles.");
        
        tesla.driveAutonomously(0); 
        assertEquals(690, tesla.getMileage(), .1, "getMileage should return 690 after running driveAutonomously for 0 miles.");
    }


    @Test
    public void TeslaModelZTest2() {
        TeslaModelZ tesla2 = new TeslaModelZ(5); 
        assertEquals(0, tesla2.getMileage(), .1, "getMileage when no mileage is passed in should be equal to zero."); 
    }


}