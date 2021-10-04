package ihm.vue;

import hearthstone.carte.Carte;
import hearthstone.carte.Classe;
import hearthstone.cartes.Cartes;
import ihm.controleur.JDialogClose;
import ihm.controleur.NouveauDeckValidation;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by E175079D on 21/06/18.
 */
public class NouveauDeck extends JDialog{

    private JLabel nomClasse;
    private JLabel nomDeck;
    private JTextField choixNomDeck;
    private JComboBox<Object> choixClasse;
    private JButton annuler;
    private JButton creer;
//CONSTRUCTEUR A VERIFIER
    public NouveauDeck (FenetreMenu menu, Cartes cartes){
        super(menu, "Creation Deck");

        Vector classesposssible = new Vector<>();
//      Création de la ComboBox contenant les classes possibles pour les decks
        classesposssible.add(Classe.DEMONISTE);
        classesposssible.add(Classe.DRUIDE);
        classesposssible.add(Classe.PALADIN);
        classesposssible.add(Classe.PRETRE);
        classesposssible.add(Classe.GUERRIER);
        classesposssible.add(Classe.VOLEUR);
        classesposssible.add(Classe.MAGE);
        classesposssible.add(Classe.CHAMAN);
        classesposssible.add(Classe.CHASSEUR);

//      Creation des differents JComponents
        nomClasse = new JLabel("classe");
        nomDeck = new JLabel("nom deck");
        choixNomDeck = new JTextField("");
        choixClasse = new JComboBox<Object>(classesposssible);
        choixClasse.setSelectedIndex(0);
        annuler = new JButton("annuler");
        creer = new JButton("creer");

//       Creation des differents JPanel pour organiser la mise en page
        JPanel saisie = new JPanel();
        JPanel validation = new JPanel();
        JPanel entrerNom = new JPanel();
        JPanel choisirClasse = new JPanel();

        choixNomDeck.setPreferredSize(new Dimension( 150, 24 ) );

//      Ajout des JComponents aux JPanels
        entrerNom.add(nomDeck);
        entrerNom.add(choixNomDeck);
        choisirClasse.add(nomClasse);
        choisirClasse.add(choixClasse);
        validation.add(annuler);
        validation.add(creer);

//      Ajout des JPanel au JPanel supérieur
        saisie.setLayout(new BoxLayout(saisie, BoxLayout.Y_AXIS));
        saisie.add(entrerNom);
        saisie.add(choisirClasse);

//      AJout des JPanel supérieur à la fenetre
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(saisie, BorderLayout.CENTER);
        this.getContentPane().add(validation, BorderLayout.SOUTH);

//      Ajout des controleurs
        annuler.addActionListener(new JDialogClose(this));
        creer.addActionListener(new NouveauDeckValidation(this, cartes,menu));

//      Configuration de la fenêtre créé
        this.setPreferredSize(new Dimension(300, 200));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(300,300);
        this.pack();
        this.setVisible(true);
    }

    //Donne le nom du deck voulu
    public String getNomDeck(){
        return choixNomDeck.getText();
    }

    //Donne la classe sélectionnée
    public Classe getChoixClasse(){
        return (Classe) choixClasse.getSelectedItem();
    }
    
    public void erreur(String titre, Object message) {
        JOptionPane.showMessageDialog(this, message, titre, JOptionPane.ERROR_MESSAGE);
    }
}
