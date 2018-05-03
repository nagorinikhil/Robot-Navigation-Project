package parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import application.Navigation;
import application.Navigation3;
import application.Navigation4b;
import model.Board;
import model.Coordinates;
import model.FileDetails;

/*
 * This class parses the data of the file such as start point, goal point and obstacle points to construct the environment
 */
public class FileParser {
	public void parseDataFile(FileDetails fileDetail) {
		Board board = Board.getBoardInstance();
		try {
			File file = new File(fileDetail.getFilePath());
			Scanner input = new Scanner(file);
			int polygonCount=0;
			while (input.hasNextLine()) {
				String line = input.nextLine();
				String[] tokens = line.split("#");
				String last = tokens[tokens.length - 1];
				Coordinates coord ;
				String[] getCoordinates = last.trim().split(" ");
				board.setFileOpen(true);
				if(line.contains("Start")){
					coord = new Coordinates();
					coord.setX(Integer.parseInt(getCoordinates[0]));
					coord.setY(Integer.parseInt(getCoordinates[1]));
					board.setStart(coord);
					Navigation3.start = coord;
					Navigation4b.start = coord;
					Navigation.start = coord;
				}else if( line.contains("Goal")){
					coord = new Coordinates();
					coord.setX(Integer.parseInt(getCoordinates[0]));
					coord.setY(Integer.parseInt(getCoordinates[1]));
					board.setGoal(coord);
				}else if( line.contains("Obstacle")){
					List<Coordinates> obstacleCoordinates = new ArrayList<Coordinates>();
					int i=0;
					polygonCount++;
					while(getCoordinates.length > i){
						coord = new Coordinates();
						coord.setX(Integer.parseInt(getCoordinates[i]));
						coord.setY(Integer.parseInt(getCoordinates[i+1]));
						obstacleCoordinates.add(coord);
						i+=2;
					}
					board.setObstacleMap(polygonCount,obstacleCoordinates);
				}
			}
			input.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
