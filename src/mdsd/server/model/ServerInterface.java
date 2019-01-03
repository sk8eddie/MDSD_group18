package mdsd.server.model;

import mdsd.rover.RoverCommunication;
import project.Point;

import java.util.concurrent.locks.Lock;

public interface ServerInterface {
    /**
     * Sets the next destination point for the rover when the last one is reached
     *
     * @param roverCommunication the communication for a rover
     */
    void nextDestinationReached(RoverCommunication roverCommunication);

    boolean isEntryPoint(Point destination);

    boolean isExitPoint(Point destination);

    Lock getLock(Point destination);
}
