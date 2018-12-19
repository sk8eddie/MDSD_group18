package mdsd.server.model;

import mdsd.rover.RoverCommunication;
import mdsd.server.controller.Mission;
import project.Point;

import java.util.HashMap;

public class ServerModel implements ServerInterface {

    int rewardPoints; // Init by procedure controller
    HashMap<RoverCommunication, Mission> roverMissions; // Rover is the key and mission is the value
    //HashMap<Rover, RoverCommunication> roverCommunication;


    public ServerModel(){
        this.rewardPoints = 0;
        this.roverMissions = new HashMap<RoverCommunication, Mission>();
    }

    @Override
    public void nextDestinationReached(RoverCommunication roverCommunication) { // get rover , Lookup mission for that rover and get the next point/destination and set new destination
        Mission mission = roverMissions.get(roverCommunication);
        if (mission.iterator().hasNext()) {
            Point p = (Point)mission.iterator().next();
            System.out.println(p.getX() + "   " + p.getZ() + "   " + roverCommunication.getPosition());
            roverCommunication.setNewDestination(p);

        } else {
            missionComplete(roverCommunication);
        }
    }

    /*public void setRoverCommunication(HashMap<Rover, RoverCommunication> roverComm){
        this.roverCommunication = roverComm;
    }*/

    public void getState(){ // Report everything, damage, location , reward points and report if any fault

    }

    public void missionComplete(RoverCommunication roverCommunication){ // Check if all points reached - mission complete and then ask for a new mission from the controller

    }

    public void setRoverMissions(HashMap roverMissions) {
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
    }

}
