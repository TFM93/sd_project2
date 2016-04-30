package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ContestantRepoMessage;
import pt.ua.sd.RopeGame.enums.ContestantState;
import pt.ua.sd.RopeGame.interfaces.IRepoContestant;

/**
 * Contestants' Repo Broker
 *
 * Sends the desired messages to the Repo
 */
public class ContestantRepoBroker implements IRepoContestant{


    /**
     * Host name
     * @serialfield hostName
     */
    private final String hostName;

    /**
     * Port number
     * @serialfield portNum
     */
    private final int portNum;

    /**
     * Repo Broker for contestant
     * @param hostName host name
     * @param portNum port number
     */
    public ContestantRepoBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    /**
     * Send a terminate message to the repo
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantRepoMessage inMessage;
        ContestantRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to repo  */
        outMessage = new ContestantRepoMessage(ContestantRepoMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a contestant log message to the repo
     * @param id contestant's id
     * @param team_id team id
     * @param strength contestant's strength
     * @param state contestant's state
     */
    @Override
    public void contestantLog(int id, int team_id, int strength, ContestantState state) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantRepoMessage inMessage;
        ContestantRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message contestant log to repo  */
        outMessage = new ContestantRepoMessage(ContestantRepoMessage.CONTESTANTLOG,id,team_id,strength,state.ordinal());
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ContestantRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != ContestantRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send an update rope center message to the repo
     * @param new_val new rope center value
     */
    @Override
    public void updtRopeCenter(int new_val) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantRepoMessage inMessage;
        ContestantRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message update rope center to repo  */
        outMessage = new ContestantRepoMessage(ContestantRepoMessage.UPDATEROPECENTER,new_val);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ContestantRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != ContestantRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }
}
