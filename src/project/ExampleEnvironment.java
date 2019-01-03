package project;

import java.util.Set;

import simbad.sim.EnvironmentDescription;
import simbad.sim.StaticObject;

public class ExampleEnvironment extends EnvironmentDescription {

	public ExampleEnvironment(Set<StaticObject> objects) {

		// turn on the lights
		this.light1IsOn = true;
		this.light2IsOn = true;

		// enable the physics engine in order to have better physics effects on
		// the objects
		this.setUsePhysics(true);

		// show the axes so that we know where things are
		this.showAxis(true);

		this.setWorldSize(20);

	}
}
