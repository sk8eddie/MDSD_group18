package simbad.sim;

import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

public abstract class Boundary extends Box {

	public Boundary(Vector3d v1, Vector3f v2, EnvironmentDescription wd) {
		super(v1, v2, wd);
	}

	public abstract Float getP1x();

	public abstract Float getP1z();

	public abstract Float getP2x();

	public abstract Float getP2z();
}
