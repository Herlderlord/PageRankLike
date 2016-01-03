import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/*TODO:
 * Faire la demande d'entree 
 * 	DONE - Un clef 
 *  Multi clef 
 * DONE - Am�liorer l'affichage 
 * DONE - 	Am�liorer le score
 * DONE - Faire une liste tri�e pour le score 
 */

public class App {

	static Scanner reader = new Scanner(System.in);
	
	/**
	 * Classe principale du programme. 
	 * @param args
	 */
	public static void main(String[] args) {
		// Pr�paration des donn�es
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

	/**
	 * Permet de g�rer l'input en faisant un parse et 
	 * d'appeler les bonnes m�thodes en cons�quence
	 * 
	 * @param input
	 * @param builder
	 */
	public static void processInput(String input, MoteurDeRechercheBuilder builder) {
		List<PageOccurence> sites = null;
		String [] keywords = parseInput(input);
		System.out.println("Nb Key Words : " + keywords.length);
		if(keywords.length == 1) {
			sites = builder.getMoteurDeRecherche().search(keywords[0]);
		}
		else if(keywords.length > 1) {
			sites = builder.getMoteurDeRecherche().search(keywords);
		}		
		if(sites != null)
			Collections.sort(sites);
		System.out.println("Votre r�sultat : ");
		displayResult(sites);
	}
	
	/**
	 * M�thode qui permet de parse l'input. Elle a �t� g�r�e dans une autre m�thode 
	 * au cas o� l'on voudrait l'am�liorer. 
	 * 
	 * @param input
	 * @return
	 */
	public static String[] parseInput(String input) {
		return input.split(" ");
	}
	
	/**
	 * Permet de r�cup�rer une ligne que l'utilisateur entre dans la console.
	 * 
	 * @return
	 */
	public static String cin() {
		return reader.nextLine();
	}
	
	/**
	 * Afficher le r�sultat de la recherche. 
	 * 
	 * @param sites
	 */
	public static void displayResult(List<PageOccurence> sites) {
		if(sites == null) {
			System.out.println("Aucun r�sultat.");
			return;
		}
		for(int i = 0; i < sites.size(); i++) { 
			Page p = sites.get(i).getPage();
			System.out.println("[ " + p.getUrl() + ", " + p.getScore() + " ]");
		}	
	}
}
