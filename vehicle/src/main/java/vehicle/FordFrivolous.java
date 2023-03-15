package vehicle;

public class FordFrivolous extends GasPoweredCar implements SelfDriving, Flying {
    /** FordFrivolous has a gas tank of 20 gallons and an MPG of 23.6. */
    public FordFrivolous(double startingMileage) {
        super("Ford", "Frivolous", startingMileage, 23.6, 20);
    }

    /** Defaults mileage to 0. */
    public FordFrivolous() {
        this(0);
    }

    @Override
    public boolean canFly(double miles) {
        if (miles < 0){
            throw new IllegalArgumentException();
        }
        return getRemainingRange() > miles * 3;
    }

    @Override
    public void fly(double miles) {

        if (!canFly(miles)){
            throw new IllegalArgumentException("Can't fly that");
        }

        decreaseFuelLevel(miles * 3);
        
    }

    @Override
    public void driveAutonomously(double miles) {
        if (miles < 0)
            throw new IllegalArgumentException("Miles cant be negative");

        

        drive(miles * 2);
    }

    
    
}
