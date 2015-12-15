import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class MoteurDeRechercheBuilder {


	private MoteurDeRecherche moteur;
	
	
	/**
	 * 
	 */
	public MoteurDeRechercheBuilder() {
		moteur = new MoteurDeRecherche();
	}
	
	/**
	 * 
	 * @param filename
	 */
	public void buildMoteurDeRecherche(String filename) {
		// On récupère le hach
		HachageAbstract<ArrayList<PageOccurence>> hach = moteur.getHach();
		
		// On lit le fichier.
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("ISO-8859-1")))
		{
			System.out.println("Chargement du fichier en cours ...");
			String sCurrentLine;
			String keyWord = "";
			ArrayList<PageOccurence> pages = new ArrayList<PageOccurence>();
			int nbKeyWords = 0;
			boolean first = true;
			while ((sCurrentLine = br.readLine()) != null) {
				
				// On vérifie si c'est un mot clef
				if(sCurrentLine.charAt(0) != '\t') {
					if(!first) {
						hach.add(keyWord, pages);
						pages = new ArrayList<PageOccurence>();
						nbKeyWords ++;
					}
					keyWord = sCurrentLine;
					first = false;
				}
				else {
					// On découpe et on ajoute 
					String[] entity = sCurrentLine.split(" ");
					if(entity.length == 2 && !entity[0].substring(1).contains("ignored")) {
						pages.add(new PageOccurence(Integer.parseInt(entity[0].substring(1)), entity[1]));
					}
				}
			}
			System.out.println("Fichier chargé avec " + nbKeyWords + " mots clefs.");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 
	 * @return
	 */
	public MoteurDeRecherche getMoteurDeRecherche() {
		return moteur;
	}
	
}
