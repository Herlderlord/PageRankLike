import java.util.List;


public class App {

	public static void main(String[] args) {

		MoteurDeRechercheBuilder builder = new MoteurDeRechercheBuilder();
		builder.buildMoteurDeRecherche("data/index.txt");
		String[] keywords = {"twittertwitter", "abonnersimple", "twittertwitter", "abonnersimple"};
		List<PageOccurence> sites = builder.getMoteurDeRecherche().search(keywords);

		
		
		
		System.out.println("Liste de sites : " + sites.size());
		for(int i = 0; i < sites.size(); i++) {
			System.out.println("Entity ["); 
			System.out.println("Url : " + sites.get(i).getUrl()); 
			System.out.println("NbOccurence : " + sites.get(i).getNbOccurence());
			System.out.println("]");
		}
	}
	
}
