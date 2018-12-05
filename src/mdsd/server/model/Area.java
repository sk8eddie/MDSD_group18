package mdsd.server.model;

import project.Point;

public interface Area {

    Area createArea (Point position, String areaName);

    String getAreaType();

    boolean inArea(Point coordinate);
}
