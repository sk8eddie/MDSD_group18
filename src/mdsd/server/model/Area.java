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
     * @param coordinate
     * @return
     */
    boolean inArea(Point coordinate);

    String getAreaName();

    List<AbstractWall> getWallList();
}
