package pt.ua.sd.RopeGame.shared_mem.RepoSide;


import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

public class RepoServer {

    public static void main(String[] args){


        // Get host name and port number of configuration server
        if (args.length < 2) {
            System.out.println("Usage: java -jar reposite.jar <configuration host name> <configuration port number>");
            System.exit(0);
        }


        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        // Get ref site configuration
        RepoConfiguration conf = getRepoConfiguration(configurationServerHostname, configurationServerPortnum);


        MGeneralInfoRepo repoSite;                        // Referee site
        RepoInterface refSiteInterface;       // Interface to Referee Site
        ServerComm scon, sconi;            // Communication channels
        RepoProxy cliProxy;         // Agent service provider thread

        // Establish the service
 /*       scon = new ServerComChannel(conf.portNumb); // Creation of the listening channel
        scon.start();
        repoSite = new MGeneralInfoRepo();                                    // Activation of the service
        refSiteInterface = new RepoInterface(refereeSite, conf.nEntities);  // Activation of the service interface

        System.out.println("Referee Site: The service was established");
        System.out.println("Referee Site: The server is listening");

        // Requests processing
        while (true) {
            sconi = scon.accept();                                    // Listening
            cliProxy = new RepoProxy(sconi, refSiteInterface);     // Agent service provider
            cliProxy.start();
        }*/
    }


    private static RepoConfiguration getRepoConfiguration(String configServer,int configPort){


        // Instatiate a communication socket
        ClientComm con = new ClientComm (configServer, configPort);

        // In and out message
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        // Open connection
        con.open();

        // Define out message
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETREP);

        // Send message
        con.writeObject(outMessage);

        // Get answer
        inMessage = (ConfigurationMessage) con.readObject();

        // Validate answer
        if ((inMessage.getMsgType() != ConfigurationMessage.GETREP_ANSWER)) {
            System.out.println("Invalid message type at " + RepoServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        // Close connection
        con.close();


        RepoConfiguration conf = new RepoConfiguration(inMessage.getHostName(),inMessage.getPortNumb());
        return conf;
    }
}
