package mdsd.server.model;

import mdsd.rover.Rover;

import java.util.HashMap;

public class ServerModel implements ServiceInterface {
    int rewardPoints;
    HashMap roverMissions;

    @Override
    public void nextDestinationReached(Rover rover) {

    }
    
    public void reportState(){

    }

    public void missionComplete(Rover rover){

    }
}
