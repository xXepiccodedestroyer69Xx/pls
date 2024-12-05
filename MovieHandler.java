import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MovieHandler {
    private ArrayList<Movie> movies;
    private Random random;
    private ShowtimeHandler showtimeHandler;

    public MovieHandler(ShowtimeHandler showtimeHandler) {
        this.showtimeHandler = showtimeHandler;
        movies = new ArrayList<>();
        random = new Random();
        initializeMovies();
    }

    
    private void initializeMovies() {
        movies.add(new Movie("Inception", "Sci-Fi", 8.8, "A mind-bending thriller about dreams within dreams, directed by Christopher Nolan."));
        movies.add(new Movie("The Dark Knight", "Action", 9.0, "Batman faces his greatest challenge yet as the Joker wreaks havoc in Gotham."));
        movies.add(new Movie("Toy Story", "Animation", 8.3, "A heartwarming tale of toys coming to life and their adventures."));
        movies.add(new Movie("The Godfather", "Crime", 9.2, "An epic saga of a crime family and their rise to power, directed by Francis Ford Coppola."));
        movies.add(new Movie("Interstellar", "Sci-Fi", 8.6, "A space adventure exploring humanity's survival and the mysteries of the universe."));
    }

    
    public void recommendMovie() {
        if (movies.isEmpty()) {
            System.out.println("MovieBot: Sorry, there are no movies available right now.");
            return;
        }

        int index = random.nextInt(movies.size());
        Movie movie = movies.get(index);

        System.out.println("MovieBot: \"" + movie.getName() + "\" is a " + movie.getGenre() +
                " movie with a rating of " + movie.getRating() + " stars.");

        Scanner scanner = new Scanner(System.in);
        System.out.print("MovieBot: Would you like to know the showtime for \"" + movie.getName() + "\"? (yes/no): ");
        String response = scanner.nextLine().toLowerCase();

        if (response.contains("yes")) {
            String showtime = showtimeHandler.getShowtimeForMovie(movie.getName());
            System.out.println("MovieBot: The showtime for \"" + movie.getName() + "\" is " + showtime + ".");
        } else {
            System.out.println("MovieBot: Got it! Let me know if there's anything else you'd like to know.");
        }
    }

    
    public boolean handleInput(String input) {
        if (input.contains("recommend")) {
            recommendMovie();
            return true;
        }

        if (input.contains("what") || input.contains("all") || input.contains("list")|| input.contains("movie")|| input.contains("movies")) {
            System.out.println("MovieBot: Here's a list of all the movies and their genres:");
            for (Movie movie : movies) {
                System.out.println(" - " + movie.getName() + " (" + movie.getGenre() + ")");
            }
            System.out.println("Input the movie name that interests you for a description.");
            return true;
        }

        for (Movie movie : movies) {
            if (input.toLowerCase().contains(movie.getName().toLowerCase())) {
                System.out.println("MovieBot: \"" + movie.getName() + "\": " + movie.getDescription());
                return true;
            }
        }

        
        System.out.println("MovieBot: Is \"" + input + "\" a movie? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();

        if (response.contains("yes")) {
            System.out.println("MovieBot: Sorry, \"" + input + "\" is not on our list. Here's what we have available:");
            for (Movie movie : movies) {
                System.out.println(" - " + movie.getName());
            }
        } else {
            System.out.println("MovieBot: Alright, let me know if you'd like help with anything else.");
        }

        return false;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
