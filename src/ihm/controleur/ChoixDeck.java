package ihm.controleur;

import hearthstone.carte.Classe;
import hearthstone.cartes.Cartes;
import hearthstone.cartes.Deck;
import hearthstone.exception.ClasseNeutreException;
import hearthstone.exception.LimiteNombreDeCartesException;
import ihm.vue.FenetreMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Choix d'un deck dans la combo box de decks
public class ChoixDeck implements ActionListener {
	private FenetreMenu source;
	
	public ChoixDeck(FenetreMenu source) {
		this.source = source;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Deck deck = source.getSelectedDeck();
		if (deck != null)
			source.setDeckListModel(deck);
		else {
			try {
				source.setDeckListModel(new Deck(new Cartes(), Classe.CHAMAN));
			} catch (Exception e1) {
				source.erreur(e1.getClass().getName(), e1.getMessage());
			}
		}
	}
}
