import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

// --== CS400 File Header Information ==--
// Name: Sydney Benck
// Email: sbenck@wisc.edu
// Team: GG
// Role: FrontEnd Developer
// TA: Surabhi
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
public class MovieMapper {
  /**
   * This method prints out a welcome message to the user when the app is started. The arguments
   * passed through this method are then sent to the base mode method.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // File path to the CSV File with the movie information
    Backend movies = new Backend(args);

    // Welcome message
    System.out.println("Welcome to Movie Mapper!");

    // Sends the user to the base mode
    baseMode(movies);
  }

  /**
   * This method represents the Base Mode of the Movie-Mapper App. The base mode is the mode that
   * the Movie-Mapper will start in. The base mode starts by displaying a list of the top 3
   * avg-rated movies. Then the user is prompted to enter in a command to switch into other modes or
   * exit the app.
   */
  public static void baseMode(Backend movies) {

    System.out.println("Here's a list of our top-rated movies:");

    // Display 3 movies from BackEnd interface
    if (movies.getNumberOfMovies() <= 3) {
      for (int i = 0; i < movies.getNumberOfMovies(); i++) {
        System.out.println(movies.getThreeMovies(i).get(i).getTitle() + " "
            + movies.getThreeMovies(i).get(i).getAvgVote());
      }
    } else if (movies.getNumberOfMovies() > 3) {
      for (int i = 0; i < 3; i++) {
        System.out.println(movies.getThreeMovies(i).get(i).getTitle());
      }
    } else if (movies.getNumberOfMovies() == 0) {
      System.out.println("There are no movies in the selection set!");
    }

    // Takes input from the user
    System.out.println("Scroll through the list by inputing the rating that "
        + "corresponds to the desired movie!");
    System.out.println("Enter 'g' to select a movie based off genre, enter 'r' "
        + "to select a movie based off ratings. Enter 'x' to exit.");

    Scanner input = new Scanner(System.in);

    // integer check
    if (input.hasNextInt()) {
      // Puts command into a variable and then gets rid of the extra line left in the scanner
      String command = input.next();
      input.hasNextLine();
      baseScroll(movies, command);
    }

    else {
      String in = input.next();

      if (in.charAt(0) == 'g') {
        // Sends user to the genre selection mode
        genreSelectionMode(movies);
      } else if (in.charAt(0) == 'r') {
        // Sends user to ratings selection mode
        ratingsSelectionMode(movies);
      } else if (in.charAt(0) == 'x') {
        // Exits the program
        System.exit(0);
      } else {
        System.out.println("This input is invalid! Try entering a number 0-10, or"
            + " switching to genre mode (g) or ratings mode (r).");
      }
      input.close();
    }
  }

  /**
   * Helper method that allows the user to continuously type in numbers in the base mode to scroll
   * through the list of movies by their average rating.
   * 
   * @param m      the list of movies
   * @param rating the rating that was selected by the user as input
   */
  private static void baseScroll(Backend m, String rating) {
    Scanner in = new Scanner(System.in);
    System.out
        .println("Here are the movies with a rating equal to or greater than the rating selected:");

    for (int i = 0; i < m.getNumberOfMovies(); i++) {
      if (m.getThreeMovies(i).get(i).getAvgVote().compareTo(rating) >= 0) {
        System.out.println(m.getThreeMovies(i).get(i).getTitle());
      }
    }
    // integer check
    if (in.hasNextInt()) {
      // Throws new command through this method again
      String input = in.next();
      baseScroll(m, input);
    } else {
      String input = in.next();

      if (input.charAt(0) == 'g') {
        // Sends user to the genre selection mode
        genreSelectionMode(m);
      } else if (input.charAt(0) == 'r') {
        // Sends user to ratings selection mode
        ratingsSelectionMode(m);
      } else {
        System.out.println("This input is invalid! Try entering a number 0-10, or"
            + " switching to genre mode (g) or ratings mode (r).");
      }
      in.close();
    }
  }

