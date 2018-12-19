package mdsd.server.model;

import project.Point;
import simbad.sim.AbstractWall;
import simbad.sim.EnvironmentDescription;

import java.awt.*;
import java.util.ArrayList;

/**
 * Environment class. Creates Areas and contains a list of them.
 */
public class Environment {

    private ArrayList<Area> areaList;

    /**
     * Constructor for Environment. Contains list of Area in the environment.
     */
    public Environment (){
        areaList = new ArrayList<>();
    }

    /**
     * Method for getting which Area a certain point is in, mainly for rovers.
     * @param position point to check.
     * @return return first Area in list that rover is in. if not in any Area, return null.
     */
    public Area getRoverArea(Point position){
        for (Area a : this.areaList){
            if (a.inArea(position)){
                return a;
            }
        }
        return null;
    }

    /**
     * Getterf or areaList.
     * @return areaList.
     */
    public ArrayList getAreas(){
        return areaList;
    }

    /**
     * Factory method for Areas. Adds the Area to the AreaList as well.
     * @param position Center point for the area.
     * @param areaName Specific name of Area, can only have certain values.
     * @param e Description of the environment.
     * @param c Color of the Area walls.
     * @return The specified Area. If areaName isn't of certain values, return null.
     */
    public Area createArea(Point position, String areaName, EnvironmentDescription e, Color c){
        Area area;
        switch (areaName){
            case "Consulting" : area = new PhysicalArea(12f, 12f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical", c);
            break;
            case "Hall" : area = new PhysicalArea(6f, 6f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical", c);
            break;
            case "Surgery" : area =  new PhysicalArea(12f,12f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical", c);
            break;
            case "Office" : area = new PhysicalArea(15f, 15f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical", c);
            break;
            case "Teaching" : area = new PhysicalArea(15f, 15f, (float)position.getX(), (float)position.getZ(), areaName, e, "Physical", c);
            break;
            case "Wifi" : area = new LogicalArea(15f, 15f, (float)position.getX(), (float)position.getZ(), areaName, e, "Logical");
            break;
            case "Eating" : area = new LogicalArea(9f, 9f, (float)position.getX(), (float)position.getZ(), areaName, e, "Logical");
            break;
            default: return null;
        }
        areaList.add(area);
        return area;
    }

    /**
     * Method for taking all wallLists and putting them together into one list.
     * @return wallList.
     */
    public ArrayList<AbstractWall> wallList(){
        ArrayList<AbstractWall> wallList = new ArrayList<AbstractWall>();
        for (Area a : areaList){
            for (AbstractWall aw : a.getWallList()){
                wallList.add(aw);
            }
        }
        return wallList;
    }
}
