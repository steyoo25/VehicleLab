package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Group3_4_Tests {

    @Test
    public void chevroletBirdSetup() {
        ChevroletBird Chevvy = new ChevroletBird();
        assertEquals(0, Chevvy.getMileage(), 0.1, "Verify mileage is 0..");
        assertEquals(false, Chevvy.checkWingsExtended(), "Verify wings are not extended at creation.");
        assertEquals(250, Chevvy.getMaxRange(), 0.1, "Confirm max range is 250 miles");
        assertEquals(Chevvy.getRemainingRange(), Chevvy.getMaxRange(), "Confirm max range = remaining range");
        assertEquals("Chevrolet Bird (0.0 mi)", Chevvy.toString(), "Verify toString matches 'Chevrolet Bird (0.0 mi)'."); //
        assertEquals(false, Chevvy.checkWingsExtended(),"Verify wings retracted before driving");
    }

    @Test
    public void chevroletBirdDrive() {
        ChevroletBird cbird = new ChevroletBird();

        assertThrows(IllegalArgumentException.class, () -> {
            cbird.drive(-1);
        }, "Driving mileage cannot be negative.");

       assertTrue(cbird.canDrive(30), "canDrive should be true");
       assertTrue(cbird.canDrive(0), "canDrive should be true");
       assertTrue(cbird.canDrive(250), "canDrive should be true");
       assertFalse(cbird.canDrive(251), "canDrive should be false");
       cbird.drive(30);
        assertEquals(30, cbird.getMileage(), .1, "Mileage should be 30 after first drive.");
        assertEquals(220, cbird.getRemainingRange(), .1, "Remaining range should be 220.");
        assertFalse(cbird.canDrive(250), "canDrive should be false");
        cbird.recharge();
        assertEquals(250, cbird.getRemainingRange(), .1, "Remaining range should be 250.");
        assertEquals(cbird.getMaxRange(), cbird.getRemainingRange(), .1, "Remaining range should be 250.");
        
        assertThrows(IllegalArgumentException.class, () -> {
            cbird.fly(-1);
        }, "Flying mileage cannot be negative.");
        cbird.fly(70);
        assertEquals(30, cbird.getMileage(), .1, "Mileage should be 30 after flying");
        assertEquals(180, cbird.getRemainingRange(), .1, "Remaining range should be 180");
        assertTrue(cbird.checkWingsExtended(), "Wings should be extended");
        cbird.drive(50);
        assertFalse(cbird.checkWingsExtended(), "Wings should not be extended");

        assertThrows(IllegalArgumentException.class, () -> {
            cbird.drive(-1);
        }, "Driving mileage cannot be negative.");
        cbird.recharge();
        List<Double> miles = new ArrayList<>(4);
        miles.add(10.0);
        miles.add(100.0);
        miles.add(200.0);
        miles.add(300.0);
        cbird.roadTrip(miles);


        //assertEquals(cbird.getFuelCapacity() * honda.getMPG() - 230, honda.getRemainingRange(), .1,
                //"Remaining range of car not correct after driving twice.");


    }
    @Test
    public void teslaModelZSetup() {
        
        TeslaModelZ tesla = new TeslaModelZ(77);
        assertEquals(0, tesla.getMileage(), 0.1, "Default mileage should be zero.");
        assertEquals("Z77", tesla.getModel(), "Model is not correct.");
        assertEquals(tesla.getMaxRange(), 340, 0.1, "Max range should be 340.");
        assertEquals(tesla.getMaxRange(), tesla.getRemainingRange(), 0.1, "Initial remaining range should be max range.");
        assertEquals(77, tesla.getModelNum(),
                "Model num should be 77.");
        assertEquals("Tesla Z77 (0.0 mi)", tesla.toString(), "toString does not match");
        TeslaModelZ tesla2 = new TeslaModelZ(10,25);
        assertEquals(10, tesla2.getMileage(), 0.1, "Starting mileage should be ten.");
    }

    @Test
    public void teslaModelZDrive() {
        
        TeslaModelZ tesla = new TeslaModelZ(77);

        assertThrows(IllegalArgumentException.class, () -> {
            tesla.canDrive(-1);
        }, "Driving mileage cannot be negative.");

       assertTrue(tesla.canDrive(0), "canDrive should be true");
       assertTrue(tesla.canDrive(340), "canDrive should be true");
       assertFalse(tesla.canDrive(341), "canDrive should be false");
       tesla.drive(30);
        assertEquals(30, tesla.getMileage(), .1, "Mileage should be 30 after first drive.");
        assertFalse(tesla.canDrive(340), "canDrive should be false");
        assertEquals(310, tesla.getRemainingRange(), .1, "Remaining range should be 310 after first drive.");
        tesla.recharge();
        assertEquals(340, tesla.getRemainingRange(), .1, "Remaining range should be 340 after recharging.");
        assertEquals(tesla.getMaxRange(), tesla.getRemainingRange(), .1, "Remaining range should be same as max range after recharging.");

        assertThrows(IllegalArgumentException.class, () -> {
            tesla.driveAutonomously(-1);
        }, "Driving distance should not be negative");
        tesla.driveAutonomously(100);
        assertEquals(130, tesla.getMileage(), .1,
                "Mileage should be 130");
        assertEquals(240, tesla.getRemainingRange(), .1,
                "Remaining range should be 240");    

        assertThrows(IllegalArgumentException.class, () -> {
                tesla.drive(270);
        }, "Driving beyond how much you have should fail.");
        tesla.driveAutonomously(280);
        assertEquals(370, tesla.getMileage(), .1, "Mileage should be 370 after this drive.");
        assertEquals(0, tesla.getRemainingRange(), .1, "Should have no range left.");
        assertThrows(IllegalArgumentException.class, () -> {
            tesla.drive(-1);
        }, "You can't drive negative.");
        tesla.recharge();
        ArrayList<Double> days = new ArrayList<Double>();
        days.add(10.0);
        days.add(100.0);
        days.add(200.0);
        days.add(300.0);
        assertEquals(3,tesla.roadTrip(days),"road trip should be 3 days");
        assertEquals(30, tesla.getRemainingRange(), .1, "Should have 30 miles left.");

    }
}
