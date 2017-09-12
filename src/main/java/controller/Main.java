package controller;

import java.util.concurrent.TimeUnit;

import model.ListOfSubjectObject;

public class Main {

	private static int TREE_DEPTH = 1;
	private static String CATEGORY_FILE= "data/skos_categories_en.ttl";
	/**
	 * This directory contain a file which contains the seeds(roots) for
	 * category tree generation. Each line contains one seed
	 */
	private static String SEED_FILE = "seeds/seeds";
	
	public static void main(String[] args) {
		
		CATEGORY_FILE = args[0];
		TREE_DEPTH = Integer.parseInt(args[1]);
		
		final CategorySeedloader seedLoader = new CategorySeedLoaderFileBased(SEED_FILE);
//		final CategorySeedloader seedLoader = new CategorySeedLoaderDummy();
		seedLoader.loadSeeds();
		
		final CategoryFileParser fileParser = new CategoryFileParser(CATEGORY_FILE);
		long now = System.currentTimeMillis();
		fileParser.parse();
		System.err.println("Reading "+TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-now)+" seconds");
		
		now = System.currentTimeMillis();
		new FastLookUpSubjectObject(ListOfSubjectObject.getListOfSubjectObjects()); 
		System.err.println("Speedup "+TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-now)+" seconds");

		now = System.currentTimeMillis();
		new CategoryTreeGenerator(seedLoader.getSeeds(), TREE_DEPTH).printTreesToFile(); 
		System.err.println("Generating Trees "+TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()-now)+" seconds");
	}

}
