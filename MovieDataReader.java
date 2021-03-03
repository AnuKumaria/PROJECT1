// --== CS400 File Header Information ==--
// Name: Jonathon Byrnes
// Email: jdbyrnes@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: Changed MovieInterface to Movie

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.io.BufferedReader;

//This class reads in movies from a .csv file, creates a movie object for each,
//and adds them to a list of movies which is then returned.
public class MovieDataReader implements MovieDataReaderInterface {

	/*
	 * Reads in a set of movies from a .csv file and creates a movie object based
	 * off of each line. Adds all of the movies to an ArrayList of movie objects
	 * 
	 * @param FileReader inputFileReader FileReader object to read the .csv file
	 * 
	 * @throws FileNotFoundException if the file cannot be located
	 * 
	 * @throws IOException if it is not a file and if it cannot be read
	 * 
	 * @throws DataFormatException when the file does not have the correct format.
	 */
	@Override
	public List<Movie> readDataSet(FileReader inputFileReader)
			throws FileNotFoundException, IOException, DataFormatException {
		List<Movie> movieList = new ArrayList<Movie>();
		File f = new File("movies.csv"); // my local file, need to change

		if (!f.exists()) {
			throw new FileNotFoundException();
		}

		if (!f.isFile() || !f.canRead()) {
			throw new IOException();
		}

		// checks for correct format
		if (!f.getName().contains(".csv")) {
			throw new DataFormatException();
		}

		inputFileReader = new FileReader(f);
		BufferedReader csvReader = new BufferedReader(inputFileReader);

		String row; // row of text from .csv file
		csvReader.readLine(); // reads the first placeholder line

		// loop where it runs until there isn't a next line
		while ((row = csvReader.readLine()) != null) {

			// creates an Object array to hold the movies data before casting them to their
			// correct type
			Object[] data = new Object[13];
			int startIndex = 0;
			int endIndex = row.indexOf(",");

			for (int i = 0; i < data.length; i++) {

				String dataFrom = null;

				// checks if data starts with a quotation mark
				if (row.charAt(startIndex) == '\"') {

					int firstQuote = startIndex;
					int lastQuote = row.indexOf('\"', firstQuote + 1);

					// checks for quotes inside of quotes by seeing if a comma directly follows the
					// quotation mark
					while (row.charAt(lastQuote + 1) != ',') {
						lastQuote = row.indexOf('\"', lastQuote + 1);
					}

					dataFrom = row.substring(firstQuote + 1, lastQuote);

					// case where reading genres

					data[i] = dataFrom;
					startIndex = lastQuote + 2;
					endIndex = row.indexOf(",", startIndex);

					if (endIndex == -1) {
						endIndex = row.length();
					}

				} else {
					if (endIndex == -1) {
						endIndex = row.length();
					}
					dataFrom = row.substring(startIndex, endIndex);
					data[i] = dataFrom;

					startIndex = endIndex + 1;
					endIndex = row.indexOf(",", startIndex);
				}
			}

			// selects and converts the info from each movie to add to the movie object
			String title = data[0].toString();

			String year = ((String) data[2]);
			int yearAsInt = Integer.parseInt(year);
			Integer yearAsInteger = ((Integer) yearAsInt);

			String[] multipleItems = ((String) data[3]).split(",");
			ArrayList<String> genresList = new ArrayList<String>();
			for (int j = 0; j < multipleItems.length; j++) {
				genresList.add(multipleItems[j]);
			}

			String director = data[7].toString();

			String description = data[11].toString();

			String avgVote = ((String) data[12]);
			//String avgVoteAsFloat = String.parseString(avgVote);

			Movie m = new Movie(title, yearAsInteger, genresList, director, description, avgVote);

			movieList.add(m);

		}

		csvReader.close();
		inputFileReader.close();
		return movieList;
	}

}
