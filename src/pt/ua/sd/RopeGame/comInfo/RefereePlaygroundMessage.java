package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.structures.TrialStat;

/**
 * Referee Playground message
 *
 * Specifies messages for referee and playground communication
 */
public class RefereePlaygroundMessage extends Message {


    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 200416L;

    /**
     * assert trial decision
     */
    public static final int ATD=1;

    /**
     * terminate message
     */
    public static final int TERMINATE =2;

    /**
     * assert trial decision answer
     */
    public static final int ATD_ANS=3;

    /**
     * private fields
     */
    private int n_players_pushing=-1;
    private int knockDif=-1;
    private TrialStat stat = null;

    /**
     * Message init
     * @param type message type
     * @param n_players_pushing number of players pushing the rope
     * @param knockDif knockout differential
     */
    public RefereePlaygroundMessage(int type, int n_players_pushing, int knockDif){
        super(type);
        this.knockDif=knockDif;
        this.n_players_pushing=n_players_pushing;
    }


    /**
     * Message init
     * @param type message type
     * @param stat trial stats
     */
    public RefereePlaygroundMessage(int type, TrialStat stat){
        super(type);
        this.stat=stat;
    }


    /**
     * Message init
     * @param type message type
     */
    public RefereePlaygroundMessage(int type){
        super(type);
    }

    public int getN_players_pushing() {
        return n_players_pushing;
    }

    public int getKnockDif() {
        return knockDif;
    }

    public TrialStat getStat(){
        return stat;
    }
}
