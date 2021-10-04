package ihm.controleur;

import hearthstone.carte.Carte;
import hearthstone.exception.ClasseNeutreException;
import ihm.vue.FenetreMenu;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class AffichageCarteCollec extends MouseAdapter {
	private FenetreMenu source;
	
	public AffichageCarteCollec(FenetreMenu source) {
		this.source = source;
	}
	//Permet d'afficher la carte sélectionnée sur la partie dédiée à l'agrandissement d'une carte
	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel parent = (JPanel) e.getSource(); // Le JPanel sur lequel on appuie
		String indexText = ((JLabel)parent.getComponent(0)).getText(); // Recuperation du contenu du label caché
		int index = Integer.parseInt(indexText); // Conversion en int
		
		// Selectionner la carte a l'index "index" des cartes affichées
		source.setSelectedCarte(source.getCartesAffichees().get(index));
	}
}
