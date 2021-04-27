package com.sudokuSolver;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class MyCanvas extends Canvas {
	private BufferedImage img = null;
	private Graphics g = null;

	public MyCanvas () {
		setBackground (Color.GRAY);
		setBounds(10, 10, 387, 279);
	}

	public void paint (Graphics g) {
		Graphics2D g2;
		g2 = (Graphics2D) g;

		if (img != null) {
			g2.drawImage( img, 0, 0, getWidth(), getHeight(), null); 
		}
		//g2.drawString ("This is is a string", 0 + 10, getHeight() - 10);
	}
	public void repaint() {
		super.repaint();	  
	}

	public void setImage(BufferedImage img) {
		this.img = img;
	}

	public Graphics getG() {
		return this.g ;
	}
}
