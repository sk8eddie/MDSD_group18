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

    public Area createArea(Point position, String areaName, EnvironmentDescription e){
        Area area = new PhysicalArea((float)position.getX(), (float)position.getZ(), areaName, e);
        areaList.add(area);
        return area;
    }

}
