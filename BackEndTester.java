import java.io.File;
import java.io.FileReader;
import java.io.Reader;
//--== CS400 File Header Information ==--
//Name: Miranda Vescio
//Email: mvescio@wisc.edu
//Team: Blue
//Group: GG
//TA: Surabhi
//Lecturer: Gary Dahl
//Notes to Grader: <optional extra notes>

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class BackEndTester {
  public static void main(String[] args) {
    (new BackEndTester()).runTests();
  }

  public void runTests() {
    // add all tests to this method
    if (this.testInitialNumberOfMovies()) {
      System.out.println("Test initial number of movies: PASSED");
    } else {
      System.out.println("Test initial number of movies: FAILED");
    }
    if (this.testGetAllGenresInitial()) {
      System.out.println("Test get all genres initial: PASSED");
    } else {
      System.out.println("Test get all genres initial: FAILED");
    }
    if (this.testInitialSize()) {
      System.out.println("Test get initial size: PASSED");
    } else {
      System.out.println("Test get initial size: FAILED");
    }
    if (this.testGetThreeMovies()) {
      System.out.println("Test get three movies: PASSED");
    } else {
      System.out.println("Test get three movies: FAILED");
    }

    if (this.testGetAllTitles()) {
      System.out.println("Test get all titles: PASSED");
    } else {
      System.out.println("Test get all titles: FAILED");
    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether the initial selection
   * is empty (getNumberOfMovies() returns 0). It passes when 0 is returned and fails in all other
   * cases, including when an exception is thrown.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testInitialNumberOfMovies() {
    try {
      File file1 = new File("movies.csv");
      Reader r = new FileReader(file1);
      Backend backendToTest = new Backend(r);

      if (backendToTest.getNumberOfMovies() == 0) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether the getAllGenres method
   * return the correct set of genres for those three movies.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testGetAllGenresInitial() {
    try {
      File file1 = new File("movies.csv");
      Reader r = new FileReader(file1);
      Backend backendToTest = new Backend(r);
      backendToTest.addGenre("Horror");
      backendToTest.addGenre("Action");

      if (backendToTest.getGenres().size() == 2
          && backendToTest.getGenres().contains("Horror")
          && backendToTest.getGenres().contains("Action")) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

  /**
   * This test instantiates the back end with three movies and tests whether the initial list
   * returned by getThreeMovies starting with the first movie (0) is empty. It passes when 0 is
   * returned and fails in all other cases, including when an exception is thrown.
   * 
   * @return true if the test passed, false if its failed
   */
  public boolean testInitialSize() {
    try {
      File file1 = new File("movies.csv");
      Reader r = new FileReader(file1);
      Backend backendToTest = new Backend(r);

      if (backendToTest.threeMovies.size() == 0) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }


  /**
   * This test instantiates the back and tests if getThreeMovies returns the correct three movies.
   * It passes when the correct movies are returned and fails in all other cases.
   * 
   * @return true if the test passed, false if its failed
   */

  public boolean testGetThreeMovies() {
    try {
      File file1 = new File("movies.csv");
      Reader r = new FileReader(file1);
      BackendInterface backendToTest = new Backend(r);

      if (backendToTest.getThreeMovies(1).size() == 3) {
        return true; // test passed return true;
      } else { // test failed return false;

        return false;
      }
    } catch (Exception e) {
      e.printStackTrace(); // testfailed
      return false;
    }
  }


  /**
   * This test instantiates the back end and tests whether the getAllTitles method return the
   * correct set of titles.
   * 
   * @return true if the test passed, false if it failed
   */
  public boolean testGetAllTitles() {
    try {
      File file1 = new File("movies.csv");
      Reader r = new FileReader(file1);
      Backend backendToTest = new Backend(r);
      String expected =
          "[La battaglia di Long Tan, Era mio figlio, The Dirt, Dirt Music, Pet Sematary, Bolden, Un principe per Natale: Royal Baby, Bull, Snatchers, Family Obligations, IRIS: A Space Opera by Justice, The Protector, Staged Killer, Once Upon a Time in Deadwood, Scare Attraction, Un cavaliere per Natale, Flat Earth: To the Edge and Back, Blood on Her Name, The Exigency, Natale, folle Natale, Dybbuk Box: The Story of Chris Chambers, Always a Bridesmaid, 2177: The San Francisco Love Hacker Crimes, 42nd Street: The Musical, Chasing Molly, NT Live: Cyrano de Bergerac, Dog Town, The Haunting of La Llorona, Christmas a la Mode, As I Am, Monster Island, D-Day, Badland, Un amore degenerato, Munchausen by Internet, Vendetta letale, VHYes, Am I a Serial Killer?, Lynn + Lucy, The Outsider, Arctic Apocalypse, Winter Song, Termination, Crudele fissazione, Bad Ben: The Way In, Lego DC Batman: Family Matters, Portals, Evil Little Things, My Alien Girlfriend, The Mummy Rebirth, Clown, Chasing Unicorns, Present Laughter, A Midsummer Night's Dream, Where We Disappear, Kinky Boots: The Musical, Ecco, Psycho-Pass: Sinners of the System Case.1 Crime and Punishment, Romang, Black Christmas, Stalked, Annabellum: The Curse of Salem, K-12, Zombie Tidal Wave, A Brother's Honor, The Deeper You Dig, Mister America, Return to Horror Hotel, Urban Fears, Empty Spaces, Space Ninjas, Into the Void, The Jack in the Box, Hell House LLC III: Lake of Fire, Il dottore vi ucciderà subito, Giants Being Lonely, A Night of Horror: Nightmare Radio, Metallica and San Francisco Symphony S&M2, Texas Death Trippin', Sacrifice, Christmas on the Range, The Beach House, The Small Hand, The Brighton Miracle, A Beauty & The Beast Christmas, Rising Free, Dirty Sexy Saint, Crypsis, Paw Patrol Mighty Pups - Il film dei supercuccioli, Inside the Rain, A Daughter's Plan To Kill, Beyond the Law - L'infiltrato, Stanley Stanton, Solar Impact, The Final Level: Escaping Rancala, Breakout, 12 Pups of Christmas, City of Trees, Christmas Cars, My Little Pony: Equestria Girls - Holidays Unwrapped, I See You, Stano, Clinton Road, Fisherman's Friends, SGT. Will Gardner, The Song of Names, Duke, Art of Deception, The Haunting of Borley Rectory, Dropa, Zeroville, Random Acts of Violence, Pupazzi alla riscossa, Let It Snow - Innamorarsi sotto la neve, Toy Story 4, Rocketman, Road to Red, Fantasy Island, The Rental, Il giorno sbagliato, The Party Planner, Tribal Get Out Alive, Scare Me, The Quarry, Dangerous Lies, Don't Let Them In, Ghost, Light from the Tower, A Stormy Night, The Father, Agent Jade Black, Work It, A Killer Next Door, Survive the Night, Force of Nature, Becky, Alone, The Dinner Party, The Windermere Children, Long Lost Sister, Carrion, The Unborn, Dead Voices, Nine Days, The Au Pair, The Trip to Greece, Revenge Ride, L'uomo invisibile, A Daughter's Ordeal, Copper Bill, Sognando il ring, Dreamkatcher, Sweet Sunshine, Don't Speak, Minari, Abominable, The Source of Shadows, DNA Killer, Feel the Beat, Abigail Haunting, Cupid, Cabal, Bill & Ted Face the Music, The Warrant, I Used to Go Here, L'ultimo colpo di mamma, The Debt Collector 2, The Escape from Auschwitz, Our Scripted Life, Lies For Rent, The Binge, Seriously Single, Malibu Rescue - Una nuova onda, Rev, Relazione pericolosa, The Will, The Alpha Test, The Moving on Phase, The Haunting of Alcatraz, Rocket Hunter, Horse Girl, La verità di Grace, Spree, Camp Blood 8: Revelations, Rogue, She Dies Tomorrow, Expectant, Ouija Shark, Hard Kill, Sweet Thing, Shifter, Uncle Peckerhead, The Insurrection, Sinister Stalker, Walk Away, Collision Earth, Homeward, Santana, Murder Death Koreatown, Tales from Six Feet Under, Killer Dream Home, Blu profondo 3, Ninna nanna mortale, Dragon Soldiers, Amityville Island, Lego DC: Shazam!: Magic and Monsters, Scare Me, Jungle Beat: The Movie, 5G Zombies, Cry Havoc, Trigger Points, One Night in Bangkok, Monstrous, Top Gunner, Gina Brillon: The Floor is Lava, Murder Manual, Larva Island - Il film, Black Is King, Valley Girl, Host, Steve-O: Gnarly, Simulation Theory Film, Deathstroke Knights & Dragons: The Movie, Desperados, The Last Days of American Crime, Target Number One, Phineas and Ferb the Movie: Candace Against the Universe, Last Moment of Clarity, Unbelievable!!!!!, Fatima, My Salinger Year]";

      if (backendToTest.getAllTitles().size() == 231
          && backendToTest.getAllTitles().toString().equals(expected)) {
        // test passed
        return true;
      } else {
        // test failed
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      // test failed
      return false;
    }
  }

}
