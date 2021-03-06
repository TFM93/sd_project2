package pt.ua.sd.RopeGame.active_entities.contestantSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

import java.util.Random;

/**
 * Contestant client class
 *
 * Instantiates a contestant client
 */
public class ContestantClient {


    public static void main(String[] args){


        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar contestant.jar <host name> <port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        // Get bench configuration
        Domain benchConf = getBenchConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get playground configuration
        Domain playgroundConf = getPlaygroundConfiguration(configurationServerHostname, configurationServerPortnum);

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
                    players_pushing);
        }

        for(int i = 0; i < players_team; i++){
            cont_t2[i] = new Contestant(i, 2, rn.nextInt(20 - 10 + 1) + 10,
                    playground,
                    bench,
                    repo,
                    players_team,
                    players_pushing);
        }


        // Start simulation
        for (int i = 0; i < players_team; i++) {
            cont_t1[i].start();
            cont_t2[i].start();
            System.out.println("Contestants with id " + i + " started!");
        }

        // Wait for end of the simulation
        for (int i = 0; i < players_team; i++) {
            try {
                cont_t1[i].join();
                cont_t2[i].join();

            } catch (InterruptedException e) {
                System.out.println();
            }
            bench.terminate();
            playground.terminate();
            repo.terminate();
            System.out.println("Contestants with id " + i + " terminated!");
        }

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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        return new Domain(inMessage.getHostName(), inMessage.getPortNumb());

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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */

        return new Domain(inMessage.getHostName(), inMessage.getPortNumb());

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

        /*  send get repo domain message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREP_DOM);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREP_ANS_DOM)) {
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */

        return new Domain(inMessage.getHostName(), inMessage.getPortNumb());
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */

        return inMessage.getArg1();
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        return inMessage.getArg2();
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        return inMessage.getArg4();
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
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        return inMessage.getArg3();
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

        /*  send get knockout differential message  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GET_KNOCK_DIF);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GET_KNOCK_DIF_ANS)) {
            System.out.println("Invalid message type at " + ContestantClient.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  get data from message  */
        return inMessage.getArg5();
    }

}
