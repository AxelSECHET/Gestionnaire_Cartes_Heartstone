package ihm.controleur;

import ihm.vue.FenetreMenu;
import ihm.vue.FiltreVue;
import ihm.vue.NouvelleCarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174557L on 21/06/18
 * Permet d'activer les paramètres optionnel de Serviteur dans NouvelleCarte
 */
public class ActivationServiteur implements ActionListener {
    private NouvelleCarte nv_carte;

    public ActivationServiteur(NouvelleCarte f){
        nv_carte=f;
    }
    //Permet l'activation des composants pour créer un Serviteur et désactive ceux qui ne sont pas utiles
    @Override
    public void actionPerformed(ActionEvent e) {
        nv_carte.activationServiteur();
    }
}
