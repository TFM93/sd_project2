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
    public final static int FOLLOW_COACH_ADVICE=1;


    /**
     * get ready, enviado pelo contestant
     */
    public final static int GETREADY=2;


    /**
     * terminate, enviado pelo contestant
     */
    public final static int TERMINATE=3;

    /**
     * follow coach advice, enviado pelo contestant
     */
    public final static int FOLLOW_COACH_ADVICE_ANS=4;


    /**
     * get ready, enviado pelo contestant
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


    public ContestantBenchMessage(int type, boolean[] advice_followed){
        super(type);
        this.advice_followed = advice_followed;
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

    public boolean[] getAdvice_followed() {
        return advice_followed;
    }
}
