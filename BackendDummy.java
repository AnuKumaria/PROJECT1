import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

// --== CS400 File Header Information ==--
// Author: CS400 Course Staff
// Email: heimerl@cs.wisc.edu / dahl@cs.wisc.edu
// Notes: This dummy class is part of the starter archive for Project One
// in spring 2021. You can extend it to work on your Project One Final
// App.
public class BackendDummy implements BackendInterface {
  public HashMap<String, List<Movie>> genreTable; // the hashtable with genre key
  public ArrayList<String> allGenres = new ArrayList<String>();
  public HashMap<String,List<Movie>> ratingsTable; // the hashtable with ratings key
  public ArrayList<String> allRatings = new ArrayList<String>();
  public List<Movie> movies = new ArrayList<Movie>();
  public MovieDataReader mReader = new MovieDataReader();

  /**
   * This constructor instantiates a backend with the command line arguments passed to the app when
   * started by the user. The arguments are expected to contain the path to the data file that the
   * backend will read in.
   * 
   * @param args list of command line arguments passed to front end
   */
  public BackendDummy(String[] args) {
    File file = new File(args[1]);
    try {
      FileReader r = new FileReader(file);
      movies = mReader.readDataSet(r);
    } catch (FileNotFoundException e1) {
      System.out.println("File not Found");
      e1.printStackTrace();
    } catch (IOException e2) {
      System.out.println("IO Exception thrown");
      e2.printStackTrace();
    } catch (DataFormatException e) {
      System.out.println("File problemo");
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * A constructor that will create a backend from data that can be read in with a reader object.
   * 
   * @param r A reader that contains the data in CSV format.
   */
  public BackendDummy(Reader r) {
    try {
      movies = mReader.readDataSet((FileReader)r);
    } catch (FileNotFoundException e1) {
      System.out.println("File not found");
    } catch (IOException e2) {
      System.out.println("IO Exception thrown");
    } catch (DataFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Method to add a genre that the user selected.
   */
  @Override
  public void addGenre(String genre) {
    allGenres.add(genre);
    genreTable.put(genre, movies);

  }

  /**
   * Method to add a rating that the user selected.
   */
  @Override
  public void addAvgRating(String rating) {
    allRatings.add(rating);
    ratingsTable.put(rating, movies);
  }

  /**
   * Method to remove a genre that the user selected.
   */
  @Override
  public void removeGenre(String genre) {
    genreTable.remove(genre);
  }

  /**
   * Method to remove a rating that the user selected.
   */
  @Override
  public void removeAvgRating(String rating) {
    ratingsTable.remove(rating);
  }

  /**
   * Return the genres added to this backend object.
   */
  @Override
  public List<String> getGenres() {
    return allGenres;
  }

  /**
   * Return the ratings added to this backend object.
   */
  @Override
  public List<String> getAvgRatings() {
    return allRatings;
  }

  /**
   * Returns the number of movies.
   */
  @Override
  public int getNumberOfMovies() {
    return genreTable.size();
  }

  /**
   * Returns all genres in the dataset.
   */
  @Override
  public List<String> getAllGenres() {
    return allGenres;
  }

  /**
   * Returns the movies that match the ratings and genres.
   */
  @Override
  public List<MovieInterface> getThreeMovies(int startingIndex) {
    ArrayList<MovieInterface> movies = new ArrayList<MovieInterface>();
    for (int i = startingIndex; i < startingIndex + 3; i++) {
      movies.add(this.movies.get(i));
    }
    return movies;
  }
}
