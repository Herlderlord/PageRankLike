import java.math.BigInteger;
import java.util.List;

public abstract class HachageAbstract<T> {

	protected int nbComparaisonsFound = 0;
	protected int nbComparaisonsNotFound = 0;
	protected int nbComparaisons = 0;
	protected int nbFound = 0;
	protected int nbNotFound = 0;
	protected int nbWordsCompared = 0;

	
	/**
	 * 
	 * @return
	 */
	public int getNbComparaisonsNotFound() {
		return this.nbComparaisonsNotFound;
	}
	
	/**
	 * 
	 */
	public void resetNbComparaisonsNotFound() {
		this.nbComparaisonsNotFound = 0;
	}
	/**
	 * 
	 * @return
	 */
	public int getNbComparaisonsFound() {
		return this.nbComparaisonsFound;
	}
	
	/**
	 * 
	 */
	public void resetNbComparaisonsFound() {
		this.nbComparaisonsFound = 0;
	}
	
	/**
	 * 
	 */
	public int getNbComparaisons() {
		return this.nbComparaisons;
	}
	
	public void resetNbComparaisons() {
		this.nbComparaisons = 0;
	}
	/**
	 * 
	 * @param nb
	 */
	public void setNbWordsCompared(int nb) {
		this.nbWordsCompared = nb;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNbWordsCompared() {
		return this.nbWordsCompared;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNbFound () {
		return this.nbFound;
	}

	/**
	 * 
	 */
	public void resetNbFound() {
		this.nbFound = 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNbNotFound () {
		return this.nbNotFound;
	}
	
	/**
	 * 
	 */
	public void resetNbNotFound() {
		this.nbNotFound = 0;
	}
	
	/**
	 * 
	 */
	public void resetStats() {
		this.resetNbFound();
		this.resetNbNotFound();
		this.resetNbComparaisons();
		this.resetNbComparaisonsFound();
		this.resetNbComparaisonsNotFound();
	}
	
	
	/**
	 * add an element with its key.
	 * 
	 * @param clef
	 * @param valeur
	 */
	public abstract void add(String clef, T valeur);
		
	/**
	 * get an element thanks its key. 
	 * @param clef
	 */
	public abstract T get(String clef); 
	
	/**
	 * Fonction de hachage. 
	 * 
	 * @param clef
	 * @return
	 */
	public abstract int hachage(BigInteger clef);
		
	/**
	 * Fonction de transformation d'une clef en un big integer. 
	 * 
	 * @param clef
	 * @return
	 */
	public BigInteger preHachage(String clef) {
		BigInteger valeur = BigInteger.valueOf(0);
		int a = 26;
		
		for(int i = 0; i < clef.length(); i++) {
			valeur = valeur.add(BigInteger.valueOf( (int)Math.pow(a, (double)i) * ((int)clef.charAt(i) )));
		}
		return valeur;
	}
	
	/**
	 * Permet un affichage simple du hachage. 
	 */
	public abstract void debug();
	
	/**
	 * Permet d'afficher sous forme de tableau.
	 */
	public abstract void afficherTableau();
	
	
	/**
	 * 
	 * @return
	 */
	public abstract int getSize(); 
	
	
	public abstract List<T> toList();
}
