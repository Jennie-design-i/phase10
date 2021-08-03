package com.mycompany.phase10;

import java.awt.Color;
import java.util.Comparator;

public class CardValueComparator implements Comparator<Card> {
    /** Return the comparator of two cards by value. Return CardValueComparator if two cards have
     * equal values Return negative int if c1 < v2 value Return positive int if c2 > c1 value
     * Return */
    @Override
    public int compare(Card c1, Card c2) {
        int val1= -1;
        int val2= -1;

        if (c1 instanceof WildCard) {
            WildCard wc= (WildCard) c1;
            if (!wc.isChangeable()) {
                val1= wc.getHiddenValue();
            }
        }
        if (c2 instanceof WildCard) {
            WildCard wc= (WildCard) c2;
            if (!wc.isChangeable()) {
                val2= wc.getHiddenValue();
            }
        }

        if (val1 < 0) {
            val1= c1.getValue();
        }

        if (val2 < 0) {
            val2= c2.getValue();
        }

        int cp= val1 - val2;

        if (cp != 0) {
            return cp;
        } else {
            int colorval1= -1;
            int colorval2= -1;

            for (int i= 0; i < Configuration.COLORS.length; i++ ) {
                if (c1.getColor().equals(Configuration.COLORS[i])) {
                    colorval1= i;
                }
                if (c2.getColor().equals(Configuration.COLORS[i])) {
                    colorval2= i;
                }
            }
            // Probably for skip or wild so that we can place them aside
            if (c1.getColor().equals(Color.BLACK)) {
                colorval1= 5;
            }
            if (c2.getColor().equals(Color.BLACK)) {
                colorval2= 5;
            }

            return colorval1 - colorval2;
        }

    }
}
