import java.util.ArrayList;

public class ShowtimeHandler {
    private ArrayList<Showtime> showtimes;

    public ShowtimeHandler(ArrayList<Movie> movies) {
        showtimes = new ArrayList<>();
        initializeShowtimes(movies);
    }

    private void initializeShowtimes(ArrayList<Movie> movies) {
        showtimes.clear();
        showtimes.add(new Showtime(movies.get(0).getName(), "7:00 PM"));
        showtimes.add(new Showtime(movies.get(1).getName(), "8:30 PM"));
        showtimes.add(new Showtime(movies.get(2).getName(), "3:15 PM"));
        showtimes.add(new Showtime(movies.get(3).getName(), "6:45 PM"));
        showtimes.add(new Showtime(movies.get(4).getName(), "9:00 PM"));
    }

    public String getShowtimeForMovie(String movieName) {
        for (Showtime showtime : showtimes) {
            if (showtime.getMovieName().equalsIgnoreCase(movieName)) {
                return showtime.getTime();
            }
        }
        return "No showtime available for this movie.";
    }

    public boolean handleInput(String input) {
        if (input.contains("showtime")) {
            showShowtimes();
            return true;
        }
        return false;
    }

    private void showShowtimes() {
        if (showtimes.isEmpty()) {
            System.out.println("MovieBot: Sorry, there are no showtimes available right now.");
            return;
        }
        System.out.println("MovieBot: Here are today's movie showtimes:");
        for (Showtime showtime : showtimes) {
            System.out.println(" - " + showtime.getMovieName() + " at " + showtime.getTime());
        }
    }
}
