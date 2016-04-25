package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.structures.GameStat;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class RefereeRefSiteMessage extends Message {




    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    public static final long serialVersionUID = 200416L;

    /**
     * announce new game, enviado pelo refereee
     */
    public static final int ANG = 1;

    /**
     * declare game winner, enviado pelo refereee
     */
    public static final int DGW = 2;

    /**
     * get number of games played, enviado pelo refereee
     */
    public static final int GNGP = 3;

    /**
     * terminate message, enviado pelo referee
     */
    public static final int TERMINATE = 4;


    /**
     * decide game winner answer
     */
    public static final int DGW_ANS = 5;


    /**
     * get number of games played
     */
    public static final int GNGP_ANS = 6;

    /**
     * get number of games played
     */
    public static final int ANG_ANS = 7;




    /**
     * private fields
     */
    private int score_T1=-1;
    private int score_T2 =-1;
    private int knock_out=-1;
    private int n_games=-1;
    private GameStat stat=null;
    private int n_games_played=-1;

    public RefereeRefSiteMessage(int type){
        super(type);
    }

    public RefereeRefSiteMessage(int type, GameStat stat){
        super(type);
        this.stat = stat;
    }

    public RefereeRefSiteMessage(int type, int n_games_played){
        super(type);
        this.n_games_played = n_games_played;
    }

    public RefereeRefSiteMessage(int type, int score_T1, int score_T2, int knock_out, int n_games){
        super(type);
        this.score_T1=score_T1;
        this.score_T2=score_T2;
        this.knock_out=knock_out;
        this.n_games=n_games;
    }

    public int get_score_t1(){
        return score_T1;
    }

    public int get_score_t2(){
        return score_T2;
    }

    public int get_knock_out(){
        return knock_out;
    }

    public int get_n_games(){
        return n_games;
    }

    public GameStat getStat() {
        return stat;
    }

    public int getN_games_played() {
        return n_games_played;
    }
}
