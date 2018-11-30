package mdsd.rover;

import mdsd.Robot;
import project.Point;

import java.util.List;

public class Rover extends Robot {

    public Rover(Point startPosition, String name){
        super(startPosition, name);
    }

    private Point currentDestination;
    private List<Point> gPSCoordinates;
    private RoverNetwork roverNetwork;



    private void setRoverDestination () {

    }

    private void checkRoverFault () {

    }
}
