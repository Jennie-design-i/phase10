package com.mycompany.phase10;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class PhaseGroup {

    private ArrayList<Card> cards;
    private int type;
    private boolean placedDown;

    private Phase10 game;

    public PhaseGroup(Phase10 game) {
        cards= new ArrayList<>();
        placedDown= false;
        type= -1;
        this.game= game;
    }

    /** Helper: Return true if the player can hit the placedDownPhaseGroup or on it's hand
     * ,otherwise return false Remove the card from the player's hand; if player's hand has no
     * cards, we move to next round Sort the PhaseGroup cards by value if it is run_phase */
    private boolean addCard(Card c, boolean beginning) {
        if (!placedDown) {
            if (!beginning) {
                cards.add(c);
            }
        } else {
            Player p= game.getCurrentPlayer();

            if (!p.hasPlacedDownPhase()) { return false; }

            PhaseGroup temp= new PhaseGroup(game);
            for (int i= 0; i < getNumberOfCards(); i++ ) {
                temp.addCard(getCard(i));
            }
//            game.getDebugLog().addEntry(
//                new DebugLogEntry(game.getRound().getTurnNumber(), p,
//                        "Attempting to add card (" + c
//                                + ") to a laid down phase group: " + temp));
            temp.addCard(c, beginning);

            if (PhaseGroup.validate(temp, type, 0)) {
                cards.add(c);

                if (c.getValue() == Configuration.WILD_VALUE) {
                    WildCard wc= (WildCard) c;
                    if (wc.isChangeable() && type != Configuration.RUN_PHASE) {
                        wc.setHiddenValue(Configuration.WILD_VALUE);
                        wc.setChangeable(false);
                    }
                }

                Collections.sort(cards, new CardValueComparator());

                p.getHand().isRemovedCard(c);

//                game.getDebugLog().addEntry(
//                    new DebugLogEntry(game.getRound().getTurnNumber(), p,
//                            "Added card to a laid down phase group: "
//                                    + this));

                if (p.getHand().getNumberOfCards() == 0) {
                    game.nextRound();
                }
                return true;
            }

        }
        return false;
    }

    /** Return true if valid to add to the placed down phase group, or if the phase is not placed
     * down; false if the card does not fit in the placed down phasegroup
     *
     * Special case: - if the phase group has not been marked Placed down, the card will be added to
     * the phase group (without validation), and will not be taken out of the player's hand - when
     * the addCard Method is called, it will validate it before adding it to the phase group, and if
     * it is valid, will remove the card from the player's hand. */
    public boolean addCard(Card c) {
        return addCard(c, false);
    }

    /** Return true if it is valid to add a card to the front of a phase group else false */
    public boolean addCardToBeginning(Card c) {
        return addCard(c, true);
    }

    public Card getCard(int index) {
        return cards.get(index);
    }

    public int getNumberOfCards() {
        return cards.size();
    }

    public int getType() {
        return type;
    }

    void setPlacedDown(int type) {
        placedDown= true;
        this.type= type;

        for (int i= 0; i < cards.size(); i++ ) {
            game.getCurrentPlayer().getHand().isRemovedCard(cards.get(i));
            if(cards.get(i).getValue()==Configuration.WILD_VALUE){
            WildCard wc= (WildCard) cards.get(i);
            if (wc.isChangeable() && !(type == Configuration.RUN_PHASE)) {
                wc.setHiddenValue(Configuration.WILD_VALUE);
                wc.setChangeable(false);
            } else {
                wc.setChangeable(false);
            }
            }
        }
        Collections.sort(cards, new CardValueComparator());
    }

    boolean getPlacedDown() {
        return placedDown;
    }

    /** Return true of it is a valid phase group, false otherwise valid phase group: match the type
     * (set, run, color) and min length */

    public static boolean validate(PhaseGroup pg, int type, int minLength) {
        // check enough cards
        if (pg.getNumberOfCards() < minLength) { return false; }
        // skip cant be in phase group
        if (!checkSkips(pg)) { return false; }

        if (type == Configuration.SET_PHASE) {
            return validateSet(pg);
        } else if (type == Configuration.RUN_PHASE) {
            return validateRun(pg);
        } else if (type == Configuration.COLOR_PHASE) { return validateAllOneColor(pg); }

        return false;

    }

    /** Return false if card in phaseGroup is a skip, else return true */
    private static boolean checkSkips(PhaseGroup pg) {
        for (int i= 0; i < pg.getNumberOfCards(); i++ ) {
            if (pg.getCard(i).getValue() == Configuration.SKIP_VALUE) { return false; }

        }
        return true;
    }

    private static boolean validateRun(PhaseGroup pg) {
        ArrayList<Integer> values= new ArrayList<>(pg.getNumberOfCards());
        int min= pg.getCard(0).getValue();
        int numWilds= 0;

        ArrayList<WildCard> wilds= new ArrayList<>();

        boolean setFirstAsWild= false;

        for (int i= 0; i < pg.getNumberOfCards(); i++ ) {
            int curVal= pg.getCard(i).getValue();
            if (curVal == Configuration.WILD_VALUE) {
                WildCard curWild= (WildCard) pg.getCard(i);

                if (curWild.getHiddenValue() < 0 || curWild.isChangeable()) {
                    numWilds++ ;
                    wilds.add(curWild);
                    if (i == 0) {
                        setFirstAsWild= true;
                    }
                } else {
                    curVal= curWild.getHiddenValue();
                    values.add(curVal);
                }
            } else {
                values.add(curVal);
            }
            if (curVal < min) {
                min= curVal;
            }
        }
        if (min + values.size() + numWilds > Configuration.WILD_VALUE && numWilds > 0) {
            setFirstAsWild= true;
        }

        if (setFirstAsWild) {
            min-- ;
            System.out.println("Setting first as wild: " + min);
            numWilds-- ;
            wilds.get(0).setHiddenValue(min);
            wilds.remove(0);
            if (min <= 0) { return false; }
        }

        int curValue= min;
        while (!values.isEmpty() || numWilds > 0) {
            boolean found= false;
            for (int i= 0; i < values.size(); i++ ) {
                if (values.get(i) == curValue) {
                    values.remove(i);
                    found= true;
                    break;
                }
            }
            if (!found && numWilds > 0) {
                numWilds-- ;
                wilds.get(numWilds).setHiddenValue(curValue);

            } else if (!found && numWilds == 0) { return false; }
            curValue++ ;
        }

        return true;
    }

    private static boolean validateSet(PhaseGroup pg) {
        int valToMatch= -1;

        for (int i= 0; i < pg.getNumberOfCards(); i++ ) {
            int curVal= pg.getCard(i).getValue();

            if (curVal != Configuration.WILD_VALUE) {
                if (valToMatch < 0) {
                    valToMatch= curVal;
                } else if (valToMatch != curVal) { return false; }
            } else {
                WildCard wc= (WildCard) pg.getCard(i);
                wc.setHiddenValue(valToMatch);
            }
        }
        return true;

    }

    private static boolean validateAllOneColor(PhaseGroup pg) {
        Color valToMatch= Color.white;
        for (int i= 0; i < pg.getNumberOfCards(); i++ ) {
            Color curVal= pg.getCard(i).getColor();

            if (pg.getCard(i).getValue() != Configuration.WILD_VALUE) {
                if (valToMatch.equals(Color.white)) {
                    valToMatch= curVal;
                } else if (valToMatch != curVal) { return false; }
            }
        }
        return true;
    }

    public void setType(int type) {
        if (placedDown) {
            throw new Phase10Exception("CANNOT change type: already placed Down");
        } else {
            this.type= type;
        }
    }

    @Override
    public String toString() {
        StringBuilder out= new StringBuilder();
        for (Card e : cards) {
            out.append(e + ", ");
        }

        return out.length() > 3 ? out.substring(0, out.length() - 2) :
            out
                .toString();
    }

    public void sortByValue() {
        Collections.sort(cards, new CardValueComparator());
    }
}
