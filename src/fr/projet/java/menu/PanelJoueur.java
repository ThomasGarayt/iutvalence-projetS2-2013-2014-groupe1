package fr.projet.java.menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.projet.java.gestionGraphique.SetDImages;

public class PanelJoueur extends JPanel {
	
	private JComboBox nationJoueur;
	private JTextField nomJoueur;
	private JCheckBox joueurIA;
	
	public PanelJoueur(String numero) {
		super();
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(440, 60));
		
		nomJoueur = new JTextField();
		nomJoueur.setPreferredSize(new Dimension(100, 25));
		this.setBorder(BorderFactory
				.createTitledBorder("Joueur " + numero + ":"));
		
		JLabel nomLabel = new JLabel("Nom :");
		this.add(nomLabel);
		this.add(nomJoueur);
		
		nationJoueur = new JComboBox(SetDImages.values());
		JLabel labelNationJoueur = new JLabel("Couleur :");
		this.add(labelNationJoueur);
		this.add(nationJoueur);
		
		joueurIA = new JCheckBox();
		JLabel labelJoueurIA = new JLabel("Joueur IA :");
		this.add(labelJoueurIA);
		this.add(joueurIA);
	}
	
	public InfosJoueur obtenirLesInfosJoueur() {
		return new InfosJoueur(nomJoueur.getText(), (SetDImages) nationJoueur
				.getSelectedItem(), joueurIA.isSelected());
	}
	
	public boolean estRempli(){
		String nom = this.nomJoueur.getText();
		if (nom == null)
			return false;
		if (nom.equals(""))
			return false;
		return true;
		//return (nom != null ||  nom != "");
	}
}
