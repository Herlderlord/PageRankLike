import java.math.BigInteger;


public class HachageDouble<T> extends HachageLineaire<T> {
	
	
	private int k = 0;
	
	public HachageDouble(int taille) {
		super(taille);
		int i = 2;
		while(true) {
			if(estPremier(this.clefs.size(), i)) {
				k = i;
				System.out.println("==> Taille : " + taille + " ==> k choisi : " + k);
				return;
			}
			i ++;
		}
		
	}
	
	static private boolean estPremier(int m, int k) {
		BigInteger b1 = new BigInteger(""+m); // there's a better way to do this. I forget.
		BigInteger b2 = new BigInteger(""+k);
		BigInteger gcd = b1.gcd(b2);
		int greater = gcd.intValue();
		if(greater == 1) 
			return true;
		else
			return false;
	}
	@Override
	public int getNextHash(int hashCode) {
		return (hashCode + k) % this.clefs.size();
	}
}
