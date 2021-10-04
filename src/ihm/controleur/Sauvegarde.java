package ihm.controleur;

import ihm.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by E174621F on 22/06/18.
 */
public class Sauvegarde implements ActionListener{
    private FenetreMenu fenetre;

    public Sauvegarde(FenetreMenu fenetre) {
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fenetre.erreur("Joyeuse paques!", "Goldorak est incapable de traiter votre demande pour le moment. Bonne journée.");
    }
}
