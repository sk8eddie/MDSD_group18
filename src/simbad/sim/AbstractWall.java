package simbad.sim;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import project.Point;

public abstract class AbstractWall extends Wall {

	public AbstractWall(Point pos, float length, float height, EnvironmentDescription wd) {
		super(new Vector3d(pos.getX(), 0, pos.getZ()), length, height, wd);
	}

	public abstract Float getP1x();

	public abstract Float getP1z();

	public abstract Float getP2x();

	public abstract Float getP2z();
	
	
}