package pt.ua.sd.RopeGame.comInfo;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class CoachPlaygroundMessage extends Message {


    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;

    /**
     * review notes , enviado pelo coach
     */
    private final static int REVIEWNOTES=1;
    /**
     * terminate, enviado pelo cach
     */
    private final static int TERMINATE=2;
    private int n_players_pushing;

    /**
     * private fields
     */
    private int[] selected_contestants;
    private int n_players;

    /**
     * Inicialização da mensagem
     * @param type tipo da mensagem
     * @param selected_contestants id dos contestants selecionados
     * @param n_players numero de players
     * @param n_players_pushing numero de players a puxar a corda
     */
    public CoachPlaygroundMessage(int type, int[] selected_contestants, int n_players, int n_players_pushing){
        super(type);
        this.selected_contestants = selected_contestants;
        this.n_players = n_players;
        this.n_players_pushing = n_players_pushing;
    }

    public int getN_players() {
        return n_players;
    }

    public int[] getSelected_contestants() {
        return selected_contestants;
    }

    public int getN_players_pushing() {
        return n_players_pushing;
    }
}
