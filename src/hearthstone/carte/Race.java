package hearthstone.carte;

import com.google.gson.annotations.SerializedName;

/**
 * Enumération définissant les races possibles pour les cartes Serviteur
 *
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public enum Race {
    @SerializedName("Beast")
    BETE,

    @SerializedName("Demon")
    DEMON,

    @SerializedName("Dragon")
    DRAGON,

    @SerializedName("Elemental")
    ELEMENTAIRE,

    @SerializedName("Mech")
    MECA,

    @SerializedName("Murloc")
    MURLOC,

    @SerializedName("Pirate")
    PIRATE,

    @SerializedName("Totem")
    TOTEM


}
