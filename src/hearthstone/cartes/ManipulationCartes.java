package hearthstone.cartes;

import hearthstone.carte.Carte;
import hearthstone.exception.CarteAbsenteException;
import hearthstone.exception.HearthstoneException;

import java.util.Collection;

/**
 * Interface décrivant des opérations basiques de manipulation d'un "paquet" de cartes
 * @author lanoix-a remm-jf
 * @version 1.0
 */
interface ManipulationCartes {

    /**
     *
     * @return le "paquet de cartes" sous la forme d'une collection de cartes au sens Collection de Cartes
     */
    Collection<Carte> collection();

    /**
     * Ajout d'une carte au "paquet"
     * @param carte la carte à ajouter
     * @throws HearthstoneException si l'ajout n'est pas possible
     */
    void ajouter(Carte carte) throws HearthstoneException;

    /**
     * test si la carte est présente dans le "paquet"
     * @param carte la carte à rechercher
     * @return true si la carte est présente
     */
    boolean estPresente(Carte carte);

    /**
     * supprime la carte du "paquet"
     * @param carte la carte à supprimer
     * @throws CarteAbsenteException si la carte n'est pas dans le "paquet"
     */
    void effacer(Carte carte) throws CarteAbsenteException;
}
