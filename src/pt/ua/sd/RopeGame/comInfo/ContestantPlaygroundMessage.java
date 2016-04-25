package pt.ua.sd.RopeGame.comInfo;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class ContestantPlaygroundMessage extends Message {

    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;

    /**
     * pull the rope, enviado pelo contestant
     */
    public static final int PULLROPE=1;

    /**
     * am done, enviado pelo contestant
     */
    public static final int AMDONE=2;
    /**
     * seat down, enviado pelo contestant
     */
    public static final int SEATDOWN=3;
    /**
     * terminate message
     */
    public static final int TERMINATE =4;
    /**
     * pull the rope, enviado pelo contestant
     */
    public static final int PULLROPE_ANS=5;

    /**
     * am done, enviado pelo contestant
     */
    public static final int AMDONE_ANS=6;
    /**
     * seat down, enviado pelo contestant
     */
    public static final int SEATDOWN_ANS=7;
    /**
     * terminate message
     */



    /**
     * private fields
     */
    private int team_id=-1;
    private int strength=-1;
    private int contestant_id=-1;
    private int n_players_pushing=-1;
    private int n_players=-1;

    /**
     * inicialização da mensagem
     * @param type tipo de mensagem
     * @param team_id  id da equipa
     * @param strength força do contestant
     * @param contestant_id   id do contestant
     * @param n_players_pushing numero de contestants a puxar a corda
     * @param n_players numero de players
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
     * inicialização da mensagem
     * @param type tipo da mensagem
     * @param n_players_pushing numero de contestants a puxar a corda
     */
    public ContestantPlaygroundMessage(int type, int n_players_pushing){
        super(type);
        this.n_players_pushing=n_players_pushing;
    }


    /**
     * inicialização da mensagem
     * @param type tipo da mensagem
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
