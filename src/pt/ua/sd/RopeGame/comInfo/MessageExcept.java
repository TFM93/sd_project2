package pt.ua.sd.RopeGame.comInfo;

/**
 * Define uma excepção lançada quando uma mensagem não é válida
 */
public class MessageExcept extends Exception {
    /**
     * Mensagem inválida
     */
    private Message msg;


    /**
     * Instância de mensagem inválida
     * @param errorMsg texto que identifica a condição de erro
     * @param msg mensagem que origina a excepção
     */
    public MessageExcept(String errorMsg, Message msg)
    {
        super(errorMsg);
        this.msg=msg;
    }

    /**
     * obter mensagem invalida
     * @return mensagem inválida
     */
    public Message getMsgVal()
    {
        return msg;
    }
}
