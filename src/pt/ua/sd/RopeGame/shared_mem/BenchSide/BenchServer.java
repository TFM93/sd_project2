package pt.ua.sd.RopeGame.shared_mem.BenchSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;

/**
 * Bench Server
 *
 * Starts the Bench server to listen for incoming messages
 *
 */

public class BenchServer {

    public static void main(String[] args) {

        /*  fetch information from configuration server  */
        if (args.length < 2) {
            System.out.println("Usage: java -jar bench.jar <host name> <port number>");
            System.exit(0);
        }

        String configurationServerHostname = args[0];
        int configurationServerPortnum = Integer.parseInt(args[1]);

        /*  get bench configuration  */
        BenchConfiguration conf = getBenchConfiguration(configurationServerHostname, configurationServerPortnum);
        MContestantsBench bench;
        BenchSideInterface benchInterface;
        ServerComm scon, sconi;
        BenchClientProxy cliProxy;

        scon = new ServerComm(conf.getPortNumb());
        scon.start();
        bench = new MContestantsBench();
        benchInterface = new BenchSideInterface(bench, conf.getN_terminates_to_receive());

        System.out.println("Contestants Bench: The service was established");
        System.out.println("Contestants Bench: The server is listening");

        /*  process requests  */
        while (true) {
            sconi = scon.accept();
            cliProxy = new BenchClientProxy(sconi, benchInterface);
            cliProxy.start();
        }
    }

    /**
     * Get Bench configuration
     * @param configurationServerHostname Configuration server host name
     * @param configurationServerPortnum Configuration server port
     * @return Repository configuration
     */
    private static BenchConfiguration getBenchConfiguration(String configurationServerHostname, int configurationServerPortnum) {

        /*  create communication socket  */
        ClientComm con = new ClientComm (configurationServerHostname, configurationServerPortnum);

        /*  instantiate the configuration messages  */
        ConfigurationMessage inMessage;
        ConfigurationMessage outMessage;

        /*  open connection  */
        con.open();

        /*  send message to get repository configuration  */
        outMessage = new ConfigurationMessage(ConfigurationMessage.GETBENCH);
        con.writeObject(outMessage);

        /*  get and validate response message  */
        inMessage = (ConfigurationMessage) con.readObject();
        if ((inMessage.getMsgType() != ConfigurationMessage.GETBENCH_ANSWER)) {
            System.out.println("Invalid message type at " + BenchServer.class.getName());
            System.out.println(inMessage.toString());
            System.exit(1);
        }

        /*  close the connection  */
        con.close();

        /*  return the configuration  */
        return new BenchConfiguration(inMessage.getHostName(), inMessage.getPortNumb());
    }


}
