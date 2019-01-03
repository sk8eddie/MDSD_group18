package mdsd.rover;

/**
 * The regular Chassi for a Rover. Should be able to take some hits but not to many.
 */
public class RegularChassi extends Chassi {

    @Override
    protected void addDamage() {
        this.damageTaken += 20;
    }
}
