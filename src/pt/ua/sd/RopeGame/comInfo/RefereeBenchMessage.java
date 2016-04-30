package pt.ua.sd.RopeGame.comInfo;

/**
 * Referee Bench message
 *
 * Specifies messages for referee and bench communication
 */
public class RefereeBenchMessage extends Message{
    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 200416L;

    /* Messages types */

    /**
     * call trial
     */
    public  final static int CALLTR=1;
    /**
     * start trial
     */
    public  final static int STARTTR=2;
    /**
     * declare match winner
     */
    public final static int DECLAREMATCHWIN=3;

    /**
     * terminate execution
     */
    public final static int TERMINATE=4;

    /**
     * call trial answer
     */
    public  final static int CALLTR_ANS=5;
    /**
     * start trial answer
     */
    public  final static int STARTTR_ANS=6;
    /**
     * declare match winner answer
     */
    public final static int DECLAREMATCHWIN_ANS=7;

    /**
     * private fields
     */
    private int games2=-1;
    private int games1=-1;
    private int winner=-1;

    /**
     * Message init
     * @param type message type
     */
    public RefereeBenchMessage(int type){
        super(type);
    }

    /**
     * Message init
     * @param type message type
     * @param games1 number of games won by team 1
     * @param games2 number of games won by team 2
     */
    public RefereeBenchMessage(int type,int games1, int games2){
        super(type);
        this.games1 = games1;
        this.games2 = games2;
    }

    /**
     * Message init
     * @param type message type
     * @param winner winner of the game
     */
    public RefereeBenchMessage(int type,int winner){
        super(type);
        this.winner = winner;
    }

    public int getGames2() {
        return games2;
    }

    public int getGames1() {
        return games1;
    }

    public int getWinner(){return winner;}
}
