package hearthstone;

import hearthstone.carte.*;
import hearthstone.cartes.Cartes;
import hearthstone.cartes.FabriqueJson;
import hearthstone.exception.CarteAbsenteException;
import hearthstone.exception.CarteDejaPresenteException;
import hearthstone.exception.ClasseNeutreException;
import ihm.vue.FenetreMenu;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * @author lanoix-a remm-jf
 * @version 1.0
 */

public class Hearthstone {

    public static void main(String[] args) throws IOException, ClasseNeutreException {
        Cartes collec = new Cartes();
        collec.preremplir();
        new FenetreMenu("Hearthstone", collec);
    }

}
