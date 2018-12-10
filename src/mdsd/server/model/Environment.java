package mdsd.server.model;

import project.Point;
import simbad.sim.EnvironmentDescription;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Environment {

    private List<Area> areaList;

    public Environment (){
        areaList = new ArrayList<Area>();
    }

    public Area getRoverArea(Point position){
        for (Area a : areaList){
            if (a.inArea(position)){
                return a;
            }
        }
        return null;
    }

    public List getAreas(){
        return areaList;
    }

    public Area createArea(float width, float height, Point position, String areaName, EnvironmentDescription e, String areaType){
        Area area;
        switch (areaType){
            case "Physical" : area = new PhysicalArea(width, height, (float) position.getX(), (float) position.getZ(), areaName, e, areaType);
            break;
            case "Logical" : area = new LogicalArea(width, height, (float) position.getX(), (float) position.getZ(), areaName, e, areaType);
            break;
            default: return null;
        }
        areaList.add(area);
        return area;
    }

}
