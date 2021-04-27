// Referencing code from 

package com.sudokuSolver;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.*;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_videoio.VideoCapture;
import org.bytedeco.javacv.CanvasFrame;
import static org.bytedeco.javacpp.opencv_videoio.CV_CAP_PROP_FRAME_HEIGHT;
import static org.bytedeco.javacpp.opencv_videoio.CV_CAP_PROP_FRAME_WIDTH;

@SuppressWarnings("serial")
public class SudokuPanel extends JPanel 
{
	private MyCanvas objCanvas;
	private JButton control;
	private JLabel errorLabel;
	
	// Constructor for the Sudoku Panel.
	public SudokuPanel(SudokuControl sc)
	{ 
		// Create the information labels.
		JLabel label = new JLabel("Sudoku Solver", JLabel.CENTER);
		errorLabel = new JLabel("",JLabel.CENTER);
		
		// Create Canvas
		objCanvas = new MyCanvas();
		JPanel canvasBuffer = new JPanel();
		canvasBuffer.add(objCanvas);

		// Create the control button.
		control = new JButton("Start");
		control.addActionListener(sc);
		JPanel controlButtonBuffer = new JPanel();
		controlButtonBuffer.add(control);

		// Arrange the components in a grid.
		JPanel grid = new JPanel(new GridLayout(4, 1, 5, 5));
		grid.add(label);
		grid.add(errorLabel);
		grid.add(canvasBuffer);
		grid.add(controlButtonBuffer);
		this.add(grid);
	}
	
	public MyCanvas getCanvas() {
		return objCanvas;
	}
	
	public void setError(String error) {
		errorLabel.setText(error);
	}
	
	public void setControlButton(String msg) {
		control.setText(msg);
	}
}
