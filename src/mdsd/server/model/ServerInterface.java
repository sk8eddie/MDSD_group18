package mdsd.server.model;

import mdsd.rover.RoverCommunication;

public interface ServerInterface {
    void nextDestinationReached(RoverCommunication roverCommunication);
}
