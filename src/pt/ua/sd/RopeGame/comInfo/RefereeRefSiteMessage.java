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
     * assert trial decision, enviado pelo refereee
     */
    private static final int ATD=1;

    /**
     * terminate message, enviado pelo referee
     */
    private static final int TERMINATE =2;

    /**
     * private fields
     */
    private int score_T1=-1;
    private int score_T2 =-1;

    public RefereeRefSiteMessage(int type){
        super(type);
    }

    public RefereeRefSiteMessage(int type, int score_T1, int score_T2, int knock_out, int n_games){
        super(type);

    }
}
