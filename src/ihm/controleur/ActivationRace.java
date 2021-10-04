package ihm.controleur;

import ihm.vue.FiltreVue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by E174557L on 22/06/18
 */
public class ActivationRace implements ActionListener {
    private FiltreVue filtre;

    public ActivationRace(FiltreVue f){
        filtre=f;
    }
    //Permet de désactiver et activer la ComboBox contenant les Races dans FiltreVue
    @Override
    public void actionPerformed(ActionEvent e) {
        if (filtre.getServiteur()){
            filtre.activeRace();
        }else {
            filtre.desactiveRace();
        }

    }

}
