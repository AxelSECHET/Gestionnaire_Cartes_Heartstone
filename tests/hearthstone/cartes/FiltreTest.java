package hearthstone.cartes;

import hearthstone.carte.*;
import hearthstone.exception.ClasseNeutreException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class FiltreTest {
	int minCout;
	private Collection<Carte> cartes;
	private Arme a1, a2;
	private Serviteur se1, se2;
	private Sort so1, so2;
	
	// Set up une liste de cartes variée qui sera utilisée pour les tests
	@Before
	public void setUp() throws Exception {
		a1 = new Arme("a", 5, "aa", Rarete.BASIQUE, Classe.DRUIDE, 10, 15);
		a2 = new Arme("b", 5, "bb", Rarete.EPIQUE, Classe.NEUTRE, 10, 15);
		se1 = new Serviteur("c", 10, "cc", Rarete.BASIQUE, Classe.GUERRIER, 10, 15, Race.DRAGON);
		se2 = new Serviteur("d", 25, "dd", Rarete.LEGENDAIRE, Classe.DRUIDE, 10, 10, Race.ELEMENTAIRE);
		so1 = new Sort("e", 2, "ee", Rarete.LEGENDAIRE, Classe.NEUTRE);
		so2 = (Sort) Carte.fabriquerCarteDoree(new Sort("f", 3, "ff", Rarete.EPIQUE, Classe.GUERRIER));
		
		cartes = new LinkedList<>();
		
		cartes.addAll(Arrays.asList(a1, a2, se1, se2, so1, so2));
		
		minCout = Rarete.BASIQUE.valeurCreation() * 2 + Rarete.LEGENDAIRE.valeurCreation() * 2 + Rarete.EPIQUE.valeurCreation() + Rarete.EPIQUE.valeurCreationDoree();
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesArme() {
		Collection<? extends Carte> cartes2 = Filtre.cartesArme(cartes);
		
		assertTrue(cartes2.contains(a1) && cartes2.contains(a2));
		assertFalse(cartes2.contains(se1) || cartes2.contains(se2) || cartes2.contains(so1) || cartes2.contains(so2));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesArme2() throws Exception {
		Filtre.cartesArme(null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesArmeVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesArme(new LinkedList<>()));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesServiteur() {
		Collection<? extends Carte> cartes2 = Filtre.cartesServiteur(cartes);
		
		assertTrue(cartes2.contains(se1) && cartes2.contains(se2));
		assertFalse(cartes2.contains(a1) || cartes2.contains(a2) || cartes2.contains(so1) || cartes2.contains(so2));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesServiteur2() throws Exception {
		Filtre.cartesServiteur(null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesServiteurVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesServiteur(new LinkedList<>()));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesSort() {
		Collection<? extends Carte> cartes2 = Filtre.cartesSort(cartes);
		
		assertTrue(cartes2.contains(so1) && cartes2.contains(so2));
		assertFalse(cartes2.contains(a1) || cartes2.contains(a2) || cartes2.contains(se1) || cartes2.contains(se2));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesSort2() throws Exception {
		Filtre.cartesSort(null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesSortVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesSort(new LinkedList<>()));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesParRarete() {
		Collection<? extends Carte> cartes2 = Filtre.cartesParRarete(cartes, Rarete.LEGENDAIRE);
		
		assertTrue(cartes2.contains(se2) && cartes2.contains(so1));
		assertFalse(cartes2.contains(a1) || cartes2.contains(a2) || cartes2.contains(se1) || cartes2.contains(so2));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesParRarete2() throws Exception {
		Filtre.cartesParRarete(null, Rarete.BASIQUE);
	}
	
	// Test en cas de paramètre null
	@Test(expected = Exception.class)
	public void testCartesParRarete3() throws Exception {
		Filtre.cartesParRarete(cartes, null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesParRareteVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesParRarete(new LinkedList<>(), Rarete.BASIQUE));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesParRace() {
		Collection<? extends Carte> cartes2 = Filtre.cartesParRace(Filtre.cartesServiteur(cartes), Race.DRAGON);
		
		assertTrue(cartes2.contains(se1));
		assertFalse(cartes2.contains(a1) || cartes2.contains(a2) || cartes2.contains(se2) || cartes2.contains(so1) || cartes2.contains(so2));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesParRace2() throws Exception {
		Filtre.cartesParRace(null, Race.DRAGON);
	}
	
	// Test en cas de paramètre null
	@Test(expected = Exception.class)
	public void testCartesParRace3() throws Exception {
		Filtre.cartesParRace(Filtre.cartesServiteur(cartes), null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesParRaceVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesParRace(new LinkedList<>(), Race.ELEMENTAIRE));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesDorees() {
		Collection<? extends Carte> cartes2 = Filtre.cartesDorees(cartes);
		
		assertTrue(cartes2.contains(so2));
		assertFalse(cartes2.contains(a1) || cartes2.contains(a2) || cartes2.contains(se1) || cartes2.contains(se2) || cartes2.contains(so1));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesDorees2() throws Exception {
		Filtre.cartesDorees(null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesDoreesVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesDorees(new LinkedList<>()));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesParClasse() throws Exception {
		Collection<? extends Carte> cartes2 = Filtre.cartesParClasse(cartes, Classe.DRUIDE);
		
		assertTrue(cartes2.contains(a1) && cartes2.contains(a2) && cartes2.contains(se2) && cartes2.contains(so1));
		assertFalse(cartes2.contains(se1) || cartes2.contains(so2));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesParClasse2() throws Exception {
		Filtre.cartesParClasse(null, Classe.DRUIDE);
	}
	
	// Test en cas de paramètre null
	@Test(expected = Exception.class)
	public void testCartesParClasse3() throws Exception {
		Filtre.cartesParClasse(cartes, null);
	}
	
	// Test en cas de Classe.NEUTRE en paramètre
	@Test(expected = ClasseNeutreException.class)
	public void testCartesParClasse4() throws Exception {
		Filtre.cartesParClasse(cartes, Classe.NEUTRE);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesParClasseVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesParClasse(new LinkedList<>(), Classe.CHAMAN));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testManaMinimalNecessaire() {
		assertEquals(50, Filtre.manaMinimalNecessaire(cartes));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testManaMinimalNecessaire2() throws Exception {
		Filtre.manaMinimalNecessaire(null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testManaMinimalNecessaireVide() throws Exception {
		assertEquals(0, Filtre.manaMinimalNecessaire(new LinkedList<>()));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testGainDesanchantementTotal() {
		int oracle = Rarete.BASIQUE.valeurDesenchantement() * 2 + Rarete.LEGENDAIRE.valeurDesenchantement() * 2 + Rarete.EPIQUE.valeurDesenchantement() + Rarete.EPIQUE.valeurDesenchantementDoree();
		assertEquals(oracle, Filtre.gainDesenchantementTotal(cartes));
	}
	
	// Test en cas de paramètre null
	@Test(expected = NullPointerException.class)
	public void testGainDesanchantementTotal2() throws Exception {
		Filtre.gainDesenchantementTotal(null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testGainDesenchantementTotalVide() throws Exception {
		assertEquals(0, Filtre.gainDesenchantementTotal(new LinkedList<>()));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testPossibleDeCreerTrue() {
		System.out.println();
		assertTrue(Filtre.possibleDeCreer(cartes, minCout));
	}
	
	// Test en cas de Collection vide
	@Test
	public void testPossibleDeCreerFalse() {
		assertFalse(Filtre.possibleDeCreer(cartes, minCout - 1));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testPossibleDeCreer2() throws Exception {
		Filtre.possibleDeCreer(null, 0);
	}
	
	@Test
	public void testPossibleDeCreerVide() throws Exception {
		assertTrue(Filtre.possibleDeCreer(new LinkedList<>(), 0));
	}
	
	// Test fonctionnement nominal
	@Test
	public void testCartesDenombrees() {
		Collection<Carte> cartes2 = new LinkedList<>();
		cartes2.addAll(Arrays.asList(a1, a1, a2, se1, se1, se1));
		
		Collection<Denombrement> oracle = new LinkedList<>();
		oracle.addAll(Arrays.asList(new Denombrement(a1, 2), new Denombrement(a2, 1), new Denombrement(se1, 3)));
		
		assertEquals(oracle, Filtre.cartesDenombrees(cartes2));
	}
	
	// Test en cas de liste null
	@Test(expected = NullPointerException.class)
	public void testCartesDenombrees2() throws Exception {
		Filtre.cartesDenombrees(null);
	}
	
	// Test en cas de Collection vide
	@Test
	public void testCartesDenombreesVide() throws Exception {
		assertEquals(new LinkedList<>(), Filtre.cartesDenombrees(new LinkedList<>()));
	}
}