package ihm.controleur;

import hearthstone.exception.CarteMauvaiseClasseException;
import hearthstone.exception.CarteNonDisponibleException;
import hearthstone.exception.DeckPleinException;
import hearthstone.exception.LimiteNombreDeCartesException;
import ihm.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174621F on 21/06/18
 */
//Permet l'ajout d'une carte dans le deck actif.
public class AjoutCarteDeck implements ActionListener{
    private FenetreMenu fenetre;
    
    public AjoutCarteDeck(FenetreMenu laFenetre){
        this.fenetre=laFenetre;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (fenetre.getSelectedDeck() != null) {
                fenetre.getSelectedDeck().ajouter(fenetre.getSelectedCarte());
                fenetre.setTextLabelTaille("" + fenetre.getSelectedDeck().tailleActuelle());
    
                fenetre.reloadListModel();
            } else {
                fenetre.erreur("Erreur", "Aucun deck n'est selectionné!");
            }
        } catch (Exception e1) {
            fenetre.erreur(e1.getClass().getName(), e1.getMessage());
        }

    }
}
