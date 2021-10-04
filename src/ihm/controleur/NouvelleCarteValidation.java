package ihm.controleur;

import hearthstone.carte.Arme;
import hearthstone.carte.Carte;
import hearthstone.carte.Serviteur;
import hearthstone.carte.Sort;
import hearthstone.cartes.Cartes;
import ihm.vue.FenetreMenu;
import ihm.vue.NouvelleCarte;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by E175079D on 22/06/18
 */
public class NouvelleCarteValidation implements ActionListener {
	private NouvelleCarte fenetre;
	private Cartes collection;
	private FenetreMenu principale;
	
	public NouvelleCarteValidation(FenetreMenu principale, NouvelleCarte f, Cartes collec) {
		this.principale = principale;
		fenetre = f;
		this.collection = collec;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Carte aAjouter = null;
			
			if (fenetre.getNom().equals("") || fenetre.getDescription().equals("")) {
				principale.erreur("Erreur", "Veuillez renseigner au moins un nom et une description.");
				return;
			}
			
			if ((fenetre.getUrlDoree().equals("")) != (fenetre.getUrl().equals(""))) {
				principale.erreur("Erreur", "Si vous voulez renseigner une image, veuillez aussi renseigner une image pour la carte dorée.");
				return;
			}
			
			if (!fenetre.getUrl().equals("") && !fenetre.getUrl().equals("")) {
				try {
					new URL(fenetre.getUrl());
					new URL(fenetre.getUrlDoree());
				} catch (MalformedURLException ex) {
					principale.erreur("Erreur", "Url malformé.");
					return;
				}
			}
			
			// Ajout d'une carte Sort si le radiobouton "sort" est sélectionné
			if (fenetre.getSort()) {
				//   Creation du sort sans url si les champs de saisie d'url sont vides
				if (fenetre.getUrl().isEmpty() && fenetre.getUrlDoree().isEmpty()) {
					aAjouter = new Sort(fenetre.getNom(), fenetre.getCoutMana(), fenetre.getDescription(),
							fenetre.getRarete(), fenetre.getClasse());
				}
				//   Sinon Creation du sort
				else {
					aAjouter = new Sort(fenetre.getNom(), fenetre.getCoutMana(), fenetre.getDescription(),
							fenetre.getRarete(), fenetre.getClasse(), fenetre.getUrl(), fenetre.getUrlDoree());
				}
			}

			// Ajout d'une carte Arme si le radiobouton "arme" est sélectionné
			if (fenetre.getArme()) {
				
				//   Creation de l'arme sans url si les champs de saisie d'url sont vides
				if (fenetre.getUrl().isEmpty() && fenetre.getUrlDoree().isEmpty()) {
					aAjouter = new Arme(fenetre.getNom(), fenetre.getCoutMana(), fenetre.getDescription(),
							fenetre.getRarete(), fenetre.getClasse(), fenetre.getPointDegat(), fenetre.getPointDurabilite());
				}
				//    Sinon Creation de l'arme
				else {
					aAjouter = new Arme(fenetre.getNom(), fenetre.getCoutMana(), fenetre.getDescription(),
							fenetre.getRarete(), fenetre.getClasse(), fenetre.getUrl(), fenetre.getUrlDoree(),
							fenetre.getPointDegat(), fenetre.getPointDurabilite());
				}
			}

			// Ajout d'une carte Serviteur si le radiobouton "serviteur" est sélectionné
			if (fenetre.getServiteur()) {
				
				//   Creation du serviteur sans url si les champs de saisie d'url sont vides
				if (fenetre.getUrl().isEmpty() && fenetre.getUrlDoree().isEmpty()) {
					aAjouter = new Serviteur(fenetre.getNom(), fenetre.getCoutMana(), fenetre.getDescription(),
							fenetre.getRarete(), fenetre.getClasse(), fenetre.getPointDegat(), fenetre.getPointVie(), fenetre.getRace());
				}
				//   Sinon Creation du serviteur
				else {
					aAjouter = new Serviteur(fenetre.getNom(), fenetre.getCoutMana(), fenetre.getDescription(),
							fenetre.getRarete(), fenetre.getClasse(), fenetre.getUrl(), fenetre.getUrlDoree(),
							fenetre.getPointDegat(), fenetre.getPointVie(), fenetre.getRace());
				}
			}
			
			// Ajout a la collection et affichage
			collection.ajouter(aAjouter);
			principale.afficherCollection();
			
			fenetre.dispose();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			principale.erreur(e1.getClass().getName(), e1.getMessage());
		}
	}
}
