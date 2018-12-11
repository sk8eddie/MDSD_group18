package mdsd.server.controller;

import project.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//TODO add current destination
public class Mission implements Iterable{

    private List<Point> points;
    private Iterator missionIterator = new MissionIterator();

    public Mission(){
    	this.points = new ArrayList<>();
	}

	public void addPoint(Point point){
		this.points.add(point);
	}

	public List<Point> getPoints(){
		return this.points;
	}

   public void updateStrategy(){ //TODO handle for when a rover has reached a certain amount of points.
	   Collections.shuffle(this.points);
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
			currentIndex++;
			return nextPoint;
		}
	}
}
