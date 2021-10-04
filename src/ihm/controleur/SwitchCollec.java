package ihm.controleur;

import ihm.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchCollec implements ActionListener {
	private FenetreMenu source;
	
	public SwitchCollec(FenetreMenu source) {
		this.source = source;
	}
	//Permet d'afficher la fenêtre avec les options pour la gestion de Decks
	@Override
	public void actionPerformed(ActionEvent e) {
		source.changerMode(FenetreMenu.MODE_COLLEC); // Passer en mode collection
	}
}
