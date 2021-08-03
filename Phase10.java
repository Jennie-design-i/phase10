package com.mycompany.phase10;

import java.util.ArrayList;

/** Logistics of a game */
public class Phase10 {

    private final ArrayList<Player> players;
    private Round round;
    private int roundNumber;

    private int dealer;

    private boolean started;

//    private DebugLog debugLog;

    private ArrayList<Player> winners = new ArrayList<>();
    
    private transient Main gameManager;


    /** Constructor: Instantiate a game with players, round, gameManager and dealer */
    public Phase10(Main m) {
        players= new ArrayList<>();
        dealer= -1;
        roundNumber= 0;
        started= false;
        gameManager=m;


        // debugLog = new DebugLog();
    }

    /** Return current round */
    public Round getRound() {
        return round;
    }

    /** Return current Round Number */
    public int getRoundNumber() {
        return roundNumber;
    }

    /** Return the number of players */
    public int getNumberOfPlayers() {
        return players.size();
    }

    /** Return required player */
    public Player getPlayer(int index) {
        return players.get(index);
    }

    /** Return the current player. Throw Phase10Exception if game not started */
    public Player getCurrentPlayer() {
        if (!started) throw new Phase10Exception(
            "Phase10 has NOT yet been started. Must call startGame before this action can be done. ");
        return getPlayer(round.getCurPlayerTurnNum());
    }

    /** Adds a player to the game. Throw Phase10Exception if game has already started. */
    public void addPlayer(Player p) {
        if (!started) {
            players.add(p);
            // debugLog.addEntry(new DebugLogEntry(0, p, "New human player added"));
        } else {
            throw new Phase10Exception("Cannot add player after game has started.");

        }
    }

    /** Return the number of the player that is currently dealer */
    public int getDealer() {
        return dealer;
    }

    

    /** Return the first round of the game. Throw Phase10Exception if the game has already started
     * or there are < 2 players added */
    public void startGame() {
        if (!started) {
            dealer= getNumberOfPlayers() - 1;

            if (getNumberOfPlayers() >= 2) {
                started= true;
                // debugLog.addEntry(new DebugLogEntry(0, null, "STARTING GAME"));
                nextRound();
                
            }
        } else {
            throw new Phase10Exception("Game has already started");
        }
    }

    /** Reset the neccessary player data and check to see if there is a winner */
    void nextRound() {
        // checks if there are winners when one round ends. numRound must be > 0
        if (roundNumber != 0) {
            finishRound();

            winners= checkWinners();
        }
        // if no winner, continue to the next round and change dealer
        if (winners.isEmpty()) {
            roundNumber++ ;
            nextDealer();

            // debugLog.addEntry(new DebugLogEntry(0, null, "Now on round #" + roundNumber));
            
            round= new Round(this);
            round.startRound();
            
        }
        // if there are winners, need to display who's the winner
        // and end the game
        else {
//            // for (Player e: winners){
//            // debugLog.addEntry(new DebugLogEntry(0, e, "Won the game with " + e.getScore() + "
//            // points"));
//            // }
//
//            // if(Configuration.PRINT_DEBUG_LOG){
//            // debugLog.printLog();
//            // }
//
            gameManager.getGui().endGame(winners);
//
        }

    }

    /** Check if any player has completed the 10th phase, If so, called the GUI method. Update
     * winners */

    private ArrayList<Player> checkWinners() {
        ArrayList<Player> winners= new ArrayList<Player>();
        int winnerScore= Integer.MAX_VALUE;

        for (int i= 0; i < players.size(); i++ ) {
            Player p= players.get(i);
            // if completed the 10th phase, check if the player has the minimum score
            if (p.getPhase() == 11) {
                if (p.getScore() < winnerScore) {
                    winners= new ArrayList<>();
                    winnerScore= p.getScore();
                    winners.add(p);
                }
                // case when some players have the same score
                else if (p.getScore() == winnerScore) {
                    winners.add(p);
                }
            }
        }
        return winners;
    }

    /** Increment the dealer counter, wraps around when greater than the number of players */

    private void nextDealer() {
        dealer++ ;
        if (dealer >= getNumberOfPlayers()) {
            dealer= 0;
        }
    }

    /** Reset the player's hand, phase groups, and adds the points to their score. */

    private void finishRound() {
//        debugLog.addEntry(new DebugLogEntry(0, null, "Finishing round#" + roundNumber));
//        for (Player p : players) {
//            // add points for remaining cards, and remove them from the hand
//            Hand hand = p.getHand();
//            debugLog.addEntry(new DebugLogEntry(0, p, "Cards in hand: " + hand));

        for (Player p : players) {
            Hand hand= p.getHand();
            
            // Clear hand and count score
            while (hand.getNumberOfCards() > 0) {
                p.addToScore(hand.getAnyCard(0).getPointValue());
                hand.removeCard(0);
            }
//        debugLog.addEntry(new DebugLogEntry(0, p, "New score: " + p.getScore()));

            // Clear phaseGroups
            while (p.getNumberOfPhaseGroups() != 0) {
                p.removePhaseGroup(0);
            }

            // increment phase if has placed down the current phase
            if (p.hasPlacedDownPhase()) {
                p.setHasDrawnCard(false);
                p.incrementPhase();
//            debugLog.addEntry(new DebugLogEntry(0, p, "Now on phase #" + p.getPhase()));
            } // else {
//            debugLog.addEntry(new DebugLogEntry(0, p, "Still on phase #"
//                    + p.getPhase()));

            // Reset has drawn card
            p.setHasDrawnCard(false);
            p.setPlacedDownPhase(false);
        }

    }

   

//    public DebugLog getDebugLog() {
//        return debugLog;
//    }

    public ArrayList<Player> getWinners() {
        return winners;
    }

    void setWinners(ArrayList<Player> winners) {
        this.winners= winners;
    }
}
