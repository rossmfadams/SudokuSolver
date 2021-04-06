package sudokuSolver;

import java.awt.*;
import javax.swing.*;

public class SudokuPanel extends JPanel 
{
	// Private Data Fields
	private JLabel sudokuLabel;
	private JButton connectButton;
	
	
	// Constructor for the Sudoku Panel.
	  public SudokuPanel()
	  { 
		  	// Create Sudoku Solver label.
		    sudokuLabel = new JLabel("Sudoku Solver", JLabel.CENTER);
		    
		    // 
		    
		    // Create the Connect Webcam Button
		    connectButton = new JButton("Connect Webcam");
		    
		    
	  }
}
