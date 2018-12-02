package mdsd.rover;

import project.Point;

import java.util.List;

public class Rover {

    private Point currentDestination;
    private List<Point> gPSCoordinates;

    private void setRoverDestination (Point destination) {
        currentDestination = destination;
    }

    private void checkRoverFault () {

    }
}
