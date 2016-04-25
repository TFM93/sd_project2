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
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETBENCH);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETBENCH_ANSWER)) {
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
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETPLAYGROUND);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETPLAYGROUND_ANSWER)) {
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
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_REF_SITE);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_REF_SITE_ANS)) {
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
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREP_DOM);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREP_ANS_DOM)) {
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
     * Get number of players in team configuration.
     * @return number of players in team
     */
    private static int getNPlayersTeamConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NPLAYERS)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        int n_players = inMessage.getArg1();

        return n_players;
    }

    /**
     * Get number of player pushing configuration.
     * @return number of players pushing
     */
    private static int getNPlayersPushingConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS_PUSHING);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NPLAYERS_PUSHING)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        int n_players_pushing = inMessage.getArg2();

        return n_players_pushing;
    }


    /**
     * Get number of games configuration.
     * @return number of games
     */
    private static int getNGamesConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NGAMES);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NGAMES)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        int n_games = inMessage.getArg4();

        return n_games;
    }


    /**
     * Get number of trials configuration.
     * @return number of trials
     */
    private static int getNTrialsConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NTRIALS);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NTRIALS_ANS)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        int n_trials = inMessage.getArg3();

        return n_trials;
    }

    /**
     * Get number of trials configuration.
     * @return number of trials
     */
    private static int getKnockoutDifConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        // Instatiate a communication socket
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_KNOCK_DIF);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_KNOCK_DIF_ANS)) {
            System.out.println("Invalid message type at " + RefereeClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        int knock_dif = inMessage.getArg5();

        return knock_dif;
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
