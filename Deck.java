package com.mycompany.phase10;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

//Reference cards (listing the 10 Phases) and one deck of 108 cards;
//24 each of red, blue, yellow, and green cards numbered “1” through “12,”
//four “Skip” cards, and eight “Wild” cards, skips and wildcard are black.
public class Deck {
    private Phase10 game;
    private ArrayList<Card> deck;
    private Stack<Card> discardStack;

    /** Constructor: Instantiate a deck of cards. */
    public Deck(Phase10 g) {
        deck= new ArrayList<>();
        game = g;
        for (int color= 0; color < 4; color++ ) {
            for (int val= 1; val < 13; val++ ) {
                deck.add(new Card(Configuration.COLORS[color], val));
                deck.add(new Card(Configuration.COLORS[color], val));
            }
        }

        for (int i= 0; i < Configuration.NUM_WILDS; i++ ) {
            deck.add(new WildCard());
        }
        for (int j= 0; j < Configuration.NUM_SKIPS; j++ ) {
            deck.add(new Card(Configuration.SKIP_VALUE));
        }
    }

    public void shuffle() {
        Random r= new Random();
        //Get a random index of the array past the current index
        //The arugment is an exclusive bound
        //Swap the random element with the present element
        
        for (int t= 0; t < Configuration.TIME_TO_SHUFFLE; t++ ) {
            ArrayList<Card> newDeck= new ArrayList<Card>();
            while (!deck.isEmpty()) {
                int index= r.nextInt(deck.size());
                newDeck.add(deck.get(index));
                deck.remove(index);
            }
            deck= newDeck;
        }
    }

    public void deal() {
        for (int c= 0; c < Configuration.NUM_CARDS_TO_DEAL; c++ ) {
            for (int p= 0; p < game.getNumberOfPlayers(); p++ ) {
                game.getPlayer(p).getHand().addCard(deck.get(deck.size() - 1));
                deck.remove(deck.size() - 1);
            }
        }

        for (int p= 0; p < game.getNumberOfPlayers(); p++ ) {
            if (Configuration.getTypeRequired(game.getPlayer(p).getPhase(),
                0) == Configuration.COLOR_PHASE) {
                game.getPlayer(p).getHand().sortByColor();
            } else {
                game.getPlayer(p).getHand().sortByValue();
            }
        }

        discardStack= new Stack<Card>();
        discardStack.push(deck.get(deck.size() - 1));
        deck.remove(deck.size() - 1);

    }

    public int size() {
        return deck.size();
    }

    public Card get(int index) {
        return deck.get(index);
    }

    public Card remove(int index) {
        return deck.remove(index);
    }

    public boolean add(Card c) {
        return deck.add(c);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }

}
