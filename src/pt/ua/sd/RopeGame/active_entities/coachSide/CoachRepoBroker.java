package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachRepoMessage;
import pt.ua.sd.RopeGame.comInfo.RefereeRepoMessage;
import pt.ua.sd.RopeGame.enums.CoachState;
import pt.ua.sd.RopeGame.interfaces.IRepoCoach;

/**
 * Created by ivosilva on 25/04/16.
 */
public class CoachRepoBroker implements IRepoCoach {

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
     * Repo Broker for coach
     * @param hostName host name
     * @param portNum port number
     */
    public CoachRepoBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    @Override
    public void coachLog(int team_id, CoachState state) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        CoachRepoMessage inMessage;
        CoachRepoMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new CoachRepoMessage(CoachRepoMessage.COACHLOG, team_id, state.ordinal());

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (CoachRepoMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != CoachRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();
    }

    /**
     * Signals Repo server that Referee will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        CoachRepoMessage inMessage;
        CoachRepoMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new CoachRepoMessage(CoachRepoMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }
}
