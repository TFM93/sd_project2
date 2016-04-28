package pt.ua.sd.RopeGame.shared_mem.PlaygroundSide;

import pt.ua.sd.RopeGame.comInfo.*;
import pt.ua.sd.RopeGame.structures.TrialStat;

/**
 * Created by ivosilva on 25/04/16.
 */
public class PlaygroundSideInterface {

    /**
     * Referee Site
     * @serialField referee site
     */
    private final MPlayground playground;

    /**
     * Number of terminated messages received
     * @serialField nTerminateMessages
     */
    private int nTerminateMessages;

    /**
     * Number of terminated messages to end
     * @serialField nTerminateMessagesToEnd
     */
    private final int nTerminateMessagesToEnd;

    /**
     * Instantiation of Referee site interface
     * @param playground ref_site
     * @param nTerminateMessagesToEnd number of terminate messages to end
     */
    public PlaygroundSideInterface(MPlayground playground, int nTerminateMessagesToEnd) {
        this.playground = playground;
        this.nTerminateMessages = 0;
        this.nTerminateMessagesToEnd = nTerminateMessagesToEnd;
    }

    /**
     * Processing of messages executing its task and generating an answer message.
     * @param inMessage message with the request
     * @return answer message
     * @throws MessageExcept if the message with the request is considered invalid
     */
    public Message processAndReply (Message inMessage) throws MessageExcept {

        Message outMessage = null;

        if (inMessage instanceof CoachPlaygroundMessage) {
            outMessage = processAndReplyCoachMessage((CoachPlaygroundMessage) inMessage);
        }
        else if (inMessage instanceof ContestantPlaygroundMessage) {
            outMessage = processAndReplyContestantMessage((ContestantPlaygroundMessage) inMessage);
        }
        else if (inMessage instanceof RefereePlaygroundMessage) {
            outMessage = processAndReplyRefereeMessage((RefereePlaygroundMessage) inMessage);
        }
        else {
            throw new MessageExcept ("Invalid message instance.", inMessage);
        }

        return outMessage;
    }

    /**
     * Processing of messages from the referee executing its task and generating an answer message.
     * @param inMessage message with the request
     * @return answer message
     * @throws MessageExcept if the message with the request is considered invalid
     */
    private Message processAndReplyCoachMessage(CoachPlaygroundMessage inMessage) throws MessageExcept {

        CoachPlaygroundMessage outMessage = null;

        // Validate received message
        switch (inMessage.getMsgType()) {
            case CoachPlaygroundMessage.REVIEWNOTES:
                if (inMessage.getN_players() < 0) {
                    throw new MessageExcept ("Invalid number of players!", inMessage);
                }
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                if (inMessage.getSelected_contestants() == null) {
                    throw new MessageExcept ("Invalid selected contestants array!", inMessage);
                }
                break;
            case CoachPlaygroundMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        // Processing
        switch (inMessage.getMsgType()) {
            case CoachPlaygroundMessage.REVIEWNOTES:
                int[] chosen_players = playground.reviewNotes(inMessage.getSelected_contestants(),
                        inMessage.getN_players(),
                        inMessage.getN_players_pushing());
                outMessage = new CoachPlaygroundMessage(CoachPlaygroundMessage.REVIEWNOTES_ANS, chosen_players);
                break;
            case CoachPlaygroundMessage.TERMINATE:
                nTerminateMessages++;
                if (nTerminateMessages == nTerminateMessagesToEnd) {
                    System.out.println("Playground terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }


    /**
     * Processing of messages from the referee executing its task and generating an answer message.
     * @param inMessage message with the request
     * @return answer message
     * @throws MessageExcept if the message with the request is considered invalid
     */
    private Message processAndReplyContestantMessage(ContestantPlaygroundMessage inMessage) throws MessageExcept {

        ContestantPlaygroundMessage outMessage = null;

        //int team_id, int strenght, int contestant_id, int n_players_pushing, int n_players

        // Validate received message
        switch (inMessage.getMsgType()) {
            case ContestantPlaygroundMessage.PULLROPE:
                if (inMessage.getTeam_id() < 0) {
                    throw new MessageExcept ("Invalid team id! on pull the rope. team id="+inMessage.getTeam_id(), inMessage);
                }
                if (inMessage.getStrength() < 0) {
                    throw new MessageExcept ("Invalid strength!", inMessage);
                }
                if (inMessage.getContestant_id() < 0) {
                    throw new MessageExcept ("Invalid contestant id!", inMessage);
                }
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                if (inMessage.getN_players() < 0) {
                    throw new MessageExcept ("Invalid number of players!", inMessage);
                }
                break;
            case ContestantPlaygroundMessage.AMDONE:
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                break;
            case ContestantPlaygroundMessage.SEATDOWN:
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                break;
            case ContestantPlaygroundMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        // Processing
        switch (inMessage.getMsgType()) {
            case ContestantPlaygroundMessage.PULLROPE:
                playground.pullTheRope(inMessage.getTeam_id(), inMessage.getStrength(),
                        inMessage.getContestant_id(), inMessage.getN_players_pushing(), inMessage.getN_players());
                outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.PULLROPE_ANS);
                break;
            case ContestantPlaygroundMessage.AMDONE:
                playground.iAmDone(inMessage.getN_players_pushing());
                outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.AMDONE_ANS);
                break;
            case ContestantPlaygroundMessage.SEATDOWN:
                playground.seatDown(inMessage.getN_players_pushing());
                outMessage = new ContestantPlaygroundMessage(ContestantPlaygroundMessage.SEATDOWN_ANS);
                break;
            case ContestantPlaygroundMessage.TERMINATE:
                nTerminateMessages++;
                if (nTerminateMessages == nTerminateMessagesToEnd) {
                    System.out.println("Playground terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }




    /**
     * Processing of messages from the referee executing its task and generating an answer message.
     * @param inMessage message with the request
     * @return answer message
     * @throws MessageExcept if the message with the request is considered invalid
     */
    private Message processAndReplyRefereeMessage(RefereePlaygroundMessage inMessage) throws MessageExcept {

        RefereePlaygroundMessage outMessage = null;

        // Validate received message
        switch (inMessage.getMsgType()) {
            case RefereePlaygroundMessage.ATD:
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                if (inMessage.getKnockDif() < 0) {
                    throw new MessageExcept ("Invalid knockout difference!", inMessage);
                }
                break;
            case RefereePlaygroundMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        // Processing
        switch (inMessage.getMsgType()) {
            case RefereePlaygroundMessage.ATD:
                TrialStat trialStat = playground.assertTrialDecision(inMessage.getN_players_pushing(),
                        inMessage.getKnockDif());
                outMessage = new RefereePlaygroundMessage(RefereePlaygroundMessage.ATD_ANS, trialStat);
                break;
            case RefereePlaygroundMessage.TERMINATE:
                nTerminateMessages++;
                if (nTerminateMessages == nTerminateMessagesToEnd) {
                    System.out.println("Referee site terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }

}
