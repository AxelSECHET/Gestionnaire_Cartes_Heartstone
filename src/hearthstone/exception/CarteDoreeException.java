package hearthstone.exception;

/**
 * Exception levée quand on rencontre une carte dorée mais que ce n'est pas possible
 * @author lanoix-a remm-jf
 * @version 1.0
 */
public class CarteDoreeException extends HearthstoneException {

    public CarteDoreeException(String s) {
        super(s);
    }
}
