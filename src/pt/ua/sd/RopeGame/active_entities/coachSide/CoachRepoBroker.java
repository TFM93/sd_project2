package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachRepoMessage;
import pt.ua.sd.RopeGame.enums.CoachState;
import pt.ua.sd.RopeGame.interfaces.IRepoCoach;

/**
 * Coaches' Central Information Repository Broker
 *
 * Sends the desired messages to the Central Information Repository
 */
class CoachRepoBroker implements IRepoCoach {

    /**
     * Host name
     */
    private final String hostName;

    /**
     * Port number
     */
    private final int portNum;

    /**
     * Repository Broker for coach
     * @param hostName host name
     * @param portNum port number
     */
     CoachRepoBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    /**
     * Send a coach log message to the repository
     * @param team_id team id of the coach
     * @param state state of the coach
     */
    @Override
    public void coachLog(int team_id, CoachState state) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        CoachRepoMessage inMessage;
        CoachRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message to get repository configuration  */
        outMessage = new CoachRepoMessage(CoachRepoMessage.COACHLOG, team_id, state.ordinal());
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (CoachRepoMessage) con.readObject();
        if ((inMessage.getMsgType() != CoachRepoMessage.ACK)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send a terminate message to the repository
     */
    public void terminate() {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        CoachRepoMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message to get repository configuration  */
        outMessage = new CoachRepoMessage(CoachRepoMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }
}
