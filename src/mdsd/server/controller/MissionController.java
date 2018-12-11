package mdsd.server.controller;

import mdsd.server.model.Environment;
import mdsd.server.model.ServerModel;
import mdsd.Robot;
import mdsd.server.model.Area;
import mdsd.rover.Rover;

import java.util.HashMap;
import java.util.Set;

public class MissionController {

    private ServerModel model;
    private Environment environment;


    // Returns a HashMap with the Rover and an empty mission
    private void createNewMission(Rover rover) {
        Mission mission = new Mission();
        model.updateRoverMissions(rover, mission);
    }

    // Returns a HashMap with the Rover and an updated the strategy of the mission
    // which means an initialised list of points
    private void updateStrategy(Rover rover) {
        Mission mission = model.getRoverMissions().get(rover);
        mission.updateStrategy();
        //hashMap = new HashMap<>();
    }

    /*private void sendStrategy(Rover rover) {
        model.setRoverMissions(updateStrategy(rover));
    }*/

    // Sends the mission
    /*private void sendRoverMission(Rover rover) {
        model.setRoverMissions(createNewMission(rover));
    }*/




    // Checks if a rover is in the same room as objective returns true
    // and at least one rover inside the building return true
    // check boundaries
    public boolean isConstraintFulfilled(Rover rover, Area area) {
        return true;
    }

    private void startRovers(Set<Rover> rovers){
        for (Rover r : rovers){
            createNewMission(r);
        }
    }

}
