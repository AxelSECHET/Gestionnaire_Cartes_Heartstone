package hearthstone.cartes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import hearthstone.carte.Arme;
import hearthstone.carte.Carte;
import hearthstone.carte.Serviteur;
import hearthstone.carte.Sort;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe statique permettant de manipuler une représentation au format JSON pour les cartes
 */

public class FabriqueJson {

    /**
     * transforme une chaine de caractères contenant du JSON en une collection de cartes
     * @param jsonString la chaine de caractères
     * @return la collection de cartes résulat
     */
    public static Collection<Carte> deserialiseJson(String jsonString) {
        // DéSérialisation
        Collection<Carte> cartes = donneGson().fromJson(jsonString, new TypeToken<List<Carte>>(){}.getType());
        // Il n'y a pas besoin de faire appel au constructeur de carte,
        // car donneGson va permettre de préciser le type de la carte
        // tandis que le TypeToken va permettre de remplacer les attributs du Gson par les paramètres de l'Objet.

        // élimination des cartes à coup sûr avec une image non existante
        for (Iterator<Carte> ic = cartes.iterator(); ic.hasNext(); ) {
            Carte curr = ic.next();
            curr.verifie();
            if (curr.urlImage().contains("wow.zamimg.com"))
                ic.remove();
        }
        return cartes;
    }

    /**
     *
     * execute une requete vers un webservice et récupère une collection de cartes répondant au critère de recherche
     * @param recherche le critère de recherche
     * @return la collection de cartes résulat
     * @throws UnirestException si la requete n'a pas pu s'executer correctement
     */
    public static Collection<Carte> donneCartesDepuisInternet(String recherche) throws UnirestException {
        // requête synchrone pour obtenir une carte par son nom
        HttpResponse<String> reponseHttp =
                Unirest.get("https://omgvamp-hearthstone-v1.p.mashape.com/cards/search/" + recherche)
                        .header("X-Mashape-Key", "GLOLMGEX13mshG5yCp9V7VXetQXsp1foB5BjsnDCsANSXBrL7H")
                        .asString();
        // si on obtient une réponse valide
        if (reponseHttp != null && reponseHttp.getStatus() == 200) {
            return deserialiseJson(reponseHttp.getBody());
        }
        //Si la requête échoue cela envoie automatiquement l'exception UnirestException
        return null;
    }

    /**
     * lit un fichier JSON et creé une collection de cartes avec les données lues
     * @param fichier le fichier à lire
     * @return la collection de cartes résulat
     * @throws IOException en cas de problème de lecture du fichier
     */
    public static Collection<Carte> lireCartesDepuisFichier(String fichier) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fichier));
        String json = "";
        String ligne;
        while ((ligne = br.readLine()) != null) {
            json += ligne;
        }
        br.close();
        return deserialiseJson(json);
    }

    /**
     * créé, à partir d'une collection de cartes, une chaine de caractère contenant du JSON
     * @param cartes la collection de cartes à sérialiser
     * @return la chaine de caractères JSON
     */
    public static String serialiseJon(Collection<Carte> cartes) {
        List<Carte> liste = new LinkedList<>(cartes);
        String jsonString = donneGson().toJson(liste, new TypeToken<List<Carte>>(){}.getType());
        return jsonString;
    }

    /**
     * écrit une collection de cartes dans un fichier au format JSON
     * @param cartes la collection de cartes à sauvegarder
     * @param fichier le fichier dans lequel effectuer la sauvegarde
     * @throws IOException en cas de problème d'écriture dans le fichier
     */
    public static void ecrireCartesDansFichier(Collection<Carte> cartes, String fichier) throws IOException {
        BufferedWriter bw = new BufferedWriter( new FileWriter(fichier));
        bw.write(serialiseJon(cartes));
        bw.close();
    }


    private static Gson donneGson() {
        RuntimeTypeAdapterFactory<Carte> carteAdapterFactory
                = RuntimeTypeAdapterFactory.of(Carte.class, "type")
                .registerSubtype(Serviteur.class, "Minion")
                .registerSubtype(Sort.class, "Spell")
                .registerSubtype(Arme.class, "Weapon");
        return new GsonBuilder()
                .registerTypeAdapterFactory(carteAdapterFactory)
                .create();
    }

}



