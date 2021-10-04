package hearthstone.carte;

import hearthstone.exception.ValeurNegativeException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by E174621F on 18/06/18
 */
public class CarteDTest{

    @Test
    public void degats() throws Exception{                                                                                                                                                           //Vérifie si degats garde sa valeur avec en paramètre une valeur supérieur à 0
        CarteD goldo = new Serviteur("Goldorak", 5, "Le vrai Goldorak", Rarete.LEGENDAIRE, Classe.PALADIN, "", "", 666, 666, Race.MECA); //suite à l'appelle de la fonction degats()
        assertEquals("degats cassé",666,goldo.degats());
    }

    @Test
    public void degats2() throws Exception{                                                                                                         //Vérifie si degats garde sa valeur avec en paramètre une valeur égal à 0
        Arme arme = new Arme("test",0,"nani",Rarete.EPIQUE,Classe.NEUTRE,"","",0,10);     //suite à l'appelle de la fonction degats()
        assertEquals("degats cassé",0,arme.degats());
    }

    @Test(expected= ValeurNegativeException.class)
    public void degats3() throws Exception{                                                                                            //Vérifie le renvoie une exception quand la valeur de degats est inférieure à 0
        new Arme("test",0,"nani",Rarete.EPIQUE,Classe.NEUTRE,"","",-5,10);   //suite à l'appelle de la fonction degats()
    }
}