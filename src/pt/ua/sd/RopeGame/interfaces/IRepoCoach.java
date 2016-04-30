package pt.ua.sd.RopeGame.interfaces;

import pt.ua.sd.RopeGame.enums.CoachState;


public interface IRepoCoach{
    void coachLog(int team_id, CoachState state);
}
