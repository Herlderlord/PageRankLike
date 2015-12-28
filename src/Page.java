import java.util.ArrayList;
import java.util.List;

public class Page {
	private int indegree;
	private int outdegree;
	private String url; 
	private List<Page> inPages;
	private List<Page> outPages;
	private float score;
	
	public Page() {
		inPages = new ArrayList<Page>(); 
		outPages = new ArrayList<Page>(); 
		indegree = 0;
		outdegree = 0;
		url = "";
	}
	
	public Page(String url) {
		this();
		this.url = url;
	}
	
	
	/* SETTERS */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setScore(float score) {
		this.score = score;
	}

	/* ADDERS */
	public void addInPage(Page page) {
		this.inPages.add(page);
		
	}
	
	public void addOutPage(Page page) {
		this.outPages.add(page);
	}
	
	/* GETTERS */
	public int getIndegree() {
		return indegree;
	}
	
	public int getOutdegree() {
		return outdegree;
	}
	
	public String getUrl() {
		return url;
	}
	
	public List<Page> getOutPages() {
		return outPages; 
	}
	
	public List<Page> getInPages() {
		return inPages;
	}
	
	public float getScore() {
		return score;
	}
	
	
}
