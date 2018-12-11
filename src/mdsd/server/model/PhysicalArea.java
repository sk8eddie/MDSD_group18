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

    PhysicalArea (float width, float height, float xOffset, float zOffset, String name, EnvironmentDescription e, String areaType) {

        this.areaType = areaType;
        this.areaName = name;

        switch (areaName) {
            case "consulting":
                AbstractWall hw1 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1.5f + zOffset, e, c);
                AbstractWall hw2 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 1.5f + zOffset, -(width / 2) + zOffset, e, c);
                AbstractWall hw3 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1.5f + zOffset, e, c);
                AbstractWall hw4 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 1.5f + zOffset, -(width / 2) + zOffset, e, c);
                AbstractWall vw1 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1.5f + xOffset, e, c);
                AbstractWall vw2 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 1.5f + xOffset, -(height / 2) + xOffset, e, c);
                AbstractWall vw3 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1.5f + xOffset, e, c);
                AbstractWall vw4 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 1.5f + xOffset, -(height / 2) + xOffset, e, c);
                break;
            case "hall":
                if (width > height) {
                    AbstractWall hw11 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw12 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw11 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.5f + xOffset, e, c);
                    AbstractWall vw12 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 0.5f + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw13 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.5f + xOffset, e, c);
                    AbstractWall vw14 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 0.5f + xOffset, -(height / 2) + xOffset, e, c);
                } else {
                    AbstractWall hw11 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.5f + zOffset, e, c);
                    AbstractWall hw12 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 0.5f + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw13 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.5f + zOffset, e, c);
                    AbstractWall hw14 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 0.5f + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw11 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw12 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                }
                break;
            case "surgery":
                if (xOffset < -3) {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.75f + zOffset, e, c);
                    AbstractWall hw22 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 0.75f + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw23 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw21 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw22 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                } else if (xOffset > 3) {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw22 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.75f + zOffset, e, c);
                    AbstractWall hw23 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 0.75f + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw21 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw22 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                } else if (zOffset < -3) {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw22 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw21 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.75f + xOffset, e, c);
                    AbstractWall vw22 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 0.75f + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw23 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                } else {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw22 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw23 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw24 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.75f + xOffset, e, c);
                    AbstractWall vw25 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 0.75f + xOffset, -(height / 2) + xOffset, e, c);
                }
                break;

            case "Office" :
                if (xOffset < 0 ) {

                    AbstractWall hw31 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1 + zOffset, e, c);
                    AbstractWall hw32 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 1 + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw33 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw31 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    AbstractWall vw32 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw33 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    AbstractWall vw34 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                } else {
                    AbstractWall hw31 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall hw32 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1 + zOffset, e, c);
                    AbstractWall hw33 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 1 + zOffset, -(width / 2) + zOffset, e, c);
                    AbstractWall vw31 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    AbstractWall vw32 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                    AbstractWall vw33 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    AbstractWall vw34 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                }
                break;
        }
        corner1 = new Point((height / 2) + xOffset, (width / 2) + zOffset);
        corner2 = new Point(-(height / 2) + xOffset, -(width / 2) + zOffset);
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
