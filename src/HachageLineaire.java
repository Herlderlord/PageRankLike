import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class HachageLineaire<T> extends HachageAbstract<T>{

	
	protected Vector<String> clefs; 
	protected Vector<T> valeurs; 
	protected Vector<Boolean> liberee;
	protected int nbValues = 0;
	
	/**
	 * 
	 * @param taille
	 */
	public HachageLineaire(int taille) {
		this.clefs = new Vector<String>(); 
		this.valeurs = new Vector<T>();
		this.liberee = new Vector<Boolean>();
		
		clefs.setSize(taille);
		valeurs.setSize(taille);
		liberee.setSize(taille);
		for(int i = 0; i < clefs.size(); i++) {
			liberee.set(i, false);
		}
	}
	
	@Override
	public void add(String clef, T valeur) {
		/**
		 * TODO : 
		 * Il faut ajouter des valeurs et si il y a collision, ajouter la valeur à hachage ++ de manière récursive. 
		 * Il faut gérer ça avec un modulo pour revenir au début. 
		 * On garde en mémoire le hachage du début, si on y revient, on arrête, il n'y a pas la place. 
		 * 
		 */
		
		if(this.nbValues == this.clefs.size() -1 ) 
		{
			return;
		}
		
		boolean done = false;
		int clefHachee = hachage(preHachage(clef));
		int clefHachee_base = clefHachee; // On garde la première clef hachée. 
		while(!done) {
			// Gestion de la collision 
			if(clefs.get(clefHachee) != null) {
				clefHachee = this.getNextHash(clefHachee);
			}
			else {
				clefs.set(clefHachee, clef);
				valeurs.set(clefHachee, valeur);
				liberee.set(clefHachee, false);
				this.nbValues ++;
				done = true;
			}
			if(clefHachee == clefHachee_base) {
				return;
			}
		}		
	}
	
	@Override
	public T get(String clef) {
		boolean done = false;
		int clefHachee = hachage(preHachage(clef));
		int localComparaisons = 0;
		while(!done && clefHachee < clefs.size()) {
			localComparaisons ++;
			// Gestion
			if(clefs.get(clefHachee) == null) {
				if(liberee.get(clefHachee))
					clefHachee = this.getNextHash(clefHachee);
				else {
					this.nbComparaisonsNotFound += localComparaisons;
					this.nbComparaisons += localComparaisons;
					this.nbNotFound ++;
					return null;
				}
			}
			else if(clefs.get(clefHachee).compareTo(clef) == 0) {
				this.nbComparaisonsFound += localComparaisons;
				this.nbComparaisons += localComparaisons;
				this.nbFound ++;
				return valeurs.get(clefHachee);
			}
			else {
				clefHachee ++;
			}
		}
		this.nbComparaisonsNotFound += localComparaisons;
		this.nbComparaisons += localComparaisons;
		this.nbNotFound ++;
		return null;
	}
	
	public int getNextHash(int hashCode) {
		return (hashCode + 1) % this.clefs.size();
	}
	
	@Override
	public int getSize() {
		return this.clefs.size();
	}
	
	@Override
	public int hachage(BigInteger clef) {
		clef = clef.mod(BigInteger.valueOf(this.clefs.size()));
		return clef.intValue();
	}
	
	@Override
	public void debug() {
		afficherTableau();
	}
	
	@Override
	public void afficherTableau() {
		for(int i = 0; i < clefs.size(); i++) {
			System.out.print("[" + i + ", ");
			if(clefs.get(i) == null) {
				System.out.print("null, null, " + liberee.get(i));
			}
			else {
				System.out.print(clefs.get(i) + ", " + valeurs.get(i) + ", " + liberee.get(i));
			}
			System.out.println("]");
		}
	}
	
	@Override
	public List<T> toList() {
		List<T> list = new ArrayList<T>();
		for(int i = 0; i < valeurs.size(); i++) {
			if(valeurs.get(i) != null)
				list.add(valeurs.get(i));
		}
		return list;
	}

}
