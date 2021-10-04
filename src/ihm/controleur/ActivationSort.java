package ihm.controleur;

import ihm.vue.NouvelleCarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174621F on 22/06/18
 * Permet d'activer les paramètres optionnel de Sort dans NouvelleCarte
 */
public class ActivationSort implements ActionListener {
    private NouvelleCarte nv_carte;

    public ActivationSort(NouvelleCarte f){
        nv_carte=f;
    }
    //Permet l'activation des composants pour créer un sort et désactive ceux qui ne sont pas utiles
    @Override
    public void actionPerformed(ActionEvent e) {
        nv_carte.activationSort();
    }
}
