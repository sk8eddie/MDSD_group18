package mdsd.server.model;

import project.Point;
import simbad.sim.EnvironmentDescription;

public class LogicalArea implements Area{

    private Point corner1;
    private Point corner2;
    private String areaType;
    private String areaName;

    LogicalArea(float width, float height, float xOffset, float zOffset, String name, EnvironmentDescription e, String areaType){

        this.areaType = areaType;
        this.areaName = name;
        this.corner1 = new Point(xOffset + (width/2),zOffset + (height/2));
        this.corner2 = new Point(xOffset - (width/2),zOffset - (height/2));
    }

    @Override
    public String getAreaType() {
        return this.areaType;
    }

    @Override
    public boolean inArea(Point coordinate) {
        return coordinate.getX() < corner1.getX() && coordinate.getX() > corner2.getX() && coordinate.getZ() < corner1.getZ() && coordinate.getZ() > corner2.getZ();
    }

    @Override
    public String getAreaName(){
        return this.areaName;
    }

}
