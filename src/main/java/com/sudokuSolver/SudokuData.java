package com.sudokuSolver;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SudokuData implements Serializable {
	private byte[] bytes;
	
	public SudokuData(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public byte[] getBytes() {
		return bytes;
	}
	
}
