package hearthstone.cartes;

        import hearthstone.carte.Carte;
        import hearthstone.exception.*;

        import java.io.IOException;
        import java.util.*;


/**
 * Classe représentant l'ensemble des cartes disponibles par quelqu'un
 * Attention : un paquet de cartes ne peut contenir qu'un exemplaire de chaque carte
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class Cartes implements ManipulationCartes {

    /**
     * créer un paquet de cartes
     */
    private Map<String, Deck> decks;
    private Set<Carte> paquetCarte;               // J'ai choisi d'utiliser un SET comme type de
    private FiltreChoix filtreChoix;
    //private int poussiere;                        // collection car on ne veut pas de doublons
                                                  // de cartes dans la collection
    public Cartes() {
        //this.poussiere = 0;
        filtreChoix = new FiltreChoix();
        decks = new HashMap<>();
        paquetCarte = new HashSet<>();
    }

    /**
     * créer un paquet de cartes et le remplir à partir des cartes initiales passées en paramètre
     * @param cartesInitiales les cartes pour remplir le paquet de cartes
     * @throws CarteDejaPresenteException si une carte de cartes initiales est présente plusieurs fois
     */
    public Cartes(Collection<Carte> cartesInitiales) throws CarteDejaPresenteException {
        this();

        for (Iterator<Carte> it = cartesInitiales.iterator(); it.hasNext(); ){
            if (!paquetCarte.add(it.next())){
                throw new CarteDejaPresenteException("La carte est déjà présente dans le paquet de carte");
            }
        }

    }

    /**
     *
     * @return le paquet de cartes sous la forme d'une collection de cartes au sens Collection de Cartes
     */
    @Override
    public Collection<Carte> collection() {
        return paquetCarte;
    }

    /**
     * Ajout d'une carte au paquet de cartes
     * @param carte la carte à ajouter
     * @throws CarteDejaPresenteException si la carte est déjà présente
     */
    @Override
    public void ajouter(Carte carte) throws CarteDejaPresenteException {
        if (carte.equals(null)){
            throw new IllegalArgumentException("La carte ne peut pas être null");
        }
        if (!this.paquetCarte.add(carte)){
            throw new CarteDejaPresenteException("La carte est déjà présente dans le paquet de carte");
        }else{
            /*try {
                poussiere -= carte.coutCreation();
            } catch (CoutCreationException e) {
                e.printStackTrace();
            }*/
        }
    }

    /**
     * test si la carte est présente dans le paquet de cartes
     * @param carte la carte à rechercher
     * @return true si la carte est présente
     */
    @Override
    public boolean estPresente(Carte carte) {
        if (carte == null) throw new IllegalArgumentException("La carte ne peut pas être null");
    
        return paquetCarte.contains(carte);
    }

    /**
     * supprime la carte du paquet de cartes
     * @param carte la carte à supprimer
     * @throws CarteAbsenteException si la carte n'est pas dans le paquet de cartes
     */
    @Override
    public void effacer(Carte carte) throws CarteAbsenteException {
        if (carte == null) throw new IllegalArgumentException("La carte ne peut pas être null");
        
        if(!this.paquetCarte.remove(carte))
            throw new CarteAbsenteException("La carte recherchée est absente du paquet de carte");
        
        for (Deck d : decks.values()) {
            if (d.estPresente(carte))
                d.effacerAll(carte);
        }
        
        /*try {
            poussiere += carte.gainDesenchantement();
        } catch (GainDesenchantementException e) {
            e.printStackTrace();
        }*/
    }

   /* public int poussierePossede(){
        return poussiere;
    }*/

    public boolean assossierDeck(String name, Deck d) {
        if (decks.containsKey(name)) return false;
        decks.put(name, d);
        return true;
    }
    
    public boolean retirerDeck(String name) {
        return (decks.remove(name) != null);
    }
    
    public Deck getDeck(String name) {
        return decks.get(name);
    }

    //Permet de remplir la collection de cartes avec les documents json données.
    public void preremplir() throws IOException{
        //paquetCarte.addAll(((List)FabriqueJson.lireCartesDepuisFichier("json/collection.json")).subList(0, 200));
        paquetCarte.addAll(FabriqueJson.lireCartesDepuisFichier("json/cartes11.json"));
        paquetCarte.addAll(FabriqueJson.lireCartesDepuisFichier("json/doublon.json"));
        paquetCarte.addAll(FabriqueJson.lireCartesDepuisFichier("json/initial.json"));
    }
    
    public FiltreChoix getFiltreChoix() {
        return filtreChoix;
    }
    
    public void setFiltreChoix(FiltreChoix fc) {
        this.filtreChoix = fc;
    }
}
