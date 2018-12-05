package mdsd.server.model;

import project.Point;

public class LogicalArea implements Area{

    @Override
    public String getAreaType() {
        return null;
    }

    @Override
    public boolean inArea(Point coordinate) {
        return false;
    }
}
