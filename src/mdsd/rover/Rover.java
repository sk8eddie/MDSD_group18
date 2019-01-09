package mdsd.rover;

import mdsd.Robot;
import project.Point;

import java.util.List;

/**
 * The Rover is a robot roaming around the environment. It is not very "smart" but will follow instructions
 * from a controller component.
 */
public class Rover extends Robot {

    private Point currentDestination;
    private Chassi roverChassi;

    /**
     * Rover constructor, calls constructor of its superclass and adds a chassi.
     *
     * @param startPosition the starting position for the rover
     * @param name          the name of the rover
     * @param chassi        the chassi to be added
     */
    public Rover(Point startPosition, String name, Chassi chassi) {
        super(startPosition, name);
        this.roverChassi = chassi;
    }

    /**
     * Rover constructor, calls constructor of its superclass.
     *
     * @param startPosition the starting position for the rover
     * @param name          the name of the rover
     */
    public Rover(Point startPosition, String name) {
        super(startPosition, name);
        this.roverChassi = new RegularChassi();
    }

    /**
     * Stops the rover by setting its destination to the current position
     */

     void stopRover() {
        this.currentDestination = this.getPosition();
        this.setRoverDestination(this.currentDestination);
    }

    /**
     * Dummy method for Chassi-feature.
     */
    private void hitChassi() {
        if (!roverChassi.sustainHit())
            this.stopRover();
    }

    /**
     * Checks if the rover is at its destination
     *
     * @return True if the destination is reached, otherwise false
     */
    boolean isAtDestination() {
        return (isinXRange(this.getPosition().getX()) && isinZRange(this.getPosition().getZ()));
    }


    /**
     * Checks if the parameter x is in the same surrounding area as the currentDestinations x-parameter
     *
     * @param x the parameter to be checked
     * @return True if x is in the range of the currentDestinations x-parameter, otherwise false
     */
    private Boolean isinXRange(double x) {
        return (((this.currentDestination.getX() - 0.25) < x) && (x < (this.currentDestination.getX() + 0.25)));
    }

    /**
     * Checks if the parameter z is in the same surrounding area as the currentDestinations z-parameter
     *
     * @param z the parameter to be checked
     * @return True if z is in the range of the currentDestinations z-parameter, otherwise false
     */
    private Boolean isinZRange(double z) {
        return (((this.currentDestination.getZ() - 0.25) < z) && (z < (this.currentDestination.getZ() + 0.25)));

    }


    /**
     * Sets the rovers current destination to the newDestination
     * Calls the setDestination-method from the AbstractRobotSimulator-class
     *
     * @param newDestination the new destination for the rover
     */
    void setRoverDestination(Point newDestination) {
        this.currentDestination = newDestination;
        this.setDestination(newDestination);
    }

    /**
     * getter for the rovers current destination
     *
     * @return the rovers current destination
     */
    public Point getRoverDestination() {
        return currentDestination;
    }

    /**
     * Should verify if there are any physical faults with the Rover, broken sensors etc.
     */
    private void checkRoverFault() {
        // Dummy method for Rover faults.
        // Since the Rovers are in a simulator, they can't physically break in any way.
    }
}
