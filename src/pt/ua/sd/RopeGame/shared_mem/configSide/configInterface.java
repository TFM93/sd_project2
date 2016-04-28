package pt.ua.sd.RopeGame.shared_mem.ConfigSide;

import pt.ua.sd.RopeGame.comInfo.ConfigurationMessage;
import pt.ua.sd.RopeGame.comInfo.Message;
import pt.ua.sd.RopeGame.comInfo.MessageExcept;
import pt.ua.sd.RopeGame.shared_mem.ConfigSide.Configuration;

/**
 * Created by tiagomagalhaes on 25/04/16.
 */
public class ConfigInterface {
    /**
     * Repository (it is the service provided)
     * @serialField configuration
     */
    private final Configuration configuration;

    /**
     * Instantiation of Configuration interface.
     * @param configuration configuration
     */
    public ConfigInterface(Configuration configuration) {
        this.configuration = configuration;
    }

    public int getTest(){
        return configuration.getnPlayersPushing();
    }

    /**
     * Processing of messages executing its task and generating an answer message.
     * @param inMessage message with the request
     * @return answer message
     * @throws MessageExcept if the message with the request is considered invalid
     */
    public Message processAndReply (Message inMessage) throws MessageExcept {

        ConfigurationMessage outMessage = null;

        // Validate received message
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

        // Processing
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
                System.out.println("NPLAYERS PUSHING " + configuration.getnPlayersPushing());
                outMessage = new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS_PUSHING_ANSWER, configuration.getnPlayersPushing());
                break;
            case ConfigurationMessage.GET_NPLAYERS:
                outMessage=new ConfigurationMessage(ConfigurationMessage.GET_NPLAYERS_ANS,configuration.getnPlayers());
        }

        return outMessage;
    }
}
