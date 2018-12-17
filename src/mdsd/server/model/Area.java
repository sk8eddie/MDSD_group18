package mdsd.server.model;

import project.Point;
import simbad.sim.AbstractWall;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for Areas.
 */
public interface Area {

    /**
     * Getter for AreaType.
     * @return areaType.
     */
    String getAreaType();

    /**
     * Method for checking if a coordinate is in an Area.
     * @param coordinate Coordinate to check.
     * @return True if point is in Area, otherwise false.
     */
    boolean inArea(Point coordinate);

    /**
     * Getter for areaName.
     * @return areaName.
     */
    String getAreaName();

    /**
     * Getter for WallList.
     * @return wallList.
     */
    List<AbstractWall> getWallList();
}
