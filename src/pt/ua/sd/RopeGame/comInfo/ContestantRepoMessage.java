package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.active_entities.contestantSide.Contestant;
import pt.ua.sd.RopeGame.enums.ContestantState;

/**
 * Contestant Repository message
 *
 * Specifies messages for contestant and repository communication
 */
public class ContestantRepoMessage extends Message{

    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * Log changes in contestant (operation requested by Contestant)
     */
    public static final int CONTESTANTLOG = 1;


    /**
     * Update rope center (operation requested by Contestant)
     */
    public static final int UPDATEROPECENTER = 2;

    /**
     * terminate execution
     */
    public static final int TERMINATE =3;

    /**
     * Contestant's id or rope center value
     */
    private int arg1 = -1;

    /**
     * Contestant's team id
     */
    private int arg2 = -1;

    /**
     * Contestant's strength
     */
    private int arg3 = -1;

    /**
     * Contestant's state
     */
    private int arg4 = -1;


    /**
     * Messages Instantiation.
     *
     * @param type message type
     */
    public ContestantRepoMessage(int type) {
        super(type);
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg1 rope center value
     */
    public ContestantRepoMessage(int type, int arg1) {
        super(type);
        this.arg1 = arg1;
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg1 coach's id
     * @param arg2 coach's team id
     * @param arg3 coach's strength
     * @param arg4 coach's state
     */
    public ContestantRepoMessage(int type, int arg1, int arg2, int arg3, int arg4) {
        super(type);
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
    }

    /**
     * Get contestant's id or rope center value
     * @return contestant's id or rope center calue
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * Get contestant's team id
     * @return contestant's team id
     */
    public int getArg2() {
        return arg2;
    }

    /**
     * Get contestant's stregnth
     * @return contestant's strength
     */
    public int getArg3() {
        return arg3;
    }

    /**
     * Get contestant's state
     * @return contestant's state
     */
    public int getArg4() {
        return arg4;
    }

}
