package mdsd.server.controller;

import mdsd.server.model.Environment;
import mdsd.server.model.ServerModel;
import mdsd.Robot;
import mdsd.server.model.Area;
import mdsd.rover.Rover;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.Point;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MissionController {

    private ServerModel model;
    private Environment environment;

    public MissionController(ServerModel model){
        this.model = model;
    }

    //temp
    /*List<Mission> m = new ArrayList<>();

    private void create(){
        Mission m2 = new Mission();
        m2.addPoint(new Point(-2.5, -2.5));
        m2.addPoint(new Point(2.5, -2.5));
        m2.addPoint(new Point(2.5, -6));
        Mission m1 = new Mission();
        m1.addPoint(new Point(2.5, 2.5));
        m1.addPoint(new Point(-2.5, 2.5));
        m1.addPoint(new Point(-2.5, 6));
        m.add(m2);
        m.add(m1);

    }*/
    //temp

    public List<Mission> readMissionsXML(){
        List<Mission> missions = new ArrayList<>();
        try{
            File missionsXml = new File("missionData.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document missionDoc = documentBuilder.parse(missionsXml);

            missionDoc.getDocumentElement().normalize();

            NodeList missionNodes = missionDoc.getElementsByTagName("Mission");
            for(int i = 0; i < missionNodes.getLength(); i++){
                Mission newMission = new Mission();

                System.out.println("MISSION NODE: ");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return missions;
    }

    private List<Point> getPointsFromMissionXML(Node mission){
        List<Point> points = new ArrayList<>();

    }

    // Returns a HashMap with the Rover and an empty mission
    private void createNewMission(Rover rover) {
        /*List<Mission> missions = readMissionsXML();
        Mission mission = missions.get(0);
        missions.remove(0);
        model.updateRoverMissions(rover, mission);*/
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

    public void startRovers(Set<Robot> rovers){
        for (Robot r : rovers){
            createNewMission((Rover)r);
        }

        System.out.println(model.getRoverMissions());

        for(Rover r : model.getRoverMissions().keySet()){
            r.setDestination((Point)model.getRoverMissions().get(r).iterator().next());
        }
    }

}
