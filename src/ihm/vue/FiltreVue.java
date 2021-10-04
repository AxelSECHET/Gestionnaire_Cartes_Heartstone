package ihm.vue;

import javax.swing.*;
import hearthstone.carte.*;
import hearthstone.cartes.Cartes;
import ihm.controleur.FiltreVueValidation;
import ihm.controleur.JDialogClose;
import ihm.controleur.ActivationRace;

import java.awt.*;
import java.util.Vector;

/**
 * Created by E174557L on 21/06/18.
 */
public class FiltreVue  extends JDialog{
    private JLabel nomClasse;
    private JLabel nomRace;
    private JLabel nomRarete;
    private JComboBox choixClasse;
    private JComboBox choixRace;
    private JComboBox choixRarete;
    private JCheckBox arme;
    private JCheckBox serviteur;
    private JCheckBox doree;
    private JCheckBox sort;
    private JButton annuler;
    private JButton appliquer;

    public FiltreVue(FenetreMenu menu,Cartes cartes){
        super(menu);
        this.getContentPane().setLayout(new BorderLayout());
        //création des panels
        JPanel rButton = new JPanel();
        JPanel cBox = new JPanel();
        JPanel buttons = new JPanel();

        //Vecteur inséré dans les comboBox
        Vector classesposssible = new Vector<>();
        Vector racespossible = new Vector<>();
        Vector raretespossible = new Vector<>();
        //Les données dans le vecteur des classes
        classesposssible.add("");
        classesposssible.add(Classe.DEMONISTE);
        classesposssible.add(Classe.DRUIDE);
        classesposssible.add(Classe.PALADIN);
        classesposssible.add(Classe.PRETRE);
        classesposssible.add(Classe.GUERRIER);
        classesposssible.add(Classe.VOLEUR);
        classesposssible.add(Classe.MAGE);
        classesposssible.add(Classe.CHAMAN);
        classesposssible.add(Classe.CHASSEUR);
        //Les données dans le vecteur des Races
        racespossible.add("");
        racespossible.add(Race.BETE);
        racespossible.add(Race.DEMON);
        racespossible.add(Race.DRAGON);
        racespossible.add(Race.ELEMENTAIRE);
        racespossible.add(Race.MECA);
        racespossible.add(Race.MURLOC);
        racespossible.add(Race.PIRATE);
        racespossible.add(Race.TOTEM);
        //les données dans le vecteur des Raretés
        raretespossible.add("");
        raretespossible.add(Rarete.BASIQUE);
        raretespossible.add(Rarete.COMMUNE);
        raretespossible.add(Rarete.RARE);
        raretespossible.add(Rarete.EPIQUE);
        raretespossible.add(Rarete.LEGENDAIRE);

//   Instanciation des différents JComponents
        nomClasse = new JLabel("Classe");
        nomRace = new JLabel("race");
        nomRarete = new JLabel("Rarete");
        choixClasse = new JComboBox<Object>(classesposssible);
        choixRace = new JComboBox(racespossible);
        choixRarete = new JComboBox(raretespossible);
        arme = new JCheckBox("Arme");
        serviteur = new JCheckBox("Serviteur");
        doree = new JCheckBox("Dorée");
        sort = new JCheckBox("Sort");
        annuler = new JButton("ANNULER");
        appliquer = new JButton("APPLIQUER");

        choixClasse.setSelectedItem("Classe");
        choixRace.setSelectedItem("Race");
        choixRarete.setSelectedItem("Rareté");

//   Ajout des bouton au panel du haut
        rButton.add(arme);
        rButton.add(serviteur);
        rButton.add(sort);
        rButton.add(doree);

//   Ajout des listes déroulantes et des labels au panel central
        cBox.add(nomClasse, BorderLayout.NORTH);
        cBox.add(choixClasse,BorderLayout.NORTH);
        cBox.add(nomRace, BorderLayout.WEST);
        cBox.add(choixRace,BorderLayout.WEST);
        cBox.add(nomRarete, BorderLayout.EAST);
        cBox.add(choixRarete,BorderLayout.EAST);
//   Ajout des Boutons au panel inférieur
        buttons.add(annuler);
        buttons.add(appliquer);

//   Ajout controller
        appliquer.addActionListener(new FiltreVueValidation(this, menu, cartes));
        annuler.addActionListener(new JDialogClose(this));
        serviteur.addActionListener(new ActivationRace(this));


// Panel global
        this.add(rButton,BorderLayout.NORTH);
        this.add(cBox,BorderLayout.CENTER);
        this.add(buttons,BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(550,150));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocation(200,300);
        this.setVisible(true);
    }
// Getters
    public Object getClasse(){
       return choixClasse.getSelectedItem();
    }
    public Object getRace(){
        return choixRace.getSelectedItem();
    }
    public Object getRarete(){
        return choixRarete.getSelectedItem() ;
    }
    public boolean getArme(){
        return arme.isSelected();
    }
    public boolean getSort(){
        return sort.isSelected();
    }
    public boolean getServiteur(){
        return serviteur.isSelected();
    }
    public boolean getDoree(){
        return doree.isSelected();
    }
// Setters
    public void setArme(boolean s){
        arme.setSelected(s);
    }
    public void setServiteur(boolean s){
        serviteur.setSelected(s);
    }
    public void setDoree(boolean s){
        doree.setSelected(s);
    }
    public void setSort(boolean s){
        sort.setSelected(s);
    }
    public void setChoixClasse(Classe c){
        choixClasse.setSelectedItem(c);
    }
    public void setChoixRace(Race r){
        choixRace.setSelectedItem(r);
    }
    public void setChoixRarete(Rarete r){
        choixRarete.setSelectedItem(r);
    }
    public void activeRace(){
        choixRace.setEnabled(true);
    }
    public void desactiveRace(){
        choixRace.setEnabled(false);
    }



}
