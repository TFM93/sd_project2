package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.CoachRefSiteMessage;
import pt.ua.sd.RopeGame.interfaces.IRefereeSiteCoach;

/**
 * Created by ivosilva on 25/04/16.
 */
public class CoachRefereeSiteBroker implements IRefereeSiteCoach {
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
     * Workshop Broker for Craftsman instantiation.
     * @param hostName host name
     * @param portNum port number
     */
    public CoachRefereeSiteBroker(String hostName, int portNum) {
        this.hostName = hostName;
        this.portNum = portNum;
    }


}
