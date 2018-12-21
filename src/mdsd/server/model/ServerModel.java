package mdsd.server.model;

import mdsd.Main;
import mdsd.rover.RoverCommunication;
import mdsd.server.controller.Mission;
import project.Point;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;

public class ServerModel implements ServerInterface {

    private int rewardPoints; // Init by procedure controller
    private HashMap<RoverCommunication, Mission> roverMissions; // Rover is the key and mission is the value
    private HashMap<Point, Lock> entryPoints;
    private HashMap<Point, Lock> exitPoints;


    public ServerModel(){
        this.rewardPoints = 0;
        this.roverMissions = new HashMap<RoverCommunication, Mission>();
        this.entryPoints = new HashMap<>();
        this.exitPoints = new HashMap<>();
    }

    @Override
    public void nextDestinationReached(RoverCommunication roverCommunication) { // get rover , Lookup mission for that rover and get the next point/destination and set new destination
        Mission mission = roverMissions.get(roverCommunication);
        if (mission.iterator().hasNext()) {
            roverCommunication.setNewDestination((Point) mission.iterator().next());
        } else {
            missionComplete(roverCommunication);
        }
    }

    @Override
    public boolean isEntryPoint(Point destination) {
        return entryPoints.containsKey(destination);
    }

    @Override
    public boolean isExitPoint(Point destination) {
        return exitPoints.containsKey(destination);
    }

    @Override
    public Lock getLock(Point destination) {
        if (entryPoints.containsKey(destination)) {
            return entryPoints.get(destination);
        } else if (exitPoints.containsKey(destination)) {
            return exitPoints.get(destination);
        } else {
            return null;
        }
    }

    public void stopRovers(){
        for(RoverCommunication rc : roverMissions.keySet()){
            rc.setNewDestination(rc.getPosition());
        }
    }

    public void getState(){ // Report everything, damage, location , reward points and report if any fault

    }

    public void missionComplete(RoverCommunication roverCommunication){ // Check if all points reached - mission complete and then ask for a new mission from the controller

    }

    public void setRoverMissions(HashMap<RoverCommunication, Mission> roverMissions) {
        this.roverMissions = roverMissions;
    }

    public HashMap<RoverCommunication, Mission> getRoverMissions() {
        return roverMissions;
    }

    public void updateRoverMissions(RoverCommunication roverCommunication, Mission mission){
        if (roverMissions.containsKey(roverCommunication)){
            roverMissions.replace(roverCommunication, mission);
        } else {
            roverMissions.put(roverCommunication, mission);
        }
    }

    public int getRewardPoints(){
        return this.rewardPoints;
    }

    // TODO Change to a method for adding reward points instead.
    public void setRewardPoints(int newPts){
        this.rewardPoints = newPts;
        Main main = new Main();
        main.getUi().setPoints(newPts);
    }

}
