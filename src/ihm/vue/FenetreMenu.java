package ihm.vue;

import hearthstone.carte.Carte;
import hearthstone.cartes.Cartes;
import hearthstone.cartes.Deck;
import hearthstone.cartes.Denombrement;
import hearthstone.exception.ClasseNeutreException;
import ihm.WrapLayout;
import ihm.controleur.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by E174621F on 21/06/18
 */
public class FenetreMenu extends JFrame{
    public static final int
            MODE_DECK = 1,
            MODE_COLLEC = 2;
    //modèle de la vue
    private Cartes cartes;

    //les éléments soumis à modification
    private JList<Carte> cartes_deck;
    private JComboBox<String> liste_decks;
    private JButton supprimer_carte_deck;
    private JLabel nb_cartes_deck;
    private JLabel nb_cartes_max;
    private JButton collection_acces;
    private JButton deck_acces;
    private JPanel carte_collection;
    private LinkedList<Carte> cartesAffichees;
	private JPanel info_carte;
	private JButton filtre_acces;
    private JButton ajouter;
    private JButton creer;
    private JButton supprimer;
    private JButton creer_doree;
    private JPanel affiche_deck;
    private JButton nv_deck;
    private Carte selectedCarte;

    /**
     * construit une nouvelle cartes
     *
     * @param titre Titre de la fenêtre
     */
    public FenetreMenu(String titre, Cartes cartes) throws IOException, ClasseNeutreException {
        super(titre);
        
        this.cartes = cartes;
	
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//Mise en pages des différents JComponents
        JPanel zones = new JPanel();
        zones.setLayout(new BorderLayout());
        this.getContentPane().add(zones);

        //Partie centre
        JPanel affich_deck_collec = new JPanel();
        affich_deck_collec.setLayout(new BorderLayout());
        zones.add(affich_deck_collec,BorderLayout.CENTER);

        //JComponent de Deck
        affiche_deck = new JPanel();
        affiche_deck.setLayout(new BorderLayout());

        liste_decks = new JComboBox<>();
        cartes_deck = new JList<>();
        cartes_deck.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        //création des JPanels nécessaire pour les composants de Deck
        JPanel nb_cartes = new JPanel();
        JPanel bas_deck = new JPanel();
        bas_deck.setLayout(new BorderLayout());
        JPanel milieu_deck = new JPanel();
        milieu_deck.setLayout(new BorderLayout());
        JPanel boutons_deck = new JPanel();
        boutons_deck.setLayout(new BorderLayout());

        //configuration des différents boutons
        nb_cartes_deck = new JLabel("0");
        nb_cartes_max = new JLabel("/30");
        supprimer_carte_deck = new JButton("Supprimer carte");
        nv_deck = new JButton("Nouveau Deck");
        JButton suppr_deck=new JButton("Supprimer Deck");

        //partie contenant les labels
        nb_cartes.add(nb_cartes_deck);
        nb_cartes.add(nb_cartes_max);

        //partie haute de Deck en dessous de la ComboBox contenant les Decks créer
        boutons_deck.add(suppr_deck,BorderLayout.EAST);
        boutons_deck.add(nv_deck,BorderLayout.WEST);

        //partie basse de affiche deck
        bas_deck.add(supprimer_carte_deck,BorderLayout.WEST);
        bas_deck.add(nb_cartes,BorderLayout.EAST);


        //partie contenant la liste des cartes et la partie haute de Deck
        milieu_deck.add(boutons_deck,BorderLayout.NORTH);
        milieu_deck.add(cartes_deck,BorderLayout.CENTER);

        //ajout des composant des Jpanel de affiche Deck et paramétrage de la largeur
        affiche_deck.add(milieu_deck,BorderLayout.CENTER);
        affiche_deck.add(liste_decks,BorderLayout.NORTH);
        affiche_deck.add(bas_deck,BorderLayout.SOUTH);
        affiche_deck.setPreferredSize(new Dimension(300, 0));

        //ajout de affiche Deck à la JPanel contenant la cartes et les Deck
        affich_deck_collec.add(affiche_deck, BorderLayout.EAST);

        //Le bas de la page
        JPanel bas = new JPanel();
        bas.setLayout(new BorderLayout());
        affich_deck_collec.add(bas,BorderLayout.SOUTH);

        //configuration des différents boutons
        collection_acces = new JButton("Collection");
        deck_acces = new JButton("Deck");
        filtre_acces = new JButton("Filtre");
        JButton sauvegarde = new JButton("Sauvegarde");

		// Ajout des boutons d'accès aux différentes fenêtres et du switch entre mode deck/collection
        JPanel switch_acces = new JPanel();
        JPanel filtre_sauvegarde = new JPanel();
        filtre_sauvegarde.add(filtre_acces);
        filtre_sauvegarde.add(sauvegarde);
        switch_acces.add(deck_acces);
        switch_acces.add(collection_acces);
        bas.add(filtre_sauvegarde,BorderLayout.WEST);
        bas.add(switch_acces,BorderLayout.EAST);

        // Affichage de la cartes
        carte_collection = new JPanel(new WrapLayout());
        JScrollPane scroll_collec = new JScrollPane(carte_collection,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_collec.getVerticalScrollBar().setUnitIncrement(16);
        affich_deck_collec.add(scroll_collec, BorderLayout.CENTER);

        // Panel d'affichage + gestion de la carte selectionnée
        JPanel panneau_gauche = new JPanel();
        panneau_gauche.setLayout(new BorderLayout());
        zones.add(panneau_gauche,BorderLayout.WEST);
        
        // Panel d'infos
        info_carte = new JPanel();
		info_carte.setPreferredSize(new Dimension(250,0));
		panneau_gauche.add(info_carte, BorderLayout.CENTER);
		
        // Nommage des boutons de gestion d'image
        ajouter = new JButton("Ajouter");
        creer = new JButton("Créer");
        creer_doree = new JButton("Créer Dorée");
        supprimer = new JButton("Supprimer");
        
		JPanel boutons_carte = new JPanel();
		boutons_carte.setLayout(new GridLayout(2,1));
		panneau_gauche.add(boutons_carte,BorderLayout.SOUTH);

        //Ajout des boutons de gestion d'image
        JPanel bout_carte_haut = new JPanel();
        bout_carte_haut.add(creer);
        bout_carte_haut.add(ajouter);
        bout_carte_haut.add(creer_doree);

        JPanel bout_carte_bas = new JPanel();
        bout_carte_bas.add(supprimer);

        boutons_carte.add(bout_carte_haut);
        boutons_carte.add(bout_carte_bas);

        //		COMPORTEMENT PAR DEFAUT
        // Afficher cartes et carte selectionnee par défaut
        afficherCollection();
        selectFirstCarteAffich();
        
		// Mode par défaut
		this.changerMode(MODE_COLLEC);

        //		CONTROLEURS
		// Controleurs de changement de mode de la vue principale
        collection_acces.addActionListener(new SwitchCollec(this));
        deck_acces.addActionListener(new SwitchDeck(this));
        
        // Ouvertures de fenêtres
        filtre_acces.addActionListener(new FiltreVueOpen(this,cartes));
        nv_deck.addActionListener(new NouveauDeckOpen(this,cartes));
        creer.addActionListener(new NouvelleCarteOpen(this, cartes));
        
        // Gestion de carte
        creer_doree.addActionListener(new CreerDoree(this,cartes));
		ajouter.addActionListener(new AjoutCarteDeck(this));
		supprimer.addActionListener(new SupprimerCarteCollection(this,cartes));
		
		// Gestion de deck
        suppr_deck.addActionListener(new SupprimerDeck(this, cartes));
        liste_decks.addActionListener(new ChoixDeck(this));
        supprimer_carte_deck.addActionListener(new SupprimerCarteDeck(this));
        cartes_deck.addListSelectionListener(new AffichageCarteDeck(this));

        // Autres
        sauvegarde.addActionListener(new Sauvegarde(this));

        //choses extrêmements importantes
        this.setPreferredSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);

    }
    
    
    /**
     * Changement de l'affichage pour masquer certains elements et en afficher d'autres en fonction
	 * du mode passé en parametre, permettant de gérer soit les decks soit la collection
	 *
     * @param mode FenetreMenu.MODE_COLLEC ou FenetreMenu.MODE_DECK
     */
    public void changerMode(int mode) {
        boolean etat = true;
        if (mode == MODE_COLLEC){
            etat = false;
        }
        // Elements de deck
        affiche_deck.setVisible(etat);
        collection_acces.setVisible(etat);
        ajouter.setVisible(etat);
		
        // Elements de collection
        creer_doree.setVisible(!etat);
        deck_acces.setVisible(!etat);
        creer.setVisible(!etat);
        supprimer.setVisible(!etat);

    }
	
