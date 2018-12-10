package mdsd;

import java.util.Set;

import mdsd.Robot;
import project.AbstractRobotSimulator;
import project.AbstractSimulatorMonitor;
import simbad.sim.EnvironmentDescription;

public class SimulatorMonitor extends AbstractSimulatorMonitor<Robot> {

	public SimulatorMonitor(Set<? extends Robot> robots, EnvironmentDescription e) {
		super(robots, e);
	}

	

	@Override
	public void update(Robot arg0) {
		System.out.println(arg0.getName());
		System.out.println(arg0.getPosition());
	}

}