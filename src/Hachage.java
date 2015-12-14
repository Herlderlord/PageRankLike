import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Vector;


public class Hachage<T> extends HachageAbstract<T> {

	private Vector<LinkedList<T>> table = null;
	private Vector<LinkedList<String>> keys = null;
	

	/**
	 * 
	 * @param taille
	 */
	public Hachage(int taille) {
		this.table = new Vector<LinkedList<T>>();
		this.keys = new Vector<LinkedList<String>>();
		
		this.table.setSize(taille);
		this.keys.setSize(taille);
		for(int i = 0 ; i < this.table.size(); i++)
			this.table.set(i, null);
		
	}
	
	@Override
	public void add(String clef, T valeur) {
		int v = hachage(preHachage(clef));
		
		// Dans le cas où on n'a pas de collision 
		if(this.table.get(v) == null) {
			this.table.set(v, new LinkedList<T>());
			this.keys.set(v, new LinkedList<String>());

			this.table.get(v).add(valeur);
			this.keys.get(v).add(clef);
		}
		// Dans le cas où il y a une collision. 
		else {
			// On teste si la clef existe déjà. 
			boolean exists = false;
			int i_existant = 0;
			LinkedList<String> keys = this.keys.get(v);
			for(int i = 0; i < keys.size(); i++) {
				if(keys.get(i).compareTo(clef) == 0) {
					exists = true;
					i_existant = i;
					break;
				}
			}
			
			// Si la clef existe déjà. 
			if(exists) {
				LinkedList<T> values = this.table.get(v);
				values.set(i_existant, valeur);
			}
			else {
				this.table.get(v).add(valeur);
				this.keys.get(v).add(clef);
			}
		}
	}
	
	
	@Override 
	public int getSize() {
		return this.table.size();
	}
	
	@Override
	public T get(String cle) {
		this.nbWordsCompared ++;
		int v = hachage(preHachage(cle));
		
		LinkedList<T> listValues = this.table.get(v);
		if(listValues == null)
			return null;
		 
		LinkedList<String> listKeys = this.keys.get(v);
		
		// Count nb comparaisons
		int localComparaisons = 0;
		for(int i = 0; i < listKeys.size(); i++) {
			localComparaisons ++;
			if(listKeys.get(i).equals(cle)) {
				this.nbComparaisonsFound += localComparaisons;
				this.nbComparaisons += localComparaisons;
				this.nbFound ++;
				return listValues.get(i);
			}
		}
		this.nbComparaisonsNotFound += localComparaisons;
		this.nbComparaisons += localComparaisons;
		this.nbNotFound ++;
		return null;
	}
	
	@Override
	public int hachage(BigInteger clef) {
		clef = clef.mod(BigInteger.valueOf(this.table.size()));
		
		return clef.intValue();
	}
	
	
	@Override
	public void afficherTableau() {
		System.out.println("|-- Nombre de variable par case");
		System.out.print("[");
		for(int i = 0; i < this.table.size(); i++) {
			if(i != 0)
				System.out.print(", ");
			if(this.table.get(i) == null)
				System.out.print("0");
			else
				System.out.print(this.table.get(i).size());
		}
		System.out.print("]");
	}
	
	@Override
	public void debug() {
		System.out.println("|-- Debug de la structure de hachage ");
		
		for(int i = 0; i < this.table.size(); i++) {
			System.out.print("[" + i + "] :: ");
			LinkedList<T> values = this.table.get(i);
			LinkedList<String> keys = this.keys.get(i);
			
			if(values == null) 
				System.out.print("null");
			else {
				for(int j = 0; j < values.size(); j++) {
					if(j != 0)
						System.out.print(" => ");
					System.out.print("(" + values.get(j) + ", " + keys.get(j) + ")");
				}
			}
			System.out.println();
		}
	}
	
	


}