  /**
   * This method allows the user to select and deselect movies based off their genre. If multiple
   * genres are selected, only movies that have all selected genres will be displayed.
   */
  public static void genreSelectionMode(Backend movies) {
    Scanner input = new Scanner(System.in);
    String[] s = null;
    Backend selected = new Backend(s); // Initially empty
    Backend unselected = movies; // Initially contains all genre options
    Backend moviesDisplayed = movies; // Initially no movies are displayed

    // Brief explanation telling the user how to use the genre selection mode
    System.out.println("Select and deselect movies based on their genre by inputting "
        + "its corresponding number listed below!");
    System.out.println("After every entry, the selected and deselected lists will be"
        + "updated and printed out to the screen.");
    System.out.println("Here's all of our genres with their corresponding command numbers: ");

    // Assign every genre to a number and print list
    List<String> list = movies.getAllGenres();
    for (int i = 0; i < list.size(); i++) {
      System.out.println((i + 1) + ": " + list.get(i).toString());
    }

    // Brief description on how to return to the base mode.
    System.out.println("Return to the home screen by inputing (x).");

    // Initial screen is an empty list otherwise print out contents of both lists
    if (selected.getGenres().size() == 0) {
      System.out.println("There are no currently selected genres.");
    } else {
      System.out.println("Here are the genres that are currently selected:");
      System.out.println(selected.getAllGenres().toString());
    }

    // Print out unselected movies
    if (unselected.getAllGenres().size() == 0) {
      System.out.println("All genres are currently selected.");
    } else {
      System.out.println("Here are the genres that are currently unselected:");
      System.out.println(unselected.getAllGenres());
    }

    // Prompt the user to make a command
    System.out.println("Enter a number to select or unselect a genre!");

    // integer check
    if (input.hasNextInt()) {
      // Puts command into a variable
      int command = input.nextInt();
      input.hasNextLine();
      genreSelection(selected, unselected, list, command, moviesDisplayed, movies);
    } else {
      String in = input.next();
      if (in.charAt(0) == 'g') {
        // Sends user to the genre selection mode
        genreSelectionMode(movies);
      } else if (in.charAt(0) == 'r') {
        // Sends user to ratings selection mode
        ratingsSelectionMode(movies);
      } else if (in.charAt(0) == 'x') {
        baseMode(movies);
      } else {
        System.out
            .println("This input is invalid! Try entering a number that corresponds to a genre, or"
                + " switching to base mode (x) or ratings mode (r).");
      }
      input.close();
    }
  }

  /**
   * This is a private helper method to the genre selection class. This helper method allows user to
   * recursively type in commands to select or un-select desired genres. The method will prompt the
   * user after the user has selected a genre and updated the lists.
   * 
   * @param s       list of selected genres
   * @param u       list of unselected genres
   * @param all     list of all of the genres
   * @param command the input from the user that will be switched from one list to the other
   * @param display the movies that are displayed to the screen based off of the genres that are
   *                selected
   */
  private static void genreSelection(Backend s, Backend u, List<String> list, int command,
      Backend display, Backend movies) {

    Scanner in = new Scanner(System.in);
    boolean found = false; // Set to true if found in the selected list.

    // Check both lists to locate the genre and then move the genre to the other list
    if (s.getGenres().size() == 0) {
      s.addGenre(list.get(command - 1));
      u.removeGenre(list.get(command - 1).toString());
    } else {
      for (int i = 0; i < s.getGenres().size(); i++) {
        if (s.getGenres().get(i).equals(list.get(command - 1))) {
          s.removeGenre(list.get(command - 1));
          u.addGenre(list.get(command - 1).toString());
          found = true;
        }
      }
    }
    if (!found) {
      for (int i = 0; i < u.getAllGenres().size(); i++) {
        if (u.getAllGenres().get(i).equals(list.get(command - 1))) {
          u.removeGenre(list.get(command - 1));
          s.addGenre(list.get(command - 1).toString());
        }
      }
    }

    // Now that the lists are updated, print them out
    System.out.println("Here are the genres that are currently selected:");
    System.out.println(s.getGenres());

    System.out.println("Here are the genres that are currently unselected:");
    System.out.println(u.getAllGenres());

    // Find all of the movies that have the genres that are selected
    List<Movie> movieList = null;
    System.out.println("Movies based off of the genres that are selected:");
    for (int i = 0; i < s.getGenres().size(); i++) {
      movieList = display.searchByGenre(s.getGenres().get(i));
    }
    for (int i = 0; i < movieList.size(); i++) {
      System.out.println(movieList.get(i).getTitle());
    }

    // Prompt the user to enter input again
    System.out.println("Enter in another number that corresponds to a genre, or "
        + "exit back to the base mode (x).");
    // Integer check
    if (in.hasNextInt()) {
      int userInput = in.nextInt();
      genreSelection(s, u, list, userInput, display, movies);
    } else {
      String input = in.next();
      if (input.charAt(0) == 'g') {
        // Sends user to the genre selection mode
        genreSelectionMode(movies);
      } else if (input.charAt(0) == 'r') {
        // Sends user to ratings selection mode
        ratingsSelectionMode(movies);
      } else if (input.charAt(0) == 'x') {
        // Sends user to base mode
        baseMode(movies);
      } else {
        System.out
            .println("This input is invalid! Try entering a number that corresponds to a genre, or"
                + " switching to base mode (x) or ratings mode (r).");
      }
      in.close();
    }
  }

