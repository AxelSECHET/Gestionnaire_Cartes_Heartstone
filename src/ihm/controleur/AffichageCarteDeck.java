package ihm.controleur;

import ihm.vue.FenetreMenu;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AffichageCarteDeck implements ListSelectionListener {
	private FenetreMenu source;
	
	public AffichageCarteDeck(FenetreMenu source) {
		this.source = source;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (source.getSelectedCarteDeck() != null)
			source.afficherInfoCarte(source.getSelectedCarteDeck());
	}
}
