package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Created by ivosilva on 24/04/16.
 */
public class RefereeSiteServer {

    /**
     * Main program.
     * @param args arguments
     */
    public static void main(String[] args) {

        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar refsite.jar <configuration host name> <configuration port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        // Get ref site configuration
        RefSiteConfiguration conf = getRefSiteConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get repository configuration
        //Domain repConf = getRepositoryConfiguration(configurationServerHostname, configurationServerPortnum);

        MRefereeSite refereeSite;                        // Referee site
        RefereeSiteSideInterface refSiteInterface;       // Interface to Referee Site
        ServerComm scon, sconi;            // Communication channels
        RefereeSiteClientProxy cliProxy;         // Agent service provider thread

        // Establish the service
        scon = new ServerComm(conf.portNumb); // Creation of the listening channel
        scon.start();
        refereeSite = new MRefereeSite();                                    // Activation of the service
        refSiteInterface = new RefereeSiteSideInterface(refereeSite, conf.nEntities);  // Activation of the service interface

        System.out.println("Referee Site: The service was established");
        System.out.println("Referee Site: The server is listening");

        // Requests processing
        while (true) {
            sconi = scon.accept();                                    // Listening
            cliProxy = new RefereeSiteClientProxy(sconi, refSiteInterface);     // Agent service provider
            cliProxy.start();
        }
    }

    /**
     * Get Referee Site configuration.
     * @return ref site configuration
     */
    private static RefSiteConfiguration getRefSiteConfiguration(String configurationServerHostname, int configurationServerPortnum) {

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
            System.out.println("Invalid message type at " + RefereeSiteServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        RefSiteConfiguration conf = new RefSiteConfiguration(inMessage.getHostName(), inMessage.getPortNumb(),
                inMessage.getArg1());

        return conf;
    }

    /**
     * This data type defines the Referee Site configuration.
     * It includes host name, port number and initialization parameters
     */
    private static class RefSiteConfiguration {

        // Attributes
        String hostName;
        int portNumb;
        int nEntities;

        // Constructor
        public RefSiteConfiguration(String hostName, int portNumb, int nEntities) {
            this.hostName = hostName;
            this.portNumb = portNumb;
            this.nEntities = nEntities;
        }
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
