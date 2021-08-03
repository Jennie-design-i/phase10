/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.phase10;

import java.util.ArrayList;

/**
 *
 * @author yalinzhang
 */
public class GuiManager {
        protected Main mainManager;	
	private AddPlayerNames settingsWindow;
	private PhaseDescription pdWindow;
	private GameStage gameWindow;
	private Menu welcomeWindow;


	/**
	 * Constructor for GuiManager. Sets up all of the necessary windows.
	 * 
	 * @param m a reference to a GameManager class
	 */
	public GuiManager(Main m) {
		super();
		mainManager = m; //passes a reference from the game manager into the GUI manager
		
		settingsWindow = new AddPlayerNames(this);
		
		welcomeWindow = new Menu(this);
	}
	
	/**
	 * Returns a reference to GameFrame
	 * 
	 * @return the GameFrame object
	 */
	public GameStage getGameFrame() {
		return gameWindow;
	}
	
	/**
	 * 
	 * Returns the game's language
	 * 
	 * @return the current language of the game
	 */
	
	
	/**
	 * Initializes and displays the game window
	 */
	void initGameWindow() {
		gameWindow = new GameStage(this);
		displayGameFrame();
	}


	/*
	 * Begin display methods
	 */
	
	/**
	 * Displays the Settings Frame
	 */
	void displaySettingsFrame() {
		settingsWindow.setVisible(true);
	}

	/**
	 * Displays the Game Frame
	 */
	void displayGameFrame() {
		gameWindow.setVisible(true);
	}
	
	/**
	 * Displays the Score Frame
	 */
	void displayScoreFrame() {
		Score scoreWindow = new Score(this);
		scoreWindow.setVisible(true);
	}
	
	
	
	/**
	 * Displays the Phase DescriptionFrame
	 */
	void displayPhaseDescriptionFrame() {
		pdWindow = new PhaseDescription(this);
		pdWindow.setVisible(true);
	}
	
	
	/*
	 * End display methods
	 */
	
	
	/*
	 * begin GUI functional methods
	 */
	
	/**
	 * Initializes the GUI component of the game by displaying the welcome window
	 */
	public void initGui() {
		displayWelcomeWindow();
	}
	
	/**
	 * displays the welcome window
	 */
	private void displayWelcomeWindow() {
		welcomeWindow.setVisible(true);
	}

	/**
	 * updates the necessary frames for the next player's turn
	 */
//	public void newTurnWindowUpdate() {
//		gameWindow.updateFrame(mainManager.getGame());
//	}
	
	/**
	 * Performs GUI operations that occur at the end of the game
	 * 
	 * @param an arrayList of all the players who played the game
	 */
	public void endGame(ArrayList<Player> players) {
		String endMessage;
		
		if(players.size() == 1)
			endMessage = "Congratulations " + players.get(0).getName() + "! You Won!";
		else {
			endMessage = "It's a tie! The winners are: ";
			for(Player x : players)
				endMessage += x.getName() + ", ";
		}
			
		displayWelcomeWindow();
		gameWindow.dispose();
		displayScoreFrame();
	}
	
	/*
	 * end GUI functional methods
	 */
}

