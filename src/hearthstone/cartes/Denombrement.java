package hearthstone.cartes;

import hearthstone.carte.Carte;

/**
 * "couple" permettant d'associer à une carte son nombre d'exemplaires
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class Denombrement {
    private final Carte carte;
    private int nombre;

    /**
     * creer un "couple" (carte,nombre)
     * @param carte la carte à utiliser
     * @param nombre le nombre d'exemplaires
     */
    public Denombrement(Carte carte, int nombre) {
        this.carte = carte;
        this.nombre = nombre;
    }

    /**
     * creer un "couple" (carte,1)
     * @param carte la carte à utiliser
     */
    public Denombrement(Carte carte) {
        this(carte, 1);
    }

    /**
     *
     * @return la carte
     */
    public Carte carte() {
        return carte;
    }

    /**
     *
     * @return le nombre d'exemplaires
     */
    public int nombre() {
        return nombre;
    }

    /**
     * incremente le nombre d'exemplaires
     */
    public void incremente() {
        nombre++;
    }


    @Override
    public String toString() {
        return carte.nom() + " - " + nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Denombrement that = (Denombrement) o;

        if (nombre != that.nombre) return false;
        return carte.equals(that.carte);
    }

    @Override
    public int hashCode() {
        int result = carte.hashCode();
        result = 31 * result + nombre;
        return result;
    }
}
