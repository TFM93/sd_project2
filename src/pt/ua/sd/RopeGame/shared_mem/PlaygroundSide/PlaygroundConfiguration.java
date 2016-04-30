package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;

/**
 * This data type defines the Playground configuration.
 * It includes host name, port number and initialization parameters
 */
 class PlaygroundConfiguration {

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
     * Playground Configuration constructor method
     * @param hostName host name
     * @param portNumb port number
     */
     PlaygroundConfiguration(String hostName, int portNumb) {
        this.hostName = hostName;
        this.portNumb = portNumb;
        this.n_terminates_to_end = 3;
    }
}