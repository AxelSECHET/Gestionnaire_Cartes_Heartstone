package ihm.controleur;

import hearthstone.cartes.Cartes;
import hearthstone.cartes.FiltreChoix;
import ihm.vue.FenetreMenu;
import ihm.vue.FiltreVue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FiltreVueOpen implements ActionListener {
	private Cartes cartes;
	private FenetreMenu source;
	
	public FiltreVueOpen(FenetreMenu source, Cartes cartes) {
		this.source = source;
		this.cartes = cartes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Créer la fenetre et récupérer les filtres choisis précédemment
		FiltreVue dialog = new FiltreVue(source, cartes);
		FiltreChoix filChoix = cartes.getFiltreChoix();
		
		// Mettre les boutons aux valeurs choisies précédemment
		dialog.setArme(filChoix.getArme());
		dialog.setServiteur(filChoix.getServiteur());
		dialog.setSort(filChoix.getSort());
		dialog.setDoree(filChoix.getDoree());
		
		dialog.setChoixClasse(filChoix.getClasse());
		dialog.setChoixRarete(filChoix.getRarete());
		dialog.setChoixRace(filChoix.getRace());
		
		// Afficher le JDialog
		dialog.setVisible(true);
	}
}
