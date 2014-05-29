package ihmCivilisation;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoutonCarte extends JButton {

	private int x;
	private int y;
	
	public BoutonCarte(int x, int y, ImageIcon image, ActionListener auditeurBoutons) {
		super(image);
		this.setFocusPainted( false );
		this.setBorderPainted(false);
		this.setOpaque( false );
		this.setContentAreaFilled(false);
		this.x = x;
		this.y = y;
		
		this.addActionListener(auditeurBoutons);
	}

	public int obtenirX() {
		return x;
	}

	public int obtenirY() {
		return y;
	}
}
