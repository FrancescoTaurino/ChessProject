package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import View.StartingMenu;


public class ChessboardMain {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				JFrame frame  = new StartingMenu();
				frame.setTitle("Chess");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}