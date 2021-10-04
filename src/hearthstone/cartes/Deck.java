package hearthstone.cartes;

import hearthstone.carte.Carte;
import hearthstone.carte.Classe;
import hearthstone.carte.Rarete;
import hearthstone.exception.*;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Collection;


/**
 *
 *  Classe représentant un deck, c'est à dire, une sélection de cartes parmi les cartes disponible dans le paquet de cartes associés
 *  - un deck est associé à un paquet de cartes
 *  - un deck a une classe de cartes associée
 *  - un deck a une taille maximum fixé
 * On ne peut ajouter un carte à un deck qu'en respectant certaines contraintes :
 * - la carte doit être dans le paquet de cartes associé
 *  - la carte doit respectée la classe du deck ou être NEUTRE
 *  - le deck ne doit pas être plein
 *  - si la carte est LEGENDAIRE elle ne peut être qu'une 1 fois dans le deck (que la carte soit dorée ou non) ;
 *  - sinon la carte ne peut être que 1 ou 2 fois dans le deck (que la carte soit dorée ou non)
 *
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class Deck extends AbstractListModel<Carte> implements ManipulationCartes {
  private Cartes cartes;
  private Classe myclasse;
  private int taillem;
  private int tailleact;
  private List<Carte> myCards;


  /**
   * créer un deck
   * @param mesCartes le paquet de carte associé
   * @param maClasse la classe associée
   * @param tailleMax la taille maximum pour ce deck
   * @throws ClasseNeutreException si la classe du deck est NEUTRE
   * @throws LimiteNombreDeCartesException si la taille max dépasse 30
   */
    public Deck(Cartes mesCartes, Classe maClasse, int tailleMax) throws ClasseNeutreException, LimiteNombreDeCartesException {
      if (maClasse.equals(Classe.NEUTRE)) throw new ClasseNeutreException("Le deck ne peut pas être de classe NEUTRE");
      if (tailleMax>30 || tailleMax<0) throw new LimiteNombreDeCartesException("Le deck ne peut pas avoir plus de 30 ou moins de 0 cartes");
      cartes=mesCartes;
      myclasse=maClasse;
      taillem=tailleMax;
      tailleact=0;
      myCards=new LinkedList<>();//une linkedList car plus facile a utiliser grâce a ses nombreuses méthodes
    }

    /**
     * créer un deck
     *
     * @param mesCartes le paquet de carte associé
     * @param maClasse la classe associée
     * @throws ClasseNeutreException si la classe du deck est NEUTRE
     * @throws LimiteNombreDeCartesException si la taille max dépasse 30
     */
    public Deck(Cartes mesCartes, Classe maClasse) throws ClasseNeutreException, LimiteNombreDeCartesException{
      this(mesCartes, maClasse, 30);
    }
    
    /**
     *
     * @return le deck sous la forme d'une collection de cartes au sens Collection de Cartes
     */
    @Override
    public Collection<Carte> collection() {
        return myCards;
    }

    /**
     * test si la carte est présente dans le deck
     * @param carte la carte à rechercher
     * @return true si la carte est présente dans le deck
     */
    @Override
    public boolean estPresente(Carte carte) {
      if (carte==null)throw new IllegalArgumentException("Vous ne pouvez pas mettre l'argument null");
      for (Carte c : myCards) {
      	if (c.estEgalModuloDoree(carte)) return true;//pour tester qu'importe s'il y a une carte dorée ou non
	  }
	  return false;
    }

    /**
     * Ajout d'une carte dans le deck
     * @param carte la carte à ajouter
     * @throws DeckPleinException le deck ne doit pas être plein
     * @throws CarteNonDisponibleException la carte doit être dans le paquet de cartes associé
     * @throws CarteMauvaiseClasseException la carte doit respectée la classe du deck ou être NEUTRE
     * @throws LimiteNombreDeCartesException la carte ne peut être que 1 ou 2 fois dans le deck (que la carte soit dorée ou non) ; 1 seule fois si carte LEGENDAIRE
     */
    @Override
    public void ajouter(Carte carte) throws DeckPleinException, CarteNonDisponibleException, CarteMauvaiseClasseException, LimiteNombreDeCartesException {
      // verification de toutes les exceptions
      if (carte==null)throw new IllegalArgumentException("Vous ne pouvez pas mettre l'argument null");
      if (this.tailleActuelle()==taillem) throw new DeckPleinException("Le deck est plein");
      if (!cartes.estPresente(carte)) throw new CarteNonDisponibleException("la carte n'est pas dans le paquet associé");
      if(carte.classe() != myclasse && carte.classe()!= Classe.NEUTRE) throw new CarteMauvaiseClasseException("la classe de la carte n'est ni neutre, ni de la même classe que le deck");
      if (carte.rarete()==Rarete.LEGENDAIRE){
        if (this.estPresente(carte)) throw new LimiteNombreDeCartesException("vous avez déjà cette légendaire dans votre de deck");
      } else {
		  int numcartes = 0;
		  
		  for (Carte c : myCards)
		  	if (c.estEgalModuloDoree(carte)) numcartes++;
		  
		  if (numcartes >= 2)
		  	throw new LimiteNombreDeCartesException("Cette carte est déja présente 2 fois dans le deck.");
	  } //ajout de la carte
      myCards.add(carte);
      tailleact++;
      
      fireIntervalAdded(this, 0, getSize());
    }

    /**
     * supprime la carte du deck
     * @param carte la carte à supprimer
     * @throws CarteAbsenteException si la carte n'est pas dans le deck
     */
    @Override
    public void effacer(Carte carte) throws CarteAbsenteException {
      if (carte==null)throw new IllegalArgumentException("Vous ne pouvez pas mettre l'argument null");
      if(!this.estPresente(carte)) throw new CarteAbsenteException("La carte n'est pas dans le deck");
      else{
        myCards.remove(carte);
        tailleact--;
      }
      
      fireIntervalRemoved(this, 0, getSize());
    }
    
    public void effacerAll(Carte carte) throws CarteAbsenteException {
      while (estPresente(carte)) {
        effacer(carte);
      }
    }


    /**
     *
     * @return la taille maximum déterminée pour le deck
     */
    public int tailleMax() {

        return this.taillem;
    }

    /**
     *
     * @return la taille actuelle du deck
     */
    public int tailleActuelle() {

        return this.tailleact;
    }

    /**
     *
     * @return la classe du deck
     */
    public Classe classe() {

        return this.myclasse;
    }


    /**
     * melange le deck ;
     * l'ordre des cartes dans le deck doit être modifié
     */
    public void melanger() {
      Collections.shuffle(myCards);
    }
  
  @Override
  public int getSize() {
    return myCards.size();
  }
  
  @Override
  public Carte getElementAt(int index) {
    return myCards.get(index);
  }
}
