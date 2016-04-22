package pt.ua.sd.RopeGame.comInfo;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class RefereeRefSiteMessage extends Message {




    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 180415L;

    /**
     * announce new game, enviado pelo refereee
     */
    private static final int ANG=1;

    /**
     * declare game winner, enviado pelo refereee
     */
    private static final int DGW=1;

    /**
     * get number of games played, enviado pelo refereee
     */
    private static final int GNGP=1;

    /**
     * terminate message, enviado pelo referee
     */
    private static final int TERMINATE =2;

    /**
     * private fields
     */
    private int score_T1=-1;
    private int score_T2 =-1;
    private int knock_out=-1;
    private int n_games=-1;

    public RefereeRefSiteMessage(int type){
        super(type);
    }

    public RefereeRefSiteMessage(int type, int score_T1, int score_T2, int knock_out, int n_games){
        super(type);
        this.score_T1=score_T1;
        this.score_T2=score_T2;
        this.knock_out=knock_out;
        this.n_games=n_games;
    }
}
