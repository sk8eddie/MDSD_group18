package simbad.sim;

import java.awt.Color;

import javax.media.j3d.BoundingSphere;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

import project.Point;


/**
 * A wall which can be put in the environnement.
 */
public class HorizontalWall extends AbstractWall {

	private Float p1x;
	private Float p1z;
	private Float p2z;
	private static final float height = 2;

	public HorizontalWall(Float p1x, Float p1z, Float p2z, EnvironmentDescription wd, Color c) {
		super(new Point(p1x, p1z+(p2z - p1z)/2), p2z - p1z, height,  wd);
		setColor(new Color3f(c));
		this.rotate90(1);
		this.p1x=p1x;
		this.p1z=p1z;
		this.p2z=p2z;
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
		return p1x;
	}

	public Float getP2z() {
		return p2z;
	}

protected boolean intersect( BoundingSphere obj) {
		
		Point3d center=new Point3d();
		obj.getCenter(center);
//		 double radiussq = bs.getRadius()*bs.getRadius(); bb.getLower(p1);
	//	 * bb.getUpper(p2); bs.getCenter(p3); double xmin = Math.min(p1.x,p2.x);
		
		double zmin=center.getZ()-obj.getRadius();
		double zmax=center.getZ()+obj.getRadius();
		double xmin=center.getX()-obj.getRadius();
		double xmax=center.getX()+obj.getRadius();
		
		
		return (((p1x>xmin && p1x<xmax) || (p1x<xmin && p1x>xmax)) && (((center.getZ()<p1z) &&(center.getZ()>p2z)) || (((center.getZ()>p1z) &&(center.getZ()<p2z)))));
		
	}
}
