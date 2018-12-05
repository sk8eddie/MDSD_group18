package mdsd.server.model;

import java.awt.*;

public class PhysicalArea implements Area{
    @Override
    public Area createArea(int areaNumber, float position) {
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
