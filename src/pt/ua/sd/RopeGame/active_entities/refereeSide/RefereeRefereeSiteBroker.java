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
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeRefSiteMessage inMessage;
        RefereeRefSiteMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.ANG);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (RefereeRefSiteMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != RefereeRefSiteMessage.ANG_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();
    }

    @Override
    public GameStat declareGameWinner(int score_T1, int score_T2, int knock_out, int n_games) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeRefSiteMessage inMessage;
        RefereeRefSiteMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.DGW, score_T1, score_T2, knock_out, n_games);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (RefereeRefSiteMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != RefereeRefSiteMessage.DGW_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        GameStat stats = inMessage.getStat();
        return stats;
    }

    @Override
    public int getN_games_played() {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeRefSiteMessage inMessage;
        RefereeRefSiteMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.GNGP);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (RefereeRefSiteMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != RefereeRefSiteMessage.GNGP_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        int n_games_played = inMessage.getN_games_played();
        return n_games_played;
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
