
/**
 * 
 * @author mathias
 *
 */

public class PageOccurence {
	
	private int nbOccurence; 
	private String url; 
	
	public PageOccurence() {
		nbOccurence = 0; 
		url = "";
	}
	
	public PageOccurence(int nbOccurence, String url) {
		this.nbOccurence = nbOccurence;
		this.url = url;
	}
	
	public void setNbOccurence(int nbOccurence) {
		this.nbOccurence = nbOccurence;
	}
	
	public void setUrl(String url) {
		this.url = url; 
	}
	public int getNbOccurence() {
		return nbOccurence;
	}
	
	public String getUrl() {
		return url;
	}
	
	
}