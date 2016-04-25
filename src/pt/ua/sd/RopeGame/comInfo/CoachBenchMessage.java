package pt.ua.sd.RopeGame.comInfo;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class CoachBenchMessage extends Message{


    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * call contestants, enviado pelo coach
     */
    public static final int CALLCONTESTANTS = 1;

    /**
     * inform referee, enviado pelo coach
     */
    public static final int INFORMREF = 2;

    /**
     * inform referee, enviado pelo coach
     */
    public static final int TERMINATE = 3;

    /**
     * call contestants, enviado pelo coach
     */
    public static final int CALLCONTESTANTS_ANS = 4;

    /**
     * inform referee, enviado pelo coach
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
     * Inicializacao da mensagem
     * @param type tipo de mensagem
     */
    public CoachBenchMessage(int type){
        super(type);
    }

    /**
     * Inicializacao da mensagem
     * @param type
     * @param team_id
     * @param selected_contestants
     * @param n_players
     */
    public  CoachBenchMessage(int type, int team_id, int[] selected_contestants, int n_players){
        super(type);
        this.selected_contestants = selected_contestants;
        this.n_players = n_players;
        this.team_id = team_id;
    }

    /**
     * Inicializacao da mensagem
     * @param type
     * @param match_not_ended
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
