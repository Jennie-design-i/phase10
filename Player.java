package com.mycompany.phase10;

import java.util.ArrayList;

public class Player {

    private String name;
    private int score;
    private Hand hand;
    private int phase;
    private boolean hasPlacedDownPhase;
    private boolean skipNextTurn;
    private ArrayList<PhaseGroup> phaseGroups;

    private boolean hasDrawnCard;

    private boolean drewFromDiscard;

    protected Phase10 game;

    /** Constructor: Instantiate Player with no name */
    public Player(Phase10 g) {
        this(g, "[Player]");
    }

    /** Constructor: Instantiate Player with given name */
    public Player(Phase10 g, String n) {
        name= n;
        score= 0;
        phase= 1;
        game= g;
        hand= new Hand(g, this);
        phaseGroups= new ArrayList<PhaseGroup>();
        skipNextTurn= false;
        hasPlacedDownPhase= false;
        hasDrawnCard= false;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name= n;
    }

    public int getScore() {
        return score;
    }

    void addToScore(int points) {
        score+= points;
    }

    public int getPhase() {
        return phase;
    }

    void incrementPhase() {
        phase++ ;
    }

    public Hand getHand() {
        return hand;
    }

    /** Return true if valid to add, otherwise false. Add the phase group(s) to this player's phase
     * groups. */
    public boolean addPhaseGroups(PhaseGroup... pg) {
//        for (int i = 0; i < pg.length; i++)
        // game.getDebugLog().addEntry(
        // new DebugLogEntry(game.getRound().getTurnNumber(), this,
        // "Attempt to lay down phase group " + pg[i]));
        if (hasPlacedDownPhase) { return false; }

        if (!(pg.length == Configuration.getNumberRequired(phase))) {
//            game.getDebugLog().addEntry(
//                new DebugLogEntry(game.getRound().getTurnNumber(), this,
//                        "Attempted to lay down an incorrect number of phase groups: "
//                                + pg.length));
            return false;
        }

        boolean completedPhase= false;

        pg[0].sortByValue();

        if (Configuration.getNumberRequired(phase) == 1) {

            PhaseGroup phaseGroup= pg[0];
            int type= Configuration.getTypeRequired(phase, 0);
            int length= Configuration.getLengthRequired(phase, 0);

            if (PhaseGroup.validate(phaseGroup, type, length)) {
                phaseGroup.setPlacedDown(type);
                phaseGroups.add(phaseGroup);
                completedPhase= true;
            }
        } else {
            pg[1].sortByValue();
            PhaseGroup phaseGroup1= pg[0];
            PhaseGroup phaseGroup2= pg[1];

            int typeA= Configuration.getTypeRequired(phase, 0);
            int typeB= Configuration.getTypeRequired(phase, 1);
            int lengthA= Configuration.getLengthRequired(phase, 0);
            int lengthB= Configuration.getLengthRequired(phase, 1);

            if (PhaseGroup.validate(phaseGroup1, typeA, lengthA) &&
                PhaseGroup.validate(phaseGroup2, typeB, lengthB)) {
                phaseGroup1.setPlacedDown(typeA);
                phaseGroups.add(phaseGroup1);
                phaseGroup2.setPlacedDown(typeB);
                phaseGroups.add(phaseGroup2);
                completedPhase= true;
            } else if (PhaseGroup.validate(phaseGroup1, typeB, lengthB) &&
                PhaseGroup.validate(phaseGroup2, typeA, lengthA)) {
                    phaseGroup1.setPlacedDown(typeB);
                    phaseGroups.add(phaseGroup1);
                    phaseGroup2.setPlacedDown(typeA);
                    phaseGroups.add(phaseGroup2);
                    completedPhase= true;
                }
        }

        if (completedPhase) {
            setPlacedDownPhase(true);

//            game.getDebugLog().addEntry(
//                new DebugLogEntry(game.getRound().getTurnNumber(), this,
//                        "Laid down phase"));

            if (getHand().getNumberOfCards() == 0) {
                setPlacedDownPhase(false);
                game.nextRound();
                
            }
            return true;
        } else {
            return false;
        }
    }

    public int getNumberOfPhaseGroups() {
        return phaseGroups.size();
    }

    public PhaseGroup getPhaseGroup(int index) {
        return phaseGroups.get(index);
    }

    void setSkip(boolean state) {
        skipNextTurn= state;
    }

    public boolean getSkip() {
        return skipNextTurn;
    }

    public boolean hasPlacedDownPhase() {
        return hasPlacedDownPhase;
    }

    void setPlacedDownPhase(boolean state) {
        hasPlacedDownPhase= state;
    }

    void removePhaseGroup(int pg) {
        phaseGroups.remove(pg);
    }

    void setHasDrawnCard(boolean state) {
        hasDrawnCard= state;
    }

    public boolean getHasDrawnCard() {
        return hasDrawnCard;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean drewFromDiscard() {
        return drewFromDiscard;
    }

    void setDrewFromDiscard(boolean drewFromDiscard) {
        this.drewFromDiscard= drewFromDiscard;
    }

}
