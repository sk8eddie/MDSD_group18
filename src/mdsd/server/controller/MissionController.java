package mdsd.server.controller;

import java.util.Set;

import org.omg.CORBA.Environment;

import mdsd.Robot;
import mdsd.server.model.Area;

public class MissionController {

    // TODO uncomment when the ServerModel-class is added
    //private ServerModel model;

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
    private void sendMission() {

    }
    // Checks if two rovers in the same room returns false if true
    //and atleast one rover inside the building return true
    //check boundries
    public boolean isConstraintFulfilled(Robot robot, Area area) {
    	if(area.inArea(robot.getPosition()) == true){
    		return false;
    	} else{
    		return true;
    	}
    }

}
