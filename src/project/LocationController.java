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
 * 07/02/2005 - now use Box Primitive instead of QuadArray
 * $Author: sioulseuguh $ 
 * $Date: 2005/04/25 17:54:58 $
 * $Revision: 1.7 $
 * $Source: /cvsroot/simbad/src/simbad/sim/Box.java,v $
 */
package project;

import javax.media.j3d.*;
import java.awt.Color;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import com.sun.j3d.utils.geometry.Primitive;

import simbad.sim.BlockWorldObject;
import simbad.sim.Box;
import simbad.sim.EnvironmentDescription;
/** Controller governing access to an area
 * 
 */
public class LocationController {
    
    /** Object dimension.s */
	final float size   = 0.2f;
	final float height = 1f;
    Point pos;
    double radius;
    Box b;
    boolean occupied = false; 
    
    AbstractRobotSimulator owner = null;

    /**
     * CONSTRUCTOR
     * Creates a new location controller at a specified point
     * working within a given radius in a given environment
     * 
     * @param pos
     * 			the position of the controller
     * @param radius
     * 			the radius within which it is possible
     * 			communicate with the controller
     * @param wd
     * 			the environment
     */
    public LocationController(Point pos, double radius, EnvironmentDescription wd) {        
    	b = new Box(new Vector3d(pos.getX(), height, pos.getZ()),
    			    new Vector3f(size, height, size),
    			    wd,
    			    new Color3f(Color.BLUE));
    	wd.add(b);
    	this.pos = pos;
    	this.radius = radius;
    }

    /**
     * Try to get a permission to enter the area. The method should
     * only be called by a robot within range.
     * 
     * @param a
     * 			the robot
     * @return true if successful, false if unsuccessful
     */
    public boolean tryAcquire (AbstractRobotSimulator a) {
    	if(a.getPosition().dist(pos) > radius) {
    		System.out.println("loc: " + pos + ", rb: " + a.getPosition());
    		System.out.println("dist: " + a.getPosition().dist(pos));
    		System.out.println("Warning: " + a + " tries to acquire location permission out of range");
    		return false;
    	}
    	if (occupied) {
    		return false;
    	} else {
    		occupied = true;
    		owner = a;
    		// Unfortunately, changing the colour dynamically
    		// seems to be impossible
//    		b.setColor(new Color3f(Color.RED));
    		return true;
    	}
    }

    /**
     * Release the permission after having left the area. The method should only
     * be called by a robot within the range.
     * @param a
     * 			the robot
     */
    public void release (AbstractRobotSimulator a) {
    	if(a != owner) {
    		System.out.println("Warning: " + a + " tries to acquire location permission without owning it");
    		return;
    	}
    	if(a.getPosition().dist(pos) > radius * 1.2) {
    		System.out.println("Warning: " + a + " tries to release location permission out of range");
    		return;
    	}
    	occupied = false;
    	owner = null;
    	//b.setColor(new Color3f(Color.WHITE));
    }

}

