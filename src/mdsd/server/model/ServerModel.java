package mdsd.server.model;

import mdsd.rover.Rover;
import mdsd.rover.RoverCommunication;
import mdsd.server.controller.Mission;

import java.util.HashMap;

public class ServerModel implements ServerInterface {

    int rewardPoints; // Init by procedure controller
    HashMap<Rover, Mission> roverMissions; // Rover is the key and mission is the value
    HashMap<Rover, RoverCommunication> roverCommunication;

    ServerModel(HashMap<Rover, Mission> roverMissions, HashMap<Rover, RoverCommunication> roverCommunication){
        this.rewardPoints = 0;
        this.roverMissions = roverMissions;
        this.roverCommunication = roverCommunication;
    }

    @Override
    public void nextDestinationReached(Rover rover) { // get rover , Lookup mission for that rover and get the next point/destination and set new destination

    }

    public void getState(){ // Report everything, damage, location , reward points and report if any fault

    }

    public void missionComplete(Rover rover){ // Check if all points reached - mission complete and then ask for a new mission from the controller

    }

    public void setRoverMissions(HashMap roverMissions) {
        this.roverMissions = roverMissions;
    }

    public HashMap getRoverMissions() {
        return roverMissions;
    }

    public int getRewardPoints(){
        return this.rewardPoints;
    }

    // TODO Change to a method for adding reward points instead.
    public void setRewardPoints(int newPts){
        this.rewardPoints = newPts;
    }

}
