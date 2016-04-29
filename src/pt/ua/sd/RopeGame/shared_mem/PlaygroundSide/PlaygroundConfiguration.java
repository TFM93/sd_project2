package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;

/**
 * This data type defines the Playground configuration.
 * It includes host name, port number and initialization parameters
 */
public class PlaygroundConfiguration {

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
    int nEntities;

    /**
     * Playground Configuration constructor method
     * @param hostName host name
     * @param portNumb port number
     * @param nEntities number of entities
     */
    public PlaygroundConfiguration(String hostName, int portNumb, int nEntities) {
        this.hostName = hostName;
        this.portNumb = portNumb;
        this.nEntities = nEntities;
    }
}