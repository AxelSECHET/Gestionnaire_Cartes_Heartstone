package hearthstone.exception;

/**
 * Exception levée quand on essaie d'ajouter une carte mais qu'elle est déjà présente
 * @author lanoix-a remm-jf
 * @version 1.0
 */
public class CarteDejaPresenteException extends HearthstoneException {

    public CarteDejaPresenteException(String message) {
        super(message);
    }
}