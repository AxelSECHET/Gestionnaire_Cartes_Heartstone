package hearthstone.cartes;
import hearthstone.carte.*;
import hearthstone.exception.CarteAbsenteException;
import hearthstone.exception.CarteDejaPresenteException;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.Assert.*;
/**
 * Created by E175079D on 18/06/18.
 */
public class CartesTest {

    private Collection<Carte> collec;
    private Cartes paquetCarte;
    private Cartes paquetCarte2;
    private Arme arme1;
    private Serviteur serviteur1;
    private Sort sort1;

    @Before
    public void setUp() throws Exception {
        collec = new HashSet<>();
        arme1 = new Arme("test",0,"nani", Rarete.EPIQUE, Classe.NEUTRE,"","",5,10);
        serviteur1 = new Serviteur("dfdf", 5, "ndskmlfnhdr", Rarete.EPIQUE, Classe.NEUTRE, "", "", 8, 5, Race.DEMON);
        sort1 = new Sort("HUEHEUHUE", 2, "boum",Rarete.RARE, Classe.DRUIDE, "", "");


        collec.add(serviteur1);
        collec.add(sort1);
        paquetCarte = new Cartes();
        paquetCarte2 = new Cartes(collec);
    }

    @Test
    public void cartesAjouter1() throws Exception{                               // Vérification que l'arme est bien ajoutée à un paquet de cartes vide
        paquetCarte.ajouter(arme1);
        Collection<Carte> oracle = new HashSet<>(Arrays.asList(arme1));
        assertEquals(oracle, paquetCarte.collection());
    }

    @Test
    public void cartesAjouter2() throws Exception{                               // Vérification que l'arme est bien ajoutée
        paquetCarte2.ajouter(arme1);
        Collection<Carte> oracle = new HashSet<>(Arrays.asList(serviteur1,arme1,sort1));
        assertEquals(oracle, paquetCarte2.collection());
    }

    /*@Test
    public void cartesAjouterPoussiere1() throws Exception{                      // Vérification de la suppression de la poussiere d'un paquet de cartes vide
        int oracle = paquetCarte.poussierePossede() - arme1.coutCreation();
        paquetCarte.ajouter(arme1);
        assertEquals("Calcul poussiere erroné", oracle, paquetCarte.poussierePossede());
    }
    @Test
    public void cartesAjouterPoussiere2() throws Exception{                      // Vérification de la suppression de la poussiere
        int oracle = paquetCarte2.poussierePossede() - arme1.coutCreation();
        paquetCarte2.ajouter(arme1);
        assertEquals("Calcul poussiere erroné", oracle, paquetCarte2.poussierePossede());
    }*/

    @Test(expected = CarteDejaPresenteException.class)                          // Vérification lorsqu'on ajoute deux
    public void cartesAjouter_DejaPresenteException1() throws Exception{         // fois la même carted'un paquet de cartes vide
        paquetCarte.ajouter(arme1);
        paquetCarte.ajouter(arme1);
    }
    @Test(expected = CarteDejaPresenteException.class)                          // Vérification lorsqu'on ajoute deux
    public void cartesAjouter_DejaPresenteException2() throws Exception{         // fois la même carte
        paquetCarte2.ajouter(arme1);
        paquetCarte2.ajouter(arme1);
    }

    @Test
    public void cartesEstPresente1() throws Exception{                           // Vérification que la carte est bien
        paquetCarte.ajouter(arme1);                                             // présente dans un paquet de cartes vide au début
        assertEquals("Carte non présente", true, paquetCarte.estPresente(arme1));
    }
    @Test
    public void cartesEstPresente2() throws Exception{                           // Vérification que la carte est bien
        paquetCarte2.ajouter(arme1);                                             // présente
        assertEquals("Carte non présente", true, paquetCarte2.estPresente(arme1));
    }

    @Test
    public void carteEffacer() throws Exception {                               // Vérification lorsqu'on efface une carte bien présente
        paquetCarte2.effacer(sort1);
        assertEquals(new HashSet<>(), paquetCarte.collection());
    }

    @Test(expected = CarteAbsenteException.class)                               // Vérification lorsqu'on efface une carte
    public void cartesEffacer_CarteAbsenteException1() throws Exception{        // d'une collection vide
        paquetCarte.effacer(sort1);
    }

    @Test(expected = CarteAbsenteException.class)                               // Vérification lorsque'on efface deux
    public void cartesEffacer_CarteAbsenteException2() throws Exception{         // fois la même carte
        paquetCarte2.effacer(sort1);
        paquetCarte2.effacer(sort1);
    }
  /*@Test
    public void cartesEffacerPoussiere() throws Exception{                      // Vérification de l'ajout de poussiere
        int oracle = paquetCarte2.poussierePossede() + sort1.gainDesenchantement();
        paquetCarte2.effacer(sort1);
        assertEquals("Calcul poussiere erroné", oracle, paquetCarte2.poussierePossede());
    }*/
}