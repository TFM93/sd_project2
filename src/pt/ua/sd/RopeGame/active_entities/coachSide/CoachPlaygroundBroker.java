package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachPlaygroundMessage;
import pt.ua.sd.RopeGame.interfaces.IPlaygroundCoach;

/**
 * Created by ivosilva on 25/04/16.
 */
public class CoachPlaygroundBroker implements IPlaygroundCoach {

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
     * Playground Broker for coach instantiation.
     * @param hostName host name
     * @param portNum port number
     */
    public CoachPlaygroundBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    @Override
    public int[] reviewNotes(int[] selected_contestants, int n_players, int n_players_pushing) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        CoachPlaygroundMessage inMessage;
        CoachPlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new CoachPlaygroundMessage(CoachPlaygroundMessage.REVIEWNOTES, selected_contestants, n_players,
                n_players_pushing);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (CoachPlaygroundMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != CoachPlaygroundMessage.REVIEWNOTES_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        int[] new_team = inMessage.getChosen_contestants_after_review();
        return new_team;
    }

    /**
     * Signals Repo server that Referee will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        CoachPlaygroundMessage inMessage;
        CoachPlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new CoachPlaygroundMessage(CoachPlaygroundMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }
}
