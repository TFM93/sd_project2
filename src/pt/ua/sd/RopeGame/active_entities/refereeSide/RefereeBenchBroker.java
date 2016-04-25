package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereeBenchMessage;
import pt.ua.sd.RopeGame.interfaces.IContestantsBenchReferee;

/**
 * Created by ivosilva on 25/04/16.
 */
public class RefereeBenchBroker implements IContestantsBenchReferee {


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
    public RefereeBenchBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    @Override
    public void callTrial() {

    }

    @Override
    public void startTrial() {

    }

    @Override
    public int declareMatchWinner(int games1, int games2) {
        return 0;
    }

    /**
     * Signals Bench server that Referee will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeBenchMessage inMessage;
        RefereeBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }
}
