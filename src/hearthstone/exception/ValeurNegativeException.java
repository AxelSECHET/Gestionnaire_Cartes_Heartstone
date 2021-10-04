package hearthstone.exception;

/**
 * Exception levee lorsqu'une valeur negative est utilisee pour initialiser une carte
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class ValeurNegativeException extends HearthstoneException {

    public ValeurNegativeException(String message) {
        super(message);
    }
}