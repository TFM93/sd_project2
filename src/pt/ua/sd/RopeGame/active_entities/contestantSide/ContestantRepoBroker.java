package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ContestantRepoMessage;
import pt.ua.sd.RopeGame.enums.ContestantState;
import pt.ua.sd.RopeGame.interfaces.IRepoContestant;

/**
 * Created by tiagomagalhaes on 25/04/16.
 */
public class ContestantRepoBroker implements IRepoContestant{


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
     * Repo Broker for contestant
     * @param hostName host name
     * @param portNum port number
     */
    public ContestantRepoBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    /**
     * Signals Repo server that Contestant will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantRepoMessage inMessage;
        ContestantRepoMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantRepoMessage(ContestantRepoMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }

    @Override
    public void contestantLog(int id, int team_id, int strength, ContestantState state) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantRepoMessage inMessage;
        ContestantRepoMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantRepoMessage(ContestantRepoMessage.CONTESTANTLOG,id,team_id,strength,state.ordinal());

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ContestantRepoMessage) con.readObject();

        if ((inMessage.getMsgType() != ContestantRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();
    }

    @Override
    public void updtRopeCenter(int new_val) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantRepoMessage inMessage;
        ContestantRepoMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantRepoMessage(ContestantRepoMessage.UPDATEROPECENTER,new_val);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ContestantRepoMessage) con.readObject();

        if ((inMessage.getMsgType() != ContestantRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();
    }
}