	/**
	 *
	 * @return la carte selectionnée dans le Deck
	 */
	public Carte getSelectedCarteDeck() {
        return cartes_deck.getSelectedValue();
    }
	
	/**
	 *
	 * @return le deck selectionné
	 */
	public Deck getSelectedDeck() {
		return cartes.getDeck(getSelectedDeckName());
	}
	
	/**
	 *
	 * @return le nom du deck selectionné
	 */
    public String getSelectedDeckName() {
        return (String) liste_decks.getSelectedItem();
    }
	
	/**
	 * afficher soit l'image de la carte, soit les informations basiques de la carte
	 * en fonction de si elle a une url d'image ou pas
	 * @param c
	 */
	public void afficherInfoCarte(Carte c) {
    	info_carte.removeAll(); // Vide le JPanel
    	
        if (!c.urlImage().equals("")) { // Si l'url n'est pas vide
            try {
                Image img = ImageIO.read(new URL(c.urlImage())); // Récupère l'URL dans un objet Image
                img = img.getScaledInstance(250, -1, Image.SCALE_SMOOTH); // Resize l'image pour qu'elle soit a la taille du JPanel
                JLabel img_label = new JLabel(new ImageIcon(img)); // Crée un label contenant l'Image
				info_carte.add(img_label); // Le rajoute au JPanel d'affichage
            } catch (IOException e) {
                JOptionPane.showMessageDialog(new JFrame(), e.getStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);;
            }
        } else if (c != null) {
        	// Rajoute les informations de la carte
			info_carte.add(new JLabel(c.nom()));
			info_carte.add(new JLabel(c.classe().toString()));
			info_carte.add(new JLabel(c.rarete().toString()));
			info_carte.add(new JLabel("<html>" + c.description() + "</html>"));
        }
	
		info_carte.revalidate(); // Réaffichage du JPanel
		info_carte.repaint();
    }
	
