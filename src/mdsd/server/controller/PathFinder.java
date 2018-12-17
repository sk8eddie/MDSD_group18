package mdsd.server.controller;

import mdsd.GridEnvironment;
import mdsd.server.model.Area;
import mdsd.server.model.Environment;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;

import project.Point;
import simbad.sim.AbstractWall;

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
	
	GridEnvironment grid = new GridEnvironment(20, 20);
	Environment env;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GridCell> getFastestPath(Point start, Point end){
		
		grid.createCells(env.wallList());
		
		GridFinderOptions opt = new GridFinderOptions();
		opt.allowDiagonal = false;
		AStarGridFinder<GridCell> finder = new AStarGridFinder(GridCell.class, opt);

		List<GridCell> pathToEnd = finder.findPath((int) start.getX(), (int) start.getZ(), (int)end.getZ(), (int)end.getX(), grid.createNavGrid());
		
		return pathToEnd;
	}
	
	public List<Point> getPathPoints(Point start, Point end){

		List<GridCell> gridPoints = new ArrayList<GridCell>();
		List<Point> pathPoints = new ArrayList<>();
		
		gridPoints = getFastestPath(start,end);
		
		for (GridCell a : gridPoints){
           Point newPoint = new Point(a.getX(),a.getY());
           pathPoints.add(newPoint);
        }
		return pathPoints;
	}

}
