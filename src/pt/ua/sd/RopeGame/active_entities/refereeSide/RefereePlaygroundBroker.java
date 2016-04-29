package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereePlaygroundMessage;
import pt.ua.sd.RopeGame.comInfo.RefereeRefSiteMessage;
import pt.ua.sd.RopeGame.interfaces.IPlaygroundReferee;
import pt.ua.sd.RopeGame.structures.TrialStat;

/**
 * Created by ivosilva on 25/04/16.
 */
public class RefereePlaygroundBroker implements IPlaygroundReferee {


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
     * Playground Broker for referee
     * @param hostName host name
     * @param portNum port number
     */
    public RefereePlaygroundBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    @Override
    public TrialStat assertTrialDecision(int n_players_pushing, int knockDif) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereePlaygroundMessage inMessage;
        RefereePlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereePlaygroundMessage(RefereePlaygroundMessage.ATD, n_players_pushing, knockDif);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (RefereePlaygroundMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != RefereePlaygroundMessage.ATD_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        TrialStat stat = inMessage.getStat();
        return stat;
    }


    /**
     * Signals Bench server that Referee will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereePlaygroundMessage inMessage;
        RefereePlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereePlaygroundMessage(RefereePlaygroundMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }
}
