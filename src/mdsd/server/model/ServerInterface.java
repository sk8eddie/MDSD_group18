package mdsd.server.model;

import mdsd.rover.Rover;

public interface ServerInterface {
    void nextDestinationReached(Rover rover);
}
