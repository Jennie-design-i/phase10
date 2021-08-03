package com.mycompany.phase10;

import java.awt.Color;

public class Configuration {
    public static final int TIME_TO_SHUFFLE= 5;
    public static final int NUM_WILDS= 8;
    public static final int NUM_SKIPS= 4;
    public static final int NUM_CARDS_TO_DEAL= 10;

    public static final int WILD_VALUE= 13;
    public static final int SKIP_VALUE= 14;

    public static final int LOW_POINTS_VALUE= 5;
    public static final int HIGH_POINTS_VALUE= 10;
    public static final int SKIP_POINTS_VALUE= 15;
    public static final int WILD_POINTS_VALUE= 25;

    public static final Color[] COLORS= { Color.RED, Color.BLUE, Color.GREEN,
            Color.YELLOW };
    // For Display
    public static final String[] COLOR_NAMES= { "Red", "Blue", "Green",
            "Yellow" };

    // All Below are for phases
    public static final int SET_PHASE= 0;
    public static final int RUN_PHASE= 1;
    public static final int COLOR_PHASE= 2;

    private static int[][] typePhasesRequired= {
            { SET_PHASE, SET_PHASE },
            { SET_PHASE, RUN_PHASE },
            { SET_PHASE, RUN_PHASE },
            { RUN_PHASE },
            { RUN_PHASE },
            { RUN_PHASE },
            { SET_PHASE, SET_PHASE },
            { COLOR_PHASE },
            { SET_PHASE, SET_PHASE },
            { SET_PHASE, SET_PHASE }
    };

    private static int[][] minLengthOfPhasesRequired= {
            { 3, 3 },
            { 3, 4 },
            { 4, 4 },
            { 7 },
            { 8 },
            { 9 },
            { 4, 4 },
            { 7 },
            { 5, 2 },
            { 5, 3 }
    };

    /** Return the number of phase group required for a given phase from 1-10 */
    public static int getNumberRequired(int phase) {
        return typePhasesRequired[phase - 1].length;
    }

    /** Return type of phase required at a given phase from 1-10 */
    public static int getTypeRequired(int phase, int groupIndex) {
        return typePhasesRequired[phase - 1][groupIndex];
    }

    /** Return the length required for each phase. */
    public static int getLengthRequired(int phase, int groupIndex) {
        return minLengthOfPhasesRequired[phase - 1][groupIndex];
    }

}
