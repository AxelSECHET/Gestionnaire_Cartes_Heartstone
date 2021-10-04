package ihm.controleur;

import hearthstone.carte.Classe;
import hearthstone.carte.Race;
import hearthstone.carte.Rarete;
import hearthstone.cartes.Cartes;
import ihm.vue.FenetreMenu;
import ihm.vue.NouvelleCarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E175079D on 21/06/18
 */
public class NouvelleCarteOpen implements ActionListener{
    private Cartes cartes;
    private FenetreMenu source;

    public NouvelleCarteOpen(FenetreMenu source, Cartes cartes) {
        this.source = source;
        this.cartes = cartes;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Créer la fenetre et récupérer les criteres choisis précédemment
        new NouvelleCarte(source, cartes);
    }
}

