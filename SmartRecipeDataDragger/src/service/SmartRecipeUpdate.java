package service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import db2triples.YesterdaysTriplesRetriever;
import stardog.UpdateStardogDb;

/** Application logic of the SmartRecipeDataDragger. Will first retrieve all the triples.
 *  Then execute the stardog update.
 */
public class SmartRecipeUpdate implements Runnable {
	private final String INPUT_FILE = "retrieverecipesofday.ttl";
	
	private final String FILE_PREFIX = "smartrecipedata";
	private final String FILE_POSTFIX = ".nt";
	
	@Override
	public void run() {
		String outputLoc = _generateTodaysFileLoc();
		
		new YesterdaysTriplesRetriever().retrieveTriples(INPUT_FILE, outputLoc);
		new UpdateStardogDb().updateFromFile(outputLoc);
	}

	/** Generates a different file location for each day. Can be used locally to follow up. */
	private String _generateTodaysFileLoc() {
		LocalDate date = LocalDateTime.now().toLocalDate();
		
		return new StringBuilder(FILE_PREFIX)
						.append("_").append(date.getYear())
						.append("_").append(date.getDayOfYear())
						.append(FILE_POSTFIX)
						.toString();
	}
}
