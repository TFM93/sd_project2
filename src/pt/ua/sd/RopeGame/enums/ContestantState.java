package pt.ua.sd.RopeGame.enums;

/**
 *     This class represents the status of the contestant in a certain moment<br>
 *         The contestant can be seated at the bench, stand in position to play or pulling the rope. The start state is used to sincronize the first state
 *
 * @author Ivo Silva (<a href="mailto:ivosilva@ua.pt">ivosilva@ua.pt</a>)
 * @author Tiago Magalhaes (<a href="mailto:tiagoferreiramagalhaes@ua.pt">tiagoferreiramagalhaes@ua.pt</a>)
 */
public enum ContestantState {
    SEAT_AT_THE_BENCH, STAND_IN_POSITION, DO_YOUR_BEST, START
}
