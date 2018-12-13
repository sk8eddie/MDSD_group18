package mdsd.server.controller;

import javafx.util.Pair;
import mdsd.Robot;
import mdsd.server.model.*;
import java.util.HashMap;



import java.util.*;


public class ProcedureController extends TimerTask {

    public enum Procedure {
        A, B;
    }

    private Procedure currentProcedure = Procedure.A;
    private ServerModel model;
    private Set<Robot> robots;
    private Environment env;
    private Map<Area, Integer> envpnt = new HashMap<Area, Integer>();


    public ProcedureController(Set<Robot> robots, Environment env, Map<Area, Integer> envpnt, ServerModel model){
        this.model = model;
        this.robots = robots;
        this.env = env;
        this.envpnt = envpnt;
    }




    // Calculates the reward points depending on which procedure should be used,
    // Overrided from TimerTask
    @Override
    public void run() {
            setCurrentProcedure();
            Iterator<Robot> iterator = robots.iterator();
            Robot rover;

            switch (currentProcedure) {
                case A:
                    while (iterator.hasNext()) {
                        rover = iterator.next();
                        procedureA(rover);
                    }
                    break;
                case B:
                    while (iterator.hasNext()) {
                        rover = iterator.next();
                        procedureB(rover);
                    }
                    break;
            }




    }

    //Calculates reward points with procedure A
    private void procedureA(Robot rover) {
        for(Map.Entry<Area, Integer> values : envpnt.entrySet()){
            if (values.getKey().getAreaType().equals("Physical")){
                if(env.getRoverArea(rover.getPosition()).getAreaName().equals(values.getKey().getAreaName())){
                    model.setRewardPoints(model.getRewardPoints() + values.getValue());
                }
            }
        }


     /*   if(env.getRoverArea(rover.getPosition()).getAreaName().equals("office")){
            model.setRewardPoints(model.getRewardPoints() + 1);
        }

        else if(env.getRoverArea(rover.getPosition()).getAreaName().equals("teachingRoom")){
            model.setRewardPoints(model.getRewardPoints() + 2);
        }
    */
    }

    //Calculates reward points with procedure B
    private void procedureB(Robot rover) {
        for (Map.Entry<Area, Integer> values : envpnt.entrySet()) {
            if (values.getKey().getAreaType().equals("Logical")) {
                if (env.getRoverArea(rover.getPosition()).getAreaName().equals(values.getKey().getAreaName())) {
                    model.setRewardPoints(model.getRewardPoints() + values.getValue());
                }
            }

      /*  if(env.getRoverArea(rover.getPosition()).getAreaName().equals("wifiZone")){
            model.setRewardPoints(model.getRewardPoints() + 1);
        }
        else if(env.getRoverArea(rover.getPosition()).getAreaName().equals("eatingArea")){
            model.setRewardPoints(model.getRewardPoints() + 2);

        }
        */

        }
    }

    //Sets current procedure depending on which area type the rovers are in
    private void setCurrentProcedure() {
        Iterator<Robot> iterator = robots.iterator();

        while (iterator.hasNext()){
            Robot rover = iterator.next();
            if(currentProcedure == Procedure.A){
               if(env.getRoverArea(rover.getPosition()).getAreaType().equals("Logical")){
                   currentProcedure = Procedure.B;
                }
            }else if(currentProcedure == Procedure.B){
                if(env.getRoverArea(rover.getPosition()).getAreaType().equals("Physical")){
                    currentProcedure = Procedure.A;
                }
            }
        }



    }



}
