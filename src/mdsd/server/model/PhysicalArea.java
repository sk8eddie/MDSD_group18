package mdsd.server.model;

import project.Point;
import simbad.sim.AbstractWall;
import simbad.sim.EnvironmentDescription;
import simbad.sim.HorizontalWall;
import simbad.sim.VerticalWall;

import java.awt.*;

public class PhysicalArea implements Area{

    private Color c = Color.blue;

    private String areaType;
    private String areaName;
    private Point corner1;
    private Point corner2;

    PhysicalArea (float width, float height, float xOffset, float zOffset, String name, EnvironmentDescription e, String areaType){

        this.areaType = areaType;
        this.areaName = name;

        if (xOffset < 0) {

            AbstractWall hw11 = new HorizontalWall((height/2) + xOffset, (width/2) + zOffset, (width/2)-1 + zOffset, e, c);
            AbstractWall hw12 = new HorizontalWall((height/2) + xOffset, -(width/2)+1 + zOffset, -(width/2) + zOffset, e, c);
            AbstractWall hw2 = new HorizontalWall(-(height/2) + xOffset, (width/2) + zOffset, -(width/2) + zOffset, e, c);

            AbstractWall vw11 = new VerticalWall((width/2) + zOffset, (height/2) + xOffset, (height/2)-1 + xOffset, e, c);
            AbstractWall vw12 = new VerticalWall((width/2) + zOffset, -(height/2)+1 + xOffset, -(height/2) + xOffset, e, c);

            AbstractWall vw21 = new VerticalWall(-(width/2) + zOffset, (height/2) + xOffset, (height/2)-1 + xOffset, e, c);
            AbstractWall vw22 = new VerticalWall(-(width/2) + zOffset, -(height/2)+1 + xOffset, -(height/2) + xOffset, e, c);
        }
        else {
            AbstractWall hw1 = new HorizontalWall((height/2) + xOffset, (width/2) + zOffset, -(width/2) + zOffset, e, c);
            AbstractWall hw21 = new HorizontalWall(-(height/2) + xOffset, (width/2) + zOffset, (width/2)-1 + zOffset, e, c);
            AbstractWall hw22 = new HorizontalWall(-(height/2) + xOffset, -(width/2)+1 + zOffset, -(width/2) + zOffset, e, c);

            AbstractWall vw11 = new VerticalWall((width/2) + zOffset, (height/2) + xOffset, (height/2)-1 + xOffset, e, c);
            AbstractWall vw12 = new VerticalWall((width/2) + zOffset, -(height/2)+1 + xOffset, -(height/2) + xOffset, e, c);

            AbstractWall vw21 = new VerticalWall(-(width/2) + zOffset, (height/2) + xOffset, (height/2)-1 + xOffset, e, c);
            AbstractWall vw22 = new VerticalWall(-(width/2) + zOffset, -(height/2)+1 + xOffset, -(height/2) + xOffset, e, c);
        }
        corner1 = new Point((height/2)+xOffset, (width/2)+zOffset);
        corner2 = new Point(-(height/2)+xOffset, -(width/2)+zOffset);
    }


    @Override
    public String getAreaType() {
        return areaType;
    }

    @Override
    public boolean inArea(Point coordinate) {
        return this.corner2.getX() < coordinate.getX() && coordinate.getX() < this.corner1.getX() && this.corner2.getZ() < coordinate.getZ() && coordinate.getZ() < this.corner1.getZ();
    }

    @Override
    public String getAreaName(){
        return this.areaName;
    }

}
