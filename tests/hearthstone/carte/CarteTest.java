package hearthstone.carte;

import hearthstone.exception.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by E174621F on 18/06/18
 */
public class CarteTest {
    private String url, urldorre;
    private Carte test, goldo, golde;

    @Before
    public void setUp() throws Exception {
        url = "https://www.google.com/imgres?imgurl=https%3A%2F%2Fwww.king-games.fr%2F28521-17293-thickbox_default%2Fgoldorak-metal-color-40cm.jpg&imgrefurl=https%3A%2F%2Fwww.king-games.fr%2Fgoldorak%2F28521-goldorak-metal-color-40cm-4589504961131.html&docid=12x0RAPJf_HeEM&tbnid=V2YnWkMFlX6nRM%3A&vet=10ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg2KAQwBA..i&w=800&h=800&client=ubuntu&bih=923&biw=1920&q=goldorak&ved=0ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg2KAQwBA&iact=mrc&uact=8";
        urldorre="https://www.google.com/imgres?imgurl=http%3A%2F%2Fwww.lepoint.fr%2Fimages%2F2016%2F07%2F22%2F4749117lpw-4857094-article-jpg_3682081.jpg&imgrefurl=http%3A%2F%2Fwww.lepoint.fr%2Fpop-culture%2Flivres%2Fune-epopee-rejouissante-au-pays-de-goldorak-22-07-2016-2056261_2945.php&docid=AVqEs2yZJwDPcM&tbnid=yI7gDl7mSVM-NM%3A&vet=10ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg-KAwwDA..i&w=980&h=426&client=ubuntu&bih=923&biw=1920&q=goldorak&ved=0ahUKEwjg883y6tzbAhUBbxQKHeTHBngQMwg-KAwwDA&iact=mrc&uact=8";
        test = new Serviteur("",0,"",Rarete.BASIQUE,Classe.NEUTRE,0,0,Race.ELEMENTAIRE);
        goldo = new Serviteur("Goldorak",5,"Le vrai Goldorak",Rarete.LEGENDAIRE,Classe.PALADIN,url,urldorre,666,666,Race.MECA);
        golde = Carte.fabriquerCarteDoree(goldo);
    }

    @Test
    public void mana() throws Exception {                                   //Vérifie si la mana garde sa valeur avec une valeur supérieur à 0
        assertEquals("mana cassé",5, goldo.mana());       //suite à l'appelle de la fonction mana()
    }

    @Test
    public void mana2() throws Exception {                                  //Vérifie si la mana garde sa valeur avec une valeur égal à 0
        assertEquals("mana cassé",0, test.mana());        //suite à l'appelle de la fonction mana()
    }

    @Test(expected= ValeurNegativeException.class)
    public void mana3() throws Exception {                                                                      //Vérifie le renvoie une exception quand la valeur de degats est inférieure à 0
        new Serviteur("",-5,"",Rarete.BASIQUE,Classe.NEUTRE,0,0,Race.ELEMENTAIRE);   //suite à l'appelle de la fonction mana()
    }

    @Test
    public void nom() throws Exception {                                        //Vérifie si la nom possède une majuscule au début et des minuscules ensuite
        assertEquals("nom cassé","Goldorak", goldo.nom());    //suite à l'appelle de la fonction nom()
    }

    @Test
    public void description() throws Exception {                                                        //Vérifie si la description reste inchangée
        assertEquals("description cassé","Le vrai Goldorak", goldo.description());    //suite à l'appelle de la fonction description()
    }

    @Test
    public void description2() throws Exception {
        test.verifie();                                                                 //Vérifie si la description reste inchangée avec aucun caractère
        assertEquals("description cassé","", test.description());     //suite à l'appelle de la fonction description()
    }

    @Test
    public void descriptionCourte() throws Exception {                                                          //Vérifie si la description est raccourcie jusqu'à 10 caractères
        assertEquals("descriptionCourte cassé","Le vrai Go", goldo.descriptionCourte());      //suite à l'appelle de la fonction descriptionCourte()
    }

    @Test
    public void descriptionCourte2() throws Exception {                                         //Vérifie si la description reste inchangée avec aucun caractère
        test.verifie();                                                                         //suite à l'appelle de la fonction descriptionCourte()
        assertEquals("description cassé","", test.descriptionCourte());
    }

    @Test
    public void classe() throws Exception {                                             //Vérifie si la classe de la carte reste inchangée
        assertEquals("classe cassé",Classe.PALADIN, goldo.classe());           //suite à l'appelle de la fonction classe()
    }

    @Test
    public void rarete() throws Exception {                                             //Vérifie si la rarete de la carte reste inchangée
        assertEquals("rarete cassé",Rarete.LEGENDAIRE, goldo.rarete());        //suite à l'appelle de la fonction rarete()
    }

