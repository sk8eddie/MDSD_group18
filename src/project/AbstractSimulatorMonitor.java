package project;

import java.util.Set;

import simbad.gui.Simbad;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Simulator;

public abstract class AbstractSimulatorMonitor<R extends AbstractRobotSimulator> {

	public AbstractSimulatorMonitor(Set<R> robots, EnvironmentDescription e) {
		System.out.println("*********************");
		// request antialising so that diagonal lines are not "stairy"
		System.setProperty("j3d.implicitAntialiasing", "true");

		// creation of the environment containing all obstacles and robots
		// EnvironmentDescription environment = new
		// ExampleEnvironment(b.gerObjects());

		robots.forEach((r) -> {
			e.add(r.getAgent());
			r.getAgent().setAgentSimulator(r);
			r.setController(this);
		});

		// environment.add

		// here we create an instance of the whole Simbad simulator and we
		// assign the newly created environment
		Simbad frame = new Simbad(e, false);
		frame.update(frame.getGraphics());

		Simulator sim = frame.getSimulator();
		sim.startSimulation();
	}

	/**
	 * Called every time step for every robot registered in the environment
	 * 
	 * @param robot
	 *            the robot whose state has been updated by the simulator
	 */
	public abstract void update(R robot);
}
