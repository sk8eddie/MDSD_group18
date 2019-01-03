package mdsd.server.model;

import mdsd.Main;
import mdsd.rover.RoverCommunication;
import mdsd.server.controller.Mission;
import project.Point;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class ServerModel implements ServerInterface {

    private int rewardPoints; // Init by procedure controller
    private HashMap<RoverCommunication, Mission> roverMissions; // Rover is the key and mission is the value
    private HashMap<Point, Semaphore> entryPoints;
    private HashMap<Point, Semaphore> exitPoints;


    /**
     * Constructor for the ServerModel
     *
     * @param e the environment in which the program is running
     */
    public ServerModel(Environment e) {
        this.rewardPoints = 0;
        this.roverMissions = new HashMap<RoverCommunication, Mission>();
        this.entryPoints = e.getEntryMap();
        this.exitPoints = e.getExitMap();
    }

    /**
     * Sets the next destination point for the rover when the last one is reached
     *
     * @param roverCommunication the communication for a rover
     */
    @Override
    public void nextDestinationReached(RoverCommunication roverCommunication) { // get rover , Lookup mission for that rover and get the next point/destination and set new destination
        Mission mission = roverMissions.get(roverCommunication);
        if (mission.iterator().hasNext()) {
            Point p = (Point) mission.iterator().next();
            System.out.println(p.getX() + "   " + p.getZ() + "   " + roverCommunication.getPosition());
            roverCommunication.setNewDestination(p);

        } else {
            missionComplete(roverCommunication);
        }
    }

    /**
     * Checks if the point is an entry point
     *
     * @param destination the destination point to be checked
     * @return True if it is an entry point, otherwise false
     */
    @Override
    public boolean isEntryPoint(Point destination) {
        Set<Point> set = entryPoints.keySet();
        for (Point p : set){
            if (p.getX() == destination.getX() && p.getZ() == destination.getZ()){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the point is an exit point
     *
     * @param destination the destination point to be checked
     * @return True if it is an exit point, otherwise false
     */
    @Override
    public boolean isExitPoint(Point destination) {
        Set<Point> set = exitPoints.keySet();
        for (Point p : set){
            if (p.getX() == destination.getX() && p.getZ() == destination.getZ()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Semaphore getSemaphore(Point destination, boolean bool) {
        if (bool){
            Set<Point> set = entryPoints.keySet();
            for (Point p : set){
                if (p.getX() == destination.getX() && p.getZ() == destination.getZ()){
                    return entryPoints.get(p);
                }
            }
        } else {
            Set<Point> set = exitPoints.keySet();
            for (Point p : set){
                if (p.getX() == destination.getX() && p.getZ() == destination.getZ()){
                    return exitPoints.get(p);
                }
            }
        }
        return null;
    }

    /**
     * Stops all rovers in the environment
     */
    public void stopRovers() {
        for (RoverCommunication rc : roverMissions.keySet()) {
            rc.setNewDestination(rc.getPosition());
        }
    }

    public void getState() { // Report everything, damage, location , reward points and report if any fault

    }

    public void missionComplete(RoverCommunication roverCommunication) { // Check if all points reached - mission complete and then ask for a new mission from the controller

    }

    public void setRoverMissions(HashMap<RoverCommunication, Mission> roverMissions) {
        this.roverMissions = roverMissions;
    }

    /**
     * Getter for the list of rover missions
     *
     * @return the list with the rover missions
     */
    public HashMap<RoverCommunication, Mission> getRoverMissions() {
        return roverMissions;
    }

    /**
     * Updates a rovers mission
     *
     * @param roverCommunication the communication with the rover
     * @param mission            the new mission for the rover
     */
    public void updateRoverMissions(RoverCommunication roverCommunication, Mission mission) {
        if (roverMissions.containsKey(roverCommunication)) {
            roverMissions.replace(roverCommunication, mission);
        } else {
            roverMissions.put(roverCommunication, mission);
        }
    }

    /**
     * getter for the reward points
     *
     * @return the reward points
     */
    public int getRewardPoints() {
        return this.rewardPoints;
    }

    /**
     * Adds new points to the already existing reward points
     *
     * @param newPts points to be added
     */
    public void addRewardPoints(int newPts) {
        this.rewardPoints += newPts;
        Main main = new Main();
        main.getUi().setPoints(this.rewardPoints);
    }

}
