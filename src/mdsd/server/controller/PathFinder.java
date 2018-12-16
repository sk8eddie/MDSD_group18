package mdsd.server.controller;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import org.xguzm.pathfinding.grid.finders.AStarGridFinder;
import org.xguzm.pathfinding.grid.finders.GridFinderOptions;

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

	public List<GridCell> getFastestPath(NavigationGrid<GridCell> navGrid){

		GridFinderOptions opt = new GridFinderOptions();
		opt.allowDiagonal = false;
		AStarGridFinder<GridCell> finder = new AStarGridFinder(GridCell.class, opt);

		List<GridCell> pathToEnd = finder.findPath(0, 0, 20, 20, navGrid);

		return pathToEnd;
	}

}
