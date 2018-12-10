package mdsd.server.controller;

import mdsd.Robot;
import mdsd.server.model.Environment;
import mdsd.server.model.ServerModel;



import java.util.Iterator;
import java.util.Set;
import java.util.TimerTask;


public class ProcedureController extends TimerTask {

    public enum Procedure {
        A, B;
    }

    private Procedure currentProcedure = Procedure.A;
    private ServerModel model;
    private Set<Robot> robots;
    private Environment env;

    public ProcedureController(Set<Robot> robots, Environment env){
        this.robots = robots;
        this.env = env;
    }


    @Override
    public void run() {
            setCurrentProcedure(robots, env);
            Iterator<Robot> iterator = robots.iterator();
            Robot rover;

            switch (currentProcedure) {
                case A:
                    while (iterator.hasNext()) {
                        rover = iterator.next();
                        procedureA(rover, env);
                    }
                    break;
                case B:
                    while (iterator.hasNext()) {
                        rover = iterator.next();
                        procedureB(rover, env);
                    }
                    break;
            }




    }

    private void procedureA(Robot rover, Environment env) {
        if(env.getRoverArea(rover.getPosition()).getAreaName().equals("office")){
            model.setRewardPoints(model.getRewardPoints() + 1);
        }

        else if(env.getRoverArea(rover.getPosition()).getAreaName().equals("teachingRoom")){
            model.setRewardPoints(model.getRewardPoints() + 2);
        }

    }

    private void procedureB(Robot rover, Environment env) {
        if(env.getRoverArea(rover.getPosition()).getAreaName().equals("wifiZone")){
            model.setRewardPoints(model.getRewardPoints() + 1);
        }
        else if(env.getRoverArea(rover.getPosition()).getAreaName().equals("eatingArea")){
            model.setRewardPoints(model.getRewardPoints() + 2);

        }

    }

    private void setCurrentProcedure(Set<Robot> robots, Environment env) {
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