    @Test
    public void urlImage() throws Exception {                               //Vérifie si l'url de l'image de la carte reste inchangée
        assertEquals("url cassé",url,goldo.urlImage());            //suite à l'appelle de la fonction urlImage()
    }

    @Test
    public void urlImage2() throws Exception {                              //Vérifie si l'url de l'image de la carte reste inchangée avec aucun caractère
        test.verifie();                                                     //suite à l'appelle de la fonction urlImage()
        assertEquals("url cassé","",test.urlImage());
    }

    @Test
    public void urlImageDoree() throws Exception {                                  //Vérifie si l'url de l'image en version dorée de la carte reste inchangée
        assertEquals("urldoree cassé",urldorre, golde.urlImage());         //suite à l'appelle de la fonction urlImage()
    }

    @Test
    public void estDoree() throws Exception {                                           //Vérifie si la fonction renvoie le résultat voulu avec une carte dorée
        assertEquals("estdoree cassé",true, golde.estDoree());        //suite à l'appelle de la fonction estDoree()
    }

    @Test
    public void estDoree2() throws Exception {                                          //Vérifie si la fonction renvoie le résultat voulu avec une carte non dorée
        assertEquals("estdoree cassé",false, goldo.estDoree());       //suite à l'appelle de la fonction estDoree()
    }

    @Test
    public void estJouable() throws Exception {                                                             //Vérifie si la fonction renvoie le résultat voulu avec une réserve de mana suffisante pour jouer la carte
        assertEquals("estjouable cassé",true, goldo.estJouable(10));          //suite à l'appelle de la fonction estJouable()
    }

    @Test
    public void estJouable2() throws Exception {                                                            //Vérifie si la fonction renvoie le résultat voulu avec une réserve de mana insuffisante pour jouer la carte
        assertEquals("estjouable cassé",false, goldo.estJouable(2));          //suite à l'appelle de la fonction estJouable()
    }

    @Test
    public void estEgalModuloDoree() throws Exception {
        Carte golde = Carte.fabriquerCarteDoree(goldo);
        assertEquals("estEgal cassé",true, goldo.estEgalModuloDoree(golde));
    }

    @Test
    public void estEgalModuloDoree2() throws Exception {
        Carte test2 = new Serviteur("uyffuzeufguy",0,"",Rarete.EPIQUE,Classe.NEUTRE,"","",0,0,Race.ELEMENTAIRE);
        assertEquals("estEgal cassé",false, goldo.estEgalModuloDoree(test2));
    }

    @Test
    public void coutCreation() throws Exception {
        assertEquals("coutCreation cassé",Rarete.LEGENDAIRE.valeurCreation(), goldo.coutCreation());
    }

    @Test
    public void coutCreation2() throws Exception{
        assertEquals("coutCreation cassé", Rarete.LEGENDAIRE.valeurCreationDoree(), golde.coutCreation());
    }

    @Test(expected= CoutCreationException.class)
    public void coutCreation3() throws Exception {
        test.verifie();
        test.coutCreation();
    }

    @Test
    public void gainDesenchantement() throws Exception {
        assertEquals("coutDesenchantement cassé",Rarete.LEGENDAIRE.valeurDesenchantement(), goldo.gainDesenchantement());
    }

    @Test
    public void gainDesenchantement2() throws Exception {
        assertEquals("coutDesenchantement cassé",Rarete.LEGENDAIRE.valeurDesenchantementDoree(), golde.gainDesenchantement());
    }

    @Test(expected=GainDesenchantementException.class)
    public void gainDesenchantement3() throws Exception {
        test.verifie();
        test.gainDesenchantement();
    }

    @Test(expected=CarteDoreeException.class)
    public void fabriquerCarteDoree() throws Exception {
        Carte.fabriquerCarteDoree(golde);
    }

    @Test(expected=CarteBasiqueException.class)
    public void fabriquerCarteDoree2() throws Exception {
        test.verifie();
        Carte.fabriquerCarteDoree(test);
    }

    @Test
    public void fabriquerCarteDoree3() throws Exception {
        assertEquals("fabriqueCarteDoree cassé",true, golde.estDoree());
    }

    @Test
    public void fabriquerCarteDoree4() throws Exception {
        Carte sort = new Sort("Fistiland",5,"Le fond du fun",Rarete.LEGENDAIRE,Classe.PALADIN,url,urldorre);
        Carte sortgolde = Carte.fabriquerCarteDoree(sort);
        assertEquals("fabriqueCarteDoree cassé",true, sortgolde.estDoree());
    }

    @Test
    public void fabriquerCarteDoree5() throws Exception {
        Carte arme = new Arme("test",0,"nani",Rarete.EPIQUE,Classe.NEUTRE,"","",0,0);
        Carte armegolde = Carte.fabriquerCarteDoree(arme);
        assertEquals("fabriqueCarteDoree cassé",true, armegolde.estDoree());
    }
}