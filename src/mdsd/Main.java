package mdsd;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
import java.util.Timer;

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

		Set<Robot> robots = new HashSet<>();
		Robot robot1 = new Robot(new Point(2.5, 6), "Robot 1");
		Robot robot2 = new Robot(new Point(-2.5, -6), "Robot 2");

		robots.add(robot1);
		robots.add(robot2);
		
				
		AbstractSimulatorMonitor controller = new SimulatorMonitor(robots, e);

		Timer timer = new Timer();
		timer.schedule(new ProcedureController(robots, env), 0, 20000);

	}

}
