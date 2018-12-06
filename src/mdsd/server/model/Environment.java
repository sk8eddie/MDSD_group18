package mdsd.server.model;

import project.Point;
import simbad.sim.EnvironmentDescription;

import java.util.ArrayList;
import java.util.List;

public class Environment {

    private List<Area> areaList;

    public Environment (){
        areaList = new ArrayList<Area>();
    }

    public Area getRoverArea(Point position){
        return null;
    }

    public List getAreas(){
        return areaList;
    }

    public Area createArea(float width, float height, Point position, String areaName, EnvironmentDescription e, String areaType){
        Area area;
        if (areaType.equals("Physical")) {
            area = new PhysicalArea(width, height, (float) position.getX(), (float) position.getZ(), areaName, e, areaType);
        } else if (areaType.equals("Logical")){
            area = new LogicalArea(width, height, (float) position.getX(), (float) position.getZ(), areaName, e, areaType);
        }else {
            return null;
        }
        areaList.add(area);
        return area;
    }

}
