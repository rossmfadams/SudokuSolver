package com.sudokuSolver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JPanel;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_videoio.VideoCapture;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;

public class SudokuControl implements ActionListener{
	// Private data fields for the container and chat client.
	private JPanel container;
	private SudokuClient client;
	final AtomicReference<VideoCapture> capture = new AtomicReference<>(new VideoCapture());
	final AtomicReference<Boolean> start = new AtomicReference<>(true);
	private Mat colorimg, resultImg;
	private MyCanvas objCanvas;
	private static final OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();

	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String command = ae.getActionCommand();

		if (command.equals("Start")) {
			SudokuPanel sudokuPanel = (SudokuPanel)container.getComponent(3);
			start.set(true);
            capture.set(new VideoCapture());
            capture.get().open(0);
            sudokuPanel.setControlButton("Try Again");
			objCanvas = sudokuPanel.getCanvas();
			
			colorimg = new Mat();
			
			while(true) {
				while(start.get() && capture.get().read(colorimg)) {
					BufferedImage bufImg = new BufferedImage(colorimg.cols(), 
							colorimg.rows(), BufferedImage.TYPE_3BYTE_BGR);
					objCanvas.setImage(bufImg);
					objCanvas.repaint();
					
					try {
						client.sendToServer(colorimg);
					} catch (IOException e) {
						displayError("Error connecting to the server.");
					}
					
					try {
						Thread.sleep(150);
					} catch (InterruptedException ex){
						System.out.println(ex.getMessage());
					}
				} // End while start
				
				try {
					Thread.sleep(400);
				} catch (InterruptedException ex) {
					System.out.println(ex.getMessage());
				}
			} // End while true
			
			
		} else if ( command.equals("Try Again")) {
			SudokuPanel sudokuPanel = (SudokuPanel)container.getComponent(3);
			start.set(false);
            capture.get().release();
            sudokuPanel.setControlButton("Start");
		} 
		
		
	}
	
	public void sudokuSuccess(Mat resultImg) {
		SudokuPanel sudokuPanel = (SudokuPanel)container.getComponent(3);
		sudokuPanel.setControlButton("Try Again");
		this.displayError("");
		CanvasFrame result = new CanvasFrame("Result");
        result.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        result.setCanvasSize(500, 500);
        result.setLocation(0, 440);
        result.showImage(converter.convert(resultImg));
	}
	public void displayError(String error) {
		SudokuPanel sudokuPanel = (SudokuPanel)container.getComponent(3);
		sudokuPanel.setError(error);
	}
}
