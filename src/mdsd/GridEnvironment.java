package mdsd;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import simbad.sim.AbstractWall;

import java.util.ArrayList;

public class GridEnvironment {

    private GridCell[][] cells;

    public GridEnvironment (int boundryWidth, int boundryHeight){
        this.cells = new GridCell[2*boundryWidth][2*boundryHeight];
    }

    public void createCells(ArrayList<AbstractWall> wallList){
        boolean[][] listOfNotWalkable = transformWallList(wallList);
        for (int x = 0; x < 41; x++){
            for (int z = 0; z < 41; z++){
                cells[x][z] = new GridCell(x, z, listOfNotWalkable[x][z]);
            }
        }
    }

    private boolean[][] transformWallList(ArrayList<AbstractWall> wallList){
        boolean[][] pts = new boolean[40][40]; //
        for (AbstractWall aw : wallList){
            int x1 = (int) ((aw.getP1x()+10) * 2);
            int x2 = (int) ((aw.getP2x()+10) * 2);
            int z1 = (int) ((aw.getP1z()+10) * 2);
            int z2 = (int) ((aw.getP2z()+10) * 2);
            if (x1 == x2){
                for (int z = z1; z > z2; z--){
                    pts[x1][z] = false;
                }
            } else if (z1 == z2){
                for (int x = x1; x > x2; x--){
                    pts[x][z1] = false;
                }
            }
        }
        return pts;
    }

    public NavigationGrid getNavGrid(GridCell[][] g){
        return new NavigationGrid<GridCell>(g);
    }
}
	
	/*
	 * Get the Environment X,Y + Wall list
	 * isWalkable on the environment walls
	 * Return final environment
	 * 
	 * Read the XML and points send to PathFinder
	 * New list of points sent to MissionController
	 * Mission controller send to Rover
	 * 
	 * 
	 * 
	 */

