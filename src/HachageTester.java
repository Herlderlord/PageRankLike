import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class HachageTester {

	static int nbFautes = 0;
	static int nbMots = 0;
	
	/**
	 * Load a file of key:string value:string and put it in a hach.
	 * 
	 * @param hach
	 * @param filename
	 */
	public static void loadFile(HachageAbstract<String> hach, String filename) {
		
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("ISO-8859-1")))
		{
			System.out.println("Chargement du fichier en cours ...");
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				hach.add(sCurrentLine, sCurrentLine);
			}
			System.out.println("Fichier chargé. ");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Check mistakes in the file gave. 
	 * 
	 * @param dictionnaire
	 * @param filename
	 */
	public static void verifierFautes(HachageAbstract<String> dictionnaire, String filename, boolean montrerFautes) {
		try (BufferedReader br = Files.newBufferedReader(Paths.get(filename), Charset.forName("UTF-8")))
		{
			String sCurrentLine;
			HachageTester.nbFautes = 0;
			HachageTester.nbMots = 0;
			while ((sCurrentLine = br.readLine()) != null) {

				sCurrentLine = sCurrentLine.replace(",", "");
				sCurrentLine = sCurrentLine.replace("?", "");
				sCurrentLine = sCurrentLine.replace("!", "");
				sCurrentLine = sCurrentLine.replace(";", "");
				sCurrentLine = sCurrentLine.replace(".", "");
				sCurrentLine = sCurrentLine.replace("\"", "");
				sCurrentLine = sCurrentLine.replace("(", "");
				sCurrentLine = sCurrentLine.replace(")", "");
				sCurrentLine = sCurrentLine.replace("…", "");
				String[] mots = sCurrentLine.split(" ");
				for(int i = 0; i < mots.length; i++) {
					dictionnaire.setNbWordsCompared(dictionnaire.getNbWordsCompared() + 1);
					HachageTester.nbMots ++;
					if(!("".equals(mots[i]))) {
						if(dictionnaire.get(mots[i].toLowerCase()) != null) {
							// On a trouvé le mot.
						}
						else {
							if(montrerFautes)
								System.out.println("Faute : " + mots[i]); 
							HachageTester.nbFautes ++;
						}
					}
					// On n'a pas trouvé le mot, faute d'ortho.
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 	
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("|-- COMMENTAIRE EN PLUS --|");
		System.out.println("Le programme va tester à 300 000 mots pour commencer.");
		System.out.println("Etant donné que le dictionnaire a un nombre supérieur de mots, le hachage linéaire sera alors lent et ne pourra tous les contenir.");
		System.out.println("Je vous prierai alors de bien vouloir patienter.");
		System.out.println("De plus, le nombre de faute sera alors bien supérieur puisque tous les mots du dictionnaire ne seront pas chargés.");
		System.out.println("|-------------------------------------|");
		System.out.println();
		
		for(int i = 3; i <= 10; i++) {
			HachageTester.nbFautes = 0;
			tester(i * 100000);
		}
		
	}
	
	public static void tester(int nbValues) {
		Hachage<String> hachChaine = new Hachage<String>(nbValues);
		HachageLineaire<String> hachLineaire = new HachageLineaire<String>(nbValues);
		HachageStandard<String> hachStandard = new HachageStandard<String>(nbValues);
		HachageDouble<String> hachDouble = new HachageDouble<String>(nbValues);
		
		// Chaine test
		test(hachChaine, "Hachage Chaine " + hachChaine.getSize(), false);
		
		// Linear test 
		test(hachLineaire, "Hachage Lineaire " + hachLineaire.getSize(), false);
		
		// Standard test 
		test(hachStandard, "Hachage Standard " + hachStandard.getSize(), false);
		
		// Double test 
		test(hachDouble, "Hachage Double" + hachDouble.getSize(), false);
		
	}
	
	
	
	public static void test(HachageAbstract<String> test, String testName, boolean montrerFautes) {

		long time_begin = 0;
		long time_end = 0;
		String fileLoaded = "data/francais.mots";
		System.out.println("|-- " + testName.toUpperCase() + " --|");
		loadFile(test, fileLoaded);
		
		// Test File 1
		System.out.println("File : data/ArticleLeMonde.fr");
		HachageTester.nbFautes = 0;
		time_begin = System.currentTimeMillis();
		verifierFautes(test, "data/ArticleLeMonde.fr", montrerFautes);

		time_end = System.currentTimeMillis();
		
		System.out.println("Nombre de mots : " + HachageTester.nbMots);
		System.out.println("Nombre de fautes : " + HachageTester.nbFautes);
		System.out.println("Nombre moyen de comparaisons pour trouver un Mot : " 
				+ test.getNbComparaisonsFound() / test.getNbFound());
		
		System.out.println("Nombre moyen de comparaisons pour déterminer qu'un mot n'existe pas : " 
				+ test.getNbComparaisonsNotFound() / test.getNbNotFound());
		
		System.out.println("Nombre de comparaisons totale : " 
				+ test.getNbComparaisons());
		
		System.out.println("Temps : " + (time_end - time_begin) + " millisecondes"); 
		//test.afficherTableau();
		System.out.println();
		
		// Test File 2
		System.out.println("File : data/ArticleLeMondeModifie.fr");
		HachageTester.nbFautes = 0;
		time_begin = System.currentTimeMillis();
		verifierFautes(test, "data/ArticleLeMondeModifie.fr", montrerFautes);

		time_end = System.currentTimeMillis();
				
		System.out.println("Nombre de mots : " + HachageTester.nbMots);
		System.out.println("Nombre de fautes : " + HachageTester.nbFautes);
		System.out.println("Nombre moyen de comparaisons pour trouver un Mot : " 
				+ test.getNbComparaisonsFound() / test.getNbFound());
		
		System.out.println("Nombre moyen de comparaisons pour déterminer qu'un mot n'existe pas : " 
				+ test.getNbComparaisonsNotFound() / test.getNbNotFound());
		
		System.out.println("Nombre de comparaisons totale : " 
				+ test.getNbComparaisons());
		
		System.out.println("Temps : " + (time_end - time_begin) + " millisecondes"); 
		//test.afficherTableau();
		System.out.println("|---------------------------------------|");
		System.out.println();
	}
	

}
