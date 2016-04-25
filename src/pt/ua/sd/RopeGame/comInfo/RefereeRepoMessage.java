package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.enums.RefState;
import pt.ua.sd.RopeGame.enums.WonType;

/**
 * Created by ivosilva on 23/04/16.
 */
public class RefereeRepoMessage extends Message {

    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * Log changes in contestant (operation requested by Referee)
     */
    public static final int REFEREELOG = 1;


    /**
     * Update game number (operation requested by Referee)
     */
    public static final int UPDATEGAMENR = 2;

    /**
     * Log changes in contestant (operation requested by Referee)
     */
    public static final int UPDATEROPECENTER = 3;

    /**
     * Print match results (operation requested by Referee)
     */
    public static final int PRINTMATCHRESULT = 4;

    /**
     * Print match results (operation requested by Referee)
     */
    public static final int ADDHEADER = 5;


    /**
     * Print match results (operation requested by Referee)
     */
    public static final int SETRESULT = 6;


    /**
     * terminate message
     */
    public static final int TERMINATE = 7;

    /**
     * Game's trial number or rope center value
     */
    private int arg1 = -1;


    /**
     * Referee's state
     */
    private RefState arg2 = null;

    /**
     * Add header flag
     */
    private boolean arg3 = false;

    /**
     * score team 1
     */
    private int score1 = -1;

    /**
     * score team 2
     */
    private int score2 = -1;

    /**
     * id of the team winner
     */
    private int winner = -1;



    /**
     * id of the team winner
     */
    private WonType wonType = null;



    /**
     * id of the team winner
     */
    private int n_trials = -1;



    /**
     * id of the team winner
     */
    private int team_id = -1;


    /**
     * Messages Instantiation.
     *
     * @param type message type
     */
    public RefereeRepoMessage(int type) {
        super(type);
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg1 rope center value
     */
    public RefereeRepoMessage(int type, int arg1) {
        super(type);
        this.arg1 = arg1;
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg3 add header flag
     */
    public RefereeRepoMessage(int type, boolean arg3) {
        super(type);
        this.arg3 = arg3;
    }

    public RefereeRepoMessage(int type,RefState state,int trialNr ){
        super(type);
        this.arg2 = state;
        this.arg1 = trialNr;
    }
    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg1 referee's trial number
     * @param arg2 referee's state
     */
    public RefereeRepoMessage(int type, int arg1, RefState arg2) {
        super(type);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param team_id referee's trial number
     * @param wonType referee's trial number
     * @param n_trials referee's state
     */
    public RefereeRepoMessage(int type, int team_id, WonType wonType, int n_trials) {
        super(type);
        this.wonType = wonType;
        this.n_trials = n_trials;
        this.team_id = team_id;
    }


    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param winner referee's trial number
     * @param score1 referee's trial number
     * @param score2 referee's state
     */
    public RefereeRepoMessage(int type, int winner, int score1, int score2) {
        super(type);
        this.winner = winner;
        this.score1 = score1;
        this.score2 = score2;
    }

    /**
     * Get referee's trial number or rope center value
     * @return referee's trial number or rope center value
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * Get referee's state
     * @return referee's state
     */
    public RefState getArg2() {
        return arg2;
    }

    /**
     * Get referee's add header flag
     * @return referee's add header flag
     */
    public boolean getArg3() {
        return arg3;
    }

    public int getWinner() {
        return winner;
    }

    public int getScore2() {
        return score2;
    }

    public int getScore1() {
        return score1;
    }

    public int getN_trials() { return n_trials; }

    public WonType getWonType() { return wonType; }

    public int getTeam_id() { return team_id; }
}
