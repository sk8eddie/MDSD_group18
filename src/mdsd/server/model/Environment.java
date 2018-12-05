package mdsd.server.model;

import project.Point;
import java.util.ArrayList;
import java.util.List;

public class Environment {

    private List<Area> areaList = new ArrayList<Area>();

    public Environment (){

    }

    public Area getRoverArea(Point position){
        return null;
    }

    public List getAreas(){
        return areaList;
    }
}
