package model;

/*
 * Class to store details for a state like heuristic, path cost, Coordinates of state 
 */
public class State {
	Coordinates coord;
	double h;
	double cost;
	double hdash;
	
	public State(Coordinates coord){
		this.coord = coord;
	}
	public Coordinates getCoord() {
		return coord;
	}
	public void setCoord(Coordinates coord) {
		this.coord = coord;
	}
	public double getH() {
		return h;
	}
	public void setH(double h) {
		this.h = h;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getHdash() {
		return hdash;
	}
	public void setHdash() {
		this.hdash = this.h + this.cost;
	}
	
	
	
}
