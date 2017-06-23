package com.TPP.Connect4;

import javax.swing.*;

import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {

	private JFrame frame;
	private JLabel[][] slots;
	private JButton[] buttons;
	//variables used in grid
	private int xLen = 7;
	private int yLen = 6;
	private int currentPlayer = 1;
	//Number of players variable is initialised here: up to 4 human players are supported
	private int numPlayers=2;
	//Winning condition
	private int max = 4;
	//State of game
	//-1: quit game; 0: game running; 1: player has won game; 2: game has been drawn; 3: new game;
	private int state = 0;

	//making of grid and logic
	private Grid myGrid;
	private Model myModel;

	public Gui(int tempNum, int xxLen, int yyLen, int maxx) {

		xLen=xxLen;
		yLen=yyLen;
		//making of grid and logic
		myGrid = new Grid(xLen,yLen);
		max=maxx;
		numPlayers=tempNum;
		myModel = new Model(myGrid, max);

		frame = new JFrame("Connect Four Game!");

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(xLen+1,yLen));

		slots = new JLabel[xLen][yLen];
		buttons = new JButton[yLen];

		//Buttons used for user input are setup here.
		//The action listener checks each time a button is pressed for a win or draw.
		//Keeps GUI in sync with Model.
		for (int i = 0; i < yLen; i++) {
			buttons[i] = new JButton("" + (i + 1));
			buttons[i].setActionCommand("" + i);
			buttons[i].addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							int a = Integer.parseInt(e.getActionCommand());
							int y = myGrid.findY(a);//finds the lowest space in column
							if (y != -1) {
								//sets a place to current player
								if (myModel.setAndCheck(y, a, currentPlayer)) {
									state=1;
								} else if (myModel.drawCheck()) {//checks for drawgame
									state=2;
								} else {
									//change player
									currentPlayer = myGrid.changePlayer(currentPlayer, numPlayers);
									frame.setTitle("Connect Four - Player " + currentPlayer + "'s turn");
								}
							} else {
								JOptionPane.showMessageDialog(null, "Choose another column!", "The column is filled", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					});
			panel.add(buttons[i]);
		}

		//Styling for slots
		for (int row = 0; row < xLen; row++) {
			for (int column = 0; column < yLen; column++) {
				slots[row][column] = new JLabel();
				slots[row][column].setBorder(new LineBorder(Color.black));
				panel.add(slots[row][column]);
			}
		}

		//Setup of frame
		frame.setContentPane(panel);
		frame.pack();
		frame.setSize(100*yLen, 100*xLen+100);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void updateBoard() {//keeps the GUI in sync with the Grid
		for (int row = 0; row < xLen; row++) {
			for (int column = 0; column < yLen; column++) {
				if (myGrid.matrixEquals(row, column, 1)) {
					slots[row][column].setOpaque(true);
					slots[row][column].setBackground(Color.red);
				}
				if (myGrid.matrixEquals(row, column, 2)) {
					slots[row][column].setOpaque(true);
					slots[row][column].setBackground(Color.blue);
				}
				if (myGrid.matrixEquals(row, column, 3)) {
					slots[row][column].setOpaque(true);
					slots[row][column].setBackground(Color.green);
				}
				if (myGrid.matrixEquals(row, column, 4)) {
					slots[row][column].setOpaque(true);
					slots[row][column].setBackground(Color.yellow);
				}
			}
		}
	}

	//Function to display a pop-up when the game has been won.

	public void showWon() {
		String winner = "Player " + currentPlayer + " wins!";
		int n = JOptionPane.showConfirmDialog(frame,"New game?",winner,
				JOptionPane.YES_NO_OPTION);
		if (n==0) { // new game selected
			frame.dispose();
			state=3;
		} else { //quit option selected
			frame.dispose();
			state=-1;
		}
	}

	//Function to display a pop-up when the game has been drawn.

	public void showDraw() {
		String draw = "The game was a draw!";
		int n = JOptionPane.showConfirmDialog(frame,"New game?",draw,
				JOptionPane.YES_NO_OPTION);
		if (n==0) { //new game selected
			frame.dispose();
			state=3;
		} else { //quit option selected
			frame.dispose();
			state=-1;
		}
	}

	public JLabel getSlot(int row, int column) {
		return slots[row][column];
	}
	
	public JButton getButton(int i) {
		return buttons[i];
	}
	
	public int getState() {
		return state;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getXLen() {
		return xLen;
	}
	
	public int getYLen() {
		return yLen;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}

	//This main function was used in the debugging of the GUI.
	/*
	    public static void main(String[] args) {
	        Gui Gui = new Gui();
	    }
	 */
}
