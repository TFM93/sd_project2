package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;
import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Referee Site Server
 *
 * Starts the Referee Site server to listen for incoming messages
 *
 */
public class RefereeSiteServer {

    public static void main(String[] args) {

        /*  fetch information from configuration server  */
        if (args.length < 2) {
            System.out.println("Usage: java -jar refSite.jar <host name> <port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        /*  get referee site configuration  */
        RefSiteConfiguration conf = getRefSiteConfiguration(configurationServerHostname, configurationServerPortnum);

        MRefereeSite refereeSite;
        RefereeSiteSideInterface refSiteInterface;
        ServerComm scon, sconi;
        RefereeSiteClientProxy cliProxy;

        scon = new ServerComm(conf.portNumb); // Creation of the listening channel
        scon.start();
        refereeSite = new MRefereeSite();
        refSiteInterface = new RefereeSiteSideInterface(refereeSite, conf.n_terminates_to_end);

        System.out.println("Referee Site: The service was established");
        System.out.println("Referee Site: The server is listening");

        /*  process requests  */
        while (true) {
            sconi = scon.accept();                                    // Listening
            cliProxy = new RefereeSiteClientProxy(sconi, refSiteInterface);     // Agent service provider
            cliProxy.start();
        }
    }

    /**
     * Get Referee Site configuration
     * @param configurationServerHostname Configuration server host name
     * @param configurationServerPortnum Configuration server port
     * @return Repository configuration
     */
    private static RefSiteConfiguration getRefSiteConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message to get repository configuration  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREF_SITE);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREF_SITE_ANSWER)) {
            System.out.println("Invalid message type at " + RefereeSiteServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  return the configuration  */
        return new RefSiteConfiguration(inMessage.getHostName(), inMessage.getPortNumb());
    }
}
