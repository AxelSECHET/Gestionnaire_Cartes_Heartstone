package ihm.controleur;

import ihm.vue.FenetreMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchDeck implements ActionListener {
	private FenetreMenu source;
	
	public SwitchDeck(FenetreMenu source) {
		this.source = source;
	}
	//Permet d'afficher la fenêtre avec les options pour la gestion de la Collection
	@Override
	public void actionPerformed(ActionEvent e) {
		source.changerMode(FenetreMenu.MODE_DECK); // Passer en mode Deck
	}
}
