package com.mycompany.phase10;
public class WildCard extends Card {

    private int hiddenValue;
    private boolean changeable;

    public WildCard() {
        super(Configuration.WILD_VALUE);
        hiddenValue= -1;
        changeable= true;
    }

    /** Return substitute value */
    public int getHiddenValue() {
        return hiddenValue;
    }

    /** Change the substitute value */
    public void setHiddenValue(int hiddenValue) {
        this.hiddenValue= hiddenValue;
    }

    /** Return if the wildcard is changeable */
    public boolean isChangeable() {
        return changeable;
    }

    /** Change changeable */
    public void setChangeable(boolean changeable) {
        this.changeable= changeable;
    }

    @Override
    public String toString() {
        return "Wild (" + hiddenValue + ")";
    }
}
