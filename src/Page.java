import java.util.ArrayList;
import java.util.List;

public class Page {
	private String url; 
	private List<Page> inPages;
	private List<Page> outPages;
	private float score;
	private static int counter = 0;
	
	/**
	 * 
	 */
	public Page() {
		inPages = new ArrayList<Page>(); 
		outPages = new ArrayList<Page>(); 
		url = "";
		counter ++;
	}
	
	/**
	 * 
	 * @param url
	 */
	public Page(String url) {
		this();
		this.url = url;
	}
	
	
	/* SETTERS */
	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * 
	 * @param score
	 */
	public void setScore(float score) {
		this.score = score;
	}

	/* ADDERS */
	/**
	 * 
	 * @param page
	 */
	public void addInPage(Page page) {
		this.inPages.add(page);
		
	}
	
	/**
	 * 
	 * @param page
	 */
	public void addOutPage(Page page) {
		this.outPages.add(page);
	}
	
	/* GETTERS */
	/**
	 * 
	 * @return
	 */
	public int getIndegree() {
		return inPages.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public int getOutdegree() {
		return outPages.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Page> getOutPages() {
		return outPages; 
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Page> getInPages() {
		return inPages;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getScore() {
		return score;
	}
	
	/**
	 * 
	 * @return
	 */
	public static int getCounter() {
		return counter;
	}
	
	
}
