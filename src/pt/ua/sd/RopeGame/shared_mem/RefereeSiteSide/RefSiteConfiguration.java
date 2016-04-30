package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

/**
 * This data type defines the Referee Site configuration.
 * It includes host name, port number and initialization parameters
 */
public class RefSiteConfiguration {
    /**
     * Host name
     */
    String hostName;
    /**
     * Port number
     */
    int portNumb;
    /**
     * number of entities
     */
    int n_terminates_to_end;

    /**
     * Referee Site Configuration constructor method
     * @param hostName host name
     * @param portNumb port number
     */
    public RefSiteConfiguration(String hostName, int portNumb) {
        this.hostName = hostName;
        this.portNumb = portNumb;
        this.n_terminates_to_end = 1;
    }
}