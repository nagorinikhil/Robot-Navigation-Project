package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.AppGUI;
import ui.DrawBoard;

/**
 * Robot Navigation Application
 */
public class RobotApplication extends JFrame {

	private static final long serialVersionUID = 1L;
	private DrawBoard DB;

	public RobotApplication() {
		initUI();
	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				RobotApplication robot = new RobotApplication();
				robot.setVisible(true);
			}
		});
	}

	/*
	 * Initialize UI
	 */
	private void initUI() {

		this.setJMenuBar(AppGUI.addMenuBar(this));
		DB=new DrawBoard();
		this.add(DB);
		setTitle("Robot Navigation");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(600, 600);
		setResizable(false);
		setVisible(true);
	}
}