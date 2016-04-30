package pt.ua.sd.RopeGame.active_entities.coachSide;

/**
 * Domain
 * Includes host name and port number
 */
public class Domain {
    String hostName;
    int portNumb;

    /**
     * Domain constructor method
     * @param hostName host name
     * @param portNumb port number
     */
    public Domain(String hostName, int portNumb) {
        this.hostName = hostName;
        this.portNumb = portNumb;
    }
}