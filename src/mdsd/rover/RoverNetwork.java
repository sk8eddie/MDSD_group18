package mdsd.rover;

import mdsd.server.model.ServerInterface;
import project.Point;
import simbad.sim.CameraSensor;

/**
 * Concrete implementation of the RoverCommunication interface. Is the facade
 * for other components to communicate with the Rover.
 */
public class RoverNetwork implements RoverCommunication {

    private ServerInterface server;
    private Rover rover;

    private Thread positionChecker;

    public RoverNetwork(ServerInterface server, Rover rover){
        this.server = server;
        this.rover = rover;
    }

    public void setNewDestination(Point newDestination) {
        this.rover.setRoverDestination(newDestination);
        RoverCommunication self = this;
        this.positionChecker = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started: " + this.toString());
                while (!rover.isAtDestination()) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Rover " + rover.getName() + " at position");
                server.nextDestinationReached(self);
                System.out.println("Thread exit: " + this.toString());
            }
        });
        positionChecker.start();
    }

    public Point getPosition() {
        return rover.getPosition();
    }

    // TODO Should be the camera feed, not a boolean, just need to figure out how to access the camera class
    public Boolean getCamera(){
        return rover.checkCameraDetection();
    }

}
