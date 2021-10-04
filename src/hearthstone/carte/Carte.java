package hearthstone.carte;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import hearthstone.cartes.FabriqueJson;
import hearthstone.exception.*;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

/**
 *
 * Classe représentant une Carte de manière abstraite, c'est-à-dire tous les élements partagés par les 3 sortes de cartes considérées
 *
 * @author lanoix-a remm-jf
 * @version 1.0
 */
public abstract class Carte {

    @SerializedName("name")
    private String nom;

    @SerializedName("cost")
    private final int mana;

    @SerializedName("text")
    private String desc;

    @SerializedName("rarity")
    private Rarete rarete;

    @SerializedName("playerClass")
    private Classe classe;

    @SerializedName("img")
    private String urlImage;
    @SerializedName("imgGold")
    private String urlImageDoree;

    private boolean doree;

    /**
     * Construit une carte abstraite
     *
     * @param nom           nom de la carte
     * @param mana          valeur manna de la carte
     * @param desc          description de la carte
     * @param rarete        rareté de la carte
     * @param classe        classe de la carte
     * @param urlImage      url vers une image de la carte
     * @param urlImageDoree url vers une version doree de l'image de la carte
     */
    Carte(String nom, int mana, String desc, Rarete rarete, Classe classe, String urlImage, String urlImageDoree)  throws ValeurNegativeException, NullPointerException {
        if (nom == null || desc == null || rarete == null || classe == null || urlImage == null || urlImageDoree == null)
            throw new NullPointerException("un des paramètres = null");
        if (mana < 0)
        if (mana < 0)
            throw new ValeurNegativeException("valeur de mana negative");
        /*if (nom.equals("")) // PROVOQUE DES BUGS !!!!!!!
            this.nom = "";
        else
            this.nom = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();*/
        this.nom = nom; // <- ne provoque PAS de bugs.
        
        this.mana = mana;
        this.desc = desc;
        this.rarete = rarete;
        this.classe = classe;
        this.urlImage = urlImage;
        this.urlImageDoree = urlImageDoree;
        this.doree = false;
    }

    public void verifie() {
        if (nom == null)
            nom = "";
        if (desc == null)
            desc = "";
        if (rarete == null)
            rarete = Rarete.BASIQUE;
        if (classe == null)
            classe = Classe.NEUTRE;
        if (urlImage == null)
            urlImage = "";
        if (urlImageDoree == null)
            urlImageDoree = "";
    }

    /**
     * @return la valeur mana de la carte
     */
    public int mana() {
        return mana;
    }

    /**
     * @return le nom de la carte, bien formaté
     */
    public String nom() {
        if (nom.equals("")) return "";
        return nom;
    }

    /**
     * @return la description de la carte
     */
    public String description() {
        return desc;
    }

    /**
     * @return une version courte (10 caractères) de la description de la carte
     */
    public String descriptionCourte() {
        if (desc.length() >= 10)
            return desc.substring(0, 10);
        return desc;
    }

    /**
     * @return la classe de la carte
     */
    public Classe classe() {
        return classe;
    }

    /**
     * @return la rareté de la carte
     */
    public Rarete rarete() {
        return rarete;
    }


    /**
     * donne l'url correspondant à l'image : url ou url dorée en fonction
     *
     * @return l'url de image
     */
    public String urlImage() {
        if (estDoree())
            return urlImageDoree;
        return urlImage;
    }

    /**
     * @return true si la carte es dorée
     */
    public boolean estDoree() {
        return doree;
    }

    /**
     * indique si la carte est jouable, cad que la reserve de Mana est suffisante pour jouer la carte
     *
     * @param reserveMana une valeur de reserve de mana
     * @return true si la carte est jouable
     */
    public boolean estJouable(int reserveMana) {
        return (this.mana <= reserveMana);
    }

    /**
     * indique si deux cartes sont égales, indépendemment du fait qu'elles soient dorées ou non
     *
     * @param carte la carte a comparer
     * @return true si la carte courante est égale à la carte sans considere qu'elles soient dorées ou non
     */
    public boolean estEgalModuloDoree(Carte carte) {
        return mana == carte.mana && nom.equals(carte.nom) && desc.equals(carte.desc) && rarete == carte.rarete;
    }

