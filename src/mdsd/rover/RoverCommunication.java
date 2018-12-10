package mdsd.rover;

import project.Point;

public interface RoverCommunication {
    void setNewDestination (Point newDestination);
    Point getPosition();
}
