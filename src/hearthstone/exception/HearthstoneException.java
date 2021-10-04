package hearthstone.exception;

/**
 *
 * Exception levée dans le cadre du projet
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public abstract class HearthstoneException extends Exception {
    HearthstoneException(String message) {
        super(message);
    }
}
