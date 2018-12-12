package mdsd;
import java.util.*;

import mdsd.rover.Rover;
import mdsd.rover.RoverCommunication;
import mdsd.rover.RoverNetwork;
import mdsd.server.model.*;
import project.Point;
import project.AbstractSimulatorMonitor;
import simbad.sim.AbstractWall;
import simbad.sim.Boundary;
import simbad.sim.EnvironmentDescription;
import simbad.sim.HorizontalBoundary;
import simbad.sim.HorizontalWall;
import simbad.sim.VerticalBoundary;
import simbad.sim.VerticalWall;
import java.awt.Color;

import mdsd.server.controller.*;
@SuppressWarnings("unused")

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {

		EnvironmentDescription e = new EnvironmentDescription();
		
		Color color = Color.GRAY;
		Color c = Color.blue;

		Boundary w1 = new HorizontalBoundary(-10.0f, -10.0f, 10.0f, e, color);
		Boundary w2 = new HorizontalBoundary(10.0f, -10.0f, 10.0f, e, color);
		Boundary w3 = new VerticalBoundary(10.0f, -10.0f, 10.0f, e, color);
		Boundary w4 = new VerticalBoundary(-10.0f, -10.0f, 10.0f, e, color);

		/*AbstractWall roomWall1 = new HorizontalWall(-1f, 4.5f, 3.5f, e, color);
		AbstractWall roomWall2 = new HorizontalWall(-4.5f, 4.5f, 1f, e, color);
		AbstractWall roomWall3 = new VerticalWall(4.5f, -4.5f, -1f, e, color);
		AbstractWall roomWall4 = new VerticalWall(1f, -4.5f, -1f, e, color);*/

		Environment env = new Environment();
		Area a = env.createArea(5f, 5f, new Point(-2.5, -2.5), "Kitchen", e, "Physical");
		Area b = env.createArea(5f, 5f,new Point(2.5, -2.5), "Living room", e, "Physical");
		Area cc = env.createArea(5f, 5f,new Point(-2.5f, 2.5f), "Bathroom", e, "Physical");
		Area d = env.createArea(5f, 5f,new Point(2.5f, 2.5f), "Bedroom", e, "Physical");

		// Rover creation
		Set<Robot> robots = new HashSet<>();
		//HashMap<Rover, Mission> roverMissions = new HashMap<>();

		Robot robot1 = new Rover(new Point(2.5, 6), "Rover 1");
		Robot robot2 = new Rover(new Point(-2.5, -6), "Rover 2");

		robots.add(robot1);
		robots.add(robot2);

		// Start create Missions


		// End create missions

		// Init rovers
		//roverMissions.put((Rover)robot1, m1);
		//roverMissions.put((Rover)robot2, m2);

		ServerInterface sInter = new ServerModel();

		RoverCommunication rovCom1  = new RoverNetwork(sInter, (Rover)robot1);
		RoverCommunication rovCom2  = new RoverNetwork(sInter, (Rover)robot2);

		AbstractSimulatorMonitor controller = new SimulatorMonitor(robots, e);
		// End init rovers

		// Start rovers
		ServerModel servM = (ServerModel)sInter;
		MissionController mController = new MissionController(servM);

		mController.readMissionsXML();

		mController.startRovers(robots);

		// end start rovers

		//Calls the method to calculate the reward points every 20 seconds
		Timer timer = new Timer();
		timer.schedule(new ProcedureController(robots, env), 0, 20000);



	}

}
