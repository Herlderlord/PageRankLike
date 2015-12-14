import java.util.ArrayList;
import java.util.List;


public class App {

	public static void main(String[] args) {

		MoteurDeRechercheBuilder builder = new MoteurDeRechercheBuilder();
		builder.buildMoteurDeRecherche("data/index.txt");
		List<String> sites = builder.getMoteurDeRecherche().search("twittertwitter");
		
		System.out.println("Liste de sites : " + sites.size());
		for(int i = 0; i < sites.size(); i++) {
			System.out.println(sites.get(i));
		}
	}
	
}
