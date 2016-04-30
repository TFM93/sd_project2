package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereeRepoMessage;
import pt.ua.sd.RopeGame.enums.RefState;
import pt.ua.sd.RopeGame.enums.WonType;
import pt.ua.sd.RopeGame.interfaces.IRepoReferee;

/**
 * Referee's Repo Broker
 *
 * Sends the desired messages to the Repo
 */
class RefereeRepoBroker implements IRepoReferee {


    /**
     * Host name
     */
    private final String hostName;

    /**
     * Port number
     */
    private final int portNum;

    /**
     * Repo Broker for referee
     * @param hostName host name
     * @param portNum port number
     */
    RefereeRepoBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    /**
     * Send a referee log message to the repo
     * @param state referee's state
     * @param trial_number game's trial number
     */
    @Override
    public void refereeLog(RefState state, int trial_number) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRepoMessage inMessage;
        RefereeRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message referee log to repo  */
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.REFEREELOG, state, trial_number);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send an Add header message to the repo
     * @param first is the first log?
     */
    @Override
    public void Addheader(boolean first) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRepoMessage inMessage;
        RefereeRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message set result to repo  */
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.ADDHEADER, first);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a set result message to the repo
     * @param team_id team id
     * @param wonType game data
     * @param nr_trials number of trials
     */
    @Override
    public void setResult(int team_id, WonType wonType, int nr_trials) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRepoMessage inMessage;
        RefereeRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message set result to repo  */
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.SETRESULT, team_id, wonType.ordinal(), nr_trials);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

    }

    /**
     * send a print match result message to the repo
     * @param winner team that won
     * @param score1 score of team 1
     * @param score2 score of team 2
     */
    @Override
    public void printMatchResult(int winner, int score1, int score2) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRepoMessage inMessage;
        RefereeRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message print match result to repo  */
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.PRINTMATCHRESULT, winner, score1, score2);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

    }

    /**
     * Send an update game number message to the repo
     */
    @Override
    public void updGame_nr() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRepoMessage inMessage;
        RefereeRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message update game number to repo  */
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.UPDATEGAMENR);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

    }

    /**
     * Send update rope center message to the repo
     * @param center rope center
     */
    @Override
    public void updtRopeCenter(int center) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRepoMessage inMessage;
        RefereeRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message update rope center to repo  */
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.UPDATEROPECENTER, center);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send terminate message to the repo
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to repo  */
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }
}
