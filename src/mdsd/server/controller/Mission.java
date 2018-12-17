package mdsd.server.controller;

import mdsd.rover.Rover;
import mdsd.server.model.Environment;
import project.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class Mission implements Iterable{

	private Point currentDestination;
    private List<Point> points;
    private Iterator missionIterator = new MissionIterator();

    public Mission(){
    	this.points = new ArrayList<>();
	}

	public Mission(List<Point> points){
    	this.points = points;
	}

	public void addPoint(Point point){
		this.points.add(point);
	}

	public List<Point> getPoints(){
		return this.points;
	}

	public Point getCurrentDestination(){
		return this.currentDestination;
	}

   public void updateStrategy(Rover rover, Mission mission, Environment env){ //TODO handle for when a rover has reached a certain amount of points.
	   PathFinder p = new PathFinder();
	   List l = p.getPathPoints(rover.getPosition(), mission.getCurrentDestination(), env);
	   Mission m = new Mission(l);
   }

	@Override
	public Iterator iterator() {
		return this.missionIterator;
	}

	private class MissionIterator implements Iterator<Point>{
   		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return points.get(currentIndex) != null;
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
