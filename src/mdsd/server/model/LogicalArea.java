package mdsd.server.model;

import project.Point;
import simbad.sim.AbstractWall;
import simbad.sim.EnvironmentDescription;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

/**
 * Class for LogicalArea, i.e. Wifi-zone
 */
public class LogicalArea implements Area {

    private Point corner1;
    private Point corner2;
    private String areaType;
    private String areaName;

    /**
     * Constructor for LogicalArea. Doesn't create walls since you can pass through a logical area anywhere.
     * Only creates top-right and bottom-left corner.
     *
     * @param width    Width of Area.
     * @param height   Height of Area.
     * @param xOffset  Offset on x-axis.
     * @param zOffset  Offset on z-axis.
     * @param name     Name of the Area.
     * @param e        Environment description.
     * @param areaType Tpe of Area (always Logical).
     */
    LogicalArea(float width, float height, float xOffset, float zOffset, String name, EnvironmentDescription e, String areaType) {

        this.areaType = areaType;
        this.areaName = name;
        this.corner1 = new Point(xOffset + (width / 2), zOffset + (height / 2));
        this.corner2 = new Point(xOffset - (width / 2), zOffset - (height / 2));
    }

    /**
     * Getter for the area type
     *
     * @return the type of the area
     */
    @Override
    public String getAreaType() {
        return this.areaType;
    }

    /**
     * Checks if the coordinate is in the area
     *
     * @param coordinate Coordinate to check.
     * @return True if the coordinate is in the area, otherwise false.
     */
    @Override
    public boolean inArea(Point coordinate) {
        return coordinate.getX() < corner1.getX() && coordinate.getX() > corner2.getX() && coordinate.getZ() < corner1.getZ() && coordinate.getZ() > corner2.getZ();
    }

    /**
     * Getter for the area name
     *
     * @return the name of the area
     */
    @Override
    public String getAreaName() {
        return this.areaName;
    }

    @Override
    public List<AbstractWall> getWallList() {
        return null;
    }

    @Override
    public HashMap<Point, Semaphore> getEntryList() {
        return null;
    }

    @Override
    public HashMap<Point, Semaphore> getExitList() {
        return null;
    }

}
