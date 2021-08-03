package com.mycompany.phase10;

import java.awt.Color;
import java.util.Comparator;

public class CardColorComparator implements Comparator<Card> {
    /** Return the comparator of two cards. If the colors are equal (aka the index are the same),
     * then compare them by value if c1 has index of colors greater than c2, return the difference,
     * same for if c1 has index of colors less than c2.
     * @param c1
     * @param  c2*/
    @Override
    public int compare(Card c1, Card c2) {
        int val1= -1;
        int val2= -1;

        for (int i= 0; i < Configuration.COLORS.length; i++ ) {
            if (c1.getColor().equals(Configuration.COLORS[i])) {
                val1= i;
            }
            if (c2.getColor().equals(Configuration.COLORS[i])) {
                val2= i;
            }
        }
        // Probably for skip or wild so that we can place them aside
        if (c1.getColor().equals(Color.BLACK)) {
            val1= 5;
        }
        if (c2.getColor().equals(Color.BLACK)) {
            val2= 5;
        }
        // compare index of the color
        int cp= val1 - val2;
        if (cp != 0) return cp;
        // when same color, compare value
        else {
            return c1.getValue() - c2.getValue();
        }

    }

}
