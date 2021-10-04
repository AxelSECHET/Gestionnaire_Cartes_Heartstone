package hearthstone.carte;

import hearthstone.exception.ValeurNegativeException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by E174621F on 18/06/18
 */
public class ArmeTest {

    @Test
    public void durabilite() throws Exception {                                                                                                         //Vérifie si la durabilité garde sa valeur avec en paramètre une valeur supérieur à 0
        Arme arme = new Arme("test",0,"nani",Rarete.EPIQUE,Classe.NEUTRE,"","",5,10);         //suite à l'appelle de la fonction durabilite()
        assertEquals("durabilité cassé",10, arme.durabilite());
    }

    @Test
    public void durabilite2() throws Exception {                                                                                                        //Vérifie si la durabilité garde sa valeur avec en paramètre 0
        Arme arme = new Arme("test",0,"nani",Rarete.EPIQUE,Classe.NEUTRE,"","",5,0);          //suite à l'appelle de la fonction durabilite()
        assertEquals("durabilité cassé",0, arme.durabilite());
    }

    @Test(expected= ValeurNegativeException.class)
    public void durabilite3() throws Exception {                                                                                                               //Vérifie le renvoie une exception quand la valeur de durabilite est inférieure à 0
         new Arme("test",0,"nani",Rarete.EPIQUE,Classe.NEUTRE,"","",5,-10);                         //suite à l'appelle de la fonction durabilite()
    }
}