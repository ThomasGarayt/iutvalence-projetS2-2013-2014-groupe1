package fr.projet.java.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
@SuppressWarnings("rawtypes")
public class DialogPreferencePartie extends JDialog {
	private InfoPreferencePartie info;
	protected InfosJoueur[] infosJoueurs;
	protected PanelJoueur[] recupInfoJoueur;
	protected File fichierCarte;
	protected JComboBox carte;

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
		this.setSize(1000, 405);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

		this.infosJoueurs = new InfosJoueur[9];
		this.recupInfoJoueur = new PanelJoueur[9];
		for (int j = 0; j < recupInfoJoueur.length; j++)
			recupInfoJoueur[j] = new PanelJoueur(Integer.toString(j + 1));

		this.initComponent();
	}

	/**
	 * Affiche le dialogue de choix des options de la partie.
	 * 
	 * @return Les options choisi par le joueur.
	 */
	public InfoPreferencePartie showDialog() {
		this.setVisible(true);
		return this.info;
	}

	@SuppressWarnings("unchecked")
	private void initComponent() {

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

		JPanel content = new JPanel();
		content.setBackground(Color.white);

		for (int j = 0; j < recupInfoJoueur.length; j++)
			content.add(recupInfoJoueur[j]);
		content.add(panelCarte);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = 0;
				for (int j = 0; j < infosJoueurs.length; j++) {
					if (recupInfoJoueur[j].estRempli()) {
						infosJoueurs[i] = recupInfoJoueur[j]
								.obtenirLesInfosJoueur();
						i++;
					}
				}
				fichierCarte = new File("Cartes/" + (String) carte.getSelectedItem());
				info = new InfoPreferencePartie(infosJoueurs, fichierCarte);
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