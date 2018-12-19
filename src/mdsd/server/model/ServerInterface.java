package mdsd.server.model;

import mdsd.rover.RoverCommunication;
import project.Point;

import java.util.concurrent.locks.Lock;

public interface ServerInterface {
    void nextDestinationReached(RoverCommunication roverCommunication);
    boolean isEntryPoint(Point destination);
    boolean isExitPoint(Point destination);
    Lock getLock(Point destination);
}
