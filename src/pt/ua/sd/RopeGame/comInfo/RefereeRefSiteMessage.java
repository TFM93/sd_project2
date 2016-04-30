package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.structures.GameStat;

/**
 * Referee RefSite message
 *
 * Specifies messages for referee and referee site communication
 */
public class RefereeRefSiteMessage extends Message {




    /**
     * Serialization key
     */
    public static final long serialVersionUID = 200416L;

    /**
     * announce new game
     */
    public static final int ANG = 1;

    /**
     * declare game winner
     */
    public static final int DGW = 2;

    /**
     * get number of games played
     */
    public static final int GNGP = 3;

    /**
     * terminate execution
     */
    public static final int TERMINATE = 4;


    /**
     * decide game winner answer
     */
    public static final int DGW_ANS = 5;


    /**
     * get number of games played answer
     */
    public static final int GNGP_ANS = 6;

    /**
     * get number of games played answer
     */
    public static final int ANG_ANS = 7;




    /**
     * private fields
     */
    private int score_T1=-1;
    private int score_T2 =-1;
    private int knock_out=Integer.MAX_VALUE;
    private int n_games=-1;
    private GameStat stat=null;
    private int n_games_played=-1;

    /**
     * Message init
     * @param type message type
     */
    public RefereeRefSiteMessage(int type){
        super(type);
    }

    /**
     * Message init
     * @param type message type
     * @param stat game stats
     */
    public RefereeRefSiteMessage(int type, GameStat stat){
        super(type);
        this.stat = stat;
    }

    /**
     * Message init
     * @param type message type
     * @param n_games_played number of games played
     */
    public RefereeRefSiteMessage(int type, int n_games_played){
        super(type);
        this.n_games_played = n_games_played;
    }

    /**
     * Message init
     * @param type message type
     * @param score_T1 score of team 1
     * @param score_T2 score of team 2
     * @param knock_out knockout differential
     * @param n_games number of games
     */
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
