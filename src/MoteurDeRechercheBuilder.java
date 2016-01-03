import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MoteurDeRechercheBuilder {

	private int nbKeyWords;
	private int nbLinks;
	private MoteurDeRecherche moteur;
	
	
	/**
	 * Constructeur qui instancie le moteur.
	 * 
	 */
	public MoteurDeRechercheBuilder() {
		moteur = new MoteurDeRecherche();
	}
	
	/**
	 * Permet de générer le moteur de recherche et toutes ses structures internes 
	 * à partir du fichier index et du fichier new. 
	 * 
	 * @param indexFileName
	 * @param newFileName
	 */
	public void buildMoteurDeRecherche(String indexFileName, String newFileName) {
		System.out.println("Chargement du fichier " + indexFileName + " ... ");
		this.buildIndex(indexFileName);
		System.out.println("Chargement du fichier " + newFileName + " ... ");
		this.buildNew(newFileName);
	}
	
	/**
	 * Génère les structures en fonction du fichier index.
	 * 
	 * @param filename
	 */
	private void buildIndex(String filename) {
		
		HachageAbstract<ArrayList<PageOccurence>> hach = moteur.getHach();
		HachageAbstract<Page> pages = moteur.getPages();
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("ISO-8859-1")))
		{			
			String sCurrentLine;
			String keyWord = "";
			ArrayList<PageOccurence> pagesOccurences = new ArrayList<PageOccurence>();
			nbKeyWords = 0;
			boolean first = true;
			while ((sCurrentLine = br.readLine()) != null) {
				
				// On vÃ©rifie si c'est un mot clef
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
					// On dÃ©coupe et on ajoute 
					String[] entity = sCurrentLine.split(" ");
					if(entity.length == 2 && !entity[0].substring(1).contains("ignored")) {
						Page p = pages.get(entity[1]);
						// On vérifie si la page n'a jamais été ajoutée.
						if(p == null){
							p = new Page(entity[1]);
							pages.add(entity[1], p);
						}
						pagesOccurences.add(new PageOccurence(Integer.parseInt(entity[0].substring(1)), p));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Génère les structures de données en fonction de build new. 
	 * 
	 * @param filename
	 */
	private void buildNew(String filename) {
		
		//HachageAbstract<Page> pages = moteur.getPages();
	
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("ISO-8859-1")))
		{
			
			String sCurrentLine = br.readLine();
			nbLinks = 0; 
			
			while ((sCurrentLine = br.readLine()) != null) {
				String [] splited = sCurrentLine.split("\t");
				String url = splited[7];
				String secondUrl = ""; 
				String from = "";
				
				// Dans le cas où on a toutes les informations
				if(splited.length == 10) {
					secondUrl = splited[8];
					if(secondUrl.length() == 0)
						secondUrl = "";
					from = splited[9].substring(5, splited[9].length() - 1);
				}
				
				// Dans le cas où l'on n'en a que deux, url et from
				else if(splited.length == 9){
					System.out.println("Length found");
					if(splited[8].substring(0, 4).contains("(from")) {
						secondUrl = "";
						from = splited[8].substring(5, splited[8].length() - 1);
					}
				}

				
				// Dans le cas où l'on a un from. 
				if(from.length() > 1) {
					nbLinks ++;
					
					if(from != "" && from.charAt(0) == ' ') {
						from = from.substring(1);
					}
					from = from.replace("http://", "");
					from = from.replace("https://", "");
					if(secondUrl != "") {
						url = secondUrl.substring(2);
					}
					else {
						url = url.replace("http://", "");
						url = url.replace("https://", "");
					}
					Page toPage = moteur.getPages().get(url);
					Page fromPage = moteur.getPages().get(from + "");
					if(toPage == null) {
						toPage = new Page(url);
						moteur.getPages().add(url, toPage);
					}
					if(fromPage == null) {
						//System.out.println("Ajout de " + from);
						fromPage = new Page(from);
						moteur.getPages().add(from, fromPage);
					}
					toPage.addInPage(fromPage);
					fromPage.addOutPage(toPage);
				}
				// Dans le cas où l'on n'en a pas. 
				else {
					if(secondUrl != "")
						url = secondUrl;
					else {
						url = url.replace("http://", "");
						url = url.replace("https://", "");
					}
					Page toPage = moteur.getPages().get(url);
					if(toPage == null) {
						toPage = new Page(url);
						moteur.getPages().add(url, toPage);
					}
						
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Permet de récupérer la référence du moteur de recherche créé par le builder.
	 * 
	 * @return
	 */
	public MoteurDeRecherche getMoteurDeRecherche() {
		return moteur;
	}
	
	/**
	 * Calcule le score de manière naïve. 
	 */
	public void calculScores() {
		List<Page> pages = moteur.getPages().toList();
		
		for(int i = 0; i < pages.size(); i++) {
			Page p = pages.get(i);
			p.setScore((float)p.getIndegree());
		}
	}
	
	/**
	 * Calcule le score avec la méthode pondérée.
	 */
	public void calculScoresPondere() {
		List<Page> pages = moteur.getPages().toList();
		
		for(int i = 0; i < pages.size(); i++) {
			Page p = pages.get(i);
			p.setScore((float)p.getIndegree() / (float)p.getOutdegree());
		}
	}
	
	/**
	 * Calcule le score avec la méthode récursive. 
	 * @deprecated Peut poser des problèmes dans le cas d'un graphe trop grand
	 */
	public void calculRecursif() {
		List<Page> pages = moteur.getPages().toList();
		
		for(int i = 0; i < pages.size(); i++) {
			Page p = pages.get(i);
			p.setScore(calculRecursifPage(p)); 
		}
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public float calculRecursifPage(Page p) {
		float score = 0;
		List<Page> froms = p.getInPages();
		
		// On parcourt la liste des pages from 
		for(int j = 0; j < froms.size(); j++) {
			Page from = froms.get(j);
			p.setScore(p.getScore() + (calculRecursifPage(from) / p.getOutdegree()));   
		}
		return score;
	}
	/**
	 * 
	 * @return
	 */
	public int getNbKeyWords() {
		return nbKeyWords;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNbLinks() {
		return nbLinks;
	}
}
