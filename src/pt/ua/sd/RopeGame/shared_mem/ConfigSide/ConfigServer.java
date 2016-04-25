package pt.ua.sd.RopeGame.shared_mem.ConfigSide;

import java.io.File;
import java.io.IOException;

public class ConfigServer {

    public static void main(String[] args){
        // Get port number and configuration file path for Configuration Server
        if (args.length < 2) {
            System.out.println("Usage: configuration.jar <port number> <configuration file>");
            System.exit(0);
        }

        int portNumb = Integer.parseInt(args[0]);
        String confFilePath = args[1];

        Configuration configuration = null;                     // Configuration (it is service provided)
        ConfigInterface confInterface;               // Interface to Configuration
        ServerComChannel scon, sconi;                                  // Communication channels
        ConfigProxy cliProxy;                      // Agent service provider thread

        File configurationFile = new File(confFilePath);

        // Establish the service
        scon = new ServerComChannel(portNumb);                                         // Creation of the listening channel
        scon.start();
        try {
            configuration = new Configuration(configurationFile);               // Activation of the service
        } catch (IOException ex) {
            System.out.println("There was a problem reading the configuration file: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(0);
        }
        confInterface = new ConfigInterface(configuration);          // Activation of the service interface

        System.out.println("Configuration: The service was established");
        System.out.println("Configuration: The server is listening");

        // Requests processing
        while (true) {
            sconi = scon.accept();                                              // Listening
            cliProxy = new ConfigProxy(sconi, confInterface);      // Agent service provider
            cliProxy.start();
        }

    }
}
