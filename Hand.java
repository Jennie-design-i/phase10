package com.mycompany.phase10;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {

    private ArrayList<Card> cards;
    private Phase10 game;
    private Player owner;

    Hand(Phase10 g, Player own) {
        cards= new ArrayList<>();
        game= g;
        owner= own;
    }

    void addCard(Card card) {
        cards.add(card);
    }

    void removeCard(int card) {
        cards.remove(card);
    }

    void isRemovedCard(Card card) {
        boolean removed= cards.remove(card);
        if (!removed) {
            throw new Phase10Exception("Attempt to remove card that does not exist in the hand");
        }
    }

    public int getNumberOfCards() {
        return cards.size();
    }

    /** Gets a card at the specified index from the player's hand */
    public Card getCard(int cardIndex) {
        if (owner != game.getCurrentPlayer())
            throw new Phase10Exception(
                "Cannot get cards from player who's turn it currently isn't: player " + owner);
        Card out= null;
        try {
            out= cards.get(cardIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid hand index: " + cardIndex);
        }
        return out;
    }

    /** Gets a card at the specified index from the player's hand */

    public Card getAnyCard(int cardIndex) {
        Card out= null;
        try {
            out= cards.get(cardIndex);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid hand index: " + cardIndex);
        }
        return out;
    }

    /** Sorts the hand by color, then value */
    public void sortByColor() {
        CardColorComparator colorComp= new CardColorComparator();
        Collections.sort(cards, colorComp);
    }

    /** Sorts the hand by value, then color */
    public void sortByValue() {
        CardValueComparator valueComp= new CardValueComparator();
        Collections.sort(cards, valueComp);
    }

    /** Returns a string representation of the hand */
    @Override
    public String toString() {
        StringBuilder out= new StringBuilder("Length: " + cards.size() + " Hand: ");
        for (Card e : cards) {
            out.append(e + ", ");
        }
        return out.toString();
    }
}
