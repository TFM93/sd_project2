package pt.ua.sd.RopeGame.shared_mem.ConfigSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;
import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;

/**
 * Interface to the Configuration Server
 */
class ConfigInterface {
    /**
     * Configuration
     * @serialField configuration
     */
    private final Configuration configuration;

    /**
     * Configuration interface constructor method
     * @param configuration configuration
     */
    ConfigInterface(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * Process and reply the incoming messages
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    public Message processAndReply (Message inMessage) throws MessageExcept {

        ConfigurationMessage outMessage = null;

        /*  Configuration Server receives messages from the repository, coaches, contestants and the referee  */
        /*  validate incoming messages  */
        switch (inMessage.getMsgType()) {
            case ConfigurationMessage.GETREP:
                break;
            case ConfigurationMessage.GETBENCH:
                break;
            case ConfigurationMessage.GETPLAYGROUND:
                break;
            case ConfigurationMessage.GETREF_SITE:
                break;
            case ConfigurationMessage.GETREP_DOM:
                break;
            case ConfigurationMessage.GETBENCH_DOM:
                break;
            case ConfigurationMessage.GETPLAYG_DOM:
                break;
            case ConfigurationMessage.GETREF_SITE_DOM:
                break;
            case ConfigurationMessage.GET_NPLAYERS:
                break;
            case ConfigurationMessage.GET_NPLAYERS_PUSHING:
                break;
            case ConfigurationMessage.GET_KNOCK_DIF:
                break;
            case ConfigurationMessage.GET_NTRIALS:
                break;
            case ConfigurationMessage.GET_NGAMES:
                break;
            case ConfigurationMessage.GET_LOG_FILE_NAME:
                break;
            default:
                throw new MessageExcept("Invalid type!", inMessage);
        }

        /*  process and reply to the messages  */
        switch (inMessage.getMsgType()) {
            case ConfigurationMessage.GETREP:
                String hostname = configuration.getRepositoryHostName();
                int portnum = configuration.getRepositoryPortNum();

                int nplayers = configuration.getnPlayers();
                int nplayers_pushing = configuration.getnPlayersPushing();
                int ntrials = configuration.getnTrials();
                int ngames = configuration.getnGames();
                int knock_dif = configuration.getKnockDif();
                String logFileName = configuration.getLogFileName();
                outMessage = new ConfigurationMessage(ConfigurationMessage.GETREP_ANSWER, hostname, portnum,
                        nplayers, nplayers_pushing, ntrials, ngames,knock_dif, logFileName);
                break;
            case ConfigurationMessage.GETBENCH:
                hostname = configuration.getBenchHostName();
                portnum = configuration.getBenchPortNum();
                outMessage = new ConfigurationMessage(ConfigurationMessage.GETBENCH_ANSWER, hostname, portnum);
                break;
            case ConfigurationMessage.GETPLAYGROUND:
                hostname = configuration.getPlayGHostName();
                portnum = configuration.getPlayGPortNum();
                outMessage = new ConfigurationMessage(ConfigurationMessage.GETPLAYGROUND_ANSWER, hostname, portnum);
                break;
            case ConfigurationMessage.GETREF_SITE:
                hostname = configuration.getRefSiteHostName();
                portnum = configuration.getRefSitePortNum();
                outMessage = new ConfigurationMessage(ConfigurationMessage.GETREF_SITE_ANSWER, hostname, portnum);
                break;
            case ConfigurationMessage.GETREP_DOM:
                hostname = configuration.getRepositoryHostName();
                portnum = configuration.getRepositoryPortNum();
                outMessage = new ConfigurationMessage(ConfigurationMessage.GETREP_ANS_DOM, hostname, portnum);
                break;
            case ConfigurationMessage.GET_KNOCK_DIF:
                outMessage = new ConfigurationMessage(ConfigurationMessage.GET_KNOCK_DIF_ANS,configuration.getKnockDif());
                break;
            case ConfigurationMessage.GET_LOG_FILE_NAME:
                outMessage = new ConfigurationMessage(ConfigurationMessage.GET_LOG_FILE_NAME_ANS, configuration.getLogFileName());
                break;
            case ConfigurationMessage.GET_NGAMES:
                outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NGAMES_ANS, configuration.getnGames());
                break;
            case ConfigurationMessage.GET_NTRIALS:
                outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NTRIALS_ANS, configuration.getnTrials());
                break;
            case ConfigurationMessage.GET_NPLAYERS_PUSHING:
                outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS_PUSHING_ANSWER, configuration.getnPlayersPushing());
                break;
            case ConfigurationMessage.GET_NPLAYERS:
                outMessage=new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS_ANS,configuration.getnPlayers());
        }

        return outMessage;
    }
}
