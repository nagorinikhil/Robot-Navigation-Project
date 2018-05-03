package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import application.Navigation;
import application.Navigation2;
import application.Navigation3;
import application.Navigation4b;
import model.Board;
import model.Coordinates;

//Draws path, obstacles (polygons) and goal state in UI
public class DrawBoard extends JPanel {

	private static final long serialVersionUID = 1L;
	static Board board = Board.getBoardInstance();
	public static Arc2D vision;
	Graphics2D g2d; 
	Graphics gref;
	public static int count=0;

	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g){
		super.paintComponent( g );
		g2d = (Graphics2D)g.create();
		gref=g;

		if(board.isFileOpen()){
			g2d.drawString("Start", board.getStart().getX(), board.getStart().getY());
			g2d.drawString("Goal", board.getGoal().getX(), board.getGoal().getY());
			drawObstacle(g);
		
			if(count==0 ){
				if(AppGUI.navigation1 == true){
					Navigation nv= new Navigation();
					nv.findPath();
				}
				
				count++;
			}
			
			// Draw path for Question 1
			if(Navigation.pathCoordinates!=null){
				int i=1;
				for(Coordinates coord:Navigation.pathCoordinates){
					if(i<Navigation.pathCoordinates.size()){
						Line2D  line= new Line2D.Double(coord.getX(),coord.getY(), Navigation.pathCoordinates.get(i).getX(), Navigation.pathCoordinates.get(i).getY());
						g2d.setColor(Color.red);
						g2d.draw(line);
						i++;
					}
				}
				g2d.drawString("Performance Measure = "+Navigation.performance, 250, 350);
			}

			//Draw path for Question 2
			if(Navigation2.pathCoordinates2!=null){
				int i=1;
				for(Coordinates coord:Navigation2.pathCoordinates2){
					if(i<Navigation2.pathCoordinates2.size()){
						Line2D  line= new Line2D.Double(coord.getX(),coord.getY(), Navigation2.pathCoordinates2.get(i).getX(), Navigation2.pathCoordinates2.get(i).getY());
						g2d.setColor(Color.red);
						g2d.draw(line);
						i++;
					}
				}
				g2d.drawString(" Average Performance Measure = "+Navigation2.performance, 250, 350);
			}
			
			//Draw path for Question 3
			if(Navigation3.pathCoordinates!=null){
				int i=1;
				for(Coordinates coord:Navigation3.pathCoordinates){
					if(i<Navigation3.pathCoordinates.size()){
						Line2D  line= new Line2D.Double(coord.getX(),coord.getY(), Navigation3.pathCoordinates.get(i).getX(), Navigation3.pathCoordinates.get(i).getY());
						g2d.setColor(Color.red);
						g2d.draw(line);
						i++;
					}
				}
				g2d.drawString("Performance Measure = "+Navigation3.performance, 250, 350);
			}
			
			//Draw path for Question 4b
			if(Navigation4b.pathCoordinates!=null){
				int i=1;
				for(Coordinates coord:Navigation4b.pathCoordinates){
					if(i<Navigation4b.pathCoordinates.size()){
						Line2D  line= new Line2D.Double(coord.getX(),coord.getY(), Navigation4b.pathCoordinates.get(i).getX(), Navigation4b.pathCoordinates.get(i).getY());
						g2d.setColor(Color.red);
						g2d.draw(line);
						i++;
					}
				}
				g2d.drawString("Performance Measure = "+Navigation4b.performance, 250, 350);
			}
			
		}
		revalidate();
		repaint();
		g2d.dispose();
	} 
	
	/*
	 * This functions draws all the polygons in the environment
	 */
	public static void drawObstacle(Graphics g) {

		if(board.getObstacleMap()!=null){
			Iterator<?> it = board.getObstacleMap().entrySet().iterator();

			while(it.hasNext()){
				Map.Entry<Integer, List<Coordinates>> pair = (Map.Entry)it.next();
				List<Coordinates> coordinateList = pair.getValue();
				int sides =coordinateList.size();
				int[] polyX = new int[sides];
				int[] polyY =new int[sides];

				int counter=0;
				for(Coordinates obj:coordinateList){
					polyX[counter]=obj.getX();
					polyY[counter]=obj.getY();
					counter++;				
				}
				g.setColor(Color.GRAY);
				g.fillPolygon(polyX, polyY, sides);
			}
		}
	}

}
