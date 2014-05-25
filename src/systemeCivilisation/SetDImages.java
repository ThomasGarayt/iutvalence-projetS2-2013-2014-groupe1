package systemeCivilisation;

public enum SetDImages {

	imagesRouges("Images/Char_rouge.jpg", "Images/Soldat_rouge.png"),
	imagesBleu("Images/Char_bleu.jpeg", "Images/Soldat_bleu.png");
	
	private String imageDuChar;
	private String imageDuSoldat;

	private SetDImages(String imageDuChar, String imageDuSoldat) {
		this.imageDuChar = imageDuChar;
		this.imageDuSoldat = imageDuSoldat;
	}
	
	public String obtenirLImageDuChar() {
		return this.imageDuChar;
	}

	public String obtenirLImageDuSoldat() {
		return this.imageDuSoldat;
	}
}