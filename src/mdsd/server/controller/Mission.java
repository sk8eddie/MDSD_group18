package mdsd.server.controller;

import project.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Mission implements Iterable {

    private Point currentDestination;
    private List<Point> points;
    private Iterator missionIterator = new MissionIterator();

    /**
     * Empty constructor for the Mission, creates an empty arraylist for the points
     */
    public Mission() {
        this.points = new ArrayList<>();
    }


    /**
     * Constructor for the Mission, sets the list of points to the paramteters list of points
     *
     * @param points list of mission points
     */
    public Mission(List<Point> points) {
        this.points = points;
    }

    /**
     * Adds a new point to the points-list
     *
     * @param point the point to be added
     */
    public void addPoint(Point point) {
        this.points.add(point);
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
     * Getter for the current destination for the mission
     *
     * @return the missions current destination
     */
    public Point getCurrentDestination() {
        return this.currentDestination;
    }

    /**
     * Updates the strategy by shuffling the the mission-points
     */
    public void updateStrategy() { //TODO handle for when a rover has reached a certain amount of points.
        Collections.shuffle(this.points);
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
        }

        @Override
        public Point next() {
            Point nextPoint = points.get(currentIndex);
            currentDestination = nextPoint;
            currentIndex++;
            return nextPoint;
        }
    }
}
