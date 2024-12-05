import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FoodHandler {
    private ArrayList<TheaterFood> menu;
    private Random random;

    public FoodHandler() {
        menu = new ArrayList<>();
        random = new Random();
        initializeMenu();
    }

    
    private void initializeMenu() {
        menu.add(new TheaterFood("Popcorn", 5.99, "Large"));
        menu.add(new TheaterFood("Nachos", 4.99, "Regular"));
        menu.add(new TheaterFood("Hot Dog", 3.49, "Regular"));
        menu.add(new TheaterFood("Soda", 2.99, "Medium"));
        menu.add(new TheaterFood("Candy", 1.99, "Small"));
    }

   
    public boolean handleInput(String input) {
        if (input.contains("menu")||input.contains("food")||input.contains("foods")||input.contains("what")) {
            showMenu();
            return true;
        } 
        else if (input.contains("recommend")) {
            recommendFood();
            return true;
        } 
        else {
            for (TheaterFood item : menu) {
                if (input.contains(item.getName().toLowerCase())) {
                    System.out.println("MovieBot: You can purchase \"" + item.getName() + "\" at the theater!");
                    return true;
                }
            }

            
            Scanner scanner = new Scanner(System.in);
            System.out.print("MovieBot: Is \"" + input + "\" a food item? (yes/no): ");
            String response = scanner.nextLine().toLowerCase();

            if (response.contains("yes")) {
                System.out.println("MovieBot: Sorry, \"" + input + "\" is not on our menu. Here's what we have:");
                showMenu();
            } 
            else {
                System.out.println("MovieBot: Got it. Let me know if you need help with anything else!");
            }
            return true;
        }
    }

    
    private void showMenu() {
        if (menu.isEmpty()) {
            System.out.println("MovieBot: Sorry, the menu is currently empty.");
            return;
        }
        System.out.println("MovieBot: Here's the menu:");
        for (TheaterFood item : menu) {
            System.out.println(" - " + item.getName() + " (" + item.getSize() + ") - $" + item.getPrice());
        }
    }

    
    private void recommendFood() {
        if (menu.isEmpty()) {
            System.out.println("MovieBot: Sorry, the menu is currently empty. I can't recommend anything.");
            return;
        }

        int index = random.nextInt(menu.size());
        TheaterFood recommendedFood = menu.get(index);
        System.out.println("MovieBot: I recommend trying our " + recommendedFood.getName() +
                ". It's a " + recommendedFood.getSize() + " size and costs $" + recommendedFood.getPrice() + ".");
    }
}
