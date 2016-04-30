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
     * Number of entities of the bench
     */
    private final int n_terminates_to_receive;

    /**
     * Bench Configuration constructor method
     * @param hostName host name
     * @param portNumb port number
     */
    public BenchConfiguration(String hostName, int portNumb) {
        this.hostName = hostName;
        this.portNumb = portNumb;
        this.n_terminates_to_receive = 3;
    }


    public String getHostName() {
        return hostName;
    }

    public int getPortNumb() {
        return portNumb;
    }

    public int getN_terminates_to_receive() {
        return n_terminates_to_receive;
    }
}