
import hearthstone.carte.*;
import hearthstone.cartes.Cartes;
import hearthstone.cartes.FabriqueJson;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCarte {


    @Test
    public void test() throws Exception {
        Carte arme = new Arme("Marteau de Thor",10, "Marteau de Thor...", Rarete.LEGENDAIRE, Classe.GUERRIER, 100, 100);
        assertEquals("manna different",10, arme.mana());
    }

    @Test
    public void test2() throws Exception {
        Cartes cartes = new Cartes(FabriqueJson.lireCartesDepuisFichier("json/initial.json"));
        assertEquals(3, cartes.collection().size());
    }

}