package hearthstone.exception;

/**
 *
 * Exception levée quand une limite de nombre de cartes est atteinte
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class LimiteNombreDeCartesException extends HearthstoneException {

    public LimiteNombreDeCartesException(String message) {
        super(message);
    }
}
