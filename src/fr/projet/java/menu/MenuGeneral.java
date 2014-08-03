package fr.projet.java.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @author Romain La classe qui permet d'afficher un menu general.
 */
public class MenuGeneral implements Runnable, ActionListener {

	private JFrame fenetre;
	private JPanel panel = new JPanel();
	private JButton boutonJouer = new JButton("Nouvelle Partie");
	private JButton boutonQuitter = new JButton("Quitter");
	private InfoPreferencePartie infoPreferencePartie;
	private volatile boolean preferenceChoisi;

	/**
	 * Demande des informations afin de creer une partie.
	 * 
	 * @return Les informations de la partie.
	 */
	public InfoPreferencePartie obtenirInfoPartie() {
		while (!preferenceChoisi)
			;
		return infoPreferencePartie;
	}

	@Override
	public void run() {
		initialiserLeMenu();
	}

	private void initialiserLeMenu() {
		this.preferenceChoisi = false;

		fenetre = new JFrame();

		panel.add(boutonJouer);
		panel.add(boutonQuitter);

		Box box = Box.createVerticalBox();
		box.add(boutonJouer);
		box.add(boutonQuitter);

		fenetre.setContentPane(box);
		fenetre.setSize(700, 600);
		fenetre.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		fenetre.setResizable(false);

		boutonQuitter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		boutonJouer.addActionListener(this);

		fenetre.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		DialogPreferencePartie zd = new DialogPreferencePartie(null,
				"Preference de la partie", true);
		infoPreferencePartie = zd.showZDialog();
		this.preferenceChoisi = true;
	}

}
