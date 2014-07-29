package gestionUnite;

/**
 * @author Romain
 * Le set d'images qui sera untiliser pour differencier les unite d'un joueur sur la carte.
 */
public enum SetDImages {

	/**
	 * Des images d'unites rouge.
	 */
	imagesRouges("Images/Char_rouge.png", "Images/Soldat_rouge.png"),
	/**
	 * Des images d'unites bleu.
	 */
	imagesBleu("Images/Char_bleu.png", "Images/Soldat_bleu.png");
	
	private String imageDuChar;
	private String imageDuSoldat;

	private SetDImages(String imageDuChar, String imageDuSoldat) {
		this.imageDuChar = imageDuChar;
		this.imageDuSoldat = imageDuSoldat;
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
}
