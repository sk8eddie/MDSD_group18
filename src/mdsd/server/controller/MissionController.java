package mdsd.server.controller;

import mdsd.rover.RoverCommunication;
import mdsd.server.model.Area;
import mdsd.server.model.Environment;
import mdsd.server.model.ServerModel;
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
    // private Environment environment;

    /**
     * Constructor for the MissionController
     *
     * @param model the ServerModel for the program
     */
    public MissionController(ServerModel model) {
        this.model = model;
    }

    //TODO Method for Mission complete

    /**
     * From a xml-file that is in "mdsdSimultar/missionData.xml" predefined missions are read and will
     * generate a java list of Missions that are assigned to each Rover. The xml-file should be in the
     *
     * @return a java list of Missions.
     */
    public List<Mission> readMissionsXML() {
        List<Mission> missions = new ArrayList<>();
        try {
            // Read the xml-file
            File missionsXml = new File("../mdsd/src/mdsd/server/controller/missionData.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document missionDoc = documentBuilder.parse(missionsXml);

            missionDoc.getDocumentElement().normalize();

            // Get all xml-missions and create java objects from them
            NodeList missionNodes = missionDoc.getElementsByTagName("Mission");
            if (missionNodes.getLength() == 0) {
                throw new Exception("There are no Missions defined in the 'missionData.xml'-file.");
            }
            for (int i = 0; i < missionNodes.getLength(); i++) {
                Mission newMission = new Mission(getPointsFromMissionXML(missionNodes.item(i)));
                missions.add(newMission);
                System.out.println("Mission added");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return missions;
    }

    /**
     * From each mission tag in the XML-file, extract its points and create objects for them according to attributes.
     *
     * @param mission the Mission-tag from the xml-file.
     * @return a java list of Points that belonging to the sent Mission-tag.
     */
    private List<Point> getPointsFromMissionXML(Node mission) {
        List<Point> points = new ArrayList<>();
        NodeList missionPoints = mission.getChildNodes(); // Get points from XML-tag

        for (int i = 0; i < missionPoints.getLength(); i++) {
            Node pointNode = missionPoints.item(i);
            if (pointNode.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlPoint = (Element) pointNode;

                double x = Double.parseDouble(xmlPoint.getAttribute("x"));
                double y = Double.parseDouble(xmlPoint.getAttribute("y"));
                points.add(new Point(x, y));
            }
        }
        return points;
    }


    /**
     * Calls the updateStrategy-method in Mission-class in order to update the strategy
     *
     * @param roverCommunication the communication with a rover
     * @param env                the environment the rovers run in
     */

    private void updateStrategy(RoverCommunication roverCommunication, Environment env) {
        Mission mission = model.getRoverMissions().get(roverCommunication);
        mission.updateStrategy(roverCommunication, env);
    }

    // Checks if a rover is in the same room as objective returns true
    // and at least one rover inside the building return true
    // check boundaries
    public boolean isConstraintFulfilled(RoverCommunication roverCommunication, Area area) {
        return true;

    }


    /**
     * Stops all rovers in the environment
     */
    public void stopAllRovers() {
        this.model.stopRovers();
    }

    /**
     * Starts all rovers in the environment by adding missions to each one of them
     * as well as setting their destination-points
     *
     * @param rovers a list of the RoverCommunication for each rover
     * @param env    the environment the rovers run in
     */
    public void startRovers(Set<RoverCommunication> rovers, Environment env) {


        List<Mission> missions = readMissionsXML();

        // Add all rovers and their missions to the model
        if (rovers.size() == missions.size()) {
            int missionIndex = 0;
            for (RoverCommunication r : rovers) {
                this.model.updateRoverMissions(r, missions.get(missionIndex));
                updateStrategy(r, env);
                missionIndex++;
            }
        }

        System.out.println("START ROVERS" + model.getRoverMissions());

        for (RoverCommunication r : model.getRoverMissions().keySet()) {
            r.setNewDestination((Point) model.getRoverMissions().get(r).iterator().next());
            System.out.println("Test");
        }
    }

}
