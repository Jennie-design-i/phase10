package com.mycompany.phase10;

import java.awt.Color;

public class Card {

    private Color color;
    private int value;

    /** Constructor: Instantiate card with color and value.
     * @param c
     * @param val*/
    public Card(Color c, int val) {
        color= c;
        value= val;
    }

    /** Constructor: Instantiate card with value. */
    public Card(int v) {
        this(Color.BLACK, v);
    }

    /** @return the color. */
    public final Color getColor() {
        return color;
    }

    /** @return the value. */
    public final int getValue() {
        return value;
    }

    /** Compare the color and value of two cards 
     * @return true if equal otherwise false.
     * @param other*/
    public boolean equals(Card other) {
        return color.equals(other.getColor()) && value == other.getValue();
    }

    /** Give each Value with corresponding points
     * @return points*/
    public final int getPointValue() {
        if (value >= 1 && value <= 9)
            return Configuration.LOW_POINTS_VALUE;
        if (value >= 10 && value <= 12)
            return Configuration.HIGH_POINTS_VALUE;
        if (value == Configuration.SKIP_VALUE)
            return Configuration.SKIP_POINTS_VALUE;
        if (value == Configuration.WILD_VALUE)
            return Configuration.WILD_POINTS_VALUE;

        return 0;
    }

    /** Return a string with the color with the corresponding value. */
    @Override
    public String toString() {
        String color= "";
        for (int i= 0; i < Configuration.COLORS.length; i++ ) {
            if (Configuration.COLORS[i].equals(getColor())) {
                color= Configuration.COLOR_NAMES[i];
            }

        }

        return color + " " + getValue();
    }
}
