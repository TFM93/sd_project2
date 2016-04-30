package pt.ua.sd.RopeGame.comInfo;

/**
 * Message spec between coaches and playground
 */
public class CoachPlaygroundMessage extends Message {


    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;

    /**
     * review notes
     */
    public final static int REVIEWNOTES=1;
    /**
     * terminate execution
     */
    public final static int TERMINATE=2;

    /**
     * review notes answer
     */
    public final static int REVIEWNOTES_ANS=3;

    /**
     * private fields
     */
    private int n_players_pushing;
    private int[] selected_contestants;
    private int n_players;
    private int[] chosen_contestants_after_review;

    /**
     * Message init
     * @param type message type
     * @param selected_contestants selected contestants
     * @param n_players number of players
     * @param n_players_pushing number of players pushing the rope
     */
    public CoachPlaygroundMessage(int type, int[] selected_contestants, int n_players, int n_players_pushing){
        super(type);
        this.selected_contestants = selected_contestants;
        this.n_players = n_players;
        this.n_players_pushing = n_players_pushing;
    }


    /**
     * Message init
     * @param type message type
     */
    public CoachPlaygroundMessage(int type){
        super(type);
    }


    /**
     * Message init
     * @param type message type
     * @param chosen_contestants_after_review new chosen contestants
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
