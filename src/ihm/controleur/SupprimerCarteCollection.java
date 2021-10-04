package ihm.controleur;

import hearthstone.cartes.Cartes;
import hearthstone.exception.CarteAbsenteException;
import hearthstone.exception.ClasseNeutreException;
import ihm.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174621F on 22/06/18
 */
public class SupprimerCarteCollection implements ActionListener {
    private FenetreMenu fenetre;
    private Cartes collec;

    public SupprimerCarteCollection(FenetreMenu laFenetre,Cartes collec){
        this.collec=collec;
        this.fenetre=laFenetre;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            collec.effacer(fenetre.getSelectedCarte());//Supprime la carte sélectionnée de la collection
            fenetre.afficherCollection();//Permet de rafraichir l'affichage de la collection
            fenetre.selectFirstCarteAffich();
        } catch (CarteAbsenteException e1) {
            fenetre.erreur("Erreur!",e1.getMessage());
        }
    }
}