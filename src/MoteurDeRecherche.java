import java.util.ArrayList;
import java.util.List;


public class MoteurDeRecherche {
	
	private HachageAbstract<ArrayList<String>> hach;
	
	public MoteurDeRecherche() {
		hach = new HachageDouble<ArrayList<String>>(300000);
	}
	
	public MoteurDeRecherche(HachageAbstract<ArrayList<String>> hach) {
		this.hach = hach;
	}
	
	public List<String> search(String keyword) {
		return hach.get(keyword);
	}
	
	public List<String> search(String[] keywords) {
		return null;
	}
	
	public HachageAbstract<ArrayList<String>> getHach() {
		return hach;
	}

	
}
