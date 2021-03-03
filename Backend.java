import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

// --== CS400 File Header Information ==--
// Name: Miranda Vescio
// Email: mvescio@wisc.edu
// Team: Blue
// Group: GG
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class creates an instance of a Backend in order to interact with the files from the Frontend
 * and DataWrangler
 */
public class Backend implements BackendInterface {
  public HashMap<String, List<Movie>> genreTable = new HashMap<String, List<Movie>>(); // the
                                                                                       // hashtable
                                                                                       // with genre
                                                                                       // key
  public ArrayList<String> allGenres = new ArrayList<String>(); // keep track of all the genres
  public HashMap<String, List<Movie>> ratingsTable = new HashMap<String, List<Movie>>(); // the
                                                                                         // hashtable
                                                                                         // with
                                                                                         // ratings
                                                                                         // key
  public ArrayList<String> allRatings = new ArrayList<String>(); // keep track of all the ratings
  public List<Movie> movies = new ArrayList<Movie>(); // list of movies from the file
  public MovieDataReader mReader = new MovieDataReader(); // MovieDataReader for reading from the
                                                          // file
  public List<Movie> threeMovies = new ArrayList<Movie>(); // list of three
                                                           // movies to be
                                                           // returned
  public List<String> allSelectedGenres = new ArrayList<String>();
  public List<String> allSelectedRatings = new ArrayList<String>();

  /**
   * This constructor instantiates a backend with the command line arguments passed to the app when
   * started by the user. The arguments are expected to contain the path to the data file that the
   * backend will read in.
   * 
   * @param args list of command line arguments passed to front end
   */
  public Backend(String[] args) {
    // File file = new File(args[1]);
    File file = new File("movies.csv");
    try {
      FileReader r = new FileReader(file);
      movies = mReader.readDataSet(r);
      for (int i = 0; i < movies.size(); i++) {
        for (int j = 0; j < movies.get(i).getGenres().size(); j++) {
          if (genreTable.get(movies.get(i).getGenres().get(j)) == null) {
            List<Movie> m = new ArrayList<Movie>();
            m.add(movies.get(i));
            genreTable.put(movies.get(i).getGenres().get(j), m);
          } else {
            genreTable.get(movies.get(i).getGenres().get(j)).add(movies.get(i));
          }


          if (!allGenres.contains(movies.get(i).getGenres().get(j))) {
            allGenres.add(movies.get(i).getGenres().get(j));
          }
        }
      }

      for (int i = 0; i < movies.size(); i++) {
        if (ratingsTable.get(movies.get(i).getAvgVote()) == null) {
          List<Movie> rat = new ArrayList<Movie>();
          rat.add(movies.get(i));
          ratingsTable.put(movies.get(i).getAvgVote(), rat);
        } else {
          ratingsTable.get(movies.get(i).getAvgVote()).add(movies.get(i));
        }

        if (!allRatings.contains(movies.get(i).getAvgVote())) {
          allRatings.add(movies.get(i).getAvgVote());
        }
      }
    } catch (FileNotFoundException e1) {
      System.out.println("File not Found");
      e1.printStackTrace();
    } catch (IOException e2) {
      System.out.println("IO Exception thrown");
      e2.printStackTrace();
    } catch (DataFormatException e3) {
      System.out.println("Data Format Exception Thrown");
      e3.printStackTrace();
    }
  }

