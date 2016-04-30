package pt.ua.sd.RopeGame.interfaces;

import pt.ua.sd.RopeGame.structures.TrialStat;


public interface IPlaygroundReferee {

    TrialStat assertTrialDecision(int n_players_pushing, int knockDif);

}
