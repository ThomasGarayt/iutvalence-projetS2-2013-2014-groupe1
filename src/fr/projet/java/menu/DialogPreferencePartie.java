package fr.projet.java.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import fr.projet.java.gestionGraphique.SetDImages;

/**
 * @author Romain
 * 
 */
public class DialogPreferencePartie extends JDialog {
	private InfoPreferencePartie zInfo;
	private JComboBox nationJoueur1;
	private JComboBox carte;
	private JTextField nomJoueur1;
	private JComboBox nationJoueur2;
	private JTextField nomJoueur2;

	/**
	 * Creation de la boite de dialogue.
	 * 
	 * @param parent
	 *            La JFrame parente de la boite de dialogue
	 * @param title
	 *            Le titre de la boite de dialogue
	 * @param modal
	 *            Si utilise en modal
	 */
	public DialogPreferencePartie(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	/** 
	 * Affiche le dialogue de choix des options de la partie.
	 * 
	 * @return Les options choisi par le joueur.
	 */
	public InfoPreferencePartie showZDialog() {
		this.setVisible(true);
		return this.zInfo;
	}

	private void initComponent() {
		// Joueur 1
		// Le nom du joueur 1
		JPanel panelNomJoueur1 = new JPanel();
		panelNomJoueur1.setBackground(Color.white);
		panelNomJoueur1.setPreferredSize(new Dimension(220, 60));
		nomJoueur1 = new JTextField();
		nomJoueur1.setPreferredSize(new Dimension(100, 25));
		panelNomJoueur1.setBorder(BorderFactory
				.createTitledBorder("Nom du joueur 1 :"));
		JLabel nomLabel = new JLabel("Saisir un nom :");
		panelNomJoueur1.add(nomLabel);
		panelNomJoueur1.add(nomJoueur1);

		// La nation du joueur 1
		JPanel panelNationJoueur1 = new JPanel();
		panelNationJoueur1.setBackground(Color.white);
		panelNationJoueur1.setPreferredSize(new Dimension(220, 60));
		panelNationJoueur1.setBorder(BorderFactory
				.createTitledBorder("Nation du joueur 1 :"));
		nationJoueur1 = new JComboBox(SetDImages.values());
		JLabel sexeLabel = new JLabel("Couleur :");
		panelNationJoueur1.add(sexeLabel);
		panelNationJoueur1.add(nationJoueur1);

		// Joueur 2
		// Le nom du joueur 2
		JPanel panelNomJoueur2 = new JPanel();
		panelNomJoueur2.setBackground(Color.white);
		panelNomJoueur2.setPreferredSize(new Dimension(220, 60));
		nomJoueur2 = new JTextField();
		nomJoueur2.setPreferredSize(new Dimension(100, 25));
		panelNomJoueur2.setBorder(BorderFactory
				.createTitledBorder("Nom du joueur 2 :"));
		JLabel nomLabel2 = new JLabel("Saisir un nom :");
		panelNomJoueur2.add(nomLabel2);
		panelNomJoueur2.add(nomJoueur2);

		// La nation du joueur 2
		JPanel panelNationJoueur2 = new JPanel();
		panelNationJoueur2.setBackground(Color.white);
		panelNationJoueur2.setPreferredSize(new Dimension(220, 60));
		panelNationJoueur2.setBorder(BorderFactory
				.createTitledBorder("Nation du joueur 2 :"));
		nationJoueur2 = new JComboBox(SetDImages.values());
		JLabel sexeLabel2 = new JLabel("Couleur :");
		panelNationJoueur2.add(sexeLabel2);
		panelNationJoueur2.add(nationJoueur2);

		// La carte
		JPanel panelCarte = new JPanel();
		panelCarte.setBackground(Color.white);
		panelCarte.setPreferredSize(new Dimension(440, 60));
		panelCarte.setBorder(BorderFactory
				.createTitledBorder("La carte du monde"));

		carte = new JComboBox();
		File repertoire = new File("Cartes");
		String[] listefichiers;
		int i;
		listefichiers = repertoire.list();
		for (i = 0; i < listefichiers.length; i++)
			carte.addItem(listefichiers[i]);

		JLabel labelCarte = new JLabel("Choix de la carte : ");
		panelCarte.add(labelCarte);
		panelCarte.add(carte);

		// Initialisation du contenu.
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panelNomJoueur1);
		content.add(panelNationJoueur1);
		content.add(panelNomJoueur2);
		content.add(panelNationJoueur2);
		content.add(panelCarte);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				zInfo = new InfoPreferencePartie(nomJoueur1.getText(),
						nomJoueur2.getText(), (SetDImages) nationJoueur1
								.getSelectedItem(), (SetDImages) nationJoueur2
								.getSelectedItem(), (String) carte
								.getSelectedItem());
				setVisible(false);
			}
		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}