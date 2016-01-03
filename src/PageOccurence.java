
/**
 * 
 * @author mathias
 *
 */

public class PageOccurence implements Comparable<PageOccurence> {
	
	private int nbOccurence; 
	private Page page; 
	
	public PageOccurence() {
		nbOccurence = 0; 
		page = null;
	}
	
	public PageOccurence(int nbOccurence, Page page) {
		this.nbOccurence = nbOccurence;
		this.page = page;
	}
	
	public void setNbOccurence(int nbOccurence) {
		this.nbOccurence = nbOccurence;
	}
	
	public void setPage(Page page) {
		this.page = page; 
	}
	public int getNbOccurence() {
		return nbOccurence;
	}
	
	public Page getPage() {
		return page;
	}
	
	@Override
	public int compareTo(PageOccurence p) {
		System.out.println("Une comparaison");
		return Float.compare(this.getPage().getScore(), p.getPage().getScore());	
	}
	
}