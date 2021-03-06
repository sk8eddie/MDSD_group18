package mdsd.rover;

import mdsd.server.model.ServerInterface;
import project.Point;
import simbad.sim.CameraSensor;

import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

/**
 * Concrete implementation of the RoverCommunication interface.
 * The facade for other components to communicate with the Rover.
 */
public class RoverNetwork implements RoverCommunication {

    private ServerInterface server;
    private Rover rover;
    private boolean hasLock = false;
    private Thread positionChecker;

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
     * Uses threads to poll the current destination,
     * when reached the thread checks if the point is an entry or exit point
     * Then it releases, acquires the semaphore accordingly
     *
     * @param newDestination the new destination for the rover
     */
    public void setNewDestination(Point newDestination) {
        //server has hashmap with room entry and exit points?


        this.rover.setRoverDestination(newDestination);
        RoverCommunication self = this;
        positionChecker = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Started: " + this.toString());
                    while (!rover.isAtDestination())
                        Thread.sleep(1);

                    Thread.sleep(300);
                    System.out.println("Rover " + rover.getName() + " at position");
                    if (hasLock) {
                        if (server.isExitPoint(newDestination)) {
                            Semaphore semaphore = server.getSemaphore(newDestination, false);
                            if (semaphore.availablePermits() == 0) {
                                semaphore.release();
                            }
                            hasLock = false;
                            Thread.sleep(1);
                        }
                    } else {
                        if (server.isEntryPoint(newDestination)) {
                            Thread.sleep(2000);
                            server.getSemaphore(newDestination, true).acquire();
                            hasLock = true;
                        }
                    }
                    server.nextDestinationReached(self);
                    System.out.println("Thread exit: " + this.toString());
                } catch (InterruptedException e) {
                    e.getStackTrace();
                    rover.stopRover();
                }
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

    /**
     * Stops the rover by interrupting the thread
     */
    public void stopRover () {
        rover.stopRover();
        positionChecker.interrupt();
    }

    // TODO Should be the camera feed, not a boolean, just need to figure out how to access the camera class
    public Boolean getCamera() {
        return rover.checkCameraDetection();
    }

}
