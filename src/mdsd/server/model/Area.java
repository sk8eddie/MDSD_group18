package mdsd.server.model;

import project.Point;
import simbad.sim.AbstractWall;

import java.util.ArrayList;
import java.util.List;

public interface Area {

    String getAreaType();

    boolean inArea(Point coordinate);

    String getAreaName();

    List<AbstractWall> getWallList();
}
