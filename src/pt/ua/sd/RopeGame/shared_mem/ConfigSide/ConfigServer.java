package pt.ua.sd.RopeGame.shared_mem.ConfigSide;

import java.io.File;
import java.io.IOException;

/**
 * Playground Server
 *
 * Starts the Configuration server to listen for incoming messages
 *
 */
public class ConfigServer {

    public static void main(String[] args){
        /*  Get port number and configuration file path for Configuration Server  */
        if (args.length < 2) {
            System.out.println("Usage: configuration.jar <port number> <configuration file>");
            System.exit(0);
        }

        int portNumb = Integer.parseInt(args[0]);
        String confFilePath = args[1];

        Configuration configuration = null;
        ConfigInterface confInterface;
        ServerComChannel scon, sconi;
        ConfigProxy cliProxy;

        File configurationFile = new File(confFilePath);

        /*  establish the service  */
        scon = new ServerComChannel(portNumb);
        scon.start();
        try {
            configuration = new Configuration(configurationFile);
        } catch (IOException ex) {
            System.out.println("There was a problem reading the configuration file: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(0);
        }
        confInterface = new ConfigInterface(configuration);

        System.out.println("Configuration: The service was established");
        System.out.println("Configuration: The server is listening");

        /*  process requests  */
        while (true) {
            sconi = scon.accept();                                              // Listening
            cliProxy = new ConfigProxy(sconi, confInterface);      // Agent service provider
            cliProxy.start();
        }

    }
}
