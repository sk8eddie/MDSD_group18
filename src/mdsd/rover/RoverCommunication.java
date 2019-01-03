package mdsd.rover;

import project.Point;

public interface RoverCommunication {

    /**
     * Sets the new destination
     *
     * @param newDestination the new destination for the rover
     */
    void setNewDestination(Point newDestination);

    /**
     * Getter for the position
     *
     * @return the porition of the rover
     */
    Point getPosition();
}
