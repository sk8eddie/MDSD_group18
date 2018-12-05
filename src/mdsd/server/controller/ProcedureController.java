package mdsd.server.controller;

import mdsd.Robot;
import mdsd.server.model.ServerModel;
import org.omg.CORBA.Environment;

import java.sql.Time;
import java.time.Clock;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;


public class ProcedureController {

    public enum Procedure {
        A, B;
    }

    //private Clock clock;
    private int clock = 20;
    private Procedure currentProcedure = Procedure.A;
    private Environment environment;


    private void computeRewardPoints(Set<Robot> robots) {
        if (clock % 20 == 0) {
            setCurrentProcedure(robots);

            switch (currentProcedure) {
                case A:
                    procedureA();
                    break;
                case B:
                    procedureB();
                    break;
            }

        }


    }

    private void procedureA() {

    }

    private void procedureB() {

    }

    private void setCurrentProcedure(Set<Robot> robots) {
        Iterator<Robot> iterator = robots.iterator();

        while (iterator.hasNext()){
            Robot r = iterator.next();
            if(currentProcedure == Procedure.A){
              //  if(environment.getRoverArea(r.getPosition()).getAreaType().equals("Logical")){
              //      currentProcedure = Procedure.B;
               // }
            }else if(currentProcedure == Procedure.B){
              //  if(environment.getRoverArea(r.getPosition()).getAreaType().equals("Physical")){
              //      currentProcedure = Procedure.A;
              //  }
            }
        }



    }



}
