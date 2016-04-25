package pt.ua.sd.RopeGame.shared_mem.BenchSide;

import pt.ua.sd.RopeGame.comInfo.*;

/**
 * Created by ivosilva on 25/04/16.
 */
public class BenchSideInterface {

    /**
     * Referee Site
     * @serialField referee site
     */
    private final MContestantsBench bench;

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
     * @param bench ref_site
     * @param nTerminateMessagesToEnd number of terminate messages to end
     */
    public BenchSideInterface(MContestantsBench bench, int nTerminateMessagesToEnd) {
        this.bench = bench;
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

        if (inMessage instanceof CoachBenchMessage) {
            outMessage = processAndReplyCoachMessage((CoachBenchMessage) inMessage);
        }
        else if (inMessage instanceof ContestantBenchMessage) {
            outMessage = processAndReplyContestantMessage((ContestantBenchMessage) inMessage);
        }
        else if (inMessage instanceof RefereeBenchMessage) {
            outMessage = processAndReplyRefereeMessage((RefereeBenchMessage) inMessage);
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
    private Message processAndReplyCoachMessage(CoachBenchMessage inMessage) throws MessageExcept {

        CoachBenchMessage outMessage = null;

        // Validate received message
        switch (inMessage.getMsgType()) {
            case CoachBenchMessage.CALLCONTESTANTS:
                if (inMessage.getN_players() < 0) {
                    throw new MessageExcept ("Invalid number of players!", inMessage);
                }
                if (inMessage.getTeam_id() < 0) {
                    throw new MessageExcept ("Invalid team id!", inMessage);
                }
                if (inMessage.getSelected_contestants() == null) {
                    throw new MessageExcept ("Invalid selected contestants array!", inMessage);
                }
                break;
            case CoachBenchMessage.INFORMREF:
                break;
            case CoachBenchMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        // Processing
        switch (inMessage.getMsgType()) {
            case CoachBenchMessage.CALLCONTESTANTS:
                boolean match_not_ended = bench.callContestants(inMessage.getTeam_id(),
                        inMessage.getSelected_contestants(), inMessage.getN_players());
                outMessage = new CoachBenchMessage(CoachBenchMessage.CALLCONTESTANTS_ANS, match_not_ended);
                break;
            case CoachBenchMessage.INFORMREF:
                bench.informReferee();
                outMessage = new CoachBenchMessage(CoachBenchMessage.INFORMREF_ANS);
                break;
            case CoachBenchMessage.TERMINATE:
                nTerminateMessages++;
                if (nTerminateMessages == nTerminateMessagesToEnd) {
                    System.out.println("Bench terminated!");
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
    private Message processAndReplyContestantMessage(ContestantBenchMessage inMessage) throws MessageExcept {

        ContestantBenchMessage outMessage = null;

        // Validate received message
        switch (inMessage.getMsgType()) {
            case ContestantBenchMessage.FOLLOW_COACH_ADVICE:
                if (inMessage.getTeam_id() < 0) {
                    throw new MessageExcept ("Invalid team id!", inMessage);
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
            case ContestantBenchMessage.GETREADY:
                if (inMessage.getN_players_pushing() < 0) {
                    throw new MessageExcept ("Invalid number of players pushing!", inMessage);
                }
                break;
            case ContestantBenchMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        // Processing
        switch (inMessage.getMsgType()) {
            case ContestantBenchMessage.FOLLOW_COACH_ADVICE:
                boolean[] followed_advice = bench.followCoachAdvice(inMessage.getContestant_id(), inMessage.getStrength(), inMessage.getTeam_id(),
                        inMessage.getN_players(), inMessage.getN_players_pushing());
                outMessage = new ContestantBenchMessage(ContestantBenchMessage.FOLLOW_COACH_ADVICE_ANS, followed_advice);
                break;
            case ContestantBenchMessage.GETREADY:
                bench.getReady(inMessage.getN_players_pushing());
                outMessage = new ContestantBenchMessage(ContestantBenchMessage.GETREADY_ANS);
                break;
            case ContestantBenchMessage.TERMINATE:
                nTerminateMessages++;
                if (nTerminateMessages == nTerminateMessagesToEnd) {
                    System.out.println("Bench terminated!");
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
    private Message processAndReplyRefereeMessage(RefereeBenchMessage inMessage) throws MessageExcept {

        RefereeBenchMessage outMessage = null;

        // Validate received message
        switch (inMessage.getMsgType()) {
            case RefereeBenchMessage.CALLTR:
                break;
            case RefereeBenchMessage.STARTTR:
                break;
            case RefereeBenchMessage.DECLAREMATCHWIN:
                if(inMessage.getGames1() < 0){
                    throw new MessageExcept ("Invalid games 1!", inMessage);
                }
                if(inMessage.getGames2() < 0){
                    throw new MessageExcept ("Invalid games 2!", inMessage);
                }
                break;
            case RefereeBenchMessage.TERMINATE:
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        // Processing
        switch (inMessage.getMsgType()) {
            case RefereeBenchMessage.CALLTR:
                bench.callTrial();
                outMessage = new RefereeBenchMessage(RefereeBenchMessage.CALLTR_ANS);
                break;
            case RefereeBenchMessage.STARTTR:
                bench.startTrial();
                outMessage = new RefereeBenchMessage(RefereeBenchMessage.STARTTR_ANS);
                break;
            case RefereeBenchMessage.DECLAREMATCHWIN:
                int winner = bench.declareMatchWinner(inMessage.getGames1(), inMessage.getGames2());
                outMessage = new RefereeBenchMessage(RefereeBenchMessage.DECLAREMATCHWIN_ANS, winner);
                break;
            case RefereeBenchMessage.TERMINATE:
                nTerminateMessages++;
                if (nTerminateMessages == nTerminateMessagesToEnd) {
                    System.out.println("Bench terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }


}
