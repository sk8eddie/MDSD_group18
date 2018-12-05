package mdsd.server.model;

import java.awt.*;

public interface Area {

    Area createArea (int areaNumber, float position);

    String getAreaType();

    boolean inArea(Point coordinate);
}
