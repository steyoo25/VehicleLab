package vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Group3_2HondaAccordianTesters {
    @Test
    public void hondaTest1() {
        // creating object
        assertThrows(IllegalArgumentException.class, () -> {
            HondaAccordian honda = new HondaAccordian(-5, 2016);
        }, "Starting mileage cannot be negative.");
    
        HondaAccordian honda1 = new HondaAccordian(2018);
    
        HondaAccordian honda2 = new HondaAccordian(50, 2017);

        // toString
        assertEquals(honda1.toString(), "2018 Honda Accordian (0.0 mi)");
        assertEquals(honda2.toString(), "2017 Honda Accordian (50.0 mi)");

        // mileage
        assertEquals(honda1.getMileage(), 0);
        assertEquals(honda2.getMileage(), 50);

        // fuelCapacity
        assertEquals(honda1.getFuelCapacity(), 14.5);
        assertEquals(honda2.getFuelCapacity(), 14.5);

        // fuelLevel
        assertEquals(honda1.getFuelLevel(), 14.5);
        assertEquals(honda2.getFuelLevel(), 14.5);

        // drive
        honda1.drive(3);
        honda2.drive(3);

        honda1.drive(0);
        honda2.drive(0);

        // mileage
        assertEquals(honda1.getMileage(), 3);
        assertEquals(honda2.getMileage(), 53);

        // driving negative miles
        assertThrows(IllegalArgumentException.class, () -> {
            honda1.drive(-1);
        }, "Car cannot drive negative miles");

        // more mileage
        assertEquals(honda1.getMileage(), 3);
        assertEquals(honda2.getMileage(), 53);

        // fuelLevel
        assertEquals(honda1.getFuelLevel(),  14.5-(3/33.2), .10, "Fuel level is not correct");
        assertEquals(honda2.getFuelLevel(), 14.5-(3/33.2));

        // mpg
        assertEquals(honda1.getMPG(), 33.2);
        assertEquals(honda2.getMPG(), 33.2);

        // remainingRange
        assertEquals(honda1.getRemainingRange(), honda1.getMPG() * honda1.getFuelLevel(), 0.1);
        assertEquals(honda2.getRemainingRange(), honda2.getMPG() * honda2.getFuelLevel(), 0.1);

        // toString
        assertEquals(honda1.toString(), "2018 Honda Accordian (3.0 mi)");
        assertEquals(honda2.toString(), "2017 Honda Accordian (53.0 mi)");

        // refillTank
        honda1.refillTank();
        honda2.refillTank();

        // fuelLevel = fuelCapacity
        assertEquals(honda1.getFuelLevel(), honda1.getFuelCapacity() );
        assertEquals(honda2.getFuelLevel(), honda2.getFuelCapacity());

        // driving remainingRange
        honda1.drive(honda1.getRemainingRange());
        honda2.drive(honda2.getRemainingRange());

        // fuelLevel
        assertEquals(honda1.getFuelLevel(), 0, 0.1);
        assertEquals(honda2.getFuelLevel(), 0, 0.1);

        // mileage
        assertEquals(honda1.getMileage(), honda1.getFuelCapacity() * honda1.getMPG() + 3, 0.1);
        
        assertEquals(honda2.getMileage(), honda2.getFuelCapacity() * honda2.getMPG() + 53, 0.1);

        // illegal drive
        assertThrows(IllegalArgumentException.class, () -> {
            honda1.drive(80);
        }, "Miles exceeds remaining range");

        assertThrows(IllegalArgumentException.class, () -> {
            honda2.drive(80);
        }, "Miles exceeds remaining range");

        // non-illegal drive
        honda1.drive(0);
        honda2.drive(0);

        // mileage
        assertEquals(honda1.getMileage(), honda1.getFuelCapacity() * honda1.getMPG() + 3, 0.1);
        assertEquals(honda2.getMileage(), honda2.getFuelCapacity() * honda2.getMPG() + 53, 0.1);

        // drive
        assertThrows(IllegalArgumentException.class, () -> {
            honda1.drive(-1);
        }, "Car cannot drive negative miles");

        assertThrows(IllegalArgumentException.class, () -> {
            honda2.drive(-1);
        }, "Car cannot drive negative miles");

        // mileage
        assertEquals(honda1.getMileage(), honda1.getFuelCapacity() * honda1.getMPG() + 3, 0.1);
        assertEquals(honda2.getMileage(), honda2.getFuelCapacity() * honda2.getMPG() + 53, 0.1);

        // refillTank
        honda1.refillTank(5);
        honda2.refillTank(5);

        // remainingRange
        assertEquals(honda1.getRemainingRange(), 166);
        assertEquals(honda2.getRemainingRange(), 166);

        // canDrive
        assertEquals(honda1.canDrive(0), true);
        assertEquals(honda2.canDrive(0), true);

        assertThrows(IllegalArgumentException.class, () -> {
            honda1.canDrive(-5);
        }, "Car cannot drive negative miles");

        assertThrows(IllegalArgumentException.class, () -> {
            honda2.canDrive(-5);
        }, "Car cannot drive negative miles");

        // canDrive
        assertEquals(honda1.canDrive(166), true);
        assertEquals(honda2.canDrive(166), true);

        assertEquals(honda1.canDrive(167), false);
        assertEquals(honda2.canDrive(167), false);

        assertEquals(honda1.canDrive(160), true);
        assertEquals(honda2.canDrive(160), true);

        // getModel
        assertEquals(honda1.getModel(), "Accordian");
        assertEquals(honda2.getModel(), "Accordian");

        // roadTrip
        List<Double> milesEachDay = new ArrayList<>();
        milesEachDay.add(10.0); 
        milesEachDay.add(200.0);
        milesEachDay.add(100.0);
        milesEachDay.add(400.0);

        assertEquals(honda1.roadTrip(milesEachDay), 1);
        assertEquals(honda2.roadTrip(milesEachDay), 1);

        milesEachDay.clear();
        milesEachDay.add(-10.0); 
        milesEachDay.add(200.0);
        milesEachDay.add(100.0);
        milesEachDay.add(400.0);

        assertThrows(IllegalArgumentException.class, () -> {
            honda1.roadTrip(milesEachDay);
        }, "Car cannot drive negative miles");

        assertThrows(IllegalArgumentException.class, () -> {
            honda2.roadTrip(milesEachDay);
        }, "Car cannot drive negative miles");

        milesEachDay.clear();
        milesEachDay.add(1.0); 
        milesEachDay.add(2.0);
        milesEachDay.add(3.0);
        milesEachDay.add(4.0);

        assertEquals(honda1.roadTrip(milesEachDay), 4);
        assertEquals(honda2.roadTrip(milesEachDay), 4);
    }
}

