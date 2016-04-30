package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachRefSiteMessage;
import pt.ua.sd.RopeGame.interfaces.IRefereeSiteCoach;

/**
 * Coaches' Referee Site Broker
 *
 * Sends the desired messages to the Referee Site
 */
public class CoachRefereeSiteBroker implements IRefereeSiteCoach {
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
     * Referee Site Broker for coach
     * @param hostName host name
     * @param portNum port number
     */
    public CoachRefereeSiteBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


}
