package hearthstone.cartes;

import hearthstone.carte.*;
import hearthstone.exception.ClasseNeutreException;

import java.util.Collection;
import java.util.LinkedList;

public class FiltreChoix {
	private boolean arme, serviteur, sort, doree;
	private Classe classe;
	private Rarete rarete;
	private Race race;
	
	public FiltreChoix(boolean arme, boolean serviteur, boolean sort, boolean doree, Classe classe, Rarete rarete, Race race) {
		this.arme = arme;
		this.serviteur = serviteur;
		this.sort = sort;
		this.doree = doree;
		this.classe = classe;
		this.rarete = rarete;
		this.race = race;
	}
	
	// Valeurs par defaut
	public FiltreChoix() {
		this(true, true, true, false, null, null, null);
	}
	
	public Collection<? extends Carte> filtrer(Collection<Carte> cartes) {
		Collection<Carte> out = new LinkedList<>();
		
		if (arme) out.addAll(Filtre.cartesArme(cartes));
		if (serviteur) out.addAll(Filtre.cartesServiteur(cartes));
		if (sort) out.addAll(Filtre.cartesSort(cartes));
		
		if (doree) out = Filtre.cartesDorees(out);
		
		if (classe != null) try {
			out = Filtre.cartesParClasse(out, classe);
		} catch (ClasseNeutreException e) {}
		if (rarete != null) out = Filtre.cartesParRarete(out, rarete);
		if (race != null)
			return Filtre.cartesParRace(Filtre.cartesServiteur(out), race);
		
		return out;
	}
	
	public boolean getArme() {
		return arme;
	}
	
	public boolean getServiteur() {
		return serviteur;
	}
	
	public boolean getSort() {
		return sort;
	}
	
	public boolean getDoree() {
		return doree;
	}
	
	public Classe getClasse() {
		return classe;
	}
	
	public Rarete getRarete() {
		return rarete;
	}
	
	public Race getRace() {
		return race;
	}
}
