package mdsd.server.controller;

import mdsd.rover.RoverCommunication;
import mdsd.server.model.Area;
import mdsd.server.model.Environment;
import mdsd.server.model.ServerModel;

import java.util.*;


public class ProcedureController extends TimerTask {

    /**
     * The different procedures that can be chosen
     */
    public enum Procedure {
        A, B;
    }

    private Procedure currentProcedure = Procedure.A;
    private ServerModel model;
    private Set<RoverCommunication> rovers;
    private Environment env;
    private Map<Area, Integer> envpnt = new HashMap<Area, Integer>();


    /**
     * Constructor for the ProcedureController
     *
     * @param rovers list of the rovers in the environment
     * @param env    the Environment
     * @param envpnt the areas of the environment as well as corresponding points of each of the areas
     * @param model  the ServerModel
     */
    public ProcedureController(Set<RoverCommunication> rovers, Environment env, Map<Area, Integer> envpnt, ServerModel model) {
        this.rovers = rovers;
        this.env = env;
        this.envpnt = envpnt;
        this.model = model;
    }


    /**
     * Override from TimerTask
     * Loops through all rovers in the environment when the current procedure is sat
     * Calculates the reward points gained from each rover depending on which procedure should be used
     */
    @Override
    public void run() {
        setCurrentProcedure();
        Iterator<RoverCommunication> iterator = rovers.iterator();
        RoverCommunication rover;


        while (iterator.hasNext()) {
            switch (currentProcedure) {
                case A:
                    rover = iterator.next();
                    procedure(rover, "Physical");
                    break;
                case B:
                    rover = iterator.next();
                    procedure(rover, "Logical");
                    break;
            }
        }


    }

    /**
     * Calculates reward points with procedure
     * Done by checking if the rover is located in any of the areas
     * rewarded by the currentProcedure (physical when A, logical when B)
     * and add the reward points connected to that area if the rover is located in it
     *
     * @param rover the rover to be checked if within an rewarded area
     */
    private void procedure(RoverCommunication rover, String areaType) {
        for (Map.Entry<Area, Integer> values : envpnt.entrySet()) {
            if (values.getKey().getAreaType().equals(areaType)) {
                if (values.getKey().inArea(rover.getPosition())) {
                    model.setRewardPoints(model.getRewardPoints() + values.getValue());
                }
            }
        }
    }

    /**
     * Calculates reward points with procedure A
     * Done by checking if the rover is located in any of the physical areas
     * and add the reward points connected to that area if the rover is located in it
     *
     * @param rover the rover to be checked if within an rewarded area
     */
    /**
     private void procedureA(RoverCommunication rover) {
     for (Map.Entry<Area, Integer> values : envpnt.entrySet()) {
     if (values.getKey().getAreaType().equals("Physical")) {
     if (values.getKey().inArea(rover.getPosition())) {
     model.setRewardPoints(model.getRewardPoints() + values.getValue());
     }
     }
     }
     }
     */
    /**
     * Calculates reward points with procedure B
     * Done by checking if the rover is located in any of the logical areas
     * and add the reward points connected to that area if the rover is located in it
     *
     * @param rover the rover to be checked if within an rewarded area
     */
/**
 private void procedureB(RoverCommunication rover) {
 for (Map.Entry<Area, Integer> values : envpnt.entrySet()) {
 if (values.getKey().getAreaType().equals("Logical")) {
 if (values.getKey().inArea(rover.getPosition())) {
 model.setRewardPoints(model.getRewardPoints() + values.getValue());
 }
 }
 }
 }
 */

    /**
     * Sets the current procedure depending on which area type the rovers are in
     * <p>
     * If the current procedure is A and at least one rover is in a logical area
     * the procedure should change to B.
     * <p>
     * If the current procedure is B and at least one rover is in a physical area
     * the procedure should change to A.
     */
    private void setCurrentProcedure() {
        Iterator<RoverCommunication> iterator = rovers.iterator();

        while (iterator.hasNext()) {
            RoverCommunication rover = iterator.next();

            if (currentProcedure == Procedure.A) {
                if (env.getRoverArea(rover.getPosition()).getAreaType().equals("Logical")) {
                    currentProcedure = Procedure.B;
                    break;
                }
            } else {
                if (env.getRoverArea(rover.getPosition()).getAreaType().equals("Physical")) {
                    currentProcedure = Procedure.A;
                    break;
                }
            }
        }
    }
}
