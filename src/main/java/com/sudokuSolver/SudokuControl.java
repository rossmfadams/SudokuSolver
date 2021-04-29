package com.sudokuSolver;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * A class to process a Mat img, detect a sudoku puzzle, recognize digits, and solve the puzzle
 *
 * Modified from Taha Emara, http://www.emaraic.com
 * @author Ross Adams
 */
public class SudokuControl implements ActionListener{
	// Private data fields for the container and chat client.
	private JPanel container;

	public SudokuControl(JPanel container) {
		this.container = container;
	}
	
	
    
	public void actionPerformed(ActionEvent ae) {
		
		String command = ae.getActionCommand();
		if (command.equals("Logout")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container,"1");
		}

		if (command.equals("Open Webcam")) {
			SudokuPanel sudokuPanel = (SudokuPanel)container.getComponent(3);
            sudokuPanel.setControlButton("Stop");
            sudokuPanel.updateUI();
            sudokuPanel.runSolver();
		}
	}
}
