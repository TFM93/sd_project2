package pt.ua.sd.RopeGame.shared_mem.configSide;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Este tipo de dados define a informação requesitada pelas várias entidades nas respectivas inicializações
 *
 * @author Ivo Silva
 * @author Tiago Magalhães
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
     * Knockout, max rope dealocation
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
     * Configuration Instantiation.
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
    public String getRepositoryHostName() {
        return repositoryHostName;
    }

    /**
     * Get repository port number.
     *
     * @return repository port number
     */
    public int getRepositoryPortNum() {
        return repositoryPortNum;
    }

    /**
     * Get log file name.
     *
     * @return log file name
     */
    public String getLogFileName() {
        return logFileName;
    }


    /**
     * get referee site host name
     * @return host name
     */
    public String getRefSiteHostName() {
        return refSiteHostName;
    }

    /**
     * get referee site port number
     * @return port number
     */
    public int getRefSitePortNum() {
        return refSitePortNum;
    }

    /**
     * get bench host name
     * @return host name
     */
    public String getBenchHostName() {
        return benchHostName;
    }

    /**
     * get bench port number
     * @return port number
     */
    public int getBenchPortNum() {
        return benchPortNum;
    }

    /**
     * get playground host name
     * @return host name
     */
    public String getPlayGHostName() {
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
    public int getPlayGPortNum() {
        return playGPortNum;
    }

    /**
     * get number of trials
     * @return number of trials
     */
    public int getnTrials() {
        return nTrials;
    }


    /**
     * get number of players pushing the rope
     * @return number of players pushing the rope
     */
    public int getnPlayersPushing() {
        return nPlayersPushing;
    }

    /**
     * get number of total games
     * @return number of games
     */
    public int getnGames() {
        return nGames;
    }

    /**
     * get the maximum dealocation of the rope for the knockout
     * @return knockout max dif from de center of rope
     */
    public int getKnockDif() {
        return knockDif;
    }

    /**
     * get the number of players
     * @return number of players
     */
    public int getnPlayers() {
        return nPlayers;
    }
}
