package hearthstone.carte;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by E174621F on 18/06/18
 */
public class RareteTest {
    //Vérification des références pour toutes les raretés
    @Test
    public void gemme() throws Exception {
        assertEquals("gemme cassé", Color.WHITE,Rarete.COMMUNE.gemme());
    }

    @Test
    public void gemme2() throws Exception {
        assertEquals("gemme cassé", Color.BLUE,Rarete.RARE.gemme());
    }

    @Test
    public void gemme3() throws Exception {
        assertEquals("gemme cassé", Color.PINK,Rarete.EPIQUE.gemme());
    }

    @Test
    public void gemme4() throws Exception {
        assertEquals("gemme cassé", Color.ORANGE,Rarete.LEGENDAIRE.gemme());
    }

    @Test
    public void valeurDesenchantement() throws Exception {
        assertEquals("desenchantement cassé", 5,Rarete.COMMUNE.valeurDesenchantement());
    }

    @Test
    public void valeurDesenchantement2() throws Exception {
        assertEquals("desenchantement cassé", 20,Rarete.RARE.valeurDesenchantement());
    }

    @Test
    public void valeurDesenchantement3() throws Exception {
        assertEquals("desenchantement cassé", 100,Rarete.EPIQUE.valeurDesenchantement());
    }

    @Test
    public void valeurDesenchantement4() throws Exception {
        assertEquals("desenchantement cassé", 400,Rarete.LEGENDAIRE.valeurDesenchantement());
    }

    @Test
    public void valeurCreation() throws Exception {
        assertEquals("creation cassé", 40,Rarete.COMMUNE.valeurCreation());
    }

    @Test
    public void valeurCreation2() throws Exception {
        assertEquals("creation cassé", 100,Rarete.RARE.valeurCreation());
    }

    @Test
    public void valeurCreation3() throws Exception {
        assertEquals("creation cassé", 400,Rarete.EPIQUE.valeurCreation());
    }

    @Test
    public void valeurCreation4() throws Exception {
        assertEquals("creation cassé", 1600,Rarete.LEGENDAIRE.valeurCreation());
    }

    @Test
    public void valeurCreationDoree() throws Exception {
        assertEquals("creationdoree cassé", 400,Rarete.COMMUNE.valeurCreationDoree());
    }

    @Test
    public void valeurCreationDoree2() throws Exception {
        assertEquals("creationdoree cassé", 800,Rarete.RARE.valeurCreationDoree());
    }

    @Test
    public void valeurCreationDoree3() throws Exception {
        assertEquals("creationdoree cassé", 1600,Rarete.EPIQUE.valeurCreationDoree());
    }

    @Test
    public void valeurCreationDoree4() throws Exception {
        assertEquals("creationdoree cassé", 3200,Rarete.LEGENDAIRE.valeurCreationDoree());
    }

    @Test
    public void valeurDesenchantementDoree() throws Exception {
        assertEquals("desenchantementdoree cassé", 50,Rarete.COMMUNE.valeurDesenchantementDoree());
    }

    @Test
    public void valeurDesenchantementDoree2() throws Exception {
        assertEquals("desenchantementdoree cassé", 100,Rarete.RARE.valeurDesenchantementDoree());
    }

    @Test
    public void valeurDesenchantementDoree3() throws Exception {
        assertEquals("desenchantementdoree cassé", 400,Rarete.EPIQUE.valeurDesenchantementDoree());
    }

    @Test
    public void valeurDesenchantementDoree4() throws Exception {
        assertEquals("desenchantementdoree cassé", 1600,Rarete.LEGENDAIRE.valeurDesenchantementDoree());
    }

}