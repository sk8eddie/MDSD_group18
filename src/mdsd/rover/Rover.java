package mdsd.rover;

import mdsd.Robot;
import project.Point;

import java.util.List;

/**
 * The Rover is a robot roaming around the environment. It is not very "smart" but will follow instructions
 * from a controller component.
 */
public class Rover extends Robot {

    /**
     * Calls cuntructor of its superclass.
     * @param startPosition
     * @param name
     */
    public Rover(Point startPosition, String name){
        super(startPosition, name);
    }

    private Point currentDestination;
    private List<Point> gPSCoordinates;

    void setRoverDestination(Point newDestination) {
        this.setDestination(newDestination);
    }

    /**
     * Should verify if there are any physical faults with the Rover, broken sensors etc.
     */
    private void checkRoverFault () {
        // TODO way to surveil the rover parts
    }
}
