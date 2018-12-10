package mdsd.server.model;

import project.Point;

public interface Area {

    String getAreaType();

    boolean inArea(Point coordinate);

    String getAreaName();
}
