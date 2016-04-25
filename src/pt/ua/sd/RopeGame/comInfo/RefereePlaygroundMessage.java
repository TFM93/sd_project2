package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.structures.TrialStat;

/**
 * Created by tiagomagalhaes on 21/04/16.
 */
public class RefereePlaygroundMessage extends Message {


    /**
     * Serialization key
     * @serialField serialVersionUID
     */
    private static final long serialVersionUID = 200416L;

    /**
     * assert trial decision, enviado pelo refereee
     */
    public static final int ATD=1;

    /**
     * terminate message
     */
    public static final int TERMINATE =2;

    /**
     * assert trial decision, enviado pelo refereee
     */
    public static final int ATD_ANS=3;

    /**
     * private fields
     */
    private int n_players_pushing=-1;
    private int knockDif=-1;
    private TrialStat stat = null;

    /**
     * inicialização da mensagem
     * @param type tipo de mensagem
     * @param n_players_pushing numero de contestants a puxar a corda
     * @param knockDif limite maximo do deslocamento da corda para decidir um knockout
     */
    public RefereePlaygroundMessage(int type, int n_players_pushing, int knockDif){
        super(type);
        this.knockDif=knockDif;
        this.n_players_pushing=n_players_pushing;
    }


    /**
     * inicialização da mensagem
     * @param type tipo de mensagem
     * @param stat
     */
    public RefereePlaygroundMessage(int type, TrialStat stat){
        super(type);
        this.stat=stat;
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
