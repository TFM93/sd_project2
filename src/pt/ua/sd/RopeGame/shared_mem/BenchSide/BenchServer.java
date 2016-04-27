package pt.ua.sd.RopeGame.shared_mem.BenchSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Created by ivosilva on 25/04/16.
 */
public class BenchServer {

    /**
     * Main program.
     * @param args arguments
     */
    public static void main(String[] args) {

        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar bench.jar <configuration host name> <configuration port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        // Get ref site configuration
        BenchConfiguration conf = getBenchConfiguration(configurationServerHostname, configurationServerPortnum);

        // Get repository configuration
        //Domain repConf = getRepositoryConfiguration(configurationServerHostname, configurationServerPortnum);

        MContestantsBench bench;                        // Referee site
        BenchSideInterface benchInterface;       // Interface to Referee Site
        ServerComm scon, sconi;            // Communication channels
        BenchClientProxy cliProxy;         // Agent service provider thread

        // Establish the service
        scon = new ServerComm(conf.portNumb); // Creation of the listening channel
        scon.start();
        bench = new MContestantsBench();                                    // Activation of the service
        benchInterface = new BenchSideInterface(bench, conf.nEntities);  // Activation of the service interface

        System.out.println("Contestants Bench: The service was established");
        System.out.println("Contestants Bench: The server is listening");

        // Requests processing
        while (true) {
            sconi = scon.accept();                                    // Listening
            cliProxy = new BenchClientProxy(sconi, benchInterface);     // Agent service provider
            cliProxy.start();
        }
    }

    /**
     * Get Bench configuration.
     * @return BenchConfiguration
     */
    private static BenchConfiguration getBenchConfiguration(String configurationServerHostname, int configurationServerPortnum) {

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
            System.out.println("Invalid message type at " + BenchServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();

        // Extract data from message
        BenchConfiguration conf = new BenchConfiguration(inMessage.getHostName(), inMessage.getPortNumb(),
                inMessage.getArg1());

        return conf;
    }

    /**
     * This data type defines the Referee Site configuration.
     * It includes host name, port number and initialization parameters
     */
    private static class BenchConfiguration {

        // Attributes
        String hostName;
        int portNumb;
        int nEntities;

        // Constructor
        public BenchConfiguration(String hostName, int portNumb, int nEntities) {
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
