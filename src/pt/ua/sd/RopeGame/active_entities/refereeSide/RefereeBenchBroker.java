package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereeBenchMessage;
import pt.ua.sd.RopeGame.interfaces.IContestantsBenchReferee;

/**
 * Referee's Bench Broker
 *
 * Sends the desired messages to the Bnech
 */
class RefereeBenchBroker implements IContestantsBenchReferee {


    /**
     * Host name
     */
    private final String hostName;

    /**
     * Port number
     */
    private final int portNum;

    /**
     * Bench Broker for referee
     * @param hostName host name
     * @param portNum port number
     */
    RefereeBenchBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    /**
     * Send call trial message to Bench
     */
    @Override
    public void callTrial() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeBenchMessage inMessage;
        RefereeBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message call trial to bench  */
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.CALLTR);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeBenchMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeBenchMessage.CALLTR_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

    }

    /**
     * Send start trial message to Bench
     */
    @Override
    public void startTrial() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeBenchMessage inMessage;
        RefereeBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message start trial to bench  */
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.STARTTR);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeBenchMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeBenchMessage.STARTTR_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

    }

    /**
     * Send declare match winner message to Bench
     * @param games1 games won by team 1
     * @param games2 games won by team 2
     * @return match_winner
     */
    @Override
    public int declareMatchWinner(int games1, int games2) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeBenchMessage inMessage;
        RefereeBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message declare match winner to bench  */
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.DECLAREMATCHWIN, games1, games2);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeBenchMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeBenchMessage.DECLAREMATCHWIN_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
        return inMessage.getWinner();
    }

    /**
     * Send a terminate message to the Bench
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to bench  */
        outMessage = new RefereeBenchMessage(RefereeBenchMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }
}
