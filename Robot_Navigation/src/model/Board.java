package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * This class maintains the instance of the board or GUI created 
 * It stores the hash map of all the polygon i.e. obstacles in the Environment
 * It stores the Start and Goal Coordinates parsed from the file
 * It also stores that if the file is opened or not
 */
public class Board{
	static Board boardInstance = null;
	
	/*
	 * returns the current instance of the board in order to maintain consistency among GUI
	 */
	public static Board getBoardInstance(){
		if(boardInstance == null)
			boardInstance = new Board();

		return boardInstance;
	}

	Coordinates goal;
	Coordinates start;
	Map<Integer,List<Coordinates>> obstacleMap;
	boolean isIntersect = false;
	boolean isFileOpen = false;

	public Coordinates getGoal() {
		return goal;
	}
	public void setGoal(Coordinates goal) {
		this.goal = goal;
	}
	public Coordinates getStart() {
		return start;
	}
	public void setStart(Coordinates start) {
		this.start = start;
	}
	public Map<Integer, List<Coordinates>> getObstacleMap() {
		return obstacleMap;
	}
	public void setObstacleMap(Integer key, List<Coordinates> value) {
		if(obstacleMap == null)
			obstacleMap= new HashMap<Integer, List<Coordinates>>();

		obstacleMap.put(key, value);
	}

	public void clearObstacleMap() {
		if(obstacleMap!= null)
			obstacleMap = new HashMap<Integer, List<Coordinates>>();
	}

	public boolean isFileOpen() {
		return isFileOpen;
	}
	
	public void setFileOpen(boolean isFileOpen) {
		this.isFileOpen = isFileOpen;
	}
	
}
