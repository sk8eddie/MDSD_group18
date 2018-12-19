package mdsd;

import org.xguzm.pathfinding.grid.GridCell;
import org.xguzm.pathfinding.grid.NavigationGrid;
import simbad.sim.AbstractWall;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The GridEnvironment is used to convert the environment to a graph to
 * later be able to use pathfinders that the rovers can use.
 */

public class GridEnvironment {

    private GridCell[][] cells;

    private int width;
    private int height;

    /**
     * Constructor for the graph
     * @param boundryWidth width of the environment in the simulator.
     * @param boundryHeight height of the environment in the simulator.
     */
    public GridEnvironment (int boundryWidth, int boundryHeight){
        this.width = 2 * boundryWidth / 3;
        this.height = 2 * boundryHeight / 3;
        this.cells = new GridCell[width][height];
    }

    /**
     * Creates the graph with walkable and not walkable cells.
     * @param wallList list of walls which will be not not walkable cells.
     */
    public void createCells(ArrayList<AbstractWall> wallList){
        boolean[][] listOfNotWalkable = transformWallList(wallList);
        System.out.println(width + "   " + height);
        for (int x = 0; x < width; x++){
            System.out.println("");
            for (int z = 0; z < height; z++){
                cells[x][z] = new GridCell(x, z, listOfNotWalkable[x][z]);
                if (cells[x][z].isWalkable())
                    System.out.print("x  ");
                else
                    System.out.print("   ");
            }
        }
    }

    /**
     * Method for transforming the list of AbstractWalls to a matrix of booleans for walkable and not walkable cells.
     * @param wallList list of walls which will be not not walkable cells.
     * @return matrix of walkable and not walkable cells.
     */
    private boolean[][] transformWallList(ArrayList<AbstractWall> wallList){
        boolean[][] pts = new boolean[width][height];
        for (int a = 0; a < width; a++){
            for (int b = 0; b < height; b++){
                pts[a][b] = true;
            }
        }
        for (AbstractWall aw : wallList){
            int x1 = (int) (((aw.getP1x()/3) + 10) * 2);
            int x2 = (int) (((aw.getP2x()/3) + 10) * 2);
            int z1 = (int) (((aw.getP1z()/3) + 10) * 2);
            int z2 = (int) (((aw.getP2z()/3) + 10) * 2);
            if (x1 == x2){
                for (int z = z1; z >= z2; z--){
                    pts[x1][z] = false;
                }
            } else if (z1 == z2){
                for (int x = x1; x >= x2; x--){
                    pts[x][z1] = false;
                }
            }
        }
        return pts;
    }

    /**
     * Creates a NavigationGrid that can be used by the pathfinders.
     * @return NavigationGrid of the created cells.
     */
    public NavigationGrid createNavGrid(){
        return new NavigationGrid<GridCell>(this.cells);
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

