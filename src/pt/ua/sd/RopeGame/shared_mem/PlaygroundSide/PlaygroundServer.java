package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Created by ivosilva on 25/04/16.
 */
public class PlaygroundServer {

    /**
     * Main program.
     * @param args arguments
     */
    public static void main(String[] args) {

        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar playground.jar <configuration host name> <configuration port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        // Get ref site configuration
        PlaygroundConfiguration conf = getPlaygroundConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get repository configuration
        //Domain repConf = getRepositoryConfiguration(configurationServerHostname, configurationServerPortnum);

        MPlayground playground;                        // Referee site
        PlaygroundSideInterface playgroundInterface;       // Interface to Referee Site
        ServerComm scon, sconi;            // Communication channels
        PlaygroundClientProxy cliProxy;         // Agent service provider thread

        // Establish the service
        scon = new ServerComm(conf.portNumb); // Creation of the listening channel
        scon.start();
        playground = new MPlayground();                                    // Activation of the service
        playgroundInterface = new PlaygroundSideInterface(playground, conf.nEntities);  // Activation of the service interface

        System.out.println("Playground: The service was established");
        System.out.println("Playground: The server is listening");

        // Requests processing
        while (true) {
            sconi = scon.accept();                                    // Listening
            cliProxy = new PlaygroundClientProxy(sconi, playgroundInterface);     // Agent service provider
            cliProxy.start();
        }
    }

    /**
     * Get Playground configuration.
     * @return playground configuration
     */
    private static PlaygroundConfiguration getPlaygroundConfiguration(String configurationServerHostname, int configurationServerPortnum) {

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
            System.out.println("Invalid message type at " + PlaygroundServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        PlaygroundConfiguration conf = new PlaygroundConfiguration(inMessage.getHostName(), inMessage.getPortNumb(),
                inMessage.getArg1());

        return conf;
    }

    /**
     * This data type defines the Playground configuration.
     * It includes host name, port number and initialization parameters
     */
    private static class PlaygroundConfiguration {

        // Attributes
        String hostName;
        int portNumb;
        int nEntities;

        // Constructor
        public PlaygroundConfiguration(String hostName, int portNumb, int nEntities) {
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
