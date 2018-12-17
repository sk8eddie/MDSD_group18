package mdsd.test;

import mdsd.server.controller.Mission;
import mdsd.server.controller.MissionController;
import mdsd.server.model.ServerInterface;
import mdsd.rover.*;
import mdsd.server.*;
import mdsd.server.model.ServerModel;
import org.junit.jupiter.api.Test;
import project.Point;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    @Test
    void roverNetworkTests () {
        ServerInterface serverInterface = new ServerInterface() {
            @Override
            public void nextDestinationReached(RoverCommunication roverCommunication) {
                System.out.println("Dummy");
            }
        };
        Point startPosition = new Point(0,0);
        Rover rover = new Rover(startPosition,"Test");
        RoverNetwork roverNetwork = new RoverNetwork(serverInterface,rover);



        //Tests
        assertTrue(roverNetwork.getPosition().getX() == startPosition.getX() && roverNetwork.getPosition().getZ() == startPosition.getZ());
        Point newPoint = new Point(10,10);
        roverNetwork.setNewDestination(newPoint);
        assertTrue(rover.getRoverDestination().getX() == newPoint.getX() && rover.getRoverDestination().getZ() == newPoint.getZ());

    }

    @Test
    void missonTest () {
        List<Mission> missionList = new ArrayList<>();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0,0));
        points.add(new Point(6,0));
        missionList.add(new Mission(points));
        points = new ArrayList<>();
        points.add(new Point(0,0));
        points.add(new Point(-6,0));
        missionList.add(new Mission(points));


        MissionController missionController = new MissionController(new ServerModel());
        List<Mission> xmlList = missionController.readMissionsXML();
        for (int i = 0;i<missionList.size();i++) {
            for (int j = 0; j < xmlList.get(i).getPoints().size(); j++) {
                assertTrue(missionList.get(i).getPoints().get(j).getX() == xmlList.get(i).getPoints().get(j).getX());
                assertTrue(missionList.get(i).getPoints().get(j).getZ() == xmlList.get(i).getPoints().get(j).getZ());
            }
        }
    }



}
