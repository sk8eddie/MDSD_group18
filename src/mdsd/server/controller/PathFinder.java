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

/**
 * Pathfinder class. Creates grid and finder and performs pathfinder to get the shortest path between two points.
 */

class PathFinder {

    private GridEnvironment grid = new GridEnvironment(40, 40);

    /**
     * Method that performs the pathfinding. Creates a grid and a finder and performs the pathfinding.
     * @param start Start point for the finder.
     * @param end End point for the finder.
     * @param env Environment to be able to get the walls where the pathfinder can't go.
     * @return List of cells that is the shortest path.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    List<GridCell> getFastestPath(Point start, Point end, Environment env) {

        grid.createCells(env.wallList());

        GridFinderOptions opt = new GridFinderOptions();
        opt.allowDiagonal = true;
        AStarGridFinder<GridCell> finder = new AStarGridFinder(GridCell.class, opt);

        return finder.findPath(convertPoints(start.getX()), convertPoints(start.getZ()), convertPoints(end.getX()), convertPoints(end.getZ()), grid.createNavGrid());
    }

    /**
     * Gets the list of cells with the shortest path and converts it into a list of Points.
     * @param rover Rover to get the position of the rover.
     * @param mission The mission for that rover.
     * @param env Environment to get walls where rovers can't go.
     * @return List of points that is the shortest path.
     */
    List<Point> getPathPoints(RoverCommunication rover, Mission mission, Environment env) {

        List<GridCell> gridPoints = new ArrayList<>();
        List<Point> pathPoints = new ArrayList<>();

        Point current = rover.getPosition();

        for (Point p : mission.getPoints()) {
            gridPoints.addAll(getFastestPath(current, p, env));
            current = p;
        }

        for (GridCell a : gridPoints) {
                Point newPoint = new Point(unConvertPoints(a.getX()), unConvertPoints(a.getY()));
                pathPoints.add(newPoint);
            }
        return pathPoints;
    }


    /**
     * Converts a value of a position from the simulator to the grid, since they have different dimensions.
     * @param val Value to change.
     * @return Changed value.
     */
    private int convertPoints(double val) {
        return (int) (val + 10) * 2;
    }

    /**
     * Unconverts a value of a position from the simulator to the grid, since they have different dimensions.
     * @param val Value to change.
     * @return Changed value.
     */
    private double unConvertPoints(double val) {
        return val / 2 - 10;
    }

}
