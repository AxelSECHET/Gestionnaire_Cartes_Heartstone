package hearthstone.carte;

import com.google.gson.annotations.SerializedName;

import java.awt.Color;

/**
 * Enumération définissant les niveaux de raretés possible pour des cartes
 *
 * @author lanoix-a remm-jf
 * @version 1.0
 */
public enum Rarete {
    @SerializedName("Free")
    BASIQUE(),
    @SerializedName("Common")
    COMMUNE(Color.WHITE,5,40,50,400),
    @SerializedName("Rare")
    RARE(Color.BLUE,20,100,100,800),
    @SerializedName("Epic")
    EPIQUE(Color.PINK,100,400,400,1600),
    @SerializedName("Legendary")
    LEGENDAIRE(Color.ORANGE,400,1600,1600,3200);


    private final Color gemme;
    private final int valeurDesenchantement;
    private final int valeurCreation;
    private final int valeurDesenchantementDoree;
    private final int valeurCreationDoree;


    Rarete(Color couleur, int  vd, int vc, int vdd, int vcd) {
        gemme = couleur;
        valeurCreation = vc;
        valeurDesenchantement = vd;
        valeurCreationDoree = vcd;
        valeurDesenchantementDoree = vdd;

    }

    Rarete(){
        this(null,0, 0, 0, 0);
    }

    /**
     *
     * @return la couleur associée a la rareté
     */
    public Color gemme() {
        return gemme;
    }

    /**
     *
     * @return la valeur de creation associée à la rareté
     */
    public int valeurCreation() {
        return valeurCreation;
    }

    /**
     *
     * @return la valeur de desenchantement associée à la rareté
     */
    public int valeurDesenchantement() {
        return valeurDesenchantement;
    }

    /**
     *
     * @return la valeur de création dorée associée à la rareté
     */
    public int valeurCreationDoree() {
        return valeurCreationDoree;
    }

    /**
     *
     * @return la valeur de désenchantement doré associée à la rareté
     */
    public int valeurDesenchantementDoree() {
        return valeurDesenchantementDoree;
    }


}
