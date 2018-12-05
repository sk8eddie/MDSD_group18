package mdsd.server.model;

import project.Point;
import simbad.sim.AbstractWall;
import simbad.sim.EnvironmentDescription;
import simbad.sim.HorizontalWall;
import simbad.sim.VerticalWall;

import java.awt.*;

public class PhysicalArea implements Area{

    private EnvironmentDescription e = new EnvironmentDescription();
    private Color c = Color.blue;

    private String areaType;
    private String areaName;
    private Point corner1;
    private Point corner2;

    public PhysicalArea (float xOffset, float zOffset, String name){

        this.areaType = "Physical";
        this.areaName = name;

        if (xOffset < 0) {

            AbstractWall hw11 = new HorizontalWall(2.5f + xOffset, 2.5f + zOffset, 1.5f + zOffset, e, c);
            AbstractWall hw12 = new HorizontalWall(2.5f + xOffset, -1.5f + zOffset, -2.5f + zOffset, e, c);
            AbstractWall hw2 = new HorizontalWall(-2.5f + xOffset, 2.5f + zOffset, -2.5f + zOffset, e, c);

            AbstractWall vw11 = new VerticalWall(2.5f + zOffset, 2.5f + xOffset, 1.5f + xOffset, e, c);
            AbstractWall vw12 = new VerticalWall(2.5f + zOffset, -1.5f + xOffset, -2.5f + xOffset, e, c);

            AbstractWall vw21 = new VerticalWall(-2.5f + zOffset, 2.5f + xOffset, 1.5f + xOffset, e, c);
            AbstractWall vw22 = new VerticalWall(-2.5f + zOffset, -1.5f + xOffset, -2.5f + xOffset, e, c);
        }
        else {
            AbstractWall hw1 = new HorizontalWall(2.5f + xOffset, 2.5f + zOffset, -2.5f + zOffset, e, c);
            AbstractWall hw21 = new HorizontalWall(-2.5f + xOffset, 2.5f + zOffset, 1.5f + zOffset, e, c);
            AbstractWall hw22 = new HorizontalWall(-2.5f + xOffset, -1.5f + zOffset, -2.5f + zOffset, e, c);

            AbstractWall vw11 = new VerticalWall(2.5f + zOffset, 2.5f + xOffset, 1.5f + xOffset, e, c);
            AbstractWall vw12 = new VerticalWall(2.5f + zOffset, -1.5f + xOffset, -2.5f + xOffset, e, c);

            AbstractWall vw21 = new VerticalWall(-2.5f + zOffset, 2.5f + xOffset, 1.5f + xOffset, e, c);
            AbstractWall vw22 = new VerticalWall(-2.5f + zOffset, -1.5f + xOffset, -2.5f + xOffset, e, c);
        }
        corner1 = new Point(2.5+xOffset, 2.5+zOffset);
        corner2 = new Point(-2.5+xOffset, -2.5+zOffset);
    }


    @Override
    public Area createArea(Point position, String areaName) {
        return new PhysicalArea((float)position.getX(), (float)position.getZ(), areaName);
    }

    @Override
    public String getAreaType() {
        return areaType;
    }

    @Override
    public boolean inArea(Point coordinate) {
        return this.corner2.getX() < coordinate.getX() && coordinate.getX() < this.corner1.getX() && this.corner2.getZ() < coordinate.getZ() && coordinate.getZ() < this.corner1.getZ();
    }

    public String getAreaName(){
        return this.areaName;
    }
}
