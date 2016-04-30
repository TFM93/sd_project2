package pt.ua.sd.RopeGame.comInfo;


/**
 * Message spec between coaches and repo
 */
public class CoachRepoMessage extends Message{
    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * Log changes in coach
     */
    public static final int COACHLOG = 1;

    /**
     * terminate execution
     */
    public static final int TERMINATE =2;


    /**
     * Coach's team id
     */
    private int arg1 = -1;

    /**
     * Coach's state
     */
    private int arg2 = -1;


    /**
     * Messages Instantiation.
     *
     * @param type message type
     */
    public CoachRepoMessage(int type) {
        super(type);
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg1 coach's team id
     * @param arg2 coach's state
     */
    public CoachRepoMessage(int type, int arg1, int arg2) {
        super(type);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Get coach's team id
     * @return coach's team id
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * Get coach's state
     * @return customer state, number of goods or customer id
     */
    public int getArg2() {
        return arg2;
    }
}
