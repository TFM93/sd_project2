package pt.ua.sd.RopeGame.shared_mem.BenchSide;

/**
 * Bench configuration class
 */
public class BenchConfiguration {

    /**
     * Server host name
     */
    private final String hostName;

    /**
     * Server port number
     */
    private final int portNumb;

    /**
     * Number of entities of the repository
     */
    private final int nEntities;

    /**
     * Bench Configuration constructor method
     * @param hostName host name
     * @param portNumb port number
     * @param nEntities number of entities
     */
    public BenchConfiguration(String hostName, int portNumb, int nEntities) {
        this.hostName = hostName;
        this.portNumb = portNumb;
        this.nEntities = nEntities;
    }


    public String getHostName() {
        return hostName;
    }

    public int getPortNumb() {
        return portNumb;
    }

    public int getnEntities() {
        return nEntities;
    }
}