package ihm.controleur;

import hearthstone.cartes.Cartes;
import ihm.vue.FenetreMenu;
import ihm.vue.NouveauDeck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NouveauDeckOpen implements ActionListener {
	private FenetreMenu source;
	private Cartes cartes;
	
	public NouveauDeckOpen(FenetreMenu source, Cartes cartes) {
		this.source = source;
		this.cartes = cartes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		NouveauDeck dialog = new NouveauDeck(source, cartes); // Ouvrir une fenêtre NouveauDeck
		dialog.setVisible(true);
	}
}
