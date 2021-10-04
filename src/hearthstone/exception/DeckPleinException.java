package hearthstone.exception;

/**
 * Exception levée quand le deck est plein
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class DeckPleinException extends HearthstoneException{

    public DeckPleinException(String message) {
        super(message);
    }
}
