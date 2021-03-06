package service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import db2triples.YesterdaysTriplesRetriever;
import stardog.UpdateStardogDb;

/** Application logic of the SmartRecipeDataDragger. Will first retrieve all the triples.
 *  Then execute the stardog update.
 */
public class SmartRecipeUpdate implements Runnable {
	private final String BASE_FOLDER = "C:/Users/rosse/Documents/Java/openis";
	
	private final String INPUT_FILE = BASE_FOLDER + "/SmartRecipeDataDragger/retrieverecipesofday.ttl";
	private final String OUTPUT_PREFIX = BASE_FOLDER + "/output/smartrecipedata";
	private final String OUTPUT_POSTFIX = ".nt";
	
	@Override
	public void run() {
		String outputLoc = _generateTodaysFileLoc();
		
		new YesterdaysTriplesRetriever().retrieveTriples(INPUT_FILE, outputLoc, BASE_FOLDER);
		new UpdateStardogDb().updateFromFile(outputLoc);
	}

	/** Generates a different file location for each day. Can be used locally to follow up. */
	private String _generateTodaysFileLoc() {
		LocalDate date = LocalDateTime.now().toLocalDate();
		
		return new StringBuilder(OUTPUT_PREFIX)
						.append("_").append(date.getYear())
						.append("_").append(date.getDayOfYear() - 1)
						.append(OUTPUT_POSTFIX)
						.toString();
	}
}
