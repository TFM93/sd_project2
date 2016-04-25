package pt.ua.sd.RopeGame.shared_mem.RepoSide;import pt.ua.sd.RopeGame.comInfo.*;public class RepoInterface {    /**     * central repository     * @serialField repo     */    private final MGeneralInfoRepo repo;    /**     * Number of terminated messages received     * @serialField nTerminateMessages     */    private int nTerminateMessages;    /**     * Number of terminated messages to end     * @serialField nTerminateMessagesToEnd     */    private final int nTerminateMessagesToEnd;    /**     * Instantiation of central repository site interface     * @param repo central repo     * @param nTerminateMessagesToEnd number of terminate messages to end     */    public RepoInterface(MGeneralInfoRepo repo, int nTerminateMessagesToEnd) {        this.repo = repo;        this.nTerminateMessages = 0;        this.nTerminateMessagesToEnd = nTerminateMessagesToEnd;    }    /**     * Processing of messages executing its task and generating an answer message.     * @param inMessage message with the request     * @return answer message     * @throws MessageExcept if the message with the request is considered invalid     */    public Message processAndReply (Message inMessage) throws MessageExcept {        Message outMessage = null;        /*  only the Referee is able to call methods from the referee site  */        if (inMessage instanceof CoachRepoMessage) {            outMessage = processAndReplyCoachMessage((CoachRepoMessage) inMessage);        }        else if(inMessage instanceof ContestantRepoMessage){            outMessage = processAndReplyContestantMessage((ContestantRepoMessage) inMessage) ;        }        else if(inMessage instanceof RefereeRepoMessage){            outMessage = processAndReplyRefereeMessage((RefereeRepoMessage) inMessage);        }        else {            throw new MessageExcept ("Invalid message instance.", inMessage);        }        return outMessage;    }    private Message processAndReplyCoachMessage(CoachRepoMessage inMessage) throws MessageExcept {        return null;    }    private Message processAndReplyContestantMessage(ContestantRepoMessage inMessage) throws MessageExcept {        return null;    }    /**     * Processing of messages from the referee executing its task and generating an answer message.     * @param inMessage message with the request     * @return answer message     * @throws MessageExcept if the message with the request is considered invalid     */    private Message processAndReplyRefereeMessage(RefereeRepoMessage inMessage) throws MessageExcept {        RefereeRepoMessage outMessage = null;        // Validate received message        switch (inMessage.getMsgType()) {            case RefereeRepoMessage.PRINTMATCHRESULT:                if(inMessage.getScore1()<0)                    throw new MessageExcept("invalid score for team 1",inMessage);                if(inMessage.getScore2()<0)                    throw new MessageExcept("invalid score for team 2",inMessage);                if(inMessage.getWinner()<0 || inMessage.getWinner()>2)                    throw new MessageExcept("invalid team winner",inMessage);                break;            case RefereeRepoMessage.REFEREELOG:                if(inMessage.getArg1() < 0){                    throw new MessageExcept("invalid current trial number",inMessage);                }                //TODO-check enum type                break;            case RefereeRepoMessage.UPDATEGAMENR:                break;            case RefereeRepoMessage.UPDATEROPECENTER:                break;            default:                throw new MessageExcept ("Invalid type!", inMessage);        }        // Processing        switch (inMessage.getMsgType()) {            case RefereeRepoMessage.PRINTMATCHRESULT:                repo.printMatchResult(inMessage.getWinner(),inMessage.getScore1(),inMessage.getScore2());                outMessage = new RefereeRepoMessage(RefereeRepoMessage.ACK);                break;            case RefereeRepoMessage.REFEREELOG:                repo.refereeLog(inMessage.getArg2(),inMessage.getArg1());                outMessage = new RefereeRepoMessage(RefereeRepoMessage.ACK);                break;            case RefereeRepoMessage.UPDATEGAMENR:                repo.updGame_nr();                outMessage = new RefereeRepoMessage(RefereeRepoMessage.ACK);                break;            case RefereeRepoMessage.UPDATEROPECENTER:                repo.updtRopeCenter(inMessage.getArg1());                break;            case RefereeRepoMessage.TERMINATE:                nTerminateMessages++;                if (nTerminateMessages == nTerminateMessagesToEnd) {                    System.out.println("Referee central repo terminated!");                    System.exit(0);                }                break;        }        return outMessage;    }}