  /**
   * This method allows users to select a movie based off of their ratings. Initially all ratings
   * are selected. Users can deselect and select any ratings that are in the list. The ratings are
   * in the range [0-10] where 0 is the worst rating and 10 is the highest. When a rating is
   * selected all movies that have this rating (ignoring the decimal point) will be selected. For
   * example, selecting 8 will select all movies from 8.0 to 8.999.
   */
  public static void ratingsSelectionMode(Backend movies) {
    Scanner input = new Scanner(System.in);
    // Initial list of selected movies does not contain anything but tell the
    // user all of the ratings are selected
    String[] in = null;
    Backend deselected = movies;
    Backend selected = new Backend(in);
    Backend display = movies;

    // Prompt User
    System.out.println("Select a movie based off its rating by entering a number"
        + " that represents the rating of the movies you would like to pick from.");
    System.out.println("You can de-select and re-select ratings from the list below!");
    // Let the user know which ratings they can select
    for (int i = 0; i <= 10; i++) {
      System.out.println("[" + i + "]");
    }

    // Tell the user that all of the ratings are selected initially
    // Print out the movies
    System.out.println("Initially all ratings are selected! Here are the movies:");
    System.out.println(movies.getAllTitles());

    // Collect input from user
    String command = input.next();
    // Sends the user to the helper method that recursively checks for new ratings
    ratingSelection(command, movies, selected, deselected, display);


    System.out.println("Ratings currently selected:");
    for (int i = 0; i < selected.getRatings().size(); i++) {
      System.out.println(selected.getAvgRatings().get(i));
    }
    System.out.println("Ratings not currently selected:");
    if (deselected.getRatings().size() == 0) {
      System.out.println("All ratings are currently selected.");
    } else {
      System.out.println(deselected.toString());
    }
  }

  /**
   * This helper method takes a rating that is selected and searches for all of the movies that are
   * in this movie selection set that match that rating. This helper method allows the user to input
   * ratings over and over again until they find the preferred rating.
   * 
   * @param rating     rating that is inputed by the user.
   * @param movies     total list of movies that can be used to switch to different modes
   * @param selected   elected ratings
   * @param deselected selected ratings
   */
  private static void ratingSelection(String rating, Backend movies, Backend selected,
      Backend deselected, Backend display) {
    // Search through each list and then either add the user input to the
    // selected or de-selected list depending on where the input number is found.
    boolean found = false;

    if (selected.getRatings().size() == 0) {
      for (int i = 0; i < deselected.getAvgRatings().size(); i++) {
        if (rating.compareTo(
            String.valueOf((int) Double.parseDouble(deselected.getAvgRatings().get(i)))) == 0) {
          selected.addAvgRating(deselected.getAvgRatings().get(i));
          deselected.getAvgRatings().remove(deselected.getAvgRatings().get(i));
        }
      }
    } else {
      for (int j = 0; j < selected.getRatings().size(); j++) {
        if (selected.getRatings().get(j).equals(rating)) {
          found = true;
          selected.removeAvgRating(rating);
          deselected.addAvgRating(rating);
        }
      }
      // If not found in the selected ratings, then look for it in de-selected ratings
      if (!found) {
        for (int i = 0; i < deselected.getRatings().size(); i++) {
          if (rating.equals(deselected.getRatings().get(i))) {
            deselected.removeAvgRating((String) rating);
            selected.addAvgRating(rating.toString());
          }
        }
      }
    }

    // Print out new lists
    System.out.println("Ratings currently selected:");
    for (int i = 0; i < selected.getRatings().size(); i++) {
      System.out.print(selected.getRatings().get(i).toString() + ", ");
    }
    System.out.println();
    System.out.println("Ratings not currently selectd:");
    if (deselected.getAvgRatings().size() == 0) {
      System.out.println("All ratings are currently selected.");
    } else {
      System.out.println(deselected.getAvgRatings().toString());
    }

    // Find all of the movies that have the ratings that are selected & print out
    List<Movie> movieList = new ArrayList<Movie>();;
    System.out.println("Movies based off of the ratings that are selected:");
    for (int i = 0; i < selected.getRatings().size(); i++) {
      movieList.addAll(deselected.searchByRating(selected.getRatings().get(i)));
    }
    for (int i = 0; i < movieList.size(); i++) {
      System.out.println(movieList.get(i).getTitle() + ", ");
    }

    // Re-prompt the user
    // Collect input from user
    System.out.println("Enter a new rating or (x) to exit and go back to base mode.");
    Scanner in = new Scanner(System.in);

    // Integer check
    if (in.hasNextInt()) {
      String command = in.next();
      // Sends the user to the helper method that recursively checks for new ratings
      ratingSelection(command, movies, selected, deselected, display);
    } else {
      String input = in.next();
      if (input.charAt(0) == 'g') {
        // Sends user to the genre selection mode
        genreSelectionMode(movies);
      } else if (input.charAt(0) == 'r') {
        // Sends user to ratings selection mode
        ratingsSelectionMode(movies);
      } else if (input.charAt(0) == 'x') {
        // Sends user to base mode
        baseMode(movies);
      } else {
        System.out
            .println("This input is invalid! Try entering a number that corresponds to a genre, or"
                + " switching to base mode (x) or ratings mode (r).");
      }
      in.close();
    }
  }
}
