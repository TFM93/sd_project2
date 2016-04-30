package pt.ua.sd.RopeGame.active_entities.refereeSide;

/**
 * Domain
 * Includes host name and port number
 */
class Domain {
    String hostName;
    int portNumb;

    /**
     * Domain constructor method
     * @param hostName host name
     * @param portNumb port number
     */
    Domain(String hostName, int portNumb) {
        this.hostName = hostName;
        this.portNumb = portNumb;
    }
}