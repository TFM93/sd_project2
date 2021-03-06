package pt.ua.sd.RopeGame.shared_mem.RefereeSiteSide;

import pt.ua.sd.RopeGame.enums.WonType;
import pt.ua.sd.RopeGame.interfaces.IRefereeSiteCoach;
import pt.ua.sd.RopeGame.interfaces.IRefereeSiteContestant;
import pt.ua.sd.RopeGame.interfaces.IRefereeSiteReferee;
import pt.ua.sd.RopeGame.structures.GameStat;

/**
 * Refereee Site shared memory<br>
 *
 *    This class represents the monitor/shared memory of the referee site.
 *
 * @author Ivo Silva (<a href="mailto:ivosilva@ua.pt">ivosilva@ua.pt</a>)
 * @author Tiago Magalhaes (<a href="mailto:tiagoferreiramagalhaes@ua.pt">tiagoferreiramagalhaes@ua.pt</a>)
 *
 */
 class MRefereeSite implements IRefereeSiteCoach, IRefereeSiteReferee, IRefereeSiteContestant{


    /**
     *
     * @return  the number of games played
     */
    public int getN_games_played() {
        return n_games_played;
    }

    /**
     * Internal Data
     */
    //private static int n_games=3;
    private static int n_games_played=0;


    /**
     * The referee announce a new game
     */
    public synchronized void announceNewGame() {


    }

    /**
     * The number of played games is increased and the game winner is decided
     * @return GameStat data with the info of the winner
     */
    public synchronized GameStat declareGameWinner(int score_T1, int score_T2, int knock_out, int n_games) {
        n_games_played +=1;//increase number of games played
        if(knock_out== 1 )
        {
            return new GameStat((n_games_played<n_games),knock_out, WonType.KNOCKOUT.ordinal());
        }
        else if(knock_out == 2){

            return new GameStat((n_games_played<n_games),knock_out, WonType.KNOCKOUT.ordinal());
        }
        else if(score_T1>score_T2)
        {

            return new GameStat((n_games_played<n_games),1,WonType.POINTS.ordinal());
        }
        else if(score_T1<score_T2){

            return new GameStat((n_games_played<n_games),2,WonType.POINTS.ordinal());
        }

        return new GameStat((n_games_played<n_games),0,WonType.DRAW.ordinal());




    }

}
