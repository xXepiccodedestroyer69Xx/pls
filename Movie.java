public class Movie {
    private String name;
    private String genre;
    private double rating;
    private String description; 

    public Movie(String name, String genre, double rating, String description) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description; 
    }
}
//THIS IS CORRECT VERSION 
