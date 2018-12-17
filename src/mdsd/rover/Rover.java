package mdsd.rover;

import mdsd.Robot;
import project.Point;

import java.util.List;

/**
 * The Rover is a robot roaming around the environment. It is not very "smart" but will follow instructions
 * from a controller component.
 */
public class Rover extends Robot {

    //TODO RoverFactory

    private Point currentDestination;
    private List<Point> gPSCoordinates;
    private Chassi roverChassi;

    /**
     * Calls cuntructor of its superclass and adds a chassi.
     * @param startPosition
     * @param name
     */
    public Rover(Point startPosition, String name, Chassi chassi){
        super(startPosition, name);
        this.roverChassi = chassi;
    }

    public Rover(Point startPosition, String name){
        super(startPosition, name);
        this.roverChassi = new RegularChassi();
    }

    void stopRover() {
        System.out.println("STOP, THE ROVER IS BROKEN!");
        this.currentDestination = this.getPosition();
        this.setRoverDestination(this.currentDestination);
    }

    void hitChassi () {
        if (!roverChassi.sustainHit())
            this.stopRover();
    }

    boolean isAtDestination(){
        return (isinXRange(this.getPosition().getX()) && isinZRange(this.getPosition().getZ()));
    }

    private Boolean isinXRange(double x){
        return (((this.currentDestination.getX() - 0.5) < x) && (x < (this.currentDestination.getX() + 0.5)));
    }

    private Boolean isinZRange(double z){
        return (((this.currentDestination.getZ() - 0.5) < z) && (z < (this.currentDestination.getZ() + 0.5)));
    }

    void setRoverDestination(Point newDestination) {
        this.currentDestination = newDestination;
        this.setDestination(newDestination);
    }

    public Point getRoverDestination() {
        return currentDestination;
    }

    /**
     * Should verify if there are any physical faults with the Rover, broken sensors etc.
     */
    private void checkRoverFault () {
        // TODO way to surveil the rover parts
        // Poll att the parts???
    }
}
