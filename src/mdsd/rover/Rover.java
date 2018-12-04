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

    public void stopRover() {
        System.out.println("STOP, THE ROVER IS BROKEN!");
        currentDestination = this.getPosition();
    }

    public void hitChassi () {
        if (!roverChassi.sustainHit())
            this.stopRover();
    }



    void setRoverDestination(Point newDestination) {
        this.setDestination(newDestination);
    }

    /**
     * Should verify if there are any physical faults with the Rover, broken sensors etc.
     */
    private void checkRoverFault () {
        // TODO way to surveil the rover parts
        // Poll att the parts???
    }
}