    /**
     * donne le cout de creation d'une carte, si possible, i.e. si la carte n'est pas BASIQUE
     *
     * @return le cout de creation de la carte
     * @throws CoutCreationException si la carte est BASIQUE
     */
    public int coutCreation() throws CoutCreationException {
        if (rarete == Rarete.BASIQUE) throw new CoutCreationException("Une carte basique n'a pas de coût de création");
        if (doree) return rarete.valeurCreationDoree();
        return rarete.valeurCreation();
    }

    /**
     * donne le gain a detruire la carte, si c'est possible, i.e. si la carte n'est pas BASIQUE
     *
     * @return le gain de desenchantement de la carte
     * @throws GainDesenchantementException si la carte est BASIQUE
     */
    public int gainDesenchantement() throws GainDesenchantementException {
        if (rarete == Rarete.BASIQUE) throw new GainDesenchantementException("Une carte basique n'apporte pas de gain");
        if (doree) return rarete.valeurDesenchantementDoree();
        return rarete.valeurDesenchantement();
    }

    /**
     * Fabrique une carte dorée à partir d'une carte simple, si c'est possible
     *
     * @param c la carte d'origine servant à la fabrication
     * @return la nouvelle carte dorée
     * @throws CarteDoreeException   la carte d'origine est déja une carte doree
     * @throws CarteBasiqueException la carte d'origine est une carte BASIQUE
     * @throws Exception             autres cas empechant la fabrication de la carte
     */
    public static Carte fabriquerCarteDoree(Carte c) throws Exception {
        if (c.estDoree())
            throw new CarteDoreeException("on ne peut pas fabriquer une carte dorée à partir d'une carte dorée");
        if (c.rarete() == Rarete.BASIQUE)
            throw new CarteBasiqueException("On ne peut pas fabriquer une carte basique dorée");
        Carte carteDoree;
        if (c instanceof Arme) {
            Arme a = (Arme) c;
            carteDoree = new Arme(a.nom(), a.mana(), a.description(), a.rarete(), a.classe(), c.urlImageSimple(), c.urlImageDoree(), a.degats(), a.durabilite());
        } else if (c instanceof Serviteur) {
            Serviteur s = (Serviteur) c;
            carteDoree = new Serviteur(s.nom(), s.mana(), s.description(), s.rarete(), s.classe(), c.urlImageSimple(), c.urlImageDoree(), s.degats(), s.pointSDeVie(), s.race());
        } else if (c instanceof Sort) {
            Sort s = (Sort) c;
            carteDoree = new Sort(s.nom(), s.mana(), s.description(), s.rarete(), s.classe(), c.urlImageSimple(), c.urlImageDoree());
        } else
            // normalement point du programme inateignable
            throw new Exception();
        carteDoree.doree = true;
        return carteDoree;
    }

    private String urlImageSimple() {
        return urlImage;
    }

    private String urlImageDoree() {
        return urlImageDoree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carte)) return false;

        Carte carte = (Carte) o;

        if (mana != carte.mana) return false;
        if (doree != carte.doree) return false;
        if (!nom.equals(carte.nom)) return false;
        if (!desc.equals(carte.desc)) return false;
        if (rarete != carte.rarete) return false;
        if (classe != carte.classe) return false;
        if (!urlImage.equals(carte.urlImage)) return false;
        return urlImageDoree.equals(carte.urlImageDoree);
    }

    @Override
    public int hashCode() {
        int result = nom.hashCode();
        result = 31 * result + mana;
        result = 31 * result + desc.hashCode();
        result = 31 * result + rarete.hashCode();
        result = 31 * result + classe.hashCode();
        result = 31 * result + urlImage.hashCode();
        result = 31 * result + urlImageDoree.hashCode();
        result = 31 * result + (doree ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return nom + (doree ? " dorée" : "");
    }
}
