package ihmCivilisation;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Romain
 * 
 *         Un bouton pour la carte avec comme attribut les coordonnees
 *         correspondant à la case qu'il represent.
 */
public class BoutonCarte extends JButton {


	private static final long serialVersionUID = 1L;
	private int x;
	private int y;

	/**
	 * Un bouton de la carte.
	 * 
	 * @param x
	 *            La position en x du bouton sur la carte.
	 * @param y
	 *            La position en y du bouton sur la carte.
	 * @param image
	 *            L'image à afficher dans le bouton.
	 * @param auditeurBoutons
	 *            L'auditeur du bouton.
	 */
	public BoutonCarte(int x, int y, ImageIcon image,
			ActionListener auditeurBoutons) {
		super(image);
		this.setFocusPainted(false);
		this.setBorderPainted(false);
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.x = x;
		this.y = y;

		this.addActionListener(auditeurBoutons);
	}

	/**
	 * obtenir la position x du bouton.
	 * 
	 * @return La position x du bouton sur la carte.
	 */
	public int obtenirX() {
		return x;
	}

	/**
	 * obtenir la position y du bouton.
	 * 
	 * @return La position y du bouton sur la carte.
	 */
	public int obtenirY() {
		return y;
	}
}
