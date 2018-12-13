package mdsd.server.controller;

import mdsd.rover.RoverCommunication;
import mdsd.server.model.ServerModel;
import org.omg.CORBA.Environment;

import mdsd.Robot;
import mdsd.server.model.Area;
import mdsd.rover.Rover;

import java.util.HashMap;

public class MissionController {

    private ServerModel model;
    private Environment environment;
    
    private Mission newMission = new Mission();
    private HashMap<Rover, Mission> hashMap;

    // Returns a HashMap with the Rover and an empty mission
    private HashMap createNewMission(Rover rover) {
        hashMap = new HashMap<>();
        hashMap.put(rover, newMission);
        return hashMap;
    }
    
    // Returns a HashMap with the Rover and an updated the strategy of the mission
    // which means an initialised list of points
    private HashMap updateStrategy(Rover rover) {
       // newMission.updateStrategy();
        hashMap = new HashMap<>();
        hashMap.put(rover, newMission);
        return hashMap;
    }
    
    private void sendStrategy(Rover rover) {
        model.setRoverMissions(updateStrategy(rover));
    }

    private void sendRoverMission(Rover rover) {
        model.setRoverMissions(createNewMission(rover));
    }
    
    // Checks if a rover is in the same room as objective returns true
    // and at least one rover inside the building return true
    // check boundaries
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
