package mdsd;
import java.util.*;

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

		Set<Robot> robots = new HashSet<>();
		Robot robot1 = new Robot(new Point(2.5, 6), "Robot 1");
		Robot robot2 = new Robot(new Point(-2.5, -6), "Robot 2");

		robots.add(robot1);
		robots.add(robot2);

		// List containing the points gained for being in each area
		Map<Area, Integer> environment1 = new HashMap<Area, Integer>();
		environment1.put(a, 1);
		environment1.put(b, 2);
		environment1.put(cc, 1);
		environment1.put(d, 2);
				
		AbstractSimulatorMonitor controller = new SimulatorMonitor(robots, e);

		//Calls the method to calculate the reward points every 20 seconds
		Timer timer = new Timer();
		//timer.schedule(new ProcedureController(robots, env, environment1), 0, 20000);

	}

}
