package fr.projet.java.gestionGraphique;

/**
 * @author Romain
 * Le set d'images qui sera untiliser pour differencier les unite d'un joueur sur la carte.
 */
public enum SetDImages {

	/**
	 * Des images d'unites rouge.
	 */
	imagesRouges("Images/Unite/Char_rouge.png", "Images/Unite/Soldat_rouge.png", "Images/Ville/ville_rouge.png"),
	/**
	 * Des images d'unites bleu.
	 */
	imagesBleu("Images/Unite/Char_bleu.png", "Images/Unite/Soldat_bleu.png", "Images/Ville/ville_bleu.png");
	
	private String imageDuChar;
	private String imageDuSoldat;
	private String imageDeLaVille;

	private SetDImages(String imageDuChar, String imageDuSoldat, String imageDeLaVille) {
		this.imageDuChar = imageDuChar;
		this.imageDuSoldat = imageDuSoldat;
		this.imageDeLaVille = imageDeLaVille;
	}
	
	/** Permet d'obtenir l'image du char.
	 * @return L'image du char.
	 */
	public String obtenirLImageDuChar() {
		return this.imageDuChar;
	}
	
	/**
	 * Permet d'obtenir l'image du soldat.
	 * 
	 * @return L'image du soldat.
	 */
	public String obtenirLImageDuSoldat() {
		return this.imageDuSoldat;
	}
	
	/**
	 * Permet d'obtenir l'image de la ville.
	 * 
	 * @return L'image de la ville.
	 */
	public String obtenirLImageDeLaVille() {
		return this.imageDeLaVille;
	}
}
