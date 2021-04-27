package com.sudokuSolver;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;



public class SudokuTester {
	private String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	private String[] passwords = {"hello123","pass123","123456","hello1234"};

	private Database db; 
		
	private int rando;
	@Test
	public void testSetConnection() {
		db.setConnection("SudokuSolver/db.properties");
		Connection conn = db.getConnection();
		
		if(conn == null) {
			fail("Connection is equal to null...");
		}
	}
	@Test
	public void testQuery() {
		db.setConnection("SudokuSolver/db.properties");
		Connection conn = db.getConnection();
		rando = ((int)Math.random()*users.length);
		String username = users[rando]; 
		String expected = passwords[rando];
		
		ArrayList<String> actual = db.query("select password from user where username = " + username + ";");
		//compare expected with actual using assertEquals
		if(expected != actual.get(0)) {
			fail("Passwords did not match....");
		}
		
	}
	@Test
	public void testExecuteDML()throws SQLException {
		db.setConnection("SudokuSolver/db.properties");
		Connection conn = db.getConnection();
		
		db.query("insert into user	values('testing@yahoo.com', aes_encrypt('123456', 'key'));");
		
	}
}
