import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/*TODO:
 * Faire la demande d'entree 
 * 	DONE - Un clef 
 *  Multi clef 
 * DONE - Améliorer l'affichage 
 * DONE - 	Améliorer le score
 * DONE - Faire une liste triée pour le score 
 */

public class App {

	static Scanner reader = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		// Préparation des données
		MoteurDeRechercheBuilder builder = new MoteurDeRechercheBuilder();
		builder.buildMoteurDeRecherche("data/index.txt", "data/new.txt");
		// Calcul des scores 
		System.out.println("Calcul des scores");
		builder.calculScores();
		
		List<Page> pages = builder.getMoteurDeRecherche().getPages().toList();
		
		// Affichage des statistiques 
		System.out.println("Nombre de pages " + Page.getCounter());
		System.out.println("Nombre de liens : " + builder.getNbLinks());



		
		//String[] keywords = {"twittertwitter", "abonnersimple", "twittertwitter", "abonnersimple"};
		//List<PageOccurence> sites = builder.getMoteurDeRecherche().search(keywords);

		// Demande de mots clefs
		String input = "";
		boolean continuProg = true;
		do {
			System.out.println("Votre recherche (/quit pour quitter) : ");
			input = cin();
			if(input.compareTo("/quit") == 0) 
				continuProg = false;
			else 
				processInput(input, builder); 
		} while(continuProg);
		reader.close();
		System.out.println("Programme termine");
	}


	public static void processInput(String input, MoteurDeRechercheBuilder builder) {
		List<PageOccurence> sites = null;
		String [] keywords = parseInput(input);
		System.out.println("Nb Key Words : " + keywords.length);
		if(keywords.length == 1) {
			sites = builder.getMoteurDeRecherche().search(keywords[0]);
		}
		else if(keywords.length > 1) {
			// sites = builder.getMoteurDeRecherche()
		}		
		if(sites != null)
			Collections.sort(sites);
		System.out.println("Votre résultat : ");
		displayResult(sites);
	}
	public static String[] parseInput(String input) {
		return input.split(" ");
	}
	
	public static String cin() {
		String c = reader.nextLine();
		return c;
	}
	public static void displayResult(List<PageOccurence> sites) {
		if(sites == null) {
			System.out.println("Aucun résultat.");
			return;
		}
		for(int i = 0; i < sites.size(); i++) { 
			Page p = sites.get(i).getPage();
			System.out.println("[" + p.getUrl() + "] : [");
			System.out.println("\tUrl : " + p.getUrl()); 
			System.out.println("\tIndegree : " + p.getIndegree());
			System.out.println("\tOutdegree : " + p.getOutdegree());
			System.out.println("\tScore : " + p.getScore());
			System.out.println("\tPages in :");
			for(int j = 0; j < p.getInPages().size(); j++) {
				System.out.println("\t\turl : " + p.getInPages().get(j).getUrl());
			}
			System.out.println("\tPages out :");
			for(int j = 0; j < p.getOutPages().size(); j++) {
				System.out.println("\t\turl : " + p.getOutPages().get(j).getUrl());
			}
			System.out.println("\tNbOccurence : " + sites.get(i).getNbOccurence());
			System.out.println("]");
		}	
	}
}
