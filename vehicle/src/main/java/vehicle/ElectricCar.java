package vehicle;

public abstract class ElectricCar extends Car {

    private double milesOnMaxCharge;
    private double charge; 
    /**
     * Note: Car begins with a full charge (and thus range). @throws
     * IllegalArgumentException if milesOnMaxCharge is nonpositive.
     */
    public ElectricCar(String make, String model, double startingMileage, double milesOnMaxCharge) {
        super(make, model, startingMileage);
        if (milesOnMaxCharge < 0)
            throw new IllegalArgumentException();
        this.milesOnMaxCharge = milesOnMaxCharge;
        charge = milesOnMaxCharge;
    }

    /**
     * Defaults mileage to 0. @throws IllegalArgumentException if milesOnMaxCharge
     * is nonpositive.
     */
    public ElectricCar(String make, String model, double milesOnMaxCharge) {
        this(make, model, 0, milesOnMaxCharge);
    }

    /**
     * Drives the full given number of miles. @throws IllegalArgumentException if
     * miles is negative. @throws IllegalArgumentException if miles is too high
     * given the current charge.
     */
    public void drive(double miles) {
        if (miles < 0) 
            throw new IllegalArgumentException("Miles cannot be negative in drive");
        if (miles > getRemainingRange()) 
            throw new IllegalArgumentException("We cannot drive that many miles on this much charge");

        decreaseCharge(miles);
        addMileage(miles);
        
    }

    /** Returns how many more miles the car can currently go without recharging. */
    public double getRemainingRange() {
        return charge;
    }

    /** Returns how many miles the car could go on a full charge. */
    public double getMaxRange() {
        return milesOnMaxCharge;
    }

    /** Recharges the car to max range capability. */
    public void recharge() {
        charge = milesOnMaxCharge;
    }

    /**
     * Decreases the amount of energy in the battery based by the number of miles
     * passed as an argument.
     */
    protected void decreaseCharge(double miles) {
        charge -= miles;
    }
}
