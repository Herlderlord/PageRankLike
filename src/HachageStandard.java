import java.math.BigInteger;


public class HachageStandard<T> extends HachageLineaire <T> {

	public HachageStandard(int taille) {
		super(taille);
	}
	
	@Override
	public BigInteger preHachage(String clef) {
		return BigInteger.valueOf(clef.hashCode());
	}

}

