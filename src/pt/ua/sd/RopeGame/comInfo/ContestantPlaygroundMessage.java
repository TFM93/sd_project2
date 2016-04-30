package pt.ua.sd.RopeGame.comInfo;

/**
 * Contestant Playground message
 *
 * Specifies messages for contestant and playground communication
 */
public class ContestantPlaygroundMessage extends Message {

    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;

    /**
     * pull the rope
     */
    public static final int PULLROPE=1;

    /**
     * am done
     */
    public static final int AMDONE=2;
    /**
     * seat down
     */
    public static final int SEATDOWN=3;
    /**
     * terminate execution
     */
    public static final int TERMINATE =4;
    /**
     * pull the rope answer
     */
    public static final int PULLROPE_ANS=5;

    /**
     * am done answer
     */
    public static final int AMDONE_ANS=6;
    /**
     * seat down answer
     */
    public static final int SEATDOWN_ANS=7;


    /**
     * private fields
     */
    private int team_id=-1;
    private int strength=-1;
    private int contestant_id=-1;
    private int n_players_pushing=-1;
    private int n_players=-1;

    /**
     * Message init
     * @param type message type
     * @param team_id  team id
     * @param strength contestant strength
     * @param contestant_id contestant id
     * @param n_players_pushing number of players pushing the rope
     * @param n_players number of players
     */
    public ContestantPlaygroundMessage(int type,int team_id, int strength, int contestant_id, int n_players_pushing, int n_players){
        super(type);
        this.team_id = team_id;
        this.strength = strength;
        this.contestant_id = contestant_id;
        this.n_players_pushing = n_players_pushing;
        this.n_players = n_players;
    }

    /**
     * Message init
     * @param type message type
     * @param n_players_pushing number of players pushing the rope
     */
    public ContestantPlaygroundMessage(int type, int n_players_pushing){
        super(type);
        this.n_players_pushing=n_players_pushing;
    }


    /**
     * Message init
     * @param type message type
     */
    public ContestantPlaygroundMessage(int type){
        super(type);
    }

    public int getN_players() {
        return n_players;
    }

    public int getContestant_id() {
        return contestant_id;
    }

    public int getN_players_pushing() {
        return n_players_pushing;
    }

    public int getStrength() {
        return strength;
    }

    public int getTeam_id() {
        return team_id;
    }
}
