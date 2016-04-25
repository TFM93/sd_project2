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
    public final static int REVIEWNOTES=1;
    /**
     * terminate, enviado pelo cach
     */
    public final static int TERMINATE=2;
    private int n_players_pushing;

    /**
     * review notes answer , enviado pelo coach
     */
    public final static int REVIEWNOTES_ANS=3;

    /**
     * private fields
     */
    private int[] selected_contestants;
    private int n_players;
    private int[] chosen_contestants_after_review;

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


    /**
     * Inicialização da mensagem
     * @param type tipo da mensagem
     * @param chosen_contestants_after_review id dos contestants selecionados
     */
    public CoachPlaygroundMessage(int type, int[] chosen_contestants_after_review){
        super(type);
        this.chosen_contestants_after_review = chosen_contestants_after_review;
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

    public int[] getChosen_contestants_after_review(){return chosen_contestants_after_review;}
}