	/**
	 * affiche la collection de cartes en miniatures
	 */
	public void afficherCollection() {
		// Récupère les cartes en fonction du filtre courant
        Collection<? extends Carte> collec = cartes.getFiltreChoix().filtrer(cartes.collection());
        
        // Enregistre les cartes affichées dans la liste correspondante
        cartesAffichees = (LinkedList<Carte>)collec;
        
        // Index de la carte
        int i = 0;
        
        carte_collection.removeAll(); // Vide le JPanel d'affichage
        
        for (Carte c : collec) { // Pour chaque carte
			// panneau d'affichage de la carte, avec une taille prédéfinie
            JPanel cartePane = new JPanel();
            cartePane.setPreferredSize(new Dimension(120, 180));
            
            // Ajout de l'index de la carte de facon cachée dans le
            JLabel hiddenIndex = new JLabel(Integer.toString(i));
            cartePane.add(hiddenIndex);
            hiddenIndex.setVisible(false);
            
            if (!c.urlImage().equals("")) {
                // Image de la carte
                try {
                    // Resize l'image
                    Image scaled = ImageIO.read(new URL(c.urlImage())).getScaledInstance(120, -1, Image.SCALE_SMOOTH);
                    JLabel carte = new JLabel(new ImageIcon(scaled)); // La rajouter dans un JLabel
                    cartePane.add(carte);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(new JFrame(), e.getStackTrace(), "Error", JOptionPane.ERROR_MESSAGE);;
                }
            } else { // Si pas d'image, afficher les infos de base de la carte
                cartePane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                
                cartePane.add(new JLabel(c.nom()));
                cartePane.add(new JLabel(c.classe().toString()));
                cartePane.add(new JLabel(c.rarete().toString()));
                cartePane.add(new JLabel("<html>" + c.description() + "</html>"));
            }
            
            // Rajouter la carte a l'affichage et ajouter le listener d'affichage
            carte_collection.add(cartePane);
            cartePane.addMouseListener(new AffichageCarteCollec(this));
			
            i++;
        }
        
        // Recharger le JPanel d'affichage
        carte_collection.revalidate();
        carte_collection.repaint();
    }
	
	/**
	 *
	 * @return les cartes affichées selon le filtre
	 */
	public LinkedList<Carte> getCartesAffichees() {
        return cartesAffichees;
    }
	
	/**
	 * selectionne et affiche une carte
	 * @param c la carte a selectionner
	 */
	public void setSelectedCarte(Carte c) {
        this.selectedCarte = c;
        afficherInfoCarte(c);
    }
	
	/**
	 *
	 * @return la carte selectionnée
	 */
	public Carte getSelectedCarte() {
        return selectedCarte;
    }
	
	/**
	 *
	 * @param d le nom du deck a ajouter dans la combobox
	 */
	public void ajouterChoixDeck(String d) {
    	liste_decks.addItem(d);
	}
	
	/**
	 *
	 * @param d le nom du deck a retirer des choix dans la combobox
	 */
	public void retirerChoixDeck(String d) {
		liste_decks.removeItem(d);
	}
	
	/**
	 * afficher un message d'erreur dans une fenêtre
	 * @param titre titre de la fenêtre d'erreur
	 * @param message message d'erreur
	 */
	public void erreur(String titre, Object message) {
		JOptionPane.showMessageDialog(this, message, titre, JOptionPane.ERROR_MESSAGE);
	}

    /**
     * affiche la nouvelle taille du deck
     * @param txt la nouvelle taille du Deck
     */
	public void setTextLabelTaille(String txt){
	    nb_cartes_deck.setText(txt);
    }
    
    public void setDeckListModel(Deck d) {
		cartes_deck.setModel(d);
		reloadListModel();
	}
	
	public void reloadListModel() {
		cartes_deck.revalidate();
		cartes_deck.repaint();
	}
	
	public void selectFirstCarteAffich() {
		if (cartesAffichees.size() > 0)
			setSelectedCarte(getCartesAffichees().get(0));
		else
			setSelectedCarte(null);
	}
}
