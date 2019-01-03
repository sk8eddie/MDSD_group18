package simbad.sim;

import java.awt.Color;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import project.Point;

/**
 * A wall which can be put in the environnement.
 */
public class VerticalWall extends AbstractWall {

	private Float p1x;
	private Float p2x;
	private Float p1z;
	private static final float height = 2;

	public VerticalWall(Float p1z, Float p1x, Float p2x, EnvironmentDescription wd, Color c) {
		super(new Point(p1x + (p2x - p1x) / 2, p1z), p2x - p1x, height, wd);
		setColor(new Color3f(c));
		this.p1x = p1x;
		this.p1z = p1z;
		this.p2x = p2x;
		this.setCanBeTraversed(false);
		wd.add(this);
	}

	public Float getP1x() {
		return p1x;
	}

	public Float getP1z() {
		return p1z;
	}

	public Float getP2x() {
		return p2x;
	}

	public Float getP2z() {
		return p1z;
	}

	protected boolean intersect(BoundingSphere obj) {

		Point3d center = new Point3d();
		obj.getCenter(center);
		// double radiussq = bs.getRadius()*bs.getRadius(); bb.getLower(p1);
		// * bb.getUpper(p2); bs.getCenter(p3); double xmin =
		// Math.min(p1.x,p2.x);

		double zmin = center.getZ() - obj.getRadius();
		double zmax = center.getZ() + obj.getRadius();
		double xmin = center.getX() - obj.getRadius();
		double xmax = center.getX() + obj.getRadius();

		return (((p1z > zmin && p1z < zmax) || (p1z < zmin && p1z > zmax))
				&& (((center.getX() < p1x) && (center.getX() > p2x))
						|| (((center.getX() > p1x) && (center.getX() < p2x)))));

	}
}