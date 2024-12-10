package uo.cpm.p6.model;

import uo.cpm.p6.rules.Game;
import uo.cpm.p6.rules.Game.Level;

public class Dice {

    public static int launch(Level level) {
	switch (level) {
	case EASY:
	    return ((int) (Math.random() * Game.MAX_SHOTS_EASY) + 1);
	case INTERMIDIATE:
	    return ((int) (Math.random() * Game.MAX_SHOTS) + 1);
	case HARD:
	    return ((int) (Math.random() * Game.MAX_SHOTS_HARD) + 1);
	}

	// Default
	return ((int) (Math.random() * Game.MAX_SHOTS) + 1);
    }
}
