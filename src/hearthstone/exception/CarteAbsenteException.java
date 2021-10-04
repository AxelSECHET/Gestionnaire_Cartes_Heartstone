package hearthstone.exception;


/**
 * Exception levee si une carte qui devrait etre trouvée est absente de la collection considérée
 * @author lanoix-a remm-jf
 * @version 1.0
 */
public class CarteAbsenteException extends HearthstoneException {

    public CarteAbsenteException(String message) {
        super(message);
    }
}