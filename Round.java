package com.mycompany.phase10;

import java.util.Stack;

public class Round {

    private Phase10 game;
    private Deck deck;
    private Stack<Card> discardStack;

    private int curPlayerTurnNum; // the turn number of player = who goes next (clockwise)

    private int turnNumber;

    private boolean started;

    Round(Phase10 g) {
        game= g;
        curPlayerTurnNum= game.getDealer() - 1;// the left of the dealer
        turnNumber= 0;
        started= false;

        // initialize round
        deck= new Deck(game);
        deck.shuffle();
        deck.deal();
        discardStack = new Stack<>();
        discardStack.push(deck.get(deck.size()-1));
        deck.remove(deck.size()-1);
    }

    /** Start the round. Throw Phase10Exception if round has already started */

    public void startRound() {
        if (!started) {
            started= true;
            nextTurn();
        } else {
            throw new Phase10Exception("Round already started");
        }
    }

    /** Return false if invalid move (aka picked up a skip card) otherwise true. Remove the next
     * card from the discard pile and move it into the current player's hand Throw Phase10Exception
     * if player has already drawn a card this turn */
    public boolean drawFromDiscardStack() {
        Player player= game.getCurrentPlayer();
        // has drawn
        if (player.getHasDrawnCard())
            throw new Phase10Exception("CANNOT draw from discard: has already drawn");
        // skip card
        if (discardStack.peek().getValue() == Configuration.SKIP_VALUE) return false;

        Card card= discardStack.pop();

//        game.getDebugLog().addEntry(
//        new DebugLogEntry(turnNumber, player, "Draw from discard: " + card));

        player.getHand().addCard(card);
        if (Configuration.getTypeRequired(player.getPhase(), 0) == Configuration.COLOR_PHASE) {
            player.getHand().sortByColor();
        } else {
            player.getHand().sortByValue();
        }

        player.setHasDrawnCard(true);
        player.setDrewFromDiscard(true);
        return true;
    }

    /** Remove the next card from the deck and add to the current player's hand. If there are no
     * more cards in the deck, the discard stack is reshuffled. Throw Phase10Exception if the player
     * has already drawn a card this turn */

    public void drawFromDeck() {
        Player player= game.getCurrentPlayer();

        if (player.getHasDrawnCard()) {
            throw new Phase10Exception("CANNOT draw from deck: has already drawn");
        }

        Card card= deck.get(deck.size() - 1);

        player.getHand().addCard(card);
        deck.remove(deck.size() - 1);

        player.setHasDrawnCard(true);

        if (Configuration.getTypeRequired(player.getPhase(), 0) == Configuration.COLOR_PHASE) {
            player.getHand().sortByColor();
        } else {
            player.getHand().sortByValue();
        }

//        game.getDebugLog().addEntry(
//            new DebugLogEntry(turnNumber, player, "Draw from deck: " + card));
        player.setDrewFromDiscard(false);

        if (deck.isEmpty()) {
            Card topDiscard= discardStack.pop();
            while (!discardStack.isEmpty()) {
                deck.add(discardStack.pop());
            }
            deck.shuffle();
            discardStack.push(topDiscard);
        }
    }

    /** Remove the card from the current player's hand and add it to the discard stack. Then,
     * advance to the next player's turn (check if the round is over) Throw Phase10Exception if the
     * player has not yet drawn a card this turn */

    public void discard(Card card) {
        Player player= game.getCurrentPlayer();

        if (!player.getHasDrawnCard()) {
            throw new Phase10Exception("CANNOT discard: has not yet drawn");
        }
//        game.getDebugLog().addEntry(
//            new DebugLogEntry(turnNumber, player, "Discard: " + card));

        discardStack.push(card);
        player.getHand().isRemovedCard(card);

        player.setHasDrawnCard(false);

        if (Configuration.getTypeRequired(player.getPhase(), 0) == Configuration.COLOR_PHASE) {
            player.getHand().sortByColor();
        } else {
            player.getHand().sortByValue();
        }

//        game.getDebugLog().addEntry(
//            new DebugLogEntry(turnNumber, player, "Current Hand: "
//                    + player.getHand()));

        if (card.getValue() == Configuration.SKIP_VALUE) {
            int nextPlayer= curPlayerTurnNum + 1;
            if (nextPlayer >= game.getNumberOfPlayers()) {
                nextPlayer= 0;
            }

            game.getPlayer(nextPlayer).setSkip(true);

//            game.getDebugLog().addEntry(
//                new DebugLogEntry(turnNumber, player, "Skipping player: "
//                        + game.getPlayer(nextPlayer)));
        }

        nextTurn();
    }

    /** Return the top of the discardStack, null if no card. */
    public Card getTopOfDiscardStack() {
        return discardStack.isEmpty() ? null : discardStack.peek();
    }

    /** Get the player index of who is currently playing their turn */
    public int getCurPlayerTurnNum() {
        return curPlayerTurnNum;
    }

    /** check if the round is complete; if not, advances play to the next player (skip if
     * necessary) */

    public void nextTurn() {
        if (roundIsComplete()) {
            game.nextRound();
        } else {
            advanceTurn();
            if (game.getPlayer(curPlayerTurnNum).getSkip()) {
                game.getPlayer(curPlayerTurnNum).setSkip(false);
                advanceTurn();
            }
//            try {
//                game.getGameManager().getGui().newTurnWindowUpdate();
//
//            } catch (NullPointerException e) {
//                // System.out.println("Too early");
//            }
        }
    }

    /** increases the turn counter by 1, or wraps around */
    private void advanceTurn() {
        turnNumber++ ;
        curPlayerTurnNum++ ;
        if (curPlayerTurnNum >= game.getNumberOfPlayers()) {
            curPlayerTurnNum= 0;
        }
    }

    /** Return true if the round is over (if any players have no cards left on hand) otherwise
     * false */

    private boolean roundIsComplete() {
        for (int p= 0; p < game.getNumberOfPlayers(); p++ ) {
            if (game.getPlayer(p).getHand().getNumberOfCards() == 0) { return true; }
        }
        return false;
    }

    /** Return the turn number for this round */

    public int getTurnNumber() {
        return turnNumber;
    }

}
