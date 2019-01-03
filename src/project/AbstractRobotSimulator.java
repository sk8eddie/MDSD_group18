package project;

public abstract class AbstractRobotSimulator {

	private RobotAgent agent;

	/**
	 * creates a new robot at the specified position with the given name
	 * 
	 * @param position
	 *            the position of the robot
	 * @param name
	 *            the name of the robot
	 * @throws NullPointerException
	 *             if the position is null
	 * @throws NullPointerException
	 *             if the name of the robot is null
	 */
	public AbstractRobotSimulator(Point position, String name) {
		if (name == null) {
			throw new NullPointerException("The name of the robot cannot be null");
		}
		if (position == null) {
			throw new NullPointerException("The position of the robot cannot be null");
		}
		agent = new RobotAgent(position, name);
	}

	/**
	 * set the destination of the robot
	 * 
	 * @param destination
	 *            the destination of the robot
	 * @throws NullPointerException
	 *             if the destination is null
	 */
	public void setDestination(Point destination) {
		if (destination == null) {
			throw new NullPointerException("The destination cannot be null");
		}
		agent.setDestination(destination);
	}

	/**
	 * returns the position of the robot
	 * 
	 * @return the position of the robot
	 */
	public Point getPosition() {
		return new Point(agent.getPosition().x, agent.getPosition().z);
	}

	protected void setController(AbstractSimulatorMonitor<? extends AbstractRobotSimulator> controller) {
		if (controller == null) {
			throw new NullPointerException("The controller cannot be null");
		}
		agent.setController(controller);

	}

	protected RobotAgent getAgent() {
		return this.agent;
	}

	/**
	 * returns the name of the robot
	 * 
	 * @return the name of the robot
	 */
	public String getName() {
		return agent.getName();
	}

	/**
	 * returns true if and only if the robot is at the specified destination
	 * 
	 * @param dest
	 *            the destination of the robot
	 * @return true if and only if the robot is at the specified destination
	 * @throws NullPointerException
	 *             if the destination point is null
	 */
	public boolean isAtPosition(Point dest) {
		if (dest == null) {
			throw new NullPointerException("The destination cannot be null");
		}
		return agent.isAtPosition(dest);
	}

	/**
	 * returns true if and only if an obstacle is detected by the proximity
	 * sensors
	 * 
	 * @return true if and only if an obstacle is detected by the proximity
	 *         sensors
	 */
	public boolean checkObstacle() {
		return (agent.anOtherAgentIsVeryNear() || agent.collisionDetected());
	}

	/**
	 * returns true if and only if an obstacle is detected by the camera
	 * 
	 * 
	 * @return true if and only if an obstacle is detected by the camera
	 */
	public boolean checkCameraDetection() {
		return agent.anOtherAgentIsVeryNear();
	}

	/**
	 * returns true if and only if an another agent is very near using the
	 * camera sensors
	 * 
	 * @return true if and only if an another agent is very near using the
	 *         camera sensors
	 */
	public boolean checkAgent() {
		return agent.anOtherAgentIsVeryNear();
	}

}
