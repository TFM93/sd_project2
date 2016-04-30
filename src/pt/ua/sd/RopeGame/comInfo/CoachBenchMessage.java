package pt.ua.sd.RopeGame.comInfo;

/**
 * Message spec between coaches and bench
 */
public class CoachBenchMessage extends Message{


    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * call contestants
     */
    public static final int CALLCONTESTANTS = 1;

    /**
     * inform referee
     */
    public static final int INFORMREF = 2;

    /**
     * terminate execution
     */
    public static final int TERMINATE = 3;

    /**
     * call contestants answer
     */
    public static final int CALLCONTESTANTS_ANS = 4;

    /**
     * inform referee answer
     */
    public static final int INFORMREF_ANS = 5;

    /**
     * private fields
     */
    private int team_id=-1;
    private int n_players=-1;
    private int[] selected_contestants=null;

    public boolean isMatch_not_ended() {
        return match_not_ended;
    }

    private boolean match_not_ended = true;

    /**
     * Message init
     * @param type message type
     */
    public CoachBenchMessage(int type){
        super(type);
    }

    /**
     * Message init
     * @param type message type
     * @param team_id team id
     * @param selected_contestants selected contestants
     * @param n_players number of players
     */
    public  CoachBenchMessage(int type, int team_id, int[] selected_contestants, int n_players){
        super(type);
        this.selected_contestants = selected_contestants;
        this.n_players = n_players;
        this.team_id = team_id;
    }

    /**
     * Message init
     * @param type message type
     * @param match_not_ended match not ended
     */
    public  CoachBenchMessage(int type, boolean match_not_ended){
        super(type);
        this.match_not_ended = match_not_ended;
    }

    public int getN_players() {
        return n_players;
    }

    public int[] getSelected_contestants() {
        return selected_contestants;
    }

    public int getTeam_id() {
        return team_id;
    }
}
