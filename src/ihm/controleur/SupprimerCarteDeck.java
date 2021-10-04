package ihm.controleur;

import hearthstone.cartes.Deck;
import hearthstone.exception.*;
import ihm.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174621F on 21/06/18
 */

//Permet du supprimer la carte sélectionner du deck actif.
public class SupprimerCarteDeck implements ActionListener{
    private FenetreMenu fenetre;
    
    public SupprimerCarteDeck(FenetreMenu laFenetre){
        this.fenetre=laFenetre;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Suppression de la carte selectionnée du deck
            if (fenetre.getSelectedCarteDeck() != null) {
                fenetre.getSelectedDeck().effacer(fenetre.getSelectedCarteDeck());
                fenetre.setTextLabelTaille("" + fenetre.getSelectedDeck().tailleActuelle());
                
                fenetre.selectFirstCarteAffich();
            } else {
                fenetre.erreur("Erreur", "Veuillez selectionner une carte dans le deck pour la supprimer!");
            }
        } catch (CarteAbsenteException e1) {
            fenetre.erreur(e1.getClass().getName(), e1.getMessage());
        }
    }
}
