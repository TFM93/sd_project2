package pt.ua.sd.RopeGame.comInfo;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class CoachBenchMessage extends Message{


    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 180415L;


    /**
     * call contestants, enviado pelo coach
     */
    public static final int CALLCONTESTANTS = 1;

    /**
     * inform referee, enviado pelo coach
     */
    public static final int INFORMREF = 2;
    private int team_id;
    private int n_players;
    private int[] selected_contestants=null;

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
