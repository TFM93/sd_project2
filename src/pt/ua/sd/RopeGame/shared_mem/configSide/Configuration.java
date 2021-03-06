package pt.ua.sd.RopeGame.shared_mem.ConfigSide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Defines data requested by the different entities
 *
 */
public class Configuration {

    /**
     * Repository host name
     *
     * @serialField repositoryHostName
     */
    private String repositoryHostName;

    /**
     * Repository port number
     *
     * @serialField repositoryPortNum
     */
    private int repositoryPortNum;

    /**
     * Referee site host name
     *
     * @serialField refSiteHostName
     */
    private String refSiteHostName;

    /**
     * Referee site port number
     *
     * @serialField refSitePortNum
     */
    private int refSitePortNum;

    /**
     * Bench host name
     *
     * @serialField benchHostName
     */
    private String benchHostName;

    /**
     * Bench port number
     *
     * @serialField benchPortNum
     */
    private int benchPortNum;

    /**
     * Playground host name
     *
     * @serialField playGHostName
     */
    private String playGHostName;

    /**
     * referee host name
     *
     * @serialField refHostName
     */
    private String refHostName;
    /**
     * coach host name
     *
     * @serialField coachHostname
     */
    private String coachHostName;
    /**
     * contestant host name
     *
     * @serialField contestantHostName
     */
    private String contestantHostName;

    /**
     * Playground port number
     *
     * @serialField playGPortNum
     */
    private int playGPortNum;

    /**
     * Number of trials
     *
     * @serialField nTrials
     */
    private int nTrials;


    /**
     * Number of players pushing the rope
     *
     * @serialField nPlayersPushing
     */
    private int nPlayersPushing;

    /**
     * Number of games
     *
     * @serialField nGames
     */
    private int nGames;

    /**
     * Knockout differential
     *
     * @serialField knockDif
     */
    private int knockDif;

    /**
     * Number of players
     *
     * @serialField nPlayers
     */
    private int nPlayers;


    /**
     * Log file name
     *
     * @serialField logFileName
     */
    private String logFileName;

    /**
     * Configuration constructor method
     *
     * @param configurationFile configuration file
     * @throws IOException error reading the configuration file
     */
    public Configuration(File configurationFile) throws IOException {
        loadConfiguration(configurationFile);
    }

    /**
     * Loads parameters from configuration file
     *
     * @param configurationFile configuration file
     * @throws IOException error reading the configuration file
     */
    private void loadConfiguration(File configurationFile) throws IOException {

        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(configurationFile);
        properties.load(fis);

        this.repositoryHostName = properties.getProperty("REPO_HOST_NAME");
        this.repositoryPortNum = Integer.parseInt(properties.getProperty("REPO_PORT_NUM"));
        this.logFileName = properties.getProperty("LOG_FILE_NAME");
        this.refSiteHostName = properties.getProperty("REFSITE_HOST_NAME");
        this.refSitePortNum = Integer.parseInt(properties.getProperty("REFSITE_PORT_NUM"));
        this.benchHostName = properties.getProperty("BENCH_HOST_NAME");
        this.benchPortNum = Integer.parseInt(properties.getProperty("BENCH_PORT_NUM"));
        this.playGHostName = properties.getProperty("PLAYG_HOST_NAME");
        this.playGPortNum = Integer.parseInt(properties.getProperty("PLAYG_PORT_NUM"));

        this.refHostName = properties.getProperty("REFEREE_HOST_NAME");
        this.coachHostName = properties.getProperty("COACH_HOST_NAME");
        this.contestantHostName = properties.getProperty("CONTESTANT_HOST_NAME");

        this.nPlayers = Integer.parseInt(properties.getProperty("N_PLAYERS_TEAM"));
        this.nPlayersPushing = Integer.parseInt(properties.getProperty("N_PLAYERS_PUSHING"));
        this.nTrials = Integer.parseInt(properties.getProperty("N_TRIALS"));
        this.nGames = Integer.parseInt(properties.getProperty("N_GAMES"));
        this.knockDif = Integer.parseInt(properties.getProperty("KNOCKOUT_DIF"));
    }

    /**
     * Get repository host name.
     *
     * @return repository host name
     */
    String getRepositoryHostName() {
        return repositoryHostName;
    }

    /**
     * Get repository port number.
     *
     * @return repository port number
     */
     int getRepositoryPortNum() {
        return repositoryPortNum;
    }

    /**
     * Get log file name.
     *
     * @return log file name
     */
     String getLogFileName() {
        return logFileName;
    }


    /**
     * get referee site host name
     * @return host name
     */
     String getRefSiteHostName() {
        return refSiteHostName;
    }

    /**
     * get referee site port number
     * @return port number
     */
     int getRefSitePortNum() {
        return refSitePortNum;
    }

    /**
     * get bench host name
     * @return host name
     */
     String getBenchHostName() {
        return benchHostName;
    }

    /**
     * get bench port number
     * @return port number
     */
     int getBenchPortNum() {
        return benchPortNum;
    }

    /**
     * get playground host name
     * @return host name
     */
     String getPlayGHostName() {
        return playGHostName;
    }

    /**
     * get referee's host name
     * @return host name
     */
    public String getRefHostName() {
        return refHostName;
    }

    /**
     * get coach host name
     * @return host name
     */
    public String getCoachHostName() {
        return coachHostName;
    }

    /**
     * get contestant host name
     * @return host name
     */
    public String getContestantHostName() {
        return contestantHostName;
    }

    /**
     * get playground port number
     * @return port number
     */
     int getPlayGPortNum() {
        return playGPortNum;
    }

    /**
     * get number of trials
     * @return number of trials
     */
     int getnTrials() {
        return nTrials;
    }


    /**
     * get number of players pushing the rope
     * @return number of players pushing the rope
     */
     int getnPlayersPushing() {
        return nPlayersPushing;
    }

    /**
     * get number of total games
     * @return number of games
     */
     int getnGames() {
        return nGames;
    }

    /**
     * get the maximum dealocation of the rope for the knockout
     * @return knockout max dif from de center of rope
     */
     int getKnockDif() {
        return knockDif;
    }

    /**
     * get the number of players
     * @return number of players
     */
     int getnPlayers() {
        return nPlayers;
    }
}
