package pt.ua.sd.RopeGame.shared_mem.RepoSide;
import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Central Information Repository Server
 *
 * Starts the Information Central Repository server to listen for incoming messages
 *
 */

public class RepoServer {

    public static void main(String[] args){

        /*  fetch information from configuration server  */
        if (args.length < 2) {
            System.out.println("Usage: java -jar repository.jar <host name> <port number>");
            System.exit(0);
        }
        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        /*  get repository configuration  */
        RepoConfiguration conf = getRepoConfiguration(configurationServerHostname, configurationServerPortnum);

        MGeneralInfoRepo repo;
        RepoInterface repoInterface;
        ServerComm scon, sconi;
        RepoProxy cliProxy;

        scon = new ServerComm(conf.getPortNumber());
        scon.start();
        repo = new MGeneralInfoRepo(conf.getNplayers(),conf.getPlayers_pushing(),conf.getNtrials(),conf.getNgames(),conf.getKnockdif());                                    // Activation of the service
        repoInterface = new RepoInterface(repo, conf.getnEntities());

        System.out.println("Repository: The service was established");
        System.out.println("Repository: The server is listening");

        /*  process requests  */
        while (true) {
            sconi = scon.accept();
            cliProxy = new RepoProxy(sconi, repoInterface);
            cliProxy.start();
        }
    }

    /**
     * Get Repository configuration
     * @param configServer Configuration server host name
     * @param configPort Configuration server port
     * @return Repository configuration
     */
    private static RepoConfiguration getRepoConfiguration(String configServer,int configPort){

        /*  create communication socket  */
        ClientComm con = new ClientComm (configServer, configPort);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message to get repository configuration  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREP);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREP_ANSWER)) {
            System.out.println("Invalid message type at " + RepoServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  return the configuration  */
        return new RepoConfiguration(inMessage.getHostName(),inMessage.getPortNumb(),inMessage.getArg1(),inMessage.getArg2(),inMessage.getArg3(),inMessage.getArg4(),inMessage.getArg5());
    }
}
