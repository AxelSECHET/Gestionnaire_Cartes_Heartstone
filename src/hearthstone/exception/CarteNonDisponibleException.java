package hearthstone.exception;

/**
 * Exception levée quand la carte recherchée n'est pas présente
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class CarteNonDisponibleException extends HearthstoneException {
    public CarteNonDisponibleException(String message) {
        super(message);
    }
}
