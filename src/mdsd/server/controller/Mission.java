package mdsd.server.controller;

import mdsd.rover.RoverCommunication;
import mdsd.server.model.Environment;
import project.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class for handling the points for the missions.
 */

public class Mission implements Iterable {

    private List<Point> points;
    private Iterator missionIterator = new MissionIterator();

    /**
     * Constructor for the Mission, sets the list of points to the paramteters list of points
     *
     * @param points list of mission points
     */
    public Mission(List<Point> points) {
        this.points = points;
    }

    /**
     * Getter for the points-list
     *
     * @return a list of points
     */
    public List<Point> getPoints() {
        return this.points;
    }


    /**
     * Takes the points from a mission and sends them through path finding which returns new points
     * for the fastest way between the mission-points.
     */
    void updateStrategy(RoverCommunication rover, Environment env) { //TODO handle for when a rover has reached a certain amount of points.
        PathFinder p = new PathFinder();
        this.points = p.getPathPoints(rover, this, env);
    }


    @Override
    public Iterator iterator() {
        return this.missionIterator;
    }

    /**
     * An iterator for the mission
     */
    private class MissionIterator implements Iterator<Point> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < points.size();
            //return points.get(currentIndex) != null;
        }

        @Override
        public Point next() {
            Point nextPoint = points.get(currentIndex);
            currentIndex++;
            return nextPoint;
        }
    }


}
