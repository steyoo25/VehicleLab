package vehicle;

public class ChevroletBird extends ElectricCar {
    private boolean wings = false;
    /** Chevrolet Birds have a 250 mile range on a full charge. They
    start with their wings retracted.*/
    public ChevroletBird(double startingMileage){
        super("Chevorlet", "Bird", startingMileage, 250);
    }
    /** Defaults mileage to 0. */
    public ChevroletBird() {
        this(0);
    }
    /** Returns whether the wings are currently extended. */
    public boolean checkWingsExtended(){
        return wings;
    }
    /** Drives just like all other Electric Cars, except make sure that
    you retract your wings first (duh).
    Coding tip: Write this method to re-use the behavior of the
    superclass drive. Don’t copy-and-paste the same code here.*/
    public void drive(double miles){
        wings = false;
        super.drive(miles);
    }
}
