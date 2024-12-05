import java.util.Random;
import java.util.Scanner;

public class MovieBot {
    private MovieHandler movieHandler;
    private FoodHandler foodHandler;
    private ShowtimeHandler showtimeHandler;
    private String currentSection;
    private Random random;

    public MovieBot() {
        foodHandler = new FoodHandler();
        movieHandler = new MovieHandler(null);
        showtimeHandler = new ShowtimeHandler(movieHandler.getMovies());
        movieHandler = new MovieHandler(showtimeHandler); 
        currentSection = null;
        random = new Random();
    }

    public void chat() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\u001b[36m" + "Welcome to MovieBot! Choose a topic: 'movies', 'food', or 'showtimes'. Type 'exit' to quit.");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().toLowerCase();

            
            if (input.equals("exit")) {
                System.out.println("MovieBot: Goodbye! Enjoy the show!");
                break; 
            }

            if (currentSection == null) {
                if (input.contains("movie")) {
                    currentSection = "movies";
                    System.out.println("\u001b[34m" + "MovieBot: You're now in the Movies section! Try asking for a recommendation.");
                }
                  else if (input.contains("food")) {
                    currentSection = "food";
                    System.out.println("\u001b[32m" + "MovieBot: You're now in the Food section! Type 'menu' for our menu.");
                }
                  else if (input.contains("showtime")) {
                    currentSection = "showtimes";
                    System.out.println("\u001b[33m" + "MovieBot: You're now in the Showtimes section! Check our showtimes.");
                  }
                 else {
                    System.out.println("MovieBot: Please choose a valid topic: 'movies', 'food', or 'showtimes'.");
                }
            } 
            else {
                

                if (input.contains("movie") && !currentSection.equals("movies")) {
                    currentSection = "movies";
                    System.out.println("\u001b[34m" + "MovieBot: Switched to Movies! Type recommend to get a recommendation");
                } 
                else if ((input.contains("menu") || input.contains("food")) && !currentSection.equals("food")) {
                    currentSection = "food";
                    System.out.println("\u001b[32m" + "MovieBot: Switched to Food! Type menu for the menu.");
                } 
                else if (input.contains("showtime") && !currentSection.equals("showtimes")) {
                    currentSection = "showtimes";
                    System.out.println("\u001b[33m" + "MovieBot: Switched to Showtimes! Type showtime to see showtimes");
                } 
                else {
                    boolean handled = false;
                    if (currentSection.equals("movies")) handled = movieHandler.handleInput(input);
                    else if (currentSection.equals("food")) handled = foodHandler.handleInput(input);
                    else if (currentSection.equals("showtimes")) handled = showtimeHandler.handleInput(input);

                    if (!handled) {
                        System.out.println(getRandomFallbackResponse());
                    }
                }
            }
        }
        scanner.close(); 
    }

    private String getColorForCurrentSection() {
        if (currentSection.equals("movies")) return "\u001b[34m";
        if (currentSection.equals("food")) return "\u001b[32m"; 
        if (currentSection.equals("showtimes")) return "\u001b[33m";
        return "\u001b[0m"; 
    }

    private String getRandomFallbackResponse() {
        String[] fallbackResponses = {
            "MovieBot: Hmm, I didn't quite catch that. Can you rephrase?",
            "MovieBot: I'm not sure about that. Can you clarify?",
            "MovieBot: Sorry, I don't understand. Could you try something else?",
            "MovieBot: That doesn't ring a bell. Want to ask about movies, food, or showtimes?",
            "MovieBot: Oops! I'm confused. Maybe try asking about another topic?"
        };
        return fallbackResponses[random.nextInt(fallbackResponses.length)];
    }

    public static void main(String[] args) {
        new MovieBot().chat();
    }
}