package hearthstone.carte;

import hearthstone.exception.ValeurNegativeException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by E174621F on 18/06/18
 */
public class ServiteurTest {
    String url, urldorre;
    Serviteur test, goldo;

    @Before
    public void setUp() throws Exception {
        url = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.king-games.fr%2F28521-17293-thickbox_default%2Fgoldorak-metal-color-40cm.jpg&imgrefurl=https%3A%2F%2Fwww.king-games.fr%2Fgoldorak%2F28521-goldorak-metal-color-40cm-4589504961131.html&docid=12x0RAPJf_HeEM&tbnid=V2YnWkMFlX6nRM%3A&vet=10ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg2KAQwBA..i&w=800&h=800&client=ubuntu&bih=923&biw=1920&q=goldorak&ved=0ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg2KAQwBA&iact=mrc&uact=8";
        urldorre="https://www.google.com/imgres?imgurl=http%3A%2F%2Fwww.lepoint.fr%2Fimages%2F2016%2F07%2F22%2F4749117lpw-4857094-article-jpg_3682081.jpg&imgrefurl=http%3A%2F%2Fwww.lepoint.fr%2Fpop-culture%2Flivres%2Fune-epopee-rejouissante-au-pays-de-goldorak-22-07-2016-2056261_2945.php&docid=AVqEs2yZJwDPcM&tbnid=yI7gDl7mSVM-NM%3A&vet=10ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg-KAwwDA..i&w=980&h=426&client=ubuntu&bih=923&biw=1920&q=goldorak&ved=0ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg-KAwwDA&iact=mrc&uact=8";
        test = new Serviteur("",0,"",Rarete.BASIQUE,Classe.NEUTRE,0,0,Race.ELEMENTAIRE);
        goldo = new Serviteur("Goldorak",5,"Le vrai Goldorak",Rarete.LEGENDAIRE,Classe.PALADIN,url,urldorre,666,666,Race.MECA);
    }
    //Test des points de vie aux limites.
    @Test
    public void pointSDeVie() throws Exception {
        assertEquals("PV cassé",666,goldo.pointSDeVie());
    }

    @Test
    public void pointSDeVie2() throws Exception {
        assertEquals("PV cassé",0,test.pointSDeVie());
    }

    @Test(expected= ValeurNegativeException.class)
    public void pointSDeVie3() throws Exception {
        new Serviteur("",-5,"",Rarete.BASIQUE,Classe.NEUTRE,0,0,Race.ELEMENTAIRE);
    }
    //Vérification de la référence à la race
    @Test
    public void race() throws Exception {
        assertEquals("race cassé",Race.MECA,goldo.race());
    }


}