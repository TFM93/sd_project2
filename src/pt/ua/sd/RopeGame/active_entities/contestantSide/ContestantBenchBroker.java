package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ContestantBenchMessage;
import pt.ua.sd.RopeGame.interfaces.IContestantsBenchContestant;


/**
 * Contestants' Bench Broker
 *
 * Sends the desired messages to the Bench
 */
class ContestantBenchBroker implements IContestantsBenchContestant {


    /**
     * Host name
     */
    private final String hostName;

    /**
     * Port number
     */
    private final int portNum;

    /**
     * Bench Broker for contestant
     * @param hostName host name
     * @param portNum port number
     */
    ContestantBenchBroker(String hostName, int portNum) {
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
        ContestantBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to bench  */
        outMessage = new ContestantBenchMessage(ContestantBenchMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a follow coach advice to the bench
     * @param contestant_id contestant's id
     * @param strength contestant's strength
     * @param team_id team id
     * @param n_players number of players
     * @param n_players_pushing number of players pushing
     * @return advice followed
     */
    @Override
    public boolean[] followCoachAdvice(int contestant_id, int strength, int team_id, int n_players, int n_players_pushing) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantBenchMessage inMessage;
        ContestantBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message follow coach advice to bench  */
        outMessage = new ContestantBenchMessage(ContestantBenchMessage.FOLLOW_COACH_ADVICE,contestant_id,strength,team_id,n_players,n_players_pushing);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ContestantBenchMessage) con.readObject();
        if ((inMessage.getMsgType() != ContestantBenchMessage.FOLLOW_COACH_ADVICE_ANS)) {
            System.out.println("Invalid message type at " + ContestantBenchBroker.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        return inMessage.getAdvice_followed();
    }

    /**
     * Send a get ready message to the bench
     * @param n_players_pushing number of players pushing
     */
    @Override
    public void getReady(int n_players_pushing) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        ContestantBenchMessage inMessage;
        ContestantBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message get ready to bench  */
        outMessage = new ContestantBenchMessage(ContestantBenchMessage.GETREADY,n_players_pushing);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ContestantBenchMessage) con.readObject();
        if ((inMessage.getMsgType() != ContestantBenchMessage.GETREADY_ANS)) {
            System.out.println("Invalid message type at " + ContestantBenchBroker.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }
}
