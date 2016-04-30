package pt.ua.sd.RopeGame.active_entities.coachSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Coach client class
 *
 * Instantiates a coach client
 */
public class CoachClient {

    public static void main(String[] args) {

        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar coach.jar <host name> <port number>");
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
        // Get number of referees
        //int nReferee = getNumberOfReferee(configurationServerHostname, configurationServerPortnum); // number of referees
        //int nReferee = 1;
        //Coach[] ref = new Referee[nReferee];  // referee array

        CoachBenchBroker bench = new CoachBenchBroker(benchConf.hostName, benchConf.portNumb);
        CoachPlaygroundBroker playground = new CoachPlaygroundBroker(playgroundConf.hostName, playgroundConf.portNumb);
        //CoachRefereeSiteBroker refSite = new CoachRefereeSiteBroker(refSiteConf.hostName, refSiteConf.portNumb);
        CoachRepoBroker repo = new CoachRepoBroker(repoConf.hostName, repoConf.portNumb);

        Coach coach_team1 = new Coach(1, 1,
                playground,
                null,
                bench,
                repo,
                players_team,
                players_pushing,
                n_trials,
                n_games,
                knockDif
        );
        Coach coach_team2 = new Coach(2, 2,
                playground,
                null,
                bench,
                repo,
                players_team,
                players_pushing,
                n_trials,
                n_games,
                knockDif
        );

        // Start simulation
        //for (int i = 0; i < nReferee; i++) {
        //    ref[i].start();
        //    System.out.println("Referee " + i + " started!");
        //}

        coach_team1.start();
        System.out.println("Coach " + coach_team1.getTeam_id() + " started!");

        coach_team2.start();
        System.out.println("Coach " + coach_team2.getTeam_id() + " started!");


        try {
            coach_team1.join();
            coach_team2.join();

        } catch (InterruptedException e) {}
        bench.terminate();
        playground.terminate();
        repo.terminate();
        System.out.println("Coach  terminated!");
    }

    /**
     * Get Bench configuration.
     * @return bench server host name and port number
     */
    private static Domain getBenchConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get bench message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETBENCH);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETBENCH_ANSWER)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName() + "expected GETBENCH_ANSWER ::"+ inMessage.getMsgType());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }

    /**
     * Get Playground configuration.
     * @return Playground server host name and port number
     */
    private static Domain getPlaygroundConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get playground message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETPLAYGROUND);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETPLAYGROUND_ANSWER)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName() + "expected GETPLAYGROUND_ANSWER :: "+ inMessage.getMsgType());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }

    /**
     * Get Referee Site configuration.
     * @return ref site server host name and port number
     */
    private static Domain getRefereeSiteConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get referee site message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREF_SITE);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREF_SITE_ANSWER)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName() + "expected GETREF_SITE_ANSWER ::" + inMessage.getMsgType());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }

    /**
     * Get Repo configuration.
     * @return repo server host name and port number
     */
    private static Domain getRepoConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get repository domain message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREP_DOM);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREP_ANS_DOM)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        Domain conf = new Domain(inMessage.getHostName(), inMessage.getPortNumb());

        return conf;
    }


    /**
     * Get number of players in team configuration.
     * @return number of players in team
     */
    private static int getNPlayersTeamConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get number of players message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NPLAYERS_ANS)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        int n_players = inMessage.getArg1();

        return n_players;
    }

    /**
     * Get number of player pushing configuration.
     * @return number of players pushing
     */
    private static int getNPlayersPushingConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get number of players pushing message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS_PUSHING);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NPLAYERS_PUSHING_ANSWER)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        int n_players_pushing = inMessage.getArg2();

        return n_players_pushing;
    }


    /**
     * Get number of games configuration.
     * @return number of games
     */
    private static int getNGamesConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get number of games message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NGAMES);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NGAMES_ANS)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        int n_games = inMessage.getArg4();

        return n_games;
    }


    /**
     * Get number of trials configuration.
     * @return number of trials
     */
    private static int getNTrialsConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send get number of trials message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NTRIALS);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_NTRIALS_ANS)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        int n_trials = inMessage.getArg3();

        return n_trials;
    }

    /**
     * Get number of trials configuration.
     * @return number of trials
     */
    private static int getKnockoutDifConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_KNOCK_DIF);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_KNOCK_DIF_ANS)) {
            System.out.println("Invalid message type at " + CoachClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        int knock_dif = inMessage.getArg5();

        return knock_dif;
    }

}
