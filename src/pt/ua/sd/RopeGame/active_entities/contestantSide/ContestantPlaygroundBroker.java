package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ContestantPlaygroundMessage;
import pt.ua.sd.RopeGame.interfaces.IPlaygroundContestant;

/**
 * Contestants' Playground Broker
 *
 * Sends the desired messages to the Repo
 */
public class ContestantPlaygroundBroker implements IPlaygroundContestant {


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
     * Playground Broker for contestant
     * @param hostName host name
     * @param portNum port number
     */
    public ContestantPlaygroundBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    /**
     * Send a terminate message to the playground
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to playground  */
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a pull the rope message to the playground
     * @param team_id team id
     * @param strenght contestant's strength
     * @param contestant_id contestant's id
     * @param n_players_pushing number of players pushing
     * @param n_players number of players
     */
    @Override
    public void pullTheRope(int team_id, int strenght, int contestant_id, int n_players_pushing, int n_players) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message pull the rope to playground  */
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.PULLROPE,team_id,strenght,contestant_id,n_players_pushing,n_players);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ContestantPlaygroundMessage) con.readObject();
        if ((inMessage.getMsgType() != ContestantPlaygroundMessage.PULLROPE_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a i Am Done message to the playground
     * @param n_players_pushing number of players pushing
     */
    @Override
    public void iAmDone(int n_players_pushing) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message i am done to playground  */
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.AMDONE,n_players_pushing);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ContestantPlaygroundMessage) con.readObject();
        if ((inMessage.getMsgType() != ContestantPlaygroundMessage.AMDONE_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a seat down message to the playground
     * @param n_players_pushing number of players pushing
     */
    @Override
    public void seatDown(int n_players_pushing) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantPlaygroundMessage inMessage;
        ContestantPlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message seat down to playground  */
        outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.SEATDOWN,n_players_pushing);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ContestantPlaygroundMessage) con.readObject();
        if ((inMessage.getMsgType() != ContestantPlaygroundMessage.SEATDOWN_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }
}
