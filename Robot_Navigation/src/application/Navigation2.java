package application;

import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import model.Board;
import model.Coordinates;
import model.State;

/*
 * Implementation of LRTA* Algorithm for Question 2
*/

public class Navigation2 {
	static Board board1 = Board.getBoardInstance();
	static List<State> nextAvailableState2 = null;
	public static List<Coordinates> pathCoordinates2 = new ArrayList<Coordinates>();
	Coordinates goal = board1.getGoal();
	Coordinates start = board1.getStart();
	int x;
	int y;
	public static int performance=0;
	

	/*
	 * This function constructs a line with the given two input coordinates and checks this line with all the lines of 
	 * the polygon in the Environment whether it intersects or not
	 * It returns true if it intersects with one line else returns false
	 */
	public static boolean intersectLine2(Coordinates start, Coordinates end) {
		boolean isIntersecting = false;
		if(board1.getObstacleMap()!=null){
			Iterator<?> it = board1.getObstacleMap().entrySet().iterator();
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
	* This function chesck if point is inside a polygon or not using Ray Algorithm
	* It takes start and end co-ordinates
	* Output is boolean i.e. true if point lies in poygon and false otherwise
	*/
	
	public static boolean pointIntersect(Coordinates start, Coordinates end) {
		int count=0;
		boolean intersect = false;
		if(board1.getObstacleMap()!=null){
			Iterator<?> it = board1.getObstacleMap().entrySet().iterator();
			Line2D line2;
			Line2D line1 = new Line2D.Double(start.getX(),start.getY(), end.getX(),end.getY());
			while(it.hasNext()){

				Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
				List<Coordinates> coordinateList = pair.getValue();
				int sides =coordinateList.size();
				for(int i=0;i<sides;i++){
					line2 = new Line2D.Double(coordinateList.get(i).getX(),coordinateList.get(i).getY(), coordinateList.get((i+1)%sides).getX(), coordinateList.get((i+1)%sides).getY());
					if(line2.intersectsLine(line1)){
						count++;
					}
				}
			}
			if(count%2 == 1)
				intersect = true;
		}
		return intersect;
	}

	/*
	 *	This function finds the path from start to goal avoiding obstacles and stores all the coordinates 
	 *  of the path in the list pathCoordinates
	 */
	public void findPath() {
		Coordinates currentState;
		State newState;
		System.out.println("In find Path");
		Coordinates end;

		for(int k=1;k<=100;k++){
			pathCoordinates2 = new ArrayList<Coordinates>();
			x = (int)(Math.random() * 150);
			y = (int)(Math.random() * 200);
			currentState = new Coordinates(x,y);
			end = new Coordinates(600,y);
			
			while(pointIntersect(currentState, end) == true){
				x = (int)(Math.random() * 150);
				y = (int)(Math.random() * 200);
				currentState = new Coordinates(x,y);
				end = new Coordinates(600,y);
			}
			
			pathCoordinates2.add(currentState);
			while(true){
				nextAvailableState2 = new ArrayList<State>();
				if(intersectLine2(currentState, goal) == false){
					pathCoordinates2.add(goal);
					performance += (1000-(pathCoordinates2.size()-1));
					break;
				}else{
					Iterator<?> it = board1.getObstacleMap().entrySet().iterator();
					while(it.hasNext()){
						Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
						List<Coordinates> coordinateList = pair.getValue();
						int sides =coordinateList.size();
						for(int i=0;i<sides;i++){
							Coordinates c = new Coordinates(coordinateList.get(i).getX(), coordinateList.get(i).getY());
							if(intersectLine2(currentState, c) == false){
								newState = new State(c);
								newState.setH(calculateHeuristic(c));
								newState.setCost(1);
								newState.setHdash();
								nextAvailableState2.add(newState);
							}
						}
					}
					currentState = findMinHState();
					pathCoordinates2.add(currentState);
				}	

			}

		}
		performance = performance/100;
		System.out.println("Randomly Generating Start State not in obstacle");
		System.out.println("Average Performance for 100 iterations : " + (performance/100));
	}

	/*
	 * This function returns coordinate of the state which is nearer to goal
	 */
	private Coordinates findMinHState() {
		double min;
		int j=0;
		min = nextAvailableState2.get(0).getHdash();
		for(int i=0; i<nextAvailableState2.size();i++){
			if(min> nextAvailableState2.get(i).getHdash()){
				min = nextAvailableState2.get(i).getHdash();
				j=i;
			}		
		}
		return nextAvailableState2.get(j).getCoord();
	}

	/*
	 *  This function calculates heuristic i.e. Eucledian Distance
	 */
	private double calculateHeuristic(Coordinates coord) {
		double h = Math.sqrt(((coord.getX() - goal.getX()) * (coord.getX() - goal.getX())) + ((coord.getY() - goal.getY()) * (coord.getY() - goal.getY())));
		return h;
	}

}
