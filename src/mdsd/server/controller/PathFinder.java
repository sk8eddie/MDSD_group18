package mdsd.server.controller;


import mdsd.rover.RoverCommunication;
import mdsd.server.model.GridEnvironment;
import mdsd.server.model.Environment;
import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;
import project.Point;

import java.util.ArrayList;
import java.util.List;

public class PathFinder {

    /*
     * Get the fastest path
     *
     * Read the XML and points send to PathFinder
     * New list of points sent to MissionController
     * Mission controller send to Rover
     *
     */
    private GridEnvironment grid = new GridEnvironment(60, 60);
    Environment env;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<GridCell> getFastestPath(Point start, Point end, Environment env) {

        grid.createCells(env.wallList());

        GridFinderOptions opt = new GridFinderOptions();
        opt.allowDiagonal = true;
        AStarGridFinder<GridCell> finder = new AStarGridFinder(GridCell.class, opt);

        List<GridCell> lst = finder.findPath(convertPoints(start.getX()), convertPoints(start.getZ()), convertPoints(end.getX()), convertPoints(end.getZ()), grid.createNavGrid());
        return lst;
    }

    public List<Point> getPathPoints(RoverCommunication rover, Mission mission, Environment env) {

        List<GridCell> gridPoints = new ArrayList<>();
        List<Point> pathPoints = new ArrayList<>();

        Point current = rover.getPosition();

        for (Point p : mission.getPoints()) {
            gridPoints.addAll(getFastestPath(current, p, env));
            current = p;
        }

        int i = 0;
        for (GridCell a : gridPoints) {
            if (i % 2 != 0) {
                Point newPoint = new Point(unConvertPoints(a.getX()), unConvertPoints(a.getY()));
                pathPoints.add(newPoint);
                System.out.println("( " + unConvertPoints(a.getX()) + " , " + unConvertPoints(a.getY()) + " )");
            }
            i++;
        }
        System.out.println("reached");
        return pathPoints;
    }


    private int convertPoints(double val) {
        return (int) ((val / 3) + 10) * 2;
    }

    private double unConvertPoints(double val) {
        return (val / 2 - 10) * 3;
    }

}
