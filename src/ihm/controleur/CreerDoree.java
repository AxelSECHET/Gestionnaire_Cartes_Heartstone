package ihm.controleur;

import hearthstone.carte.Carte;
import hearthstone.cartes.Cartes;
import ihm.vue.FenetreMenu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174557L on 22/06/18
 */
public class CreerDoree implements ActionListener {
    private FenetreMenu fenetre;
    private Cartes cartes;

    public CreerDoree(FenetreMenu f, Cartes cartes){
        fenetre=f;
        this.cartes=cartes;
    }
    //Permet la création d'une carte dorée dans la collection
    @Override
    public void actionPerformed(ActionEvent e) {
    
        try {
  //        Créé une carte dorée à partir de la carte sélectionnée
            cartes.ajouter(Carte.fabriquerCarteDoree(fenetre.getSelectedCarte()));
        } catch (Exception e1) {
            fenetre.erreur(e1.getClass().getName(), e1.getMessage());
        }
        fenetre.afficherCollection();
    }
}
