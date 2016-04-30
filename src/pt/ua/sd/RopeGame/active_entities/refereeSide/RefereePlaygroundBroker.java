package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereePlaygroundMessage;
import pt.ua.sd.RopeGame.comInfo.RefereeRefSiteMessage;
import pt.ua.sd.RopeGame.interfaces.IPlaygroundReferee;
import pt.ua.sd.RopeGame.structures.TrialStat;

/**
 * Referee's Playground Broker
 *
 * Sends the desired messages to the Playgrounds
 */
public class RefereePlaygroundBroker implements IPlaygroundReferee {


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
     * Playground Broker for referee
     * @param hostName host name
     * @param portNum port number
     */
    public RefereePlaygroundBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    /**
     * Send assert trial decision message to Playground
     * @param n_players_pushing number of players pushing
     * @param knockDif knockout differential
     * @return trial stats
     */
    @Override
    public TrialStat assertTrialDecision(int n_players_pushing, int knockDif) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereePlaygroundMessage inMessage;
        RefereePlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message assert trial decision to playground  */
        outMessage = new RefereePlaygroundMessage(RefereePlaygroundMessage.ATD, n_players_pushing, knockDif);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereePlaygroundMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereePlaygroundMessage.ATD_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        TrialStat stat = inMessage.getStat();
        return stat;
    }


    /**
     * Send a terminate message to the playground
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereePlaygroundMessage inMessage;
        RefereePlaygroundMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to repo  */
        outMessage = new RefereePlaygroundMessage(RefereePlaygroundMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }
}