  /**
   * A constructor that will create a backend from data that can be read in with a reader object.
   * 
   * @param r A reader that contains the data in CSV format.
   */
  public Backend(Reader r) {
    try {
      File file1 = new File("movies.csv");
      FileReader fr = new FileReader(file1);
      movies = mReader.readDataSet(fr);
      for (int i = 0; i < movies.size(); i++) {
        for (int j = 0; j < movies.get(i).getGenres().size(); j++) {
          if (genreTable.get(movies.get(i).getGenres().get(j)) == null) {
            List<Movie> m = new ArrayList<Movie>();
            m.add(movies.get(i));
            genreTable.put(movies.get(i).getGenres().get(j), m);
          } else {
            genreTable.get(movies.get(i).getGenres().get(j)).add(movies.get(i));
          }
          //if (!allGenres.contains(movies.get(i).getGenres().get(j))) {
            //addGenre(movies.get(i).getGenres().get(j));
          //}
        }
      }
      
      for (int i = 0; i < movies.size(); i++) {
        if (ratingsTable.get(movies.get(i).getAvgVote()) == null) {
          List<Movie> rat = new ArrayList<Movie>();
          rat.add(movies.get(i));
          ratingsTable.put(movies.get(i).getAvgVote(), rat);
        } else {
          ratingsTable.get(movies.get(i).getAvgVote()).add(movies.get(i));
        }

        if (!allRatings.contains(movies.get(i).getAvgVote())) {
          allRatings.add(movies.get(i).getAvgVote());
        }
      }
    } catch (FileNotFoundException e1) {
      System.out.println("File not found");
      e1.printStackTrace();
    } catch (IOException e2) {
      System.out.println("IO Exception thrown");
      e2.printStackTrace();
    } catch (DataFormatException e3) {
      System.out.println("Data Format Exception Thrown");
      e3.printStackTrace();
    }
  }

  /**
   * Method to add a genre that the user selected.
   */
  @Override
  public void addGenre(String genre) {

    allSelectedGenres.add(genre);
  }

  /**
   * Method to add a rating that the user selected.
   */
  @Override
  public void addAvgRating(String rating) {
    
    allSelectedRatings.add(rating);
  }

  /**
   * Method to remove a genre that the user selected.
   */
  @Override
  public void removeGenre(String genre) {
    
    allSelectedGenres.remove(genre);
  }

  /**
   * Method to remove a rating that the user selected.
   */
  @Override
  public void removeAvgRating(String rating) {
    allSelectedRatings.remove(rating);
  }

  /**
   * Return the genres added to this backend object.
   */
  @Override
  public List<String> getGenres() {

    return allSelectedGenres;
  }
  
  /**
   * Return the ratings added to this backend object.
   */
  public List<String> getRatings() {

    return allSelectedRatings;
  }

  /**
   * Return the ratings added to this backend object.
   */
  @Override
  public List<String> getAvgRatings() {
    List<String> returnRatings = new ArrayList<String>();
    for (int i = 0; i < ratingsTable.size(); i++) {
      if (ratingsTable.containsKey(allRatings.get(i))) {
        returnRatings.add(allRatings.get(i));
      }
    }
    return returnRatings;
  }

  /**
   * Returns the number of movies.
   */
  @Override
  public int getNumberOfMovies() {
    return threeMovies.size();
  }

  /**
   * Returns all genres in the dataset.
   */
  @Override
  public List<String> getAllGenres() {
    return allGenres;
  }

  /**
   * Returns three movies from the startingIndex.
   * 
   * @return threeMovies List of Movies starting at the startingIndex
   */
  @Override
  public List<Movie> getThreeMovies(int startingIndex) {
    for (int i = startingIndex; i < (startingIndex + 3) % movies.size(); i++) {
      threeMovies.add(this.movies.get(i));
    }
    return threeMovies;
  }

  /**
   * Method to get all titles.
   * 
   * @return titles List of Strings that are the titles of the movies
   */
  public List<String> getAllTitles() {
    List<String> titles = new ArrayList<String>();
    for (int i = 0; i < movies.size(); i++) {
      titles.add(movies.get(i).getTitle());
    }
    return titles;
  }

  /**
   * Returns movies in a certain genre from the hash table
   * 
   * @param genre The genre to use as a lookup key
   * @return genreMovies List of movies matching genre
   */
  public List<Movie> searchByGenre(String genre) {
    
    return genreTable.get(genre);
  }

  /**
   * Returns movies with a certain rating from the hash table
   * 
   * @param rating The rating to use as a lookup key
   * @return ratingMovies List of movies matching rating
   */
  public List<Movie> searchByRating(String rating) {

    return ratingsTable.get(rating);
  }
}
