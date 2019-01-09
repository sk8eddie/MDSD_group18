package mdsd.rover;

/**
 * A tough Chassi made for Rovers that work outdoors in a rough terrain. Can take a lot of
 * hits without breaking.
 */
public class HeavyDutyChassi extends Chassi {

    @Override
    protected void addDamage() {
        this.damageTaken += 5;
    }
}
