package mdsd.rover;

import mdsd.server.model.ServerInterface;
import project.Point;
import simbad.sim.CameraSensor;

import java.util.Locale;
import java.util.concurrent.locks.Lock;

/**
 * Concrete implementation of the RoverCommunication interface.
 * The facade for other components to communicate with the Rover.
 */
public class RoverNetwork implements RoverCommunication {

    private ServerInterface server;
    private Rover rover;
    private boolean hasLock = false;

    /**
     * Constructor for the RoverNetwork
     *
     * @param server the ServerModel for the program
     * @param rover  the rover that should be communicated with
     */
    public RoverNetwork(ServerInterface server, Rover rover) {
        this.server = server;
        this.rover = rover;
    }

    /**
     * sets the rovers new destination, checks the locks for the room
     *
     * @param newDestination the new destination for the rover
     */
    public void setNewDestination(Point newDestination) {
        //server has hashmap with room entry and exit points?


        this.rover.setRoverDestination(newDestination);
        RoverCommunication self = this;
        Thread positionChecker = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Started: " + this.toString());
                while (!rover.isAtDestination()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Rover " + rover.getName() + " at position");
                if (hasLock){
                    if (server.isExitPoint(newDestination)) {
                        unlock(server.getLock(newDestination, false));
                        hasLock=false;
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (server.isEntryPoint(newDestination)) {
                        rover.stopRover();
                        System.out.println("Waiting for lock");
                        lock(server.getLock(newDestination, true));
                        hasLock=true;
                        System.out.println("Has lock");
                    }
                }
                server.nextDestinationReached(self);
                System.out.println("Thread exit: " + this.toString());
            }
        });
        positionChecker.start();
    }

    /**
     * Getter for the rovers position
     *
     * @return the rovers porition
     */
    public Point getPosition() {
        return rover.getPosition();
    }

    // TODO Should be the camera feed, not a boolean, just need to figure out how to access the camera class
    public Boolean getCamera() {
        return rover.checkCameraDetection();
    }

    private void lock(Lock lock){
        lock.lock();
    }

    private void unlock(Lock lock){
        lock.unlock();
    }

}
