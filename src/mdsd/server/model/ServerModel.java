package mdsd.server.model;

import mdsd.rover.Rover;

import java.util.HashMap;


public class ServerModel implements ServerInterface {

    int rewardPoints; // Init by procedure controller
    HashMap roverMissions; // Rover is the key and mission is the value

    @Override
    public void nextDestinationReached(Rover rover) { // get rover , Lookup mission for that rover and get the next point/destination and set new destination

    }

    public void getState(){ // Report everything, damage, location , reward points and report if any fault

    }

    public void missionComplete(Rover rover){ // Check if all points reached - mission complete and then ask for a new mission from the controller

    }

}
