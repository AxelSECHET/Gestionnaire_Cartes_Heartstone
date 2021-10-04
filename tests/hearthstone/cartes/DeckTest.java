package hearthstone.cartes;

import hearthstone.carte.*;
import hearthstone.exception.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by E174557L on 18/06/18
 */
public class DeckTest {

    @Test(expected = ClasseNeutreException.class)// test de mattre le deck sous la classe Neutre
    public void testDeck1() throws Exception {
        Cartes mesCartes = new Cartes();
        new Deck(mesCartes, Classe.NEUTRE, 30);
    }

    @Test(expected = LimiteNombreDeCartesException.class) // test dépassement de la limite max à la constructiuon du deck
    public void testDeck2() throws Exception{
        Cartes mesCartes = new Cartes();
        new Deck(mesCartes, Classe.DEMONISTE, 45);
    }

    @Test
    public void estPresente() throws Exception{
        Cartes mesCartes = new Cartes();
        Carte machin = new Serviteur("machin", 5, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte bidule = new Serviteur("bidule", 10, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte michel = new Serviteur("michel", 5, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte camembert = new Serviteur("camembert", 3, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte rogert = new Serviteur("rogert", 2, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte guldan = new Serviteur("Guldan", 8, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        mesCartes.ajouter(machin);
        mesCartes.ajouter(bidule);
        mesCartes.ajouter(guldan);
        mesCartes.ajouter(rogert);
        mesCartes.ajouter(camembert);
        mesCartes.ajouter(michel);
        assertEquals("estPresente cassé",true,mesCartes.estPresente(guldan));
    }

    @Test
    public void estPresente2() throws Exception{
        Cartes mesCartes = new Cartes();
        Carte camembert = new Serviteur("camembert", 3, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte rogert = new Serviteur("rogert", 2, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte guldan = new Serviteur("Guldan", 8, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        Carte michel = new Serviteur("dqsdq", 2, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);
        mesCartes.ajouter(guldan);
        mesCartes.ajouter(camembert);
        mesCartes.ajouter(rogert);
        assertEquals("estPresente cassé",false,mesCartes.estPresente(michel));
    }
    @Test (expected = IllegalArgumentException.class)               // test de la présence des cartes avec un parametre null
    public void estPresente3() throws Exception {
        Carte arme1 = new Arme("1",10, "Marteau de Thor...", Rarete.LEGENDAIRE, Classe.GUERRIER, 100, 100);
        Cartes cartes = new Cartes();
        cartes.ajouter(arme1);
        Deck deck = new Deck(cartes, Classe.GUERRIER);
        deck.ajouter(arme1);
        deck.estPresente(null);
    }

    @Test // test d'ajout d'une carte de même classe que le deck
    public void testAjout() throws Exception {
        Cartes mesCartes = new Cartes();
        Deck monDeck2;
        monDeck2 = new Deck(mesCartes, Classe.DEMONISTE);

        Carte guldan = new Serviteur("Guldan", 8, "Le Traître", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);

        mesCartes.ajouter(guldan);
        monDeck2.ajouter(guldan);

        Collection<Carte> oracle = new LinkedList<>(Arrays.asList(guldan));

        assertEquals(oracle, monDeck2.collection());
    }

    @Test(expected = LimiteNombreDeCartesException.class)// test de la limite d'ajout de légendaire identique
    public void testAjout2() throws Exception {
        Cartes mesCartes = new Cartes();

        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Deck monDeck3;

        mesCartes.ajouter(kadgar);

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        monDeck3.ajouter(kadgar);
        monDeck3.ajouter(kadgar);
    }

    @Test(expected = DeckPleinException.class)// test de limite de carte  dans le deck
    public void testAjout3() throws Exception {
        Cartes mesCartes = new Cartes();

        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.EPIQUE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Deck monDeck3;

        mesCartes.ajouter(kadgar);

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 1);

        monDeck3.ajouter(kadgar);
        monDeck3.ajouter(kadgar);
    }

    @Test(expected = LimiteNombreDeCartesException.class)//test de la limite de carte normal
    public void testAjout4() throws Exception {
        Cartes mesCartes = new Cartes();

        Carte farondis = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        Deck monDeck3;

        mesCartes.ajouter(farondis);

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        monDeck3.ajouter(farondis);
        monDeck3.ajouter(farondis);
        monDeck3.ajouter(farondis);
    }

    @Test(expected = CarteNonDisponibleException.class)//test d'ajout d'une carte n'étant pas dans la collection
    public void testAjout5() throws Exception {
        Cartes mesCartes = new Cartes();

        Carte sargeras = new Serviteur("Sargeras", 12, "Chef de la Légion Ardente", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 50, 30, Race.DEMON);
        Deck monDeck3;

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        monDeck3.ajouter(sargeras);

    }
    @Test(expected = CarteMauvaiseClasseException.class)//test exception du carte ne pouvant pas aller dans le deck car elle n'est pas de la même classe
    public void testAjout6() throws Exception{
        Cartes mesCartes = new Cartes();

        Carte sargeras = new Serviteur("Sargeras", 12, "Chef de la Légion Ardente", Rarete.LEGENDAIRE, Classe.DEMONISTE, "", "", 50, 30, Race.DEMON);
        mesCartes.ajouter(sargeras);
        Deck monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        monDeck3.ajouter(sargeras);
    }

    @Test(expected = LimiteNombreDeCartesException.class) //test de la limite du nombre de carte dont une carte dorée dans le deck
    public void testAjout7() throws Exception {
        Cartes mesCartes = new Cartes();

        Carte farondis = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        Carte farondisG = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        farondisG.fabriquerCarteDoree(farondis);
        Deck monDeck3 = null;

        mesCartes.ajouter(farondis);

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        monDeck3.ajouter(farondis);
        monDeck3.ajouter(farondisG);
        monDeck3.ajouter(farondis);
    }
    @Test(expected = DeckPleinException.class)// test de limite de carte  dans le deck
    public void testAjout8() throws Exception {
        Cartes mesCartes = new Cartes();

        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.EPIQUE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Carte farondis = new Serviteur("farondis", 25, "Archimage et Chef du Kirin Tor", Rarete.EPIQUE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Carte briil = new Serviteur("Briil", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Deck monDeck3;

        mesCartes.ajouter(kadgar);
        mesCartes.ajouter(farondis);
        mesCartes.ajouter(briil);

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 4);

        monDeck3.ajouter(kadgar);
        monDeck3.ajouter(kadgar);
        monDeck3.ajouter(farondis);
        monDeck3.ajouter(farondis);
        monDeck3.ajouter(briil);
    }
    @Test // test d'ajout d'une carte de même classe que le deck
    public void testAjout9() throws Exception {
        Cartes mesCartes = new Cartes();
        Deck monDeck2;
        monDeck2 = new Deck(mesCartes, Classe.DEMONISTE);

        Carte guldan = new Serviteur("Guldan", 8, "Le Traître", Rarete.EPIQUE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);

        mesCartes.ajouter(guldan);
        monDeck2.ajouter(guldan);
        monDeck2.ajouter(guldan);

        Collection<Carte> oracle = new LinkedList<>(Arrays.asList(guldan,guldan));

        assertEquals(oracle, monDeck2.collection());
    }
    @Test(expected = DeckPleinException.class)// test de limite de carte  dans le deck
    public void testAjout10() throws Exception {
        Cartes mesCartes = new Cartes();

        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.EPIQUE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Deck monDeck3;

        mesCartes.ajouter(kadgar);

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 0);

        monDeck3.ajouter(kadgar);
    }
    @Test                                               // test d'ajout d'une carte de même classe que le deck
    public void testAjout11() throws Exception {
        Cartes mesCartes = new Cartes();
        Deck monDeck2;
        monDeck2 = new Deck(mesCartes, Classe.DEMONISTE);

        Carte guldan = new Serviteur("Guldan", 8, "Le Traître", Rarete.BASIQUE, Classe.DEMONISTE, "", "", 150, 100, Race.DEMON);

        mesCartes.ajouter(guldan);
        monDeck2.ajouter(guldan);

        Collection<Carte> oracle = new LinkedList<>(Arrays.asList(guldan));

        assertEquals(oracle, monDeck2.collection());
    }
    @Test (expected = IllegalArgumentException.class)                               //test lors de l'ajout null
    public void testAjout12() throws Exception {
        Carte arme1 = new Arme("1",10, "Marteau de Thor...", Rarete.LEGENDAIRE, Classe.GUERRIER, 100, 100);
        Cartes cartes = new Cartes();
        cartes.ajouter(arme1);
        Deck deck = new Deck(cartes, Classe.GUERRIER);
        deck.ajouter(null);
    }



    @Test // Test de la suppression
    public void testsuup1() throws Exception {
        Cartes mesCartes = new Cartes();
        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Carte farondis = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        Deck monDeck3 = null;

        mesCartes.ajouter(kadgar);
        mesCartes.ajouter(farondis);
        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        monDeck3.ajouter(kadgar);
        monDeck3.ajouter(farondis);
        monDeck3.ajouter(farondis);

        monDeck3.effacer(farondis);

        Collection<Carte> oracle = new LinkedList<>(Arrays.asList(kadgar,farondis));
        assertEquals(oracle,monDeck3.collection()); //verification de la suppression

    }
    @Test (expected = CarteAbsenteException.class) // Test de l'exception de l'absence de la carte dans le deck avant la suppression
    public void testsupp2() throws Exception {
        Cartes mesCartes = new Cartes();
        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Carte farondis = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        Deck monDeck3;
        mesCartes.ajouter(kadgar);
        mesCartes.ajouter(farondis);
        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);
        monDeck3.ajouter(kadgar);
        monDeck3.effacer(farondis);
        Collection<Carte> oracle = new LinkedList<>(Arrays.asList(kadgar,farondis));
        assertEquals(oracle,monDeck3.collection());

    }
    @Test (expected = CarteAbsenteException.class) // Test de l'exception de l'absence de la carte dans le deck avant la suppression
    public void testsupp3() throws Exception {
        Cartes mesCartes = new Cartes();
        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Deck monDeck3;
        mesCartes.ajouter(kadgar);
        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);
        monDeck3.effacer(kadgar);
    }
    @Test // Test de la suppression
    public void testsuup4() throws Exception {
        Cartes mesCartes = new Cartes();
        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);

        Deck monDeck3 = null;

        mesCartes.ajouter(kadgar);

        monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        monDeck3.ajouter(kadgar);

        monDeck3.effacer(kadgar);

        Collection<Carte> oracle = new LinkedList<>(Arrays.asList());
        assertEquals(oracle,monDeck3.collection()); //verification de la suppression

    }

    @Test (expected = CarteAbsenteException.class) // Test de l'exception de l'absence de la carte dans le deck avant la suppression
    public void testsupp5() throws Exception {
        Cartes mesCartes = new Cartes();
        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Carte farondis = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        Deck monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);

        mesCartes.ajouter(kadgar);
        mesCartes.ajouter(farondis);

        monDeck3.ajouter(kadgar);
        monDeck3.effacer(kadgar);
        monDeck3.effacer(kadgar);

    }
    @Test (expected = IllegalArgumentException.class)           //Test de l'exception de l'abscence de la carte dans le deck lorsque le param est null
    public void testsupp6() throws Exception {
        Cartes mesCartes = new Cartes();
        Carte kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Carte farondis = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        Deck monDeck3 = new Deck(mesCartes, Classe.MAGE, 30);
        mesCartes.ajouter(kadgar);
        mesCartes.ajouter(farondis);

        monDeck3.ajouter(kadgar);
        monDeck3.effacer(kadgar);
        monDeck3.effacer(null);

    }


    /*@Test (expected = Exception.class) // Test de la méthode mélanger
    public void testmelanger() throws ValeurNegativeException {
        Cartes mesCartes = new Cartes();
        Carte Kadgar = new Serviteur("Khadgar", 25, "Archimage et Chef du Kirin Tor", Rarete.LEGENDAIRE, Classe.MAGE, "", "", 150, 80, Race.ELEMENTAIRE);
        Carte Farondis = new Serviteur("Farondis", 5, "prince d'Alshara", Rarete.EPIQUE, Classe.MAGE, "", "", 10, 9, Race.ELEMENTAIRE);
        Deck monDeck4 = null;
        try {
            mesCartes.ajouter(Kadgar);
            mesCartes.ajouter(Farondis);
            monDeck4 = new Deck(mesCartes, Classe.MAGE, 30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            monDeck4.ajouter(Kadgar);
            monDeck4.ajouter(Farondis);
            monDeck4.ajouter(Farondis);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Deck oracle = monDeck4;
        monDeck4.melanger();
        assertEquals(oracle,monDeck4); // prouve que les deux sont différent et donc qu'il y a eu un mélange

    }*/

}