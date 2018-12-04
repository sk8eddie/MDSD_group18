package mdsd.server.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tubas on 2018-12-04.
 */
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
