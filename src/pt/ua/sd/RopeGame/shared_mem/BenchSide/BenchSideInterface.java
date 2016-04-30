package pt.ua.sd.RopeGame.shared_mem.BenchSide;

import pt.ua.sd.RopeGame.comInfo.*;


/**
 * Interface to the Bench
 */
class BenchSideInterface {

    /**
     * Bench
     * @serialField Bench
     */
    private final MContestantsBench bench;

    /**
     * Number of TERMINATE messages received
     * @serialField n_terminates
     */
    private int n_terminates;

    /**
     * Number of TERMINATE messages left
     * @serialField n_terminates_to_end
     */
    private final int n_terminates_to_end;

    /**
     * Bench Interface constructor method
     * @param bench Central Information Repository
     * @param n_terminates_to_end Number of TERMINATE messages left to end
     */
     BenchSideInterface(MContestantsBench bench, int n_terminates_to_end) {
        this.bench = bench;
        this.n_terminates = 0;
        this.n_terminates_to_end = n_terminates_to_end;
    }

    /**
     * Process and reply the incoming messages
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    public Message processAndReply (Message inMessage) throws MessageExcept {

        Message outMessage;

        /*  Bench receives messages from coaches, contestants and the referee  */
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
     * Process and reply the incoming messages from the coaches
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    private Message processAndReplyCoachMessage(CoachBenchMessage inMessage) throws MessageExcept {

        CoachBenchMessage outMessage = null;

        /*  validate incoming messages  */
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

        /*  process and reply to the messages  */
        switch (inMessage.getMsgType()) {
            case CoachBenchMessage.CALLCONTESTANTS:
                System.out.println("Call contestants message received");
                boolean match_not_ended = bench.callContestants(inMessage.getTeam_id(),
                        inMessage.getSelected_contestants(), inMessage.getN_players());
                System.out.println("sending match not ended flag with value " + match_not_ended);
                outMessage = new CoachBenchMessage(CoachBenchMessage.CALLCONTESTANTS_ANS, match_not_ended);
                break;
            case CoachBenchMessage.INFORMREF:
                System.out.println("Inform referee message received");
                bench.informReferee();
                outMessage = new CoachBenchMessage(CoachBenchMessage.INFORMREF_ANS);
                break;
            case CoachBenchMessage.TERMINATE:
                n_terminates++;
                if (n_terminates == n_terminates_to_end) {
                    System.out.println("Bench terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }


    /**
     * Process and reply the incoming messages from the contestants
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    private Message processAndReplyContestantMessage(ContestantBenchMessage inMessage) throws MessageExcept {

        ContestantBenchMessage outMessage = null;

        /*  validate incoming messages  */
        switch (inMessage.getMsgType()) {
            case ContestantBenchMessage.FOLLOW_COACH_ADVICE:
                System.out.println("follow coach advice received");
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
                System.out.println("get ready received");
                if (inMessage.getN_players_pushing() < 0 && inMessage.getN_players_pushing()>inMessage.getN_players()) {
                    throw new MessageExcept ("Invalid number of players pushing! " + inMessage.getN_players_pushing(), inMessage);
                }
                break;
            case ContestantBenchMessage.TERMINATE:
                System.out.println("terminate received");
                break;
            default:
                throw new MessageExcept ("Invalid type!", inMessage);
        }

        /*  process and reply to the messages  */
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
                n_terminates++;
                if (n_terminates == n_terminates_to_end) {
                    System.out.println("Bench terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }




    /**
     * Process and reply the incoming messages from the referee
     * @param inMessage Incoming message
     * @return Outgoing message
     * @throws MessageExcept Exception that shows that the incoming message is not valid
     */
    private Message processAndReplyRefereeMessage(RefereeBenchMessage inMessage) throws MessageExcept {

        RefereeBenchMessage outMessage = null;

        /*  validate incoming messages  */
        switch (inMessage.getMsgType()) {
            case RefereeBenchMessage.CALLTR:
                System.out.println("call trial received");
                break;
            case RefereeBenchMessage.STARTTR:
                System.out.println("start trial received");
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
                throw new MessageExcept ("Invalid type! " + inMessage.getMsgType(), inMessage);
        }

        /*  process and reply to the messages  */
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
                n_terminates++;
                if (n_terminates == n_terminates_to_end) {
                    System.out.println("Bench terminated!");
                    System.exit(0);
                }
                break;
        }

        return outMessage;
    }


}
