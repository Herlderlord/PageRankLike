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
	
	public List<PageOccurence> search(String[] keywords) {
		
		// Résultat final. 
		// Cette fois-ci on recherche avec plusieurs mots clefs. 
		// On va parcourir chaque clef. 
		
		HachageAbstract<PageOccurence> pages = new HachageDouble<PageOccurence>(100000);
		for(int i = 0; i < keywords.length; i++) {
			List<PageOccurence> pageOccurences = pagesKeywords.get(keywords[i]);
			if(pageOccurences != null) {
				for(int j = 0; j < pageOccurences.size(); j++) {
					PageOccurence p = pages.get(pageOccurences.get(j).getPage().getUrl());
					if(p == null) {
						pages.add(
								pageOccurences.get(j).getPage().getUrl(), 
								new PageOccurence(
										pageOccurences.get(j).getNbOccurence(), 
										pageOccurences.get(j).getPage()
										)
								);
					}
					else {
						p.setNbOccurence( p.getNbOccurence() + pageOccurences.get(j).getNbOccurence());
					}
				}
			}
		}

		return pages.toList();
	}
	
	
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

