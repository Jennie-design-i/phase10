/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phase10;


    

/**
 *
 * @author yalinzhang
 */
public class Main {

	private Phase10 game;
	private GuiManager gui;

	/**
	 * Creates a new game manager object and starts the program
	 * @param args system arguments (none required)
	 */
	public static void main(String[] args) {
		new Main();
	}

	Main() {
		gui = new GuiManager(this);
		gui.initGui();
	}

	/**
	 * Create a new Phase10 object
	 */
	public void newGame() {
		game = new Phase10(this);
	}

	/**
	 * 
	 * @return the current game object
	 */
	public Phase10 getGame() {
		return game;
	}

	GuiManager getGui() {
		return gui;
	}

	

}
