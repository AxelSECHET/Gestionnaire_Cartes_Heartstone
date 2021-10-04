package ihm.vue;

import hearthstone.carte.Classe;
import hearthstone.carte.Race;
import hearthstone.carte.Rarete;
import hearthstone.cartes.Cartes;
import ihm.controleur.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Vector;

/**
 * Created by E175079D on 21/06/18
 */
public class NouvelleCarte extends JDialog{

    private JLabel nomNom;
    private JLabel nomCoutMana;
    private JLabel nomPointVie;
    private JLabel nomDegat;
    private JLabel nomDurabilite;
    private JLabel nomUrl;
    private JLabel nomUrlDoree;
    private JLabel nomDescription;
    private JRadioButton sort;
    private JRadioButton arme;
    private JRadioButton serviteur;
    private JComboBox<Integer> coutMana;
    private JComboBox<Integer> pointVie;
    private JComboBox<Integer> pointDegat;
    private JComboBox<Integer> pointDurabilite;
    private JComboBox<Object> classe;
    private JComboBox<Object> race;
    private JComboBox<Object> rarete;
    private JTextField txtNom;
    private JTextField txtDescription;
    private JTextField url;
    private JTextField urlDoree;
    private JButton annuler;
    private JButton creer;


    public NouvelleCarte(FenetreMenu menu, Cartes cartes){
        super(menu, "Creation de carte");

        //Initialisation des JLabels
        nomNom = new JLabel("Nom");
        nomCoutMana = new JLabel("Cout en mana");
        nomPointVie = new JLabel("Nombre de points de vie");
        nomDegat= new JLabel("Nombre de points de dégats");
        nomDurabilite = new JLabel("Nombre de points de durabilite");
        nomUrl = new JLabel("Entrer l'url de l'image");
        nomUrlDoree = new JLabel("Entrer l'url de l'image doree");
        nomDescription = new JLabel("Entrer la description");

        //Initialisation des JRadioButton et du ButtonGroup associé
        sort = new JRadioButton("Sort");
        arme = new JRadioButton("Arme");
        serviteur = new JRadioButton("Serviteur");
        ButtonGroup radio = new ButtonGroup();
        radio.add(sort);
        radio.add(arme);
        radio.add(serviteur);
        

        //Initialisation des JComboBox
        coutMana = new JComboBox<>();
        pointVie = new JComboBox<>();
        pointDegat = new JComboBox<>();
        pointDurabilite = new JComboBox<>();
        classe = new JComboBox<>();
        race = new JComboBox<>();
        rarete = new JComboBox<>();

        //Initialisation des JTextField
        txtNom = new JTextField("");
        txtDescription = new JTextField("");
        url = new JTextField("");
        urlDoree = new JTextField("");

        //Initialisation des JButton
        annuler = new JButton("annuler");
        creer = new JButton("creer");

        //Initialisation des taille des JTextField
        txtNom.setPreferredSize(new Dimension( 200, 24 ) );
        txtDescription.setPreferredSize(new Dimension( 300, 30 ) );
        url.setPreferredSize(new Dimension( 200, 24 ) );
        urlDoree.setPreferredSize(new Dimension( 200, 24 ) );

//  Vecteur inséré dans les comboBox
        Vector classesposssible = new Vector<>();
        Vector racespossible = new Vector<>();
        Vector raretespossible = new Vector<>();
        Vector  manapossible = new Vector<>();
        Vector  pvpossible = new Vector<>();
        Vector  dgtpossible = new Vector<>();
        Vector  durapossible = new Vector<>();

        //Initialisation des valeurs de la JComboBox classe
        classesposssible.add(Classe.NEUTRE);
        classesposssible.add(Classe.DEMONISTE);
        classesposssible.add(Classe.DRUIDE);
        classesposssible.add(Classe.PALADIN);
        classesposssible.add(Classe.PRETRE);
        classesposssible.add(Classe.GUERRIER);
        classesposssible.add(Classe.VOLEUR);
        classesposssible.add(Classe.MAGE);
        classesposssible.add(Classe.CHAMAN);
        classesposssible.add(Classe.CHASSEUR);

        //Initialisation des valeurs de la JComboBox race
        racespossible.add(Race.BETE);
        racespossible.add(Race.DEMON);
        racespossible.add(Race.DRAGON);
        racespossible.add(Race.ELEMENTAIRE);
        racespossible.add(Race.MECA);
        racespossible.add(Race.MURLOC);
        racespossible.add(Race.PIRATE);
        racespossible.add(Race.TOTEM);

        //Initialisation des valeurs de la JComboBox rarete
        raretespossible.add(Rarete.BASIQUE);
        raretespossible.add(Rarete.COMMUNE);
        raretespossible.add(Rarete.RARE);
        raretespossible.add(Rarete.EPIQUE);
        raretespossible.add(Rarete.LEGENDAIRE);

        //Initialisation des valeurs de la JComboBox mana
        for (int i = 0; i <= 25; i++){
            manapossible.add(new Integer(i));
        }

        //Initialisation des valeurs de la JComboBox pv
        for (int i = 1; i <= 20; i++){
            pvpossible.add(new Integer(i));
        }

        //Initialisation des valeurs de la JComboBox dgt
        for (int i = 0; i <= 20; i++){
            dgtpossible.add(new Integer(i));
        }

        //Initialisation des valeurs de la JComboBox durabilite
        for (int i = 0; i <= 10; i++){
            durapossible.add(new Integer(i));
        }

//  Remplissage des JComboBox
        classe = new JComboBox(classesposssible);
        race = new JComboBox(racespossible);
        rarete = new JComboBox(raretespossible);
        coutMana = new JComboBox(manapossible);
        pointVie = new JComboBox(pvpossible);
        pointDegat = new JComboBox(dgtpossible);
        pointDurabilite = new JComboBox(durapossible);

//Panels principaux
        JPanel centre = new JPanel();
        JPanel validation = new JPanel();
        JPanel criteres = new JPanel();
        JPanel description = new JPanel();
        JPanel nomPan = new JPanel();
// Panels secondaires
        JPanel classePan = new JPanel();
        JPanel raretePan = new JPanel();
        JPanel racePan = new JPanel();
        JPanel manaPan = new JPanel();
        JPanel pvPan = new JPanel();
        JPanel dgtPan = new JPanel();
        JPanel duraPan = new JPanel();
        JPanel urlPan = new JPanel();
        JPanel urlDoreePan = new JPanel();

//  Ajout des label aux listes déroulantes d'entier sur les panels correspondant
//      pour qu'ils ne prennent qu'une seule case de la Grid au lieu de 2
//        (JLabel + JComboBox)
        classePan.add(classe);
        raretePan.add(rarete);
        racePan.add(race);
        manaPan.add(nomCoutMana);
        manaPan.add(coutMana);
        pvPan.add(nomPointVie);
        pvPan.add(pointVie);
        dgtPan.add(nomDegat);
        dgtPan.add(pointDegat);
        duraPan.add(nomDurabilite);
        duraPan.add(pointDurabilite);
        urlPan.add(nomUrl);
        urlPan.add(url);
        urlDoreePan.add(nomUrlDoree);
        urlDoreePan.add(urlDoree);


//  Panel du milieu, des criteres de creation d'une carte
        criteres.setLayout(new GridLayout(4,3));
        criteres.add(sort);
        criteres.add(arme);
        criteres.add(serviteur);
        criteres.add(classePan);
        criteres.add(raretePan);
        criteres.add(manaPan);
        criteres.add(racePan);
        criteres.add(pvPan);
        criteres.add(dgtPan);
        criteres.add(duraPan);
        criteres.add(urlPan);
        criteres.add(urlDoreePan);

//  Panel du haut, du nom
        nomPan.setLayout(new FlowLayout());
        nomPan.add(nomNom);
        nomPan.add(txtNom);
//  Panel du mileu-bas, de la description
        description.setLayout(new FlowLayout());
        description.add(nomDescription);
        description.add(nomDescription);
        description.add(txtDescription);
//  Panel du bas, validation ou annulation
        validation.setLayout(new FlowLayout());
        validation.add(annuler);
        validation.add(creer);

//  Panel milieu, criteres + description
        centre.setLayout(new BorderLayout());
        centre.add(criteres, BorderLayout.CENTER);
        centre.add(description, BorderLayout.SOUTH);
        centre.add(nomPan, BorderLayout.NORTH);

//  Ajout controller
        serviteur.addActionListener(new ActivationServiteur(this));
        sort.addActionListener(new ActivationSort(this));
        arme.addActionListener(new ActivationArme(this));
        annuler.addActionListener(new JDialogClose(this));
        creer.addActionListener(new NouvelleCarteValidation(menu, this, cartes));
        
        // Click sur le bouton automatique
        radio.getElements().nextElement().doClick();

//  Panel global
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(centre, BorderLayout.CENTER);
        this.getContentPane().add(validation, BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(800, 450));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocation(300,300);
        this.pack();
        this.setVisible(true);
    }

