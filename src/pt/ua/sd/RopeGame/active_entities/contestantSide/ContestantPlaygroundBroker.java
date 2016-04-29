package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ContestantPlaygroundMessage;
import pt.ua.sd.RopeGame.interfaces.IPlaygroundContestant;


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
     * Playground Broker for contestant
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
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.PULLROPE,team_id,strenght,contestant_id,n_players_pushing,n_players);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ContestantPlaygroundMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ContestantPlaygroundMessage.PULLROPE_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();
    }

    @Override
    public void iAmDone(int n_players_pushing) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.AMDONE,n_players_pushing);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ContestantPlaygroundMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ContestantPlaygroundMessage.AMDONE_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();
    }

    @Override
    public void seatDown(int n_players_pushing) {
        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.SEATDOWN,n_players_pushing);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ContestantPlaygroundMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ContestantPlaygroundMessage.SEATDOWN_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();
    }
}
