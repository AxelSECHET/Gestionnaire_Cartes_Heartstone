package ihm.controleur;

import hearthstone.cartes.Cartes;
import ihm.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174621F on 22/06/18
 */
public class SupprimerDeck implements ActionListener {
    private FenetreMenu fenetre;
    private Cartes cartes;
    
    public SupprimerDeck(FenetreMenu laFenetre, Cartes cartes) {
        this.fenetre=laFenetre;
        this.cartes = cartes;
    }
    
    //Permet de supprimer un Deck des données
    @Override
    public void actionPerformed(ActionEvent e) {
        cartes.retirerDeck(fenetre.getSelectedDeckName());
        fenetre.retirerChoixDeck(fenetre.getSelectedDeckName());
        fenetre.selectFirstCarteAffich();
    }
}

