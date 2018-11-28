package mdsd.rover;

import project.Point;
import simbad.sim.CameraSensor;

public interface RoverCommunication {
    void setNewDestination (Point newDestination);
    Point getDestination();
    CameraSensor getCamera();
}
