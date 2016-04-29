package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachBenchMessage;
import pt.ua.sd.RopeGame.interfaces.IContestantsBenchCoach;

/**
 * Created by ivosilva on 25/04/16.
 */
public class CoachBenchBroker implements IContestantsBenchCoach {

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
     * Bench broker for coach
     * @param hostName host name
     * @param portNum port number
     */
    public CoachBenchBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    @Override
    public boolean callContestants(int team_id, int[] selected_contestants, int n_players) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        CoachBenchMessage inMessage;
        CoachBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new CoachBenchMessage(CoachBenchMessage.CALLCONTESTANTS, team_id, selected_contestants, n_players);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (CoachBenchMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != CoachBenchMessage.CALLCONTESTANTS_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        boolean match_not_ended = inMessage.isMatch_not_ended();
        return match_not_ended;
    }

    @Override
    public void informReferee() {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        CoachBenchMessage inMessage;
        CoachBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new CoachBenchMessage(CoachBenchMessage.INFORMREF);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (CoachBenchMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != CoachBenchMessage.INFORMREF_ANS)) {
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
        CoachBenchMessage inMessage;
        CoachBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new CoachBenchMessage(CoachBenchMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }
}
