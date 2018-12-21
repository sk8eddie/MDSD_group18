package mdsd.server.controller;

import mdsd.rover.RoverCommunication;
import mdsd.server.model.Area;
import mdsd.server.model.Environment;
import mdsd.server.model.ServerModel;

import java.util.*;


public class ProcedureController extends TimerTask {

    public enum Procedure {
        A, B;
    }

    private Procedure currentProcedure = Procedure.A;
    private ServerModel model;
    private Set<RoverCommunication> rovers;
    private Environment env;
    private Map<Area, Integer> envpnt = new HashMap<Area, Integer>();


    public ProcedureController(Set<RoverCommunication> rovers, Environment env, Map<Area, Integer> envpnt, ServerModel model){
        this.model = model;
        this.rovers = rovers;
        this.env = env;
        this.envpnt = envpnt;
    }




    // Calculates the reward points depending on which procedure should be used,
    // Overrided from TimerTask
    @Override
    public void run() {
        setCurrentProcedure();
        Iterator<RoverCommunication> iterator = rovers.iterator();
            RoverCommunication rover;


        while (iterator.hasNext()) {
            switch (currentProcedure) {
                case A:
                        rover = iterator.next();
                        procedureA(rover);
                    break;
                case B:
                        rover = iterator.next();
                        procedureB(rover);
                    break;
            }
        }




    }

    //Calculates reward points with procedure A
    private void procedureA(RoverCommunication rover) {
        for(Map.Entry<Area, Integer> values : envpnt.entrySet()){
            if (values.getKey().getAreaType().equals("Physical")){
                if(values.getKey().inArea(rover.getPosition())){
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
    private void procedureB(RoverCommunication rover) {
        for (Map.Entry<Area, Integer> values : envpnt.entrySet()) {
            if (values.getKey().getAreaType().equals("Logical")) {
                if (values.getKey().inArea(rover.getPosition())) {
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
        Iterator<RoverCommunication> iterator = rovers.iterator();

        while (iterator.hasNext()){
            RoverCommunication rover = iterator.next();

            if(currentProcedure == Procedure.A){
                System.out.println("---------- setProcedure if A ----------" );
               if(env.getRoverArea(rover.getPosition()).getAreaType().equals("Logical")){
                   currentProcedure = Procedure.B;
                   System.out.println("---------- setProcedure in A ----------" );
                   System.out.println("----------" + currentProcedure +"----------" );
                   break;
                }
            }else {
                System.out.println("---------- setProcedure if B ----------" );
                if(env.getRoverArea(rover.getPosition()).getAreaType().equals("Physical")){
                    currentProcedure = Procedure.A;
                    System.out.println("---------- setProcedure in B ----------" );
                    break;
                }
            }
        }



    }



}
