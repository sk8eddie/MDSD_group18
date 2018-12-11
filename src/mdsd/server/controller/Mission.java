package mdsd.server.controller;

import project.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Mission implements Iterable{

    private List<Point> points;

    public Mission(){
    	this.points = new ArrayList<>();
	}

	public void addPoint(Point point){
		this.points.add(point);
	}

	public List<Point> getPoints(){
		return this.points;
	}

   public void updateStrategy(){
	   Collections.shuffle(this.points);
   }

	@Override
	public Iterator iterator() {
		return new MissionIterator();
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
