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
	
	private GridEnvironment grid = new GridEnvironment(40, 40);
	Environment env;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<GridCell> getFastestPath(Point start, Point end, Environment env){
		
		grid.createCells(env.wallList());
		
		GridFinderOptions opt = new GridFinderOptions();
		opt.allowDiagonal = false;
		AStarGridFinder<GridCell> finder = new AStarGridFinder(GridCell.class, opt);
		
		return finder.findPath(convertPoints(start.getX()), convertPoints(start.getZ()), convertPoints(end.getX()), convertPoints(end.getZ()), grid.createNavGrid());
	}
	
	public List<Point> getPathPoints(Point start, Point end , Environment env){

		List<GridCell> gridPoints;
		List<Point> pathPoints = new ArrayList<>();
		
		gridPoints = getFastestPath(start,end,env);
		for (GridCell a : gridPoints){
           Point newPoint = new Point(unConvertPoints(a.getX()), unConvertPoints(a.getY()));
           pathPoints.add(newPoint);
        }
		System.out.println("reached");
		return pathPoints;
	}

	private int convertPoints(double val){
		return (int)(val + 10) * 2;
	}

	private double unConvertPoints(double val){
		return val/2 - 10;
	}

}
