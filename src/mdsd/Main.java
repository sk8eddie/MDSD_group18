package mdsd;

import mdsd.rover.Rover;
import mdsd.rover.RoverCommunication;
import mdsd.rover.RoverNetwork;
import mdsd.server.controller.MissionController;
import mdsd.server.controller.ProcedureController;
import mdsd.server.model.Area;
import mdsd.server.model.Environment;
import mdsd.server.model.ServerInterface;
import mdsd.server.model.ServerModel;
import mdsd.server.view.UI;
import project.AbstractSimulatorMonitor;
import project.Point;
import simbad.sim.Boundary;
import simbad.sim.EnvironmentDescription;
import simbad.sim.HorizontalBoundary;
import simbad.sim.VerticalBoundary;

import java.awt.*;
import java.util.*;

@SuppressWarnings("unused")

public class Main implements StartObserver {

    private static UI ui;
    private static MissionController missionController;
    private static Set<RoverCommunication> rovComs;
    private static Environment env;
    private static ServerModel servM;
    private static Map<Area, Integer> environment1;

    @SuppressWarnings("unused")
    public static void main(String[] args) throws InterruptedException {

        // Creates the envirionment
        float width = 20;
        float height = 20;

        EnvironmentDescription e = new EnvironmentDescription();

        Color color = Color.GRAY;
        Color c = Color.blue;


        Boundary w1 = new HorizontalBoundary(-width / 2, -height / 2, height / 2, e, color);
        Boundary w2 = new HorizontalBoundary(width / 2, -height / 2, height / 2, e, color);
        Boundary w3 = new VerticalBoundary(width / 2, -height / 2, height / 2, e, color);
        Boundary w4 = new VerticalBoundary(-width / 2, -height / 2, height / 2, e, color);


        // Creates areas for the second environment of the assignments
        env = new Environment();
        Area c1 = env.createArea(new Point(0, 0), "Consulting", e, c);
        Area h1 = env.createArea(new Point(3, 0), "Hall", e, c);
        Area h2 = env.createArea(new Point(0, 3), "Hall", e, c);
        Area h3 = env.createArea(new Point(-3, 0), "Hall", e, c);
        Area h4 = env.createArea(new Point(0, -3), "Hall", e, c);
        Area s1 = env.createArea(new Point(6, 0), "Surgery", e, c);
        Area s2 = env.createArea(new Point(0, 6), "Surgery", e, c);
        Area s3 = env.createArea(new Point(-6, 0), "Surgery", e, c);
        Area s4 = env.createArea(new Point(0, -6), "Surgery", e, c);


        // Creates areas for the first environment of the assignments
        /*
		Area a = env.createArea(5f, 5f, new Point(-2.5, -2.5), "Demo", e, "Physical");
		Area b = env.createArea(5f, 5f,new Point(2.5, -2.5), "Demo", e, "Physical");
		Area cc = env.createArea(5f, 5f,new Point(-2.5f, 2.5f), "Demo", e, "Physical");
		Area d = env.createArea(5f, 5f,new Point(2.5f, 2.5f), "Demo", e, "Physical");
		*/


        // TODO add points to areas instead
        // List containing the points gained for being in each area
        environment1 = new HashMap<Area, Integer>();
        environment1.put(c1, 10);
        environment1.put(h1, 0);
        environment1.put(h2, 0);
        environment1.put(h3, 0);
        environment1.put(h4, 0);
        environment1.put(s1, 20);
        environment1.put(s2, 20);
        environment1.put(s3, 20);
        environment1.put(s4, 20);

        //End init environment

        // Rover creation
        Set<Robot> robots = new HashSet<>();

        Robot robot1 = new Rover(new Point(6, 0), "Rover 1");
        Robot robot2 = new Rover(new Point(-6, 0), "Rover 2");
        Robot robot3 = new Rover(new Point(0, 6), "Rover 3");
        Robot robot4 = new Rover(new Point(0, -6), "Rover 4");

        robots.add(robot1);
        robots.add(robot2);
        robots.add(robot3);
        robots.add(robot4);


        // Creates communication for each rover
        ServerInterface sInter = new ServerModel(env);

        RoverCommunication rovCom1 = new RoverNetwork(sInter, (Rover) robot1);
        RoverCommunication rovCom2 = new RoverNetwork(sInter, (Rover) robot2);
        RoverCommunication rovCom3 = new RoverNetwork(sInter, (Rover) robot3);
        RoverCommunication rovCom4 = new RoverNetwork(sInter, (Rover) robot4);


        rovComs = new HashSet<>();
        rovComs.add(rovCom1);
        rovComs.add(rovCom2);
        rovComs.add(rovCom3);
        rovComs.add(rovCom4);

        AbstractSimulatorMonitor controller = new SimulatorMonitor(robots, e);
        // End init rovers

        // Start rovers
        servM = (ServerModel) sInter;
        missionController = new MissionController(servM);


        ui = new UI(missionController, new Main(), servM);

        ui.createFrame();
    }




    @Override
    public void start() {
        missionController.startRovers(rovComs, env);
        // end start rovers

        //Calls the method to calculate the reward points every 20 seconds
        Timer timer = new Timer();
        timer.schedule(new ProcedureController(rovComs, env, environment1, servM, ui), 20000, 20000);
    }
}
