package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ContestantPlaygroundMessage;
import pt.ua.sd.RopeGame.interfaces.IPlaygroundContestant;

/**
 * Created by tiagomagalhaes on 25/04/16.
 */
public class ContestantPlaygroundBroker implements IPlaygroundContestant {


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
    public ContestantPlaygroundBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    /**
     * Signals Playground server that Contestant will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }

    @Override
    public void pullTheRope(int team_id, int strenght, int contestant_id, int n_players_pushing, int n_players) {

    }

    @Override
    public void iAmDone(int n_players_pushing) {

    }

    @Override
    public void seatDown(int n_players_pushing) {

    }
}
