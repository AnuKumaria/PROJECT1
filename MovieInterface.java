import java.util.List;
    public interface MovieInterface extends Comparable<Movie> {
        
        public String getTitle();
        public Integer getYear();
        public List<String> getGenres();
        public String getDirector();
        public String getDescription();
        public String getAvgVote();

        //from super interface Comparable
        public int compareTo(Movie otherMovie);
    }

