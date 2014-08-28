package fr.projet.java.IntelligenceArtificiel;

/**
 * @author Romain Les differentes actions possible de la part de l'IA.
 */
public enum TypeActionIA {
	/**
	 * L'IA construit une unite.
	 */
	construireUnite, /**
	 * L'IA deplace une unite.
	 */
	deplacerUnite,/**
	 * L'IA attaque une unite.
	 */
	attaquerUnite,/**
	 * L'IA ameliore une ville.
	 */
	ameliorerVille,/**
	 * L'IA ameliore une unite.
	 */
	ameliorerUnite,/**
	 * L'IA ne fait rien avec une unite.
	 */
	neRienFaireUnite,
	/**
	 * L'IA ne fait rien avec une ville.
	 */
	neRienFaireVille;
}
