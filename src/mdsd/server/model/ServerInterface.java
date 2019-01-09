package mdsd.server.model;

import mdsd.rover.RoverCommunication;
import project.Point;

import java.util.concurrent.Semaphore;

/**
 * Communications class for the server.
 */

public interface ServerInterface {
    /**
     * Sets the next destination point for the rover when the last one is reached
     *
     * @param roverCommunication the communication for a rover
     */
    void nextDestinationReached(RoverCommunication roverCommunication);

    /**
     * Checks if the point is an entry point
     *
     * @param destination the destination point to be checked
     * @return True if it is an entry point, otherwise false
     */
    boolean isEntryPoint(Point destination);

    /**
     * Checks if the point is an exit point
     *
     * @param destination the destination point to be checked
     * @return True if it is an exit point, otherwise false
     */
    boolean isExitPoint(Point destination);

    /**
     * Getter for Semaphore.
     *
     * @param destination Point to check for Semaphore.
     * @param bool        true if checking for entry point, false if checking for exit point
     * @return Semaphore if there is one at the destination, null otherwise.
     */
    Semaphore getSemaphore(Point destination, boolean bool);
}
