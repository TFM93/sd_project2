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
     * Bench Broker for referee
     * @param hostName host name
     * @param portNum port number
     */
    public RefereeBenchBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }



    @Override
    public void callTrial() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeBenchMessage inMessage;
        RefereeBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.CALLTR);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (RefereeBenchMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != RefereeBenchMessage.CALLTR_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

    }

    @Override
    public void startTrial() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeBenchMessage inMessage;
        RefereeBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.STARTTR);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (RefereeBenchMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != RefereeBenchMessage.STARTTR_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

    }

    @Override
    public int declareMatchWinner(int games1, int games2) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeBenchMessage inMessage;
        RefereeBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.DECLAREMATCHWIN, games1, games2);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (RefereeBenchMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != RefereeBenchMessage.DECLAREMATCHWIN_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        int match_winner = inMessage.getWinner();
        return match_winner;
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
