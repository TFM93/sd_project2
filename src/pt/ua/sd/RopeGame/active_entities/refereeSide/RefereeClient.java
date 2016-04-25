package pt.ua.sd.RopeGame.active_entities.refereeSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Created by ivosilva on 25/04/16.
 */
public class RefereeClient {

    /**
     * Main program.
     * @param args arguments
     */
    public static void main(String[] args) {

        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar referee.jar <configuration host name> <configuration port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        // Get bench configuration
        Domain benchConf = getBenchConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get playground configuration
        Domain playgroundConf = getPlaygroundConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get referee site configuration
        Domain refSiteConf = getRefereeSiteConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get repo configuration
        Domain repoConf = getRepoConfiguration(configurationServerHostname, configurationServerPortnum);

        // get number of players in team
        int players_team = getNPlayersTeamConfiguration(configurationServerHostname, configurationServerPortnum);

        // get number of players pushing
        int players_pushing = getNPlayersPushingConfiguration(configurationServerHostname, configurationServerPortnum);

        // get number of games
        int n_games = getNGamesConfiguration(configurationServerHostname, configurationServerPortnum);

        // get number of trials
        int n_trials = getNTrialsConfiguration(configurationServerHostname, configurationServerPortnum);

        // get knockout difference
        int knockDif = getKnockoutDifConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get number of referees
        //int nReferee = getNumberOfReferee(configurationServerHostname, configurationServerPortnum); // number of referees
        int nReferee = 1;
        Referee[] ref = new Referee[nReferee];  // referee array

        RefereeBenchBroker bench = new RefereeBenchBroker(benchConf.hostName, benchConf.portNumb);
        RefereePlaygroundBroker playground = new RefereePlaygroundBroker(playgroundConf.hostName, playgroundConf.portNumb);
        RefereeRefereeSiteBroker refSite = new RefereeRefereeSiteBroker(refSiteConf.hostName, refSiteConf.portNumb);
        RefereeRepoBroker repo = new RefereeRepoBroker(repoConf.hostName, repoConf.portNumb);

        // Create the customers
        for (int i = 0; i < nReferee; i++) {
            ref[i] = new Referee(
                    playground,
                    refSite,
                    bench,
                    repo,
                    players_team,
                    players_pushing,
                    n_trials,
                    n_games,
                    knockDif
            );;
        }

        // Start simulation
        for (int i = 0; i < nReferee; i++) {
            ref[i].start();
            System.out.println("Referee " + i + " started!");
        }

        // Wait for end of the simulation
        for (int i = 0; i < nReferee; i++) {
            try {
                ref[i].join();
                bench.terminate();
                playground.terminate();
                refSite.terminate();
                repo.terminate();
            } catch (InterruptedException e) {}
            System.out.println("Craftsman " + i + " terminated!");
        }
    }

    /**
     * Get Bench configuration.
     * @return bench server host name and port number
     */
    private static Domain getBenchConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETBENCHDOM);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETBENCHDOMANSWER)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }

    /**
     * Get Playground configuration.
     * @return Playground server host name and port number
     */
    private static Domain getPlaygroundConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.PLAYGROUNDDOM);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETWSHOPDOMANSWER)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }

    /**
     * Get Referee Site configuration.
     * @return ref site server host name and port number
     */
    private static Domain getRefereeSiteConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREFSITEDOM);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREFSITEDOMANSWER)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }

    /**
     * Get Repo configuration.
     * @return repo server host name and port number
     */
    private static Domain getRepoConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREPODOM);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREPODOMANSWER)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }

    /**
     * This data type defines the Domain.
     * It includes host name and port number
     */
    private static class Domain {

        // Attributes
        String hostName;
        int portNumb;

        // Constructor
        public Domain(String hostName, int portNumb) {
            this.hostName = hostName;
            this.portNumb = portNumb;
        }
    }

}
