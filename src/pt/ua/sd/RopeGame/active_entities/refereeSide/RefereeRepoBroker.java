package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.RefereeRefSiteMessage;
import pt.ua.sd.RopeGame.comInfo.RefereeRepoMessage;
import pt.ua.sd.RopeGame.enums.RefState;
import pt.ua.sd.RopeGame.enums.WonType;
import pt.ua.sd.RopeGame.interfaces.IRepoReferee;

/**
 * Created by ivosilva on 25/04/16.
 */
public class RefereeRepoBroker implements IRepoReferee {


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
    public RefereeRepoBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


    @Override
    public void refereeLog(RefState state, int trial_number) {

    }

    @Override
    public void Addheader(boolean first) {

    }

    @Override
    public void setResult(int team_id, WonType wonType, int nr_trials) {

    }

    @Override
    public void printMatchResult(int winner, int score1, int score2) {

    }

    @Override
    public void updGame_nr() {

    }

    @Override
    public void updtRopeCenter(int center) {

    }

    /**
     * Signals Repo server that Referee will terminate.
     */
    public void terminate() {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (hostName, portNum);

        // In and out message
        RefereeRepoMessage inMessage;
        RefereeRepoMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new RefereeRepoMessage(RefereeRepoMessage.TERMINATE);

        // Send message
        con.writeObject(outMessage);

        // Close connection
        con.close();
    }
}
