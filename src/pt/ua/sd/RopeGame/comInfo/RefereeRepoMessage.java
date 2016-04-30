package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.enums.RefState;
import pt.ua.sd.RopeGame.enums.WonType;

/**
 * Referee Repo message
 *
 * Specifies messages for referee and reop communication
 */
public class RefereeRepoMessage extends Message {

    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * Log changes in contestant
     */
    public static final int REFEREELOG = 1;


    /**
     * Update game number
     */
    public static final int UPDATEGAMENR = 2;

    /**
     * Log changes in contestant
     */
    public static final int UPDATEROPECENTER = 3;

    /**
     * Print match results
     */
    public static final int PRINTMATCHRESULT = 4;

    /**
     * Print match results
     */
    public static final int ADDHEADER = 5;


    /**
     * Print match results
     */
    public static final int SETRESULT = 6;


    /**
     * terminate execution
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
     * how was the trial winner decided
     */
    private int wonType = -1;



    /**
     * number of trials
     */
    private int n_trials = -1;



    /**
     * team id
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

    /**
     * Messages Instantiation
     * @param type message type
     * @param state referee state
     * @param trialNr trial number
     */
    public RefereeRepoMessage(int type,RefState state,int trialNr ){
        super(type);
        this.arg2 = state;
        this.arg1 = trialNr;
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param team_idOrWinner team id or winner
     * @param wonTypeOrScore1 won type or score of team 1
     * @param n_trialsOrScore2 number of trials or score of team 2
     */
    public RefereeRepoMessage(int type, int team_idOrWinner, int wonTypeOrScore1, int n_trialsOrScore2) {
        super(type);
        if(type==SETRESULT){
            this.wonType = wonTypeOrScore1;
            this.n_trials = n_trialsOrScore2;
            this.team_id = team_idOrWinner;
        }
        else{
            this.winner = team_idOrWinner;
            this.score1 = wonTypeOrScore1;
            this.score2 = n_trialsOrScore2;
    }}


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

    public int getWonType() { return wonType; }

    public int getTeam_id() { return team_id; }
}
