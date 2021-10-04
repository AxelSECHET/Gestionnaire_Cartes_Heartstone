package hearthstone.exception;


/**
 * Exception levée quand une carte de classe NEUTRE est rencontrée mais que c'est impossible
 * @author lanoix-a remm-jf
 * @version 1.0
 */
public class ClasseNeutreException extends HearthstoneException {

    public ClasseNeutreException(String message) {
        super(message);
    }
}
