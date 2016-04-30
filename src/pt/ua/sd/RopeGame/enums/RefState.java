package pt.ua.sd.RopeGame.enums;

/**
 *     This class represents the status of the referee in a certain moment.<br>
 *         He can be announcing the start of the match, the start of the game, be waiting for the teams to be ready, waiting for the end of the
 *         trial or announcing the end of the game/match
 *
 * @author Ivo Silva (<a href="mailto:ivosilva@ua.pt">ivosilva@ua.pt</a>)
 * @author Tiago Magalhaes (<a href="mailto:tiagoferreiramagalhaes@ua.pt">tiagoferreiramagalhaes@ua.pt</a>)
 */
public enum RefState {
    START_OF_THE_MATCH, START_OF_A_GAME, TEAMS_READY, WAIT_FOR_TRIAL_CONCLUSION, END_OF_A_GAME, END_OF_A_MATCH
}
