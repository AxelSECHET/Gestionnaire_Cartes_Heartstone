package hearthstone.carte;


import com.google.gson.annotations.SerializedName;

/**
 * Enumération définissant les classes possible pour des cartes
 *
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public enum Classe {
    @SerializedName("Neutral")
    NEUTRE, // valeur indiquant que la carte est compatible avec toutes les classes

    @SerializedName("Druid")
    DRUIDE,

    @SerializedName("Hunter")
    CHASSEUR,

    @SerializedName("Mage")
    MAGE,

    @SerializedName("Paladin")
    PALADIN,

    @SerializedName("Priest")
    PRETRE,

    @SerializedName("Shaman")
    CHAMAN,

    @SerializedName("Warlock")
    DEMONISTE,

    @SerializedName("Rogue")
    VOLEUR,

    @SerializedName("Warrior")
    GUERRIER


}
