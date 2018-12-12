package mdsd.server.controller;

import mdsd.server.model.Environment;
import mdsd.server.model.ServerModel;
import mdsd.Robot;
import mdsd.server.model.Area;
import mdsd.rover.Rover;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import project.Point;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MissionController {

    private ServerModel model;
    private Environment environment;

    public MissionController(ServerModel model){
        this.model = model;
    }

    /**
     * From a xml-file that is in "mdsdSimultar/missionData.xml" predefined missions are read and will
     * generate a java list of Missions that are assigned to each Rover. The xml-file should be in the
     * @return a java list of Missions.
     */
    public List<Mission> readMissionsXML(){
        List<Mission> missions = new ArrayList<>();
        try{
            // Read the xml-file
            File missionsXml = new File("../mdsd/src/mdsd/server/controller/missionData.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document missionDoc = documentBuilder.parse(missionsXml);

            missionDoc.getDocumentElement().normalize();

            // Get all xml-missions and create java objects from them
            NodeList missionNodes = missionDoc.getElementsByTagName("Mission");
            if(missionNodes.getLength() == 0){
                throw new Exception("There are no Missions defined in the 'missionData.xml'-file.");
            }
            for(int i = 0; i < missionNodes.getLength(); i++){
                Mission newMission = new Mission(getPointsFromMissionXML(missionNodes.item(i)));
                missions.add(newMission);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return missions;
    }

    /**
     * From each mission tag in the XML-file, extract its points and create objects for them according to attributes.
     * @param mission the Mission-tag from the xml-file.
     * @return a java list of Points that belonging to the sent Mission-tag.
     */
    private List<Point> getPointsFromMissionXML(Node mission){
        List<Point> points = new ArrayList<>();
        NodeList missionPoints = mission.getChildNodes(); // Get points from XML-tag

        for(int i = 0; i < missionPoints.getLength(); i++){
            Node pointNode = missionPoints.item(i);
            if(pointNode.getNodeType() == Node.ELEMENT_NODE){
                Element xmlPoint = (Element)pointNode;

                double x = Double.parseDouble(xmlPoint.getAttribute("x"));
                double y = Double.parseDouble(xmlPoint.getAttribute("y"));
                points.add(new Point(x, y));
            }
        }
        return points;
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

        List<Mission> missions = readMissionsXML();

        // Add all rovers and their missions to the model
        if(rovers.size() == missions.size()){
            int missionIndex = 0;
            for(Robot r : rovers){
                Rover rover = (Rover)r;
                this.model.updateRoverMissions(rover, missions.get(missionIndex));
                missionIndex++;
            }
        }

        System.out.println("START ROVERS" + model.getRoverMissions());

        for(Rover r : model.getRoverMissions().keySet()){
            r.setDestination((Point)model.getRoverMissions().get(r).iterator().next());
        }
    }

}
