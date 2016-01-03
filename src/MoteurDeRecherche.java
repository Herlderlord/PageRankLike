import java.util.ArrayList;
import java.util.List;


public class MoteurDeRecherche {
	
	
	private HachageAbstract<ArrayList<PageOccurence>> pagesKeywords;
	private HachageAbstract<Page> pages;
	//private HachageAbstract<ArrayList<PageOccurence>> hach;
	
	/**
	 * 
	 */
	public MoteurDeRecherche() {
		//hach = new HachageDouble<ArrayList<PageOccurence>>(300000);
		pagesKeywords = new HachageDouble<ArrayList<PageOccurence>>(300000);
		pages = new HachageDouble<Page>(100000);
	}
	
	/**
	 * 
	 * @param hach
	 */
	public MoteurDeRecherche(HachageAbstract<ArrayList<PageOccurence>> hach) {
		this.pagesKeywords = hach;
	}
	
	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public List<PageOccurence> search(String keyword) {
		return pagesKeywords.get(keyword);
	}
	
	/**
	 * 
	 * @param keywords
	 * @return
	 */
	/*
	public List<PageOccurence> search(String[] keywords) {
		
		ArrayList<PageOccurence> pagesOccurences = new ArrayList<PageOccurence>();
		
		HachageDouble<PageOccurence> hachageCounter = new HachageDouble<PageOccurence>(300000);
		
		// Résultat final. 
		List<PageOccurence> result = new ArrayList<PageOccurence>();
		
		// On parcourt les mots clefs que l'utilisateur a donné.
		for(int i = 0; i < keywords.length; i++) {
			pagesOccurences = hach.get(keywords[i]); 
			
			// On vérifie si on a des occurences avec le mot clef 
			if(pagesOccurences != null) {
				// Ajout des occurences dans un hachage pour compter.
				for(int j = 0; j < pagesOccurences.size(); j++) {
					PageOccurence p = hachageCounter.get(pagesOccurences.get(j).getUrl());	
					// Addition des scores
					if(p != null) {
						p.setNbOccurence(p.getNbOccurence() + pagesOccurences.get(j).getNbOccurence());
					}
					// Ajout de la page 
					else {
						hachageCounter.add(pagesOccurences.get(j).getUrl(), new PageOccurence(pagesOccurences.get(j).getNbOccurence(), pagesOccurences.get(j).getUrl()));
						result.add(hachageCounter.get(pagesOccurences.get(j).getUrl()));
					}
				}
			}

			
		}
		
		// Et on fait l'union des deux. 
		return result;
	}
	
	
	public List<PageOccurence> searchEngine(String request) {
		return null;
	}
	*/
	
	/**
	 * 
	 * @return
	 */
	public HachageAbstract<ArrayList<PageOccurence>> getHach() {
		return pagesKeywords;
	}
	
	public HachageAbstract<Page> getPages() {
		return pages;
	}

	
}

