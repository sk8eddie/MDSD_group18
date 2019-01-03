/*
 *  Simbad - Robot Simulator
 *  Copyright (C) 2004 Louis Hugues
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, 
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 -----------------------------------------------------------------------------
 * $Author: sioulseuguh $ 
 * $Date: 2005/04/25 17:54:59 $
 * $Revision: 1.6 $
 * $Source: /cvsroot/simbad/src/simbad/sim/Wall.java,v $
 * 
 * MODIF - LH 02 oct 2006 - add a constructor with width.
 */
package simbad.sim;

import java.awt.Color;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * A wall which can be put in the environnement.
 */
public class VerticalBoundary extends Boundary {
	final static float thickness = 0.3f;

	private Float p1x;
	private Float p2x;
	private Float p1z;
	private static final float height = 2;

	public VerticalBoundary(Float p1z, Float p1x, Float p2x, EnvironmentDescription wd, Color c) {
		super(new Vector3d(p1x+(p2x - p1x)/2, 0, p1z), new Vector3f(p2x - p1x, height, thickness), wd);
		setColor(new Color3f(c));
		this.p1x=p1x;
		this.p1z=p1z;
		this.p2x=p2x;
		wd.add(this);
	}

	public VerticalBoundary(Vector3d pos, float length, float width, float height, EnvironmentDescription wd) {
		super(pos, new Vector3f(length, height, width), wd);
		setColor(wd.wallColor);
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

}
