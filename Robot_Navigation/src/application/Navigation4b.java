package application;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import model.Board;
import model.Coordinates;
import model.State;

/*
 * Implementation of LRTA* Algorithm for Question 4b
*/

public class Navigation4b {
Stack<Coordinates> stack = new Stack();
	
	static Board board = Board.getBoardInstance();
	static List<State> nextAvailableState = null;
	public static List<Coordinates> pathCoordinates = new ArrayList<Coordinates>();
	Coordinates goal = board.getGoal();
	public static Coordinates start;
	public static int performance;
	int random;
	

	/*
	 * This function constructs a line with the given two input coordinates and checks this line with all the lines of 
	 * the polygon in the Environment whether it intersects or not
	 * It returns true if it intersects with one line else returns false
	 */
	public static boolean intersectLine(Coordinates start, Coordinates end) {
		boolean isIntersecting = false;
		if(board.getObstacleMap()!=null){
			Iterator<?> it = board.getObstacleMap().entrySet().iterator();
			Line2D line2;
			Line2D line1 = new Line2D.Double(start.getX(),start.getY(), end.getX(),end.getY());
			while(it.hasNext()){

				Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
				List<Coordinates> coordinateList = pair.getValue();
				int sides =coordinateList.size();
				for(int i=0;i<sides;i++){
					for(int j=0;j<sides;j++){
						line2 = new Line2D.Double(coordinateList.get(i).getX(),coordinateList.get(i).getY(), coordinateList.get((j)).getX(), coordinateList.get((j)).getY());
							if(line2.intersectsLine(line1)){
								if((end.getX()==coordinateList.get(i).getX() && end.getY()==coordinateList.get((i)).getY()) 
										|| (end.getX()==coordinateList.get(j).getX() && end.getY()==coordinateList.get((j)).getY())){
									continue;
								}
								if((start.getX()==coordinateList.get(i).getX() && start.getY()==coordinateList.get((i)).getY()) 
										|| (start.getX()==coordinateList.get(j).getX() && start.getY()==coordinateList.get((j)).getY())){
									continue;
								}

								isIntersecting = true;
								return isIntersecting;
							}
						
					}
					
				}
			}
		}
		return isIntersecting;
	}
	
	/*
	 *	This function finds the path from start to goal avoiding obstacles and stores all the coordinates 
	 *  of the path in the list pathCoordinates
	 */
	public void findPath() {
		pathCoordinates = new ArrayList<Coordinates>();
		Coordinates currentState = start,tempState;
		State newState;
		pathCoordinates.add(currentState);
		
		while(true){
			nextAvailableState = new ArrayList<State>();
			if(intersectLine(currentState, goal) == false){
				pathCoordinates.add(goal);
				performance = pathCoordinates.size()-1;
				performance = 1000 - performance;
				System.out.println("PERFORMANCE of 4b : "+ (1000-performance));
				return;
			}else{
				Iterator<?> it = board.getObstacleMap().entrySet().iterator();
				while(it.hasNext()){
					Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
					List<Coordinates> coordinateList = pair.getValue();
					int sides =coordinateList.size();
					for(int i=0;i<sides;i++){
						Coordinates c = new Coordinates(coordinateList.get(i).getX(), coordinateList.get(i).getY());
						if(intersectLine(currentState, c) == false){
							newState = new State(c);
							newState.setH(calculateHeuristic(c));
							newState.setCost(1);
							newState.setHdash();
							nextAvailableState.add(newState);
						}
					}
				}
				tempState = findMinHState();
				random = (int) (Math.random()* 10);
				if(random >3){
						currentState = tempState;
				}
				else{
					int t = (int) (Math.random()*nextAvailableState.size());
					while(nextAvailableState.get(t).getCoord() == tempState){
						t = (int) (Math.random()*nextAvailableState.size());
					}
					currentState = nextAvailableState.get(t).getCoord();
				}
				pathCoordinates.add(currentState);
			}	

		}
	}

	/*
	 * This function returns coordinate of the state which is nearer to goal
	 */
	private Coordinates findMinHState() {
		// TODO Auto-generated method stub
		double min;
		int j=0;
		min = nextAvailableState.get(0).getHdash();
		for(int i=0; i<nextAvailableState.size();i++){
			if(min> nextAvailableState.get(i).getHdash()){
				min = nextAvailableState.get(i).getHdash();
				j=i;
			}		
		}
		return nextAvailableState.get(j).getCoord();
	}


	/*
	 *  This function calculates heuristic i.e. Eucledian Distance
	 */
	private double calculateHeuristic(Coordinates coord) {
		// TODO Auto-generated method stub
		double h = Math.sqrt(((coord.getX() - goal.getX()) * (coord.getX() - goal.getX())) + ((coord.getY() - goal.getY()) * (coord.getY() - goal.getY())));
		return h;
	}

}
