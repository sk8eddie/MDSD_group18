package project;

import javax.vecmath.Vector3d;


import simbad.sim.Agent;
import simbad.sim.RobotFactory;

public class RobotAgent<R extends AbstractRobotSimulator> extends Agent {

	// private String currentMode;
	private boolean angleSwitch;
	private double MIN_DIST = 0.1; //0.1
	private Point destination;
	private double angle;

	private R agentSimulator;
	private AbstractSimulatorMonitor<R> controller;

	public RobotAgent(Point position, String name) {
		super(new Vector3d(position.getX(), 0, position.getZ()), name);
		this.angleSwitch=true;
		this.destination = position;
		this.angle=0;
		// Add bumpers
		RobotFactory.addBumperBeltSensor(this, 12);
		// Add sonars
		RobotFactory.addSonarBeltSensor(this, 4);
	}

	public void setDestination(Point destination) {
		this.destination = destination;
	}

	public void setController(AbstractSimulatorMonitor<R> controller) {
		this.controller = controller;

	}

	/** This method is called by the simulator engine on reset. */
	public void initBehavior() {
		System.out.println("I exist and my name is " + this.name);
	}

	/**
	 * This method is call cyclically (20 times per second) by the simulator
	 * engine.
	 */
	public void performBehavior() {

		Vector3d position = this.getPosition();

		if (!isAtPosition(this.destination)) { //-89.52713762190251 - down //
			
			if (angleSwitch){
			angle = -Math.atan2((destination.getZ() - position.z), (destination.getX() - position.x)) * 180
					/ Math.PI;
			angleSwitch=false;
			}
			this.rotateY(Math.toRadians(angle));
			this.setTranslationalVelocity(1);
		} else {
			angleSwitch=true;
			this.setTranslationalVelocity(0);
			this.setRotationalVelocity(0);
		}

		controller.update(agentSimulator);

	}

	public boolean isAtPosition(Point dest) {
		Vector3d position = this.getPosition();

		if ((Math.abs(dest.getZ() - position.z)) > MIN_DIST || ((Math.abs(dest.getX() - position.x)) > MIN_DIST)) {
			return false;
		}
		return true;
	}

	protected AbstractRobotSimulator getAgentSimulator() {
		return agentSimulator;
	}

	protected void setAgentSimulator(R agentSimulator) {
		this.agentSimulator = agentSimulator;
	}
}