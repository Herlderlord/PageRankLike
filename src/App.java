import java.util.List;



public class App {

	public static void main(String[] args) {

		MoteurDeRechercheBuilder builder = new MoteurDeRechercheBuilder();
		builder.buildMoteurDeRecherche("data/index.txt", "data/new.txt");
		builder.calculScores();
		String keyword = "twittertwitter";
		
		List<PageOccurence> sites = builder.getMoteurDeRecherche().search(keyword);
		
		//String[] keywords = {"twittertwitter", "abonnersimple", "twittertwitter", "abonnersimple"};
		//List<PageOccurence> sites = builder.getMoteurDeRecherche().search(keywords);

		
		displayResult(sites);
		
	}
	
	public static void displayResult(List<PageOccurence> sites) {
		for(int i = 0; i < sites.size(); i++) {
			System.out.println("Entity ["); 
			Page p = sites.get(i).getPage();
			System.out.println("Url : " + p.getUrl()); 
			System.out.println("Indegree : " + p.getIndegree());
			System.out.println("Outdegree : " + p.getOutdegree());
			System.out.println("Pages in : ");
			for(int j = 0; j < p.getInPages().size(); j++) {
				System.out.println("-- " + p.getInPages().get(j).getUrl());
			}
			System.out.println("Pages ou : ");
			for(int j = 0; j < p.getOutPages().size(); j++) {
				System.out.println("-- " + p.getOutPages().get(j).getUrl());
			}
			System.out.println("NbOccurence : " + sites.get(i).getNbOccurence());
			System.out.println("]");
		}	
	}
}
