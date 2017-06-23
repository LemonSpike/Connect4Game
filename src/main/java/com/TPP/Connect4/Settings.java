package com.TPP.Connect4;

import javax.swing.JOptionPane;

public class Settings {

	//variables used in grid
	private int xLen;
	private int yLen;
	//Number of players variable is initialised here: up to 4 human players are supported
	private int numPlayers;
	//Winning condition
	private int max;

	//Used for error handling
	private String numPlayerss = "2";
	private String maxx = "4";
	private String xxLen="7";
	private String yyLen="6";

	//Constructor for getting Game settings from User.
	public Settings() {
		try {
			numPlayerss=JOptionPane.showInputDialog("Please enter the number of players (only 2-4 players are supported)");
			setNumPlayers(Integer.parseInt(numPlayerss));
			
			xxLen=JOptionPane.showInputDialog("Please enter a reasonable number of rows (4-14)!");
			setXLen(Integer.parseInt(xxLen));
			
			yyLen=JOptionPane.showInputDialog("Please enter a reasonable number of columns (4-14)!");
			setYLen(Integer.parseInt(yyLen));

			maxx=JOptionPane.showInputDialog("Please enter the winning number of counters!");
			setMax(Integer.parseInt(maxx));

		//Catch blocks to display a pop-up when the game has incorrect settings, and exit the program.
			
		} catch (NumberFormatException e){
			System.err.printf("Exception: %s\n", e);
			System.err.printf("The input must be an integer number, try again.\n");
			
			if (maxx==null || numPlayerss==null || xxLen==null || yyLen==null) {
				System.exit(-1);
			} else {
				JOptionPane.showMessageDialog(null, "Try again! Input a valid number.", "There was an error!", JOptionPane.INFORMATION_MESSAGE);				
				System.exit(-1);
			}
		} catch (IllegalArgumentException e) {
			System.err.printf("Exception: %s\n", e);
			System.err.printf("%s\n",e.getMessage());

			if (e.getMessage().equals("numPlayers")) {
				JOptionPane.showMessageDialog(null, "Try again! Input a number between 2 and 4.", "There was an error!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(-1);
			} else if (e.getMessage().equals("xLen") || e.getMessage().equals("yLen")) {
				JOptionPane.showMessageDialog(null, "Try again! Input a number between 4 and 14.", "There was an error!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(-1);
			} else if (e.getMessage().equals("max")) {				
				JOptionPane.showMessageDialog(null, "Choose a valid winning condition!", "Please run again with correct settings!", JOptionPane.INFORMATION_MESSAGE);
				System.exit(-1);
			}

			System.err.printf("The grid dimensions must be an integer number between 4 and 14,  try again.\n");

			JOptionPane.showMessageDialog(null, "Try again! Input a valid number.", "There was an error!", JOptionPane.INFORMATION_MESSAGE);				
			System.exit(-1);
		}
	}

	//Get and set methods which check for invalid values.
	
	public void setNumPlayers(int players) {
		if (players < 2 || players > 4) {
			throw new IllegalArgumentException("numPlayers");
		}
		numPlayers = players;
	}

	public void setXLen(int rows) {
		if (rows < 4 || rows > 14) {
			throw new IllegalArgumentException("xLen");
		}
		xLen = rows;
	}

	public void setYLen(int columns) {
		if (columns < 4 || columns > 14) {
			throw new IllegalArgumentException("yLen");
		}
		yLen = columns;
	}

	public void setMax(int tempMax) {
		if (tempMax < 2 || tempMax > Math.min(xLen, yLen)) {
			throw new IllegalArgumentException("max");
		}
		max = tempMax;
	}

	public int getNumPlayers() {
		return numPlayers;
	}
	
	public int getXLen() {
		return xLen;
	}
	
	public int getYLen() {
		return yLen;
	}
	
	public int getMax() {
		return max;
	}
	
}
