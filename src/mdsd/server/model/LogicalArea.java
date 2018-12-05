package mdsd.server.model;

import project.Point;

public class LogicalArea implements Area{
    @Override
    public Area createArea(Point position) {
        return null;
    }

    @Override
    public String getAreaType() {
        return null;
    }

    @Override
    public boolean inArea(Point coordinate) {
        return false;
    }
}
