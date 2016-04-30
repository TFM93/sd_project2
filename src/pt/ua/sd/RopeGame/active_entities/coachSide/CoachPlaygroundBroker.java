package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachPlaygroundMessage;
import pt.ua.sd.RopeGame.interfaces.IPlaygroundCoach;

/**
 * Coaches' Playground Broker
 *
 * Sends the desired messages to the Playground
 */
public class CoachPlaygroundBroker implements IPlaygroundCoach {

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
     * Playground Broker for coach constructor method
     * @param hostName host name
     * @param portNum port number
     */
    public CoachPlaygroundBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    /**
     * Send a review notes message to the playground
     * @param selected_contestants selected contestants
     * @param n_players number of players
     * @param n_players_pushing number of players pushing
     * @return new team selected to play
     */
    @Override
    public int[] reviewNotes(int[] selected_contestants, int n_players, int n_players_pushing) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        CoachPlaygroundMessage inMessage;
        CoachPlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send review notes message to playground  */
        outMessage = new CoachPlaygroundMessage(CoachPlaygroundMessage.REVIEWNOTES, selected_contestants, n_players,
                n_players_pushing);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (CoachPlaygroundMessage) con.readObject();
        if ((inMessage.getMsgType() != CoachPlaygroundMessage.REVIEWNOTES_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        int[] new_team = inMessage.getChosen_contestants_after_review();
        return new_team;
    }

    /**
     * Send a terminate message to the playground
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        CoachPlaygroundMessage inMessage;
        CoachPlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to playground  */
        outMessage = new CoachPlaygroundMessage(CoachPlaygroundMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }
}
