package hearthstone.exception;

/**
 * Exception levée quand une carte ayant une mauvaise classe est utilisée
 * @author lanoix-a remm-jf
 * @version 1.0
 */
public class CarteMauvaiseClasseException extends HearthstoneException {

    public CarteMauvaiseClasseException(String message) {
        super(message);
    }
}
