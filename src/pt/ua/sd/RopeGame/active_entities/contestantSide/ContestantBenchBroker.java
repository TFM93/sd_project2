package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ContestantBenchMessage;
import pt.ua.sd.RopeGame.interfaces.IContestantsBenchContestant;


/**
 * Created by tiagomagalhaes on 25/04/16.
 */
public class ContestantBenchBroker implements IContestantsBenchContestant {


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
    public ContestantBenchBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    /**
     * Signals Bench server that Contestants will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantBenchMessage inMessage;
        ContestantBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantBenchMessage(ContestantBenchMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }


    @Override
    public boolean[] followCoachAdvice(int contestant_id, int strength, int team_id, int n_players, int n_players_pushing) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantBenchMessage inMessage;
        ContestantBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantBenchMessage(ContestantBenchMessage.FOLLOW_COACH_ADVICE);

        // Send message
        con.writeObject(outMessage);


        // Get answer
        inMessage = (ContestantBenchMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ContestantBenchMessage.FOLLOW_COACH_ADVICE_ANS)) {
            System.out.println("Invalid message type at " + ContestantBenchBroker.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }


        // Close connection
        con.close();

        return inMessage.getAdvice_followed();
    }

    @Override
    public void getReady(int n_players_pushing) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantBenchMessage inMessage;
        ContestantBenchMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantBenchMessage(ContestantBenchMessage.GETREADY);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ContestantBenchMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ContestantBenchMessage.GETREADY_ANS)) {
            System.out.println("Invalid message type at " + ContestantBenchBroker.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }


        // Close connection
        con.close();
    }
}
