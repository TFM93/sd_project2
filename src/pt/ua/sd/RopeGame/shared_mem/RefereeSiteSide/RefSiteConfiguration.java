package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

/**
 * This data type defines the Referee Site configuration.
 * It includes host name, port number and initialization parameters
 */
 class RefSiteConfiguration {
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
     * Referee Site Configuration constructor method
     * @param hostName host name
     * @param portNumb port number
     * @param nEntities number of entities
     */
     RefSiteConfiguration(String hostName, int portNumb, int nEntities) {
        this.hostName = hostName;
        this.portNumb = portNumb;
        this.nEntities = nEntities;
    }
}