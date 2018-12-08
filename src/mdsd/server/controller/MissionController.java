package mdsd.server.controller;

import mdsd.rover.RoverCommunication;
import mdsd.server.model.ServerModel;
import org.omg.CORBA.Environment;

import mdsd.Robot;
import mdsd.server.model.Area;

public class MissionController {

    // TODO uncomment when the ServerModel-class is added
    private ServerModel model;

    private Environment environment;
    // Creates a new mission
    private Mission createNewMission() {

        return null;
    }
    // Updates the strategy of the mission
    private Mission updateStrategy() {
    return null;
    }
    // Send the strategy
    private void sendStrategy() {

    }
    // Sends the mission
    private void sendRoverMission() {
        model.setRoverMissions(createNewMission());
    }
    // Checks if a rover is in the same room as objective returns true
    //and atleast one rover inside the building return true
    //check boundries
    public boolean isConstraintFulfilled(Robot robot, Area area) {
    	if(robot.getPosition().equals(area)){
    		return true;
    	} else{
    		return false;
    	}
    }

    private void startRovers(){
    }

}
