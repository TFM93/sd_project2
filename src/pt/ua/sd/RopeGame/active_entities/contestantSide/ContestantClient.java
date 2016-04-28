package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

import java.util.Random;

/**
 * Created by tiagomagalhaes on 25/04/16.
 */
public class ContestantClient {


    public static void main(String[] args){


        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar contestant.jar <configuration host name> <configuration port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        // Get bench configuration
        Domain benchConf = getBenchConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf bench");

        // Get playground configuration
        Domain playgroundConf = getPlaygroundConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf playg");

        // Get referee site configuration
        Domain refSiteConf = getRefereeSiteConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf refSite");

        // Get repo configuration
        Domain repoConf = getRepoConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf repo host " + repoConf.hostName+ " port " + repoConf.portNumb);

        // get number of players in team
        int players_team = getNPlayersTeamConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf players " + players_team);

        // get number of players pushing
        int players_pushing = getNPlayersPushingConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf players push " + players_pushing);

        // get number of games
        int n_games = getNGamesConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf games ->" + n_games);

        // get number of trials
        int n_trials = getNTrialsConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf ntrials ->" +n_trials);

        // get knockout difference
        int knockDif = getKnockoutDifConfiguration(configurationServerHostname, configurationServerPortnum);
        System.out.println("got conf n knockdif-> "+knockDif);
        //randomize
        Random rn = new Random();
        //contestants for each team
        Contestant[] cont_t1 = new Contestant[players_team];  // contestants array team 1
        Contestant[] cont_t2 = new Contestant[players_team];  // contestants array team 1


        ContestantBenchBroker bench = new ContestantBenchBroker(benchConf.hostName, benchConf.portNumb);
        ContestantPlaygroundBroker playground = new ContestantPlaygroundBroker(playgroundConf.hostName, playgroundConf.portNumb);
        ContestantRepoBroker repo = new ContestantRepoBroker(repoConf.hostName, repoConf.portNumb);

        // Create the contestants
        for(int i = 0; i < players_team; i++){
            cont_t1[i] = new Contestant(i, 1, rn.nextInt(20 - 10 + 1) + 10,
                    playground,
                    bench,
                    repo,
                    players_team,
                    players_pushing,
                    n_trials,
                    n_games,
                    knockDif);
           // cont_t1[i].start();
        }

        for(int i = 0; i < players_team; i++){
            cont_t2[i] = new Contestant(i, 1, rn.nextInt(20 - 10 + 1) + 10,
                    playground,
                    bench,
                    repo,
                    players_team,
                    players_pushing,
                    n_trials,
                    n_games,
                    knockDif);
            //cont_t2[i].start();
        }


        // Start simulation
        for (int i = 0; i < players_team; i++) {
            cont_t1[i].start();
            cont_t2[i].start();
            System.out.println("Contestants with " + i + " started!");
        }

        // Wait for end of the simulation
        for (int i = 0; i < players_team; i++) {
            try {
                cont_t1[i].join();
                cont_t2[i].join();
                bench.terminate();
                playground.terminate();
                //refSite.terminate();
                repo.terminate();
            } catch (InterruptedException e) {}
            System.out.println("Contestants with id " + i + " terminated!");
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREF_SITE);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREF_SITE_ANSWER)) {
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NPLAYERS_ANS)) {
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NPLAYERS_PUSHING_ANSWER)) {
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NGAMES_ANS)) {
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
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
