package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Playground Server
 *
 * Starts the Playground server to listen for incoming messages
 *
 */
public class PlaygroundServer {

    public static void main(String[] args) {

        /*  fetch information from configuration server  */
        if (args.length < 2) {
            System.out.println("Usage: java -jar playground.jar <host name> <port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        /*  get playground configuration  */
        PlaygroundConfiguration conf = getPlaygroundConfiguration(configurationServerHostname, configurationServerPortnum);

        MPlayground playground;
        PlaygroundSideInterface playgroundInterface;
        ServerComm scon, sconi;
        PlaygroundClientProxy cliProxy;

        scon = new ServerComm(conf.portNumb);
        scon.start();
        playground = new MPlayground();
        playgroundInterface = new PlaygroundSideInterface(playground, conf.nEntities);

        System.out.println("Playground: The service was established");
        System.out.println("Playground: The server is listening");

        /*  process requests  */
        while (true) {
            sconi = scon.accept();
            cliProxy = new PlaygroundClientProxy(sconi, playgroundInterface);
            cliProxy.start();
        }
    }

    /**
     * Get Playground configuration.
     * @return playground configuration
     */
    private static PlaygroundConfiguration getPlaygroundConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message to get repository configuration  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETPLAYGROUND);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETPLAYGROUND_ANSWER)) {
            System.out.println("Invalid message type at " + PlaygroundServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  return the configuration  */
        return new PlaygroundConfiguration(inMessage.getHostName(), inMessage.getPortNumb(),
                inMessage.getArg1());
    }

}
