// --== CS400 File Header Information ==--
// Name: Jonathon Byrnes
// Email: jdbyrnes@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: Changed MovieInterface to Movie

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

public class TestMovie {

	public static void main(String[] args) {
		MovieDataReader f = new MovieDataReader();
		FileReader fr = null;
		try {
			List<Movie> movieList = f.readDataSet(fr);

			System.out.println(movieList.size());
			System.out.println(movieList.get(226));

		} catch (IOException | DataFormatException e) {

			e.printStackTrace();
		}
	}
}
