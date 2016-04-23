package pt.ua.sd.RopeGame.comInfo;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class ContestantBenchMessage extends Message{


    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * follow coach advice, enviado pelo contestant
     */
    private final static int FOLLOW_COACH_ADVICE=1;


    /**
     * get ready, enviado pelo contestant
     */
    private final static int GETREADY=2;


    /**
     * terminate, enviado pelo contestant
     */
    private final static int TERMINATE=3;

    /**
     * private fields
     */
    private int n_players;
    private int team_id;
    private int strength;
    private int contestant_id;
    private int n_players_pushing;

    /**
     * Inicializaç\ao da mensagem
     * @param type tipo da mensagem
     */
    public ContestantBenchMessage(int type){
        super(type);
    }



    public ContestantBenchMessage(int type, int n_players_pushing){
        super(type);
        this.n_players_pushing = n_players_pushing;
    }


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
}
