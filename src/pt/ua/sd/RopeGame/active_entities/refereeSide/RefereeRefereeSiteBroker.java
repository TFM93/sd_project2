package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereeRefSiteMessage;
import pt.ua.sd.RopeGame.interfaces.IRefereeSiteReferee;
import pt.ua.sd.RopeGame.structures.GameStat;

/**
 * Created by ivosilva on 25/04/16.
 */
public class RefereeRefereeSiteBroker implements IRefereeSiteReferee {

    /**
     * Machine hostname
     * @serialfield hostName
     */
    private final String hostName;

    /**
     * Machine port number
     * @serialfield portNum
     */
    private final int portNum;

    /**
     * Shop Broker for Craftsman instantiation.
     * @param hostName host name
     * @param portNum port number
     */
    public RefereeRefereeSiteBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    @Override
    public void announceNewGame() {

    }

    @Override
    public GameStat declareGameWinner(int score_T1, int score_T2, int knock_out, int n_games) {
        return null;
    }

    @Override
    public int getN_games_played() {
        return 0;
    }

    /**
     * Signals Referee Site server that Referee will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeRefSiteMessage inMessage;
        RefereeRefSiteMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }
}
