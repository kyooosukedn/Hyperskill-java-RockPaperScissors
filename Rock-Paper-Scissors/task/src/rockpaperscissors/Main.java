package rockpaperscissors;

import java.util.*;

public class Main {
    static ArrayList<String> weapons = new ArrayList<>(Arrays.asList("rock", "paper", "scissors"));
    static ArrayList<String> counters;
    static Map<String, Integer> scores = new HashMap<>();
    static String computerWeapon;
    static String playerWeapon;
    static String playerName;
    static int playerScore = 0;

    public static void main(String[] args) {
        get_name();

        readRatings(scores);
        if (scores.containsKey(playerName)) {
            playerScore = scores.get(playerName);
        }
        get_weapons();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            playerWeapon = scanner.nextLine();
            if (weapons.contains(playerWeapon)) {
                computerWeapon = choose_weapon();
                get_winner();
            } else {
                if (playerWeapon.equals("!rating")) {
                    System.out.println("Your rating: " + playerScore);
                } else if (playerWeapon.equals("!exit")){
                    System.out.println("Bye!");
                    break;
                } else {
                    System.out.println("Invalid input");
                }
            }

        }
    }

    public static void get_name() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();
        System.out.println("Hello, " + playerName);
    }

    private static void readRatings(Map<String, Integer> scores) {
        try (Scanner scanner = new Scanner(new java.io.File("rating.txt"))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\s+");
                scores.put(line[0], Integer.parseInt(line[1]));
            }
        } catch (java.io.FileNotFoundException e) {
            // File does not exist, do nothing
        }
    }
    
    public static void get_weapons() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter comma-separated list of weapons: ");
        String newWeapons = scanner.nextLine();
        if (!newWeapons.isEmpty()) {
            weapons = new ArrayList<>(Arrays.asList(newWeapons.split(",")));
        }
        System.out.println("Okay, let's start");
    }


    public static String choose_weapon() {
        Random random = new Random();
        int index = random.nextInt(weapons.size());
        return weapons.get(index);
    }

    public static void get_winner() {
        counters = new ArrayList<>(weapons.subList(weapons.indexOf(playerWeapon) + 1, weapons.size()));
        counters.addAll(weapons.subList(0, weapons.indexOf(playerWeapon)));
        counters = new ArrayList<>(counters.subList(0, counters.size() / 2));
        if (computerWeapon.equals(playerWeapon)) {
            System.out.println("There is a draw (" + computerWeapon + ")");
            playerScore += 50;
        } else if (counters.contains(computerWeapon)) {
            System.out.println("Sorry, but the computer chose " + computerWeapon);
        } else {
            System.out.println("Well done. The computer chose " + computerWeapon + " and failed");
            playerScore += 100;
        }
    }
}
