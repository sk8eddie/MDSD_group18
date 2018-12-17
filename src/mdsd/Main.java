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

		float width = 20;
		float height = 20;

		EnvironmentDescription e = new EnvironmentDescription();
		
		Color color = Color.GRAY;
		Color c = Color.blue;

		Boundary w1 = new HorizontalBoundary(-width/2, -height/2, height/2, e, color);
		Boundary w2 = new HorizontalBoundary(width/2, -height/2, height/2, e, color);
		Boundary w3 = new VerticalBoundary(width/2, -height/2, height/2, e, color);
		Boundary w4 = new VerticalBoundary(-width/2, -height/2, height/2, e, color);

		//Gridcell[][] cells = new Gridcell[(int)(Math.abs(w1.getP1x()) + Math.abs(w1.getP2x()))][(int)(Math.abs(w1.getP1z()) + Math.abs(w1.getP2z()))];

		/*AbstractWall roomWall1 = new HorizontalWall(-1f, 4.5f, 3.5f, e, color);
		AbstractWall roomWall2 = new HorizontalWall(-4.5f, 4.5f, 1f, e, color);
		AbstractWall roomWall3 = new VerticalWall(4.5f, -4.5f, -1f, e, color);
		AbstractWall roomWall4 = new VerticalWall(1f, -4.5f, -1f, e, color);*/

		Environment env = new Environment();
		Area c1 = env.createArea(new Point(0,0), "Consulting", e, c);
		Area h1 = env.createArea(new Point(3,0), "Hall", e, c);
		Area h2 = env.createArea(new Point(0,3), "Hall", e, c);
		Area h3 = env.createArea(new Point(-3,0), "Hall", e, c);
		Area h4 = env.createArea(new Point(0,-3), "Hall", e, c);
		Area s1 = env.createArea(new Point(6,0), "Surgery", e, c);
		Area s2 = env.createArea(new Point(0,6), "Surgery", e, c);
		Area s3 = env.createArea(new Point(-6,0), "Surgery", e, c);
		Area s4 = env.createArea(new Point(0,-6), "Surgery", e, c);


		/*
		Area a = env.createArea(5f, 5f, new Point(-2.5, -2.5), "Demo", e, "Physical");
		Area b = env.createArea(5f, 5f,new Point(2.5, -2.5), "Demo", e, "Physical");
		Area cc = env.createArea(5f, 5f,new Point(-2.5f, 2.5f), "Demo", e, "Physical");
		Area d = env.createArea(5f, 5f,new Point(2.5f, 2.5f), "Demo", e, "Physical");
		*/

		// Rover creation
		Set<Robot> robots = new HashSet<>();
		//HashMap<Rover, Mission> roverMissions = new HashMap<>();

		Robot robot1 = new Rover(new Point(6, 0), "Rover 1");
		Robot robot2 = new Rover(new Point(-6, 0), "Rover 2");

		robots.add(robot2);


		// TODO add points to areas instead
		// List containing the points gained for being in each area
		Map<Area, Integer> environment1 = new HashMap<Area, Integer>();
		environment1.put(c1, 10);
		environment1.put(h1, 0);
		environment1.put(h2, 0);
		environment1.put(h3, 0);
		environment1.put(h4, 0);
		environment1.put(s1, 20);
		environment1.put(s2, 20);
		environment1.put(s3, 20);
		environment1.put(s4, 20);

				

		robots.add(robot1);

		// Start create Missions


		// End create missions

		// Init rovers
		//roverMissions.put((Rover)robot1, m1);
		//roverMissions.put((Rover)robot2, m2);

		ServerInterface sInter = new ServerModel();

		RoverCommunication rovCom1  = new RoverNetwork(sInter, (Rover)robot1);
		RoverCommunication rovCom2  = new RoverNetwork(sInter, (Rover)robot2);

		Set<RoverCommunication> rovComs = new HashSet<>();
        rovComs.add(rovCom2);
		rovComs.add(rovCom1);



		AbstractSimulatorMonitor controller = new SimulatorMonitor(robots, e);
		// End init rovers

		// Start rovers
		ServerModel servM = (ServerModel)sInter;
		MissionController mController = new MissionController(servM);

		mController.readMissionsXML();

		mController.startRovers(rovComs);

		// end start rovers

		//Calls the method to calculate the reward points every 20 seconds
		Timer timer = new Timer();
		timer.schedule(new ProcedureController(robots, env, environment1, servM), 0, 20000);



	}

}
