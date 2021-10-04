package ihm.controleur;

import hearthstone.cartes.Cartes;
import hearthstone.cartes.Deck;
import hearthstone.exception.ClasseNeutreException;
import hearthstone.exception.LimiteNombreDeCartesException;
import ihm.vue.FenetreMenu;
import ihm.vue.NouveauDeck;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NouveauDeckValidation implements ActionListener {
	private NouveauDeck source;
	private Cartes cartes;
	private FenetreMenu fenetre;
	
	public NouveauDeckValidation(NouveauDeck source, Cartes cartes, FenetreMenu fenetre) {
		this.source = source;
		this.cartes = cartes;
		this.fenetre = fenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			// Si on peut ajouter le deck
			Deck deck = new Deck(cartes, source.getChoixClasse());
			if (cartes.assossierDeck(source.getNomDeck(), deck)) {
				fenetre.ajouterChoixDeck(source.getNomDeck());
				source.dispose(); // Fermer la fenêtre
			} else // Sinon message d'erreur
				source.erreur("Erreur d'ajout", "Un deck avec ce nom existe déjà!");
		} catch (ClasseNeutreException | LimiteNombreDeCartesException e1) {
			source.erreur(e1.getClass().getName(), e1.getStackTrace()); // Ne devrait pas arriver
		}
	}
}
