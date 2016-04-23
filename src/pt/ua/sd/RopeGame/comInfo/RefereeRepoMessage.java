package pt.ua.sd.RopeGame.comInfo;

import pt.ua.sd.RopeGame.enums.RefState;

/**
 * Created by ivosilva on 23/04/16.
 */
public class RefereeRepoMessage extends Message {

    /**
     * Serialization key
     * @serialField serialVerisonUID
     */
    private static final long serialVersionUID = 200416L;


    /**
     * Log changes in contestant (operation requested by Referee)
     */
    public static final int REFEREELOG = 1;


    /**
     * Update game number (operation requested by Referee)
     */
    public static final int UPDATEGAMENR = 2;

    /**
     * Log changes in contestant (operation requested by Referee)
     */
    public static final int UPDATEROPECENTER = 3;

    /**
     * Print match results (operation requested by Referee)
     */
    public static final int PRINTMATCHRESULT = 4;

    /**
     * Game's trial number or rope center value
     */
    private int arg1 = -1;

    /**
     * Referee's state
     */
    private RefState arg2 = null;

    /**
     * Add header flag
     */
    private boolean arg3 = false;


    /**
     * Messages Instantiation.
     *
     * @param type message type
     */
    public RefereeRepoMessage(int type) {
        super(type);
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg1 rope center value
     */
    public RefereeRepoMessage(int type, int arg1) {
        super(type);
        this.arg1 = arg1;
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg3 add header flag
     */
    public RefereeRepoMessage(int type, boolean arg3) {
        super(type);
        this.arg3 = arg3;
    }

    /**
     * Messages Instantiation.
     *
     * @param type message type
     * @param arg1 referee's trial number
     * @param arg2 referee's state
     */
    public RefereeRepoMessage(int type, int arg1, RefState arg2) {
        super(type);
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    /**
     * Get referee's trial number or rope center value
     * @return referee's trial number or rope center value
     */
    public int getArg1() {
        return arg1;
    }

    /**
     * Get referee's state
     * @return referee's state
     */
    public RefState getArg2() {
        return arg2;
    }

    /**
     * Get referee's add header flag
     * @return referee's add header flag
     */
    public boolean getArg3() {
        return arg3;
    }

}
