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
	
	public void buildMoteurDeRecherche(String indexFileName, String newFileName) {
		//this.buildIndex(indexFileName);
		this.buildNew(newFileName);
	}
	
	private void buildIndex(String filename) {
		
		HachageAbstract<ArrayList<PageOccurence>> hach = moteur.getHach();
		HachageAbstract<Page> pages = moteur.getPages();
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("ISO-8859-1")))
		{
			System.out.println("Chargement du fichier en cours ...");
			
			String sCurrentLine;
			String keyWord = "";
			ArrayList<PageOccurence> pagesOccurences = new ArrayList<PageOccurence>();
			int nbKeyWords = 0;
			boolean first = true;
			while ((sCurrentLine = br.readLine()) != null) {
				
				// On vérifie si c'est un mot clef
				if(sCurrentLine.charAt(0) != '\t') {
					if(!first) {
						hach.add(keyWord, pagesOccurences);
						pagesOccurences = new ArrayList<PageOccurence>();
						nbKeyWords ++;
					}
					keyWord = sCurrentLine;
					first = false;
				}
				else {
					// On découpe et on ajoute 
					String[] entity = sCurrentLine.split(" ");
					if(entity.length == 2 && !entity[0].substring(1).contains("ignored")) {
						Page p = pages.get(entity[1]);
						// On v�rifie si la page n'a jamais �t� ajout�e.
						if(p == null){
							p = new Page();
							pages.add(entity[1], p);
						}
						pagesOccurences.add(new PageOccurence(Integer.parseInt(entity[0].substring(1)), p));
					}
				}
			}
			System.out.println("Fichier chargé avec " + nbKeyWords + " mots clefs.");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void buildNew(String filename) {
		System.out.println("[BUILD NEW] En cours de construction");
		
/*
		HachageAbstract<ArrayList<PageOccurence>> hach = moteur.getHach();
		HachageAbstract<Page> pages = moteur.getPages();
	*/	
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("ISO-8859-1")))
		{
			System.out.println("Chargement du fichier en cours ...");
			
			String sCurrentLine = br.readLine();
			String keyWord = "";
			ArrayList<PageOccurence> pagesOccurences = new ArrayList<PageOccurence>();
			int nbKeyWords = 0;
			boolean first = true;
			while ((sCurrentLine = br.readLine()) != null) {
				String [] splited = sCurrentLine.split("\t");
				String url = splited[7];
				String secondUrl = ""; 
				String from = "";
				if(splited.length == 10) {
					secondUrl = splited[8];
					from = splited[9].substring(5, splited[9].length() - 1);
				}
				else if(splited.length == 9){
					if(splited[8].substring(0, 4).contains("(from")) {
						secondUrl = "";
						from = splited[9].substring(5, splited[9].length() - 1);
					}
				}
				
				System.out.println("Url : " + url + " | Sec : " + secondUrl + " | from : " + from);
			}
			System.out.println("Fichier chargé avec " + nbKeyWords + " mots clefs.");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
	
	
	
	
	
	/**
	 * 
	 * @param filename
	 */
	/*
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
	*/
	/**
	 * 
	 * @return
	 */
	
	public MoteurDeRecherche getMoteurDeRecherche() {
		return moteur;
	}
	
}
