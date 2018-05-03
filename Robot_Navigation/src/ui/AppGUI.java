package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.Navigation;
import application.Navigation2;
import application.Navigation3;
import application.Navigation4b;
import model.Board;
import model.FileDetails;
import parser.FileParser;

public class AppGUI {
	
	public static boolean navigation1 = false;
	public static boolean navigation2 = false;
	public static boolean navigation3 = false;
	public static boolean navigation4b = false;
	/*
	 * This function creates the menu bar of the GUI
	 * It adds option to run Question 1, Question 2, Question 3, Question 4b
	 * It also adds option to open a file which has input for environment
	 * It also adds option to exit from the Environment
	 */
	public static JMenuBar addMenuBar(final JFrame mainFrame){

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu exitMenu = new JMenu("Exit");
		menuBar.add(fileMenu);
		menuBar.add(exitMenu);
		
		// Create and add simple menu item to one of the drop down menu
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem navigation1Action = new JMenuItem("Question 1");
		JMenuItem navigation2Action = new JMenuItem("Question 2");
		JMenuItem navigation3Action = new JMenuItem("Question 3");
		JMenuItem navigation4bAction = new JMenuItem("Question 4b");
		JMenuItem exitAction = new JMenuItem("Exit");

		//Adding Actions to the respective Menu
		fileMenu.add(openAction);
		fileMenu.add(navigation1Action);
		fileMenu.add(navigation2Action);
		fileMenu.add(navigation3Action);
		fileMenu.add(navigation4bAction);
		exitMenu.add(exitAction);
		
		final FileParser parseFile = new FileParser();
		final Board b = Board.getBoardInstance();
		final DrawBoard db=new DrawBoard();
		
		/*
		 * This listener is to open a input file from the system in order initialise the Environment
		 */
		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				String extType[] = { "txt" };
				JFileChooser chooser = new JFileChooser();
				JFrame frame = new JFrame();
				FileFilter filter = new FileNameExtensionFilter("Text File",	extType);
				chooser.setFileFilter(filter);
				chooser.setDialogTitle("Opens Text File");

				int returnVal = chooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "+ chooser.getSelectedFile().getName());
					FileDetails fileDetail = new FileDetails();
					fileDetail.setFileName(chooser.getSelectedFile().getName());
					fileDetail.setFilePath(chooser.getSelectedFile().getAbsolutePath());
					
					if (chooser.getSelectedFile() != null && parseFile != null) {
						b.clearObstacleMap();
						DrawBoard.count=0;
						parseFile.parseDataFile(fileDetail);
						mainFrame.add(db);
					}			
				}	
				navigation1= true;
				navigation2 = false;
				navigation3 = false;
				navigation4b = false;
				Navigation2.pathCoordinates2 = null;
				Navigation3.pathCoordinates = null;
				Navigation4b.pathCoordinates = null;
			}
		});
		
		/*
		 * This listener is to implement Question 1 when user chooses Question 1 option from Files Menu
		 */
		navigation1Action.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				navigation1= true;
				navigation2 = false;
				navigation3 = false;
				navigation4b = false;
				Navigation2.pathCoordinates2 = null;
				Navigation3.pathCoordinates = null;
				Navigation4b.pathCoordinates = null;
				Navigation nv = new Navigation();
				nv.findPath();
			}
		});
		
		/*
		 * This listener is to implement Question 2 when user chooses Question 2 option from Files Menu
		 */
		navigation2Action.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				DrawBoard.count = 0;
				navigation1= false;
				navigation2 = true;
				navigation3 = false;
				navigation4b = false;
				Navigation.pathCoordinates = null;
				Navigation3.pathCoordinates = null;
				Navigation4b.pathCoordinates = null;
				Navigation2 nv = new Navigation2();
				nv.findPath();
			}
		});

		/*
		 * This listener is to implement Question 3 when user chooses Question 3 option from Files Menu
		 */
		navigation3Action.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				DrawBoard.count = 0;
				navigation1= false;
				navigation2 = false;
				navigation3 = true;
				navigation4b = false;
				Navigation.pathCoordinates = null;
				Navigation2.pathCoordinates2 = null;
				Navigation4b.pathCoordinates = null;
				Navigation3 nv = new Navigation3();
				nv.findPath();
				
			}
		});
		
		/*
		 * This listener is to implement Question 4b when user chooses Question 4 option from Files Menu
		 */
		navigation4bAction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DrawBoard.count = 0;
				navigation1= false;
				navigation2 = false;
				navigation3 = false;
				navigation4b = true;
				Navigation.pathCoordinates = null;
				Navigation2.pathCoordinates2 = null;
				Navigation3.pathCoordinates = null;
				Navigation4b nv = new Navigation4b();
				nv.findPath();
			}
		});
		
		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		return menuBar;
	}
}