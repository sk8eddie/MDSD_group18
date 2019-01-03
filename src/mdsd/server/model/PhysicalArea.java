package mdsd.server.model;

import project.Point;
import simbad.sim.*;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for PhysicalArea, i.e. Consulting room
 */
public class PhysicalArea implements Area{

    private String areaType;
    private String areaName;
    private Point corner1;
    private Point corner2;
    private List<AbstractWall> wallList = new ArrayList<>();
    private HashMap<Point, Lock> entryPoint;
    private HashMap<Point, Lock> exitPoint;

    /**
     * Constructor for Physical Area. Converts witdh and height with the offsets to place Area in the correct position.
     * On case for each class of Area and different layouts depending on where it is placed.
     * @param width Width of Area.
     * @param height Height of Area.
     * @param xOffset Offset on the x-axis.
     * @param zOffset Offset on the z-axis.
     * @param name Name of the Area.
     * @param e Environment desription.
     * @param areaType Type of Area (always Physical).
     * @param c Color of walls.
     */
    PhysicalArea (float width, float height, float xOffset, float zOffset, String name, EnvironmentDescription e, String areaType, Color c) {

        this.areaType = areaType;
        this.areaName = name;
        entryPoint = new HashMap<>();
        exitPoint = new HashMap<>();

        switch (areaName) {
            case "Consulting":
                AbstractWall hw1 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1.5f + zOffset, e, c);
                wallList.add(hw1);
                AbstractWall hw2 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 1.5f + zOffset, -(width / 2) + zOffset, e, c);
                wallList.add(hw2);
                AbstractWall hw3 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1.5f + zOffset, e, c);
                wallList.add(hw3);
                AbstractWall hw4 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 1.5f + zOffset, -(width / 2) + zOffset, e, c);
                wallList.add(hw4);
                AbstractWall vw1 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1.5f + xOffset, e, c);
                wallList.add(vw1);
                AbstractWall vw2 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 1.5f + xOffset, -(height / 2) + xOffset, e, c);
                wallList.add(vw2);
                AbstractWall vw3 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1.5f + xOffset, e, c);
                wallList.add(vw3);
                AbstractWall vw4 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 1.5f + xOffset, -(height / 2) + xOffset, e, c);
                wallList.add(vw4);

                Lock con = new ReentrantLock(true);
                entryPoint.put(new Point((height / 2) + xOffset + 0.5, zOffset), con);
                entryPoint.put(new Point(-(height / 2) + xOffset - 0.5, zOffset), con);
                entryPoint.put(new Point(xOffset, (width / 2) + zOffset + 0.5), con);
                entryPoint.put(new Point(xOffset, -(width / 2) + zOffset - 0.5), con);
                exitPoint.put(new Point((height / 2) + xOffset - 0.5, zOffset), con);
                exitPoint.put(new Point(-(height / 2) + xOffset + 0.5, zOffset), con);
                exitPoint.put(new Point(xOffset, (width / 2) + zOffset - 0.5), con);
                exitPoint.put(new Point(xOffset, -(width / 2) + zOffset + 0.5), con);
                break;
            case "Hall":
                Lock hall = new ReentrantLock(true);
                if (Math.abs(zOffset) > Math.abs(xOffset)) {
                    AbstractWall hw11 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw11);
                    AbstractWall hw12 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw12);
                    AbstractWall vw11 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.5f + xOffset, e, c);
                    wallList.add(vw11);
                    AbstractWall vw12 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 0.5f + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw12);
                    AbstractWall vw13 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.5f + xOffset, e, c);
                    wallList.add(vw13);
                    AbstractWall vw14 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 0.5f + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw14);
                    entryPoint.put(new Point(xOffset, (width / 2) + zOffset + 0.5), hall);
                    entryPoint.put(new Point(xOffset, -(width / 2) + zOffset - 0.5), hall);
                    exitPoint.put(new Point(xOffset, (width / 2) + zOffset - 0.5), hall);
                    exitPoint.put(new Point(xOffset, -(width / 2) + zOffset + 0.5), hall);
                } else {
                    AbstractWall hw11 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.5f + zOffset, e, c);
                    wallList.add(hw11);
                    AbstractWall hw12 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 0.5f + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw12);
                    AbstractWall hw13 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.5f + zOffset, e, c);
                    wallList.add(hw13);
                    AbstractWall hw14 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 0.5f + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw14);
                    AbstractWall vw11 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw11);
                    AbstractWall vw12 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw12);
                    entryPoint.put(new Point((height / 2) + xOffset + 0.5, zOffset), hall);
                    entryPoint.put(new Point(-(height / 2) + xOffset - 0.5, zOffset), hall);
                    exitPoint.put(new Point((height / 2) + xOffset - 0.5, zOffset), hall);
                    exitPoint.put(new Point(-(height / 2) + xOffset + 0.5, zOffset), hall);
                }
                break;
            case "Surgery":
                Lock sur = new ReentrantLock(true);
                if (xOffset < -3) {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.75f + zOffset, e, c);
                    wallList.add(hw21);
                    AbstractWall hw22 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 0.75f + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw22);
                    AbstractWall hw23 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw23);
                    AbstractWall vw21 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw21);
                    AbstractWall vw22 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw22);
                    entryPoint.put(new Point((height / 2) + xOffset + 0.5, zOffset), sur);
                    exitPoint.put(new Point((height / 2) + xOffset - 0.5, zOffset), sur);
                } else if (xOffset > 3) {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw21);
                    AbstractWall hw22 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 0.75f + zOffset, e, c);
                    wallList.add(hw22);
                    AbstractWall hw23 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 0.75f + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw23);
                    AbstractWall vw21 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw21);
                    AbstractWall vw22 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw22);
                    entryPoint.put(new Point(-(height / 2) + xOffset - 0.5, zOffset), sur);
                    exitPoint.put(new Point(-(height / 2) + xOffset + 0.5, zOffset), sur);
                } else if (zOffset < -3) {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw21);
                    AbstractWall hw22 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw22);
                    AbstractWall vw21 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.75f + xOffset, e, c);
                    wallList.add(vw21);
                    AbstractWall vw22 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 0.75f + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw22);
                    AbstractWall vw23 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw23);
                    entryPoint.put(new Point(xOffset, (width / 2) + zOffset + 0.5), sur);
                    exitPoint.put(new Point(xOffset, (width / 2) + zOffset - 0.5), sur);
                } else {
                    AbstractWall hw21 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw21);
                    AbstractWall hw22 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw22);
                    AbstractWall vw23 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw23);
                    AbstractWall vw24 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 0.75f + xOffset, e, c);
                    wallList.add(vw24);
                    AbstractWall vw25 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 0.75f + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw25);
                    entryPoint.put(new Point(xOffset, -(width / 2) + zOffset - 0.5), sur);
                    exitPoint.put(new Point(xOffset, -(width / 2) + zOffset + 0.5), sur);
                }
                break;

            case "Office" :
                if (xOffset < 0 ) {

                    AbstractWall hw31 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1 + zOffset, e, c);
                    wallList.add(hw31);
                    AbstractWall hw32 = new HorizontalWall((height / 2) + xOffset, -(width / 2) + 1 + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw32);
                    AbstractWall hw33 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw33);
                    AbstractWall vw31 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    wallList.add(vw31);
                    AbstractWall vw32 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw32);
                    AbstractWall vw33 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    wallList.add(vw33);
                    AbstractWall vw34 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw34);
                } else {
                    AbstractWall hw31 = new HorizontalWall((height / 2) + xOffset, (width / 2) + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw31);
                    AbstractWall hw32 = new HorizontalWall(-(height / 2) + xOffset, (width / 2) + zOffset, (width / 2) - 1 + zOffset, e, c);
                    wallList.add(hw32);
                    AbstractWall hw33 = new HorizontalWall(-(height / 2) + xOffset, -(width / 2) + 1 + zOffset, -(width / 2) + zOffset, e, c);
                    wallList.add(hw33);
                    AbstractWall vw31 = new VerticalWall((width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    wallList.add(vw31);
                    AbstractWall vw32 = new VerticalWall((width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw32);
                    AbstractWall vw33 = new VerticalWall(-(width / 2) + zOffset, (height / 2) + xOffset, (height / 2) - 1 + xOffset, e, c);
                    wallList.add(vw33);
                    AbstractWall vw34 = new VerticalWall(-(width / 2) + zOffset, -(height / 2) + 1 + xOffset, -(height / 2) + xOffset, e, c);
                    wallList.add(vw34);
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

    @Override
    public List<AbstractWall> getWallList(){
        return this.wallList;
    }

    @Override
    public HashMap<Point, Lock> getEntryList(){
        return entryPoint;
    }

    @Override
    public HashMap<Point, Lock> getExitList(){
        return exitPoint;
    }

}
