package pt.ua.sd.RopeGame.comInfo;

/**
 * Contestant Bench message
 *
 * Specifies messages for contestant and bench communication
 */
public class ContestantBenchMessage extends Message{


    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * follow coach advice
     */
    public final static int FOLLOW_COACH_ADVICE=1;


    /**
     * get ready
     */
    public final static int GETREADY=2;


    /**
     * terminate execution
     */
    public final static int TERMINATE=3;

    /**
     * follow coach advice answer
     */
    public final static int FOLLOW_COACH_ADVICE_ANS=4;


    /**
     * get ready answer
     */
    public final static int GETREADY_ANS=5;

    /**
     * private fields
     */
    private int n_players=-1;
    private int team_id=-1;
    private int strength=-1;
    private int contestant_id=-1;
    private int n_players_pushing=-1;
    private boolean[] advice_followed;

    /**
     * Message init
     * @param type message type
     */
    public ContestantBenchMessage(int type){
        super(type);
    }


    /**
     * Message init
     * @param type message type
     * @param n_players_pushing number of players pushing the rope
     */
    public ContestantBenchMessage(int type, int n_players_pushing){
        super(type);
        this.n_players_pushing = n_players_pushing;
    }

    /**
     * Message init
     * @param type message type
     * @param advice_followed followed advice
     */
    public ContestantBenchMessage(int type, boolean[] advice_followed){
        super(type);
        this.advice_followed = advice_followed;
    }

    /**
     * Message init
     * @param type message type
     * @param contestant_id contestant's id
     * @param strength contestant's strength
     * @param team_id team id
     * @param n_players number of players
     * @param n_players_pushing number of players pushing the rope
     */
    public ContestantBenchMessage(int type, int contestant_id, int strength, int team_id, int n_players,int n_players_pushing){
        super(type);
        this.n_players_pushing = n_players_pushing;
        this.contestant_id = contestant_id;
        this.strength = strength;
        this.team_id = team_id;
        this.n_players = n_players;
    }

    public int getN_players_pushing() {
        return n_players_pushing;
    }

    public int getContestant_id() {
        return contestant_id;
    }

    public int getStrength() {
        return strength;
    }

    public int getTeam_id() {
        return team_id;
    }

    public int getN_players() {
        return n_players;
    }

    public boolean[] getAdvice_followed() {
        return advice_followed;
    }
}
