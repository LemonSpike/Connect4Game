package com.TPP.Connect4;

public class Connect4App {

	public static void main(String[] args) {
		int state = 0;

		//Initialisation of GUI.
		Settings Settings = new Settings();
		Gui Gui = new Gui(Settings.getNumPlayers(), Settings.getXLen(), Settings.getYLen(), Settings.getMax());
		while (state != -1) {//checks if program is in quitting stage
			switch (state) {
			case 0://Game being played
				Gui.updateBoard();
				if (Gui.getState()==3) {
					Gui = new Gui(Gui.getNumPlayers(),Gui.getXLen(), Gui.getYLen(), Gui.getMax());
				}
				state=Gui.getState();
				break;
			case 1://Game won
				Gui.showWon();
				if (Gui.getState()==3) {
					Gui = new Gui(Gui.getNumPlayers(),Gui.getXLen(), Gui.getYLen(), Gui.getMax());
				}
				state=Gui.getState();
				break;
			case 2://Draw
				Gui.showDraw();
				if (Gui.getState()==3) {
					Gui = new Gui(Gui.getNumPlayers(),Gui.getXLen(), Gui.getYLen(), Gui.getMax());
				}
				state=Gui.getState();
				break;
			case 3://Reset state after quitting
				state=0;
				break;
			}
		}
	}
}
