// --== CS400 File Header Information ==--
// Name: Jonathon Byrnes
// Email: jdbyrnes@wisc.edu
// Team: GG blue
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: Changed MovieInterface to Movie

import java.util.List;

// This class creates a Movie object and sets the title, year, genres, director, description, and
// average vote for it.
// It contains methods to access these data fields and compare two movies, as well as converting a
// movie to a string.
public class Movie implements MovieInterface {

  private String title;
  private Integer year;
  private List<String> genres;
  private String director;
  private String description;
  private String avgVote;

  /*
   * Constructor which creates the movie object and stores the movie's title, year, genres,
   * director, description, and average vote.
   */
  public Movie(String title, Integer year, List<String> genres, String director, String description,
      String avgVote) {
    this.title = title;
    this.year = year;
    this.genres = genres;
    this.director = director;
    this.description = description;
    this.avgVote = avgVote;
  }

  /*
   * returns the title of the movie object referenced
   * 
   * @return the title of the movie
   */
  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return title;
  }

  /*
   * returns the year of the movie object referenced
   * 
   * @return the year the movie was made
   */
  @Override
  public Integer getYear() {
    // TODO Auto-generated method stub
    return year;
  }

  /*
   * returns the genres of the movie referenced, with each genre as its own spot in the list
   * 
   * @return a list of the movie's genres
   */
  @Override
  public List<String> getGenres() {
    // TODO Auto-generated method stub
    return genres;
  }

  /*
   * Gets the name of the director of the movie
   * 
   * @return String of the director's name
   */
  @Override
  public String getDirector() {
    // TODO Auto-generated method stub
    return director;
  }

  /*
   * returns a short description of the movie referenced
   * 
   * @return String of the entire description
   */
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return description;
  }

  /*
   * returns the average rating of the movie object referenced
   * 
   * @return the average rating of the movie as a float
   */
  @Override
  public String getAvgVote() {
    // TODO Auto-generated method stub
    return avgVote;
  }

  /*
   * Compares two movies by their average rating and returns an int based on which is higher
   * 
   * @param Movie otherMovie the movie to compare to
   * 
   * @return 1 if otherMovie's avgVote is less than this movies avgVote, 0 if their ratings are the
   * same, and -1 if otherMovie's rating is higher than this movie's. Returns 2 if the method
   * doesn't work correctly.
   */
  @Override
  public int compareTo(Movie otherMovie) {

    if (this.getAvgVote().compareTo(otherMovie.getAvgVote()) < 0) {
      return -1;
    } else if (this.getAvgVote().equals(otherMovie.getAvgVote())) {
      return 0;
    } else if (this.getAvgVote().compareTo(otherMovie.getAvgVote()) > 0) {
      return 1;
    }
    return 2; // returns 2 if something goes wrong (and to fix compilation error)
  }

  /*
   * Converts a movie object into a string
   * 
   * @return a String containing the "title, year, [genres], director, description, avgVote" of the
   * movie
   */
  @Override
  public String toString() {
    return (this.getTitle() + ", " + this.getYear() + ", " + this.getGenres() + ", "
        + this.getDirector() + ", " + this.getDescription() + ", " + this.getAvgVote());
  }
}
