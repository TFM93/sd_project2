package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereeRefSiteMessage;
import pt.ua.sd.RopeGame.interfaces.IRefereeSiteReferee;
import pt.ua.sd.RopeGame.structures.GameStat;

/**
 * Referee's Referee Site Broker
 *
 * Sends the desired messages to the Referee Site
 */
class RefereeRefereeSiteBroker implements IRefereeSiteReferee {

    /**
     * Host name
     */
    private final String hostName;

    /**
     * Port number
     */
    private final int portNum;

    /**
     * Referee Site Broker for referee
     * @param hostName host name
     * @param portNum port number
     */
     RefereeRefereeSiteBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }

    /**
     * Send message announce new game to referee site
     */
    @Override
    public void announceNewGame() {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRefSiteMessage inMessage;
        RefereeRefSiteMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message announce new game to referee site  */
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.ANG);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRefSiteMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRefSiteMessage.ANG_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();
    }

    /**
     * Send message declare game winner to referee site
     * @param score_T1 team 1 score
     * @param score_T2 team 2 score
     * @param knock_out knockout differential
     * @param n_games number of games
     * @return game's stats
     */
    @Override
    public GameStat declareGameWinner(int score_T1, int score_T2, int knock_out, int n_games) {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRefSiteMessage inMessage;
        RefereeRefSiteMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message declare game winner to referee site  */
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.DGW, score_T1, score_T2, knock_out, n_games);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRefSiteMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRefSiteMessage.DGW_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        return inMessage.getStat();
    }

    /**
     * Send message get number of games played to referee site
     * @return number of games played
     */
    @Override
    public int getN_games_played() {
        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRefSiteMessage inMessage;
        RefereeRefSiteMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message get number of games played to referee site  */
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.GNGP);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (RefereeRefSiteMessage) con.readObject();
        if ((inMessage.getMsgType() != RefereeRefSiteMessage.GNGP_ANS)) {
            System.out.println("Invalid message type at " + this.getClass().getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        return inMessage.getN_games_played();
    }

    /**
     * Send a terminate message to the referee site
     */
    public void terminate() {

        /*  create communication socket  */
        ClientComm con = new ClientComm (hostName, portNum);

        /*  instantiate the configuration messages  */
        RefereeRefSiteMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message terminate to referee site  */
        outMessage = new RefereeRefSiteMessage(RefereeRefSiteMessage.TERMINATE);
        con.writeObject(outMessage);

        /*  close the connection  */
        con.close();
    }
}