    //Donne si le JRadioButton arme est sélectionnée
    public boolean getArme(){
        return arme.isSelected();
    }
    //Donne si le JRadioButton sort est sélectionnée
    public boolean getSort(){
        return sort.isSelected();
    }
    //Donne si le JRadioButton serviteur est sélectionnée
    public boolean getServiteur(){
        return serviteur.isSelected();
    }
    //Donne la classe sélectionnée dans la JComboBox
    public Classe getClasse(){
        return (Classe) classe.getSelectedItem();
    }
    //Donne la race sélectionnée dans la JComboBox
    public Race getRace(){
        return (Race) race.getSelectedItem();
    }
    //Donne la rareté sélectionnée dans la JComboBox
    public Rarete getRarete(){
        return (Rarete) rarete.getSelectedItem();
    }
    //Donne le coût en mana de la carte sélectionnée dans la JComboBox
    public int getCoutMana(){
        return (int) coutMana.getSelectedItem();
    }
    //Donne les points de vie de la carte sélectionnée dans la JComboBox
    public int getPointVie(){
        return (int) pointVie.getSelectedItem();
    }
    //Donne les points de dégâts qu'inflige la carte sélectionnée dans la JComboBox
    public int getPointDegat(){
        return (int) pointDegat.getSelectedItem();
    }
    //Donne les points de durabilité de la carte sélectionnée dans la JComboBox
    public int getPointDurabilite(){
        return (int) pointDurabilite.getSelectedItem();
    }
    //Donne le nom de la carte écrit dans le TextField
    public String getNom(){
        return txtNom.getText();
    }
    //Donne la description de la carte écrit dans le TextField
    public String getDescription(){
        return txtDescription.getText();
    }
    //Donne l'url de la carte écrit dans le TextField
    public String getUrl(){
        return url.getText();
    }
    //Donne l'urlDoree de la carte écrit dans le TextField
    public String getUrlDoree(){
        return urlDoree.getText();
    }

    //Permet l'activation et la désactivation des options voulues quand on sélectionne le JRadioButton serviteur
    public void activationServiteur(){
        race.setEnabled(true);
        pointVie.setEnabled(true);
        pointDegat.setEnabled(true);
        pointDurabilite.setEnabled(false);
    }

    //Permet l'activation et la désactivation des options voulues quand on sélectionne le JRadioButton sort
    public void activationSort(){
        race.setEnabled(false);
        pointVie.setEnabled(false);
        pointDegat.setEnabled(false);
        pointDurabilite.setEnabled(false);
    }

    //Permet l'activation et la désactivation des options voulues quand on sélectionne le JRadioButton arme
    public void activationArme(){
        race.setEnabled(false);
        pointVie.setEnabled(false);
        pointDegat.setEnabled(true);
        pointDurabilite.setEnabled(true);
    }


}
