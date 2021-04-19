// Referencing code from 

package sudokuSolver;

import java.awt.*;
import javax.swing.*;

import org.bytedeco.javacv.CanvasFrame;

public class SudokuPanel extends JPanel 
{
	// Private Data Fields
	private JLabel sudokuLabel;
	private JButton connectButton;
	private AtomicReference capture;

	// Constructor for the Sudoku Panel.
	public SudokuPanel()
	{ 
		// Create the Mainframe
		CanvasFrame mainframe = newCanasFrame("Sudoku Solver");
		mainframe.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		mainframe.setCanvasSize(600, 600);
		mainframe.setLocationRelativeTo(null);
        mainframe.setLayout(new BoxLayout(mainframe.getContentPane(), BoxLayout.Y_AXIS));
        
        
		// Create Sudoku Solver label.
		sudokuLabel = new JLabel("Sudoku Solver", JLabel.CENTER);
		
		// Create the Video Panels
		capture = new AtomicReference<>(new VideoCapture());
		capture.get().set(CV_CAP_PROP_FRAME_WIDTH, 1280);
		capture.get().set(CV_CAP_PROP_FRAME_HEIGHT, 720);

		if (!capture.get().open(0)) {
			//log.error("Can not open the cam !!!");
		}

		final AtomicReference<Boolean> start = new AtomicReference<>(true);
		
		// Colored Video Feed
		Mat colorimg = new Mat();

		// Black and White Video Feed
		while(true) {
			
		}

		// Black and White Results Image
		CanvasFrame result = new CanvasFrame("Results");
		result.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		result.setCanvasSize(500, 500);
		result.setLocation(0, 440);

		
		// Create the Connect Webcam Button
		connectButton = new JButton("Connect Webcam");
		

		// Add components to frame
		mainframe.add(sudokuLabel, BorderLayout.CENTER);
		
		mainframe.pack();
		mainframe.setVisible(true);
		
	}
}
