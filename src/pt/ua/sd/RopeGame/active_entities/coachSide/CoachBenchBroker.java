package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachBenchMessage;
import pt.ua.sd.RopeGame.interfaces.IContestantsBenchCoach;

/**
 * Coaches' Bench Broker
 *
 * Sends the desired messages to the Bench
 * @author Ivo Silva (<a href="mailto:ivosilva@ua.pt">ivosilva@ua.pt</a>)
 * @author Tiago Magalhaes (<a href="mailto:tiagoferreiramagalhaes@ua.pt">tiagoferreiramagalhaes@ua.pt</a>)
 */
class CoachBenchBroker implements IContestantsBenchCoach {

    /**
     * Host name
     */
    private final String hostName;

    /**
     * Port number
     */
    private final int portNum;

    /**
     * Bench broker for coach
     * @param hostName host name
     * @param portNum port number
     */
    CoachBenchBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    /**
     * Send a call contestants message to the bench
     * @param team_id team id
     * @param selected_contestants selected contestants
     * @param n_players number of players
     * @return has match ended?
     */
    @Override
    public boolean callContestants(int team_id, int[] selected_contestants, int n_players) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        CoachBenchMessage inMessage;
        CoachBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send call contestants message to bench  */
        outMessage = new CoachBenchMessage(CoachBenchMessage.CALLCONTESTANTS, team_id, selected_contestants, n_players);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (CoachBenchMessage) con.readObject();
        if ((inMessage.getMsgType() != CoachBenchMessage.CALLCONTESTANTS_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        return inMessage.isMatch_not_ended();
    }

    /**
     * Send a inform referee message to the bench
     */
    @Override
    public void informReferee() {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        CoachBenchMessage inMessage;
        CoachBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send inform referee message to bench  */
        outMessage = new CoachBenchMessage(CoachBenchMessage.INFORMREF);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (CoachBenchMessage) con.readObject();
        if ((inMessage.getMsgType() != CoachBenchMessage.INFORMREF_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a terminate message to the playground
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        CoachBenchMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to bench  */
        outMessage = new CoachBenchMessage(CoachBenchMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }
}
