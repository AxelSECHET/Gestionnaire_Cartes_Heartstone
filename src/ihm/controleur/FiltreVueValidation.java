package ihm.controleur;

import hearthstone.carte.Classe;
import hearthstone.carte.Race;
import hearthstone.carte.Rarete;
import hearthstone.cartes.Cartes;
import hearthstone.cartes.Deck;
import hearthstone.cartes.FiltreChoix;
import hearthstone.exception.ClasseNeutreException;
import hearthstone.exception.LimiteNombreDeCartesException;
import ihm.vue.FenetreMenu;
import ihm.vue.FiltreVue;
import ihm.vue.NouveauDeck;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FiltreVueValidation implements ActionListener {
	private FiltreVue source;
	private FenetreMenu menu;
	private Cartes cartes;
	
	public FiltreVueValidation(FiltreVue source, FenetreMenu menu, Cartes cartes) {
		this.source = source;
		this.menu = menu;
		this.cartes = cartes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Si le choix n'est pas une race -> ne pas filtrer = mettre null
		Classe classe = (source.getClasse() instanceof Classe ? (Classe)source.getClasse() : null);
		Rarete rarete = (source.getRarete() instanceof Rarete ? (Rarete)source.getRarete() : null);
		Race race = (source.getRace() instanceof Race ? (Race)source.getRace() : null);
		// Créer un objet choix
		FiltreChoix fc = new FiltreChoix(
				source.getArme(),
				source.getServiteur(),
				source.getSort(),
				source.getDoree(),
				classe, rarete, race
		);
		// Mettre ces choix dans les cartes
		cartes.setFiltreChoix(fc);
		source.dispose();
		menu.afficherCollection();
	}
}
