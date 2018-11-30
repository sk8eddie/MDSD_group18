package mdsd.rover;

/**
 * A representation of the Chassi of the Rover. The amount of "physical" damage that it can sustain.
 */
abstract class Chassi {

    protected int damageTaken = 0;        // As for now an arbitrary number
    protected int threshold = 100;

    /**
     * Method to use when the Rover hits something.
     */
    void takeHit(){
        addDamage();
        if(damageTaken >= threshold){
            BrokenRoverHandler.stopRover();
        }
    }

    protected abstract void addDamage();
}
