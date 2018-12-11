package mdsd.server.model;

import project.Point;
import simbad.sim.EnvironmentDescription;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Environment {

    private List<Area> areaList;

    public Environment (){
        areaList = new ArrayList<>();
    }

    public Area getRoverArea(Point position){
        for (Area a : this.areaList){
            if (a.inArea(position)){
                return a;
            }
        }
        return null;
    }

    public List getAreas(){
        return areaList;
    }

    public Area createArea(Point position, String areaName, EnvironmentDescription e, Color c){
        Area area;
        switch (areaName){
            case "Consulting" : area = new PhysicalArea(4f, 4f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical");
            break;
            case "Hall" : area = new PhysicalArea(2f, 2f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical");
            break;
            case "Surgery" : area =  new PhysicalArea(4f,4f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical");
            break;
            case "Office" : area = new PhysicalArea(5f, 5f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical");
            break;
            case "Teaching" : area = new PhysicalArea(5f, 5f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical");
            break;
            case "Wifi" : area = new LogicalArea(5f, 5f, (float)position.getX(), (float)position.getZ(), areaName, e, "Logical");
            break;
            case "Eating" : area = new LogicalArea(3f, 3f, (float)position.getX(), (float)position.getZ(), areaName, e, "Logical");
            break;
            default: return null;
        }
        areaList.add(area);
        return area;
    }

}
