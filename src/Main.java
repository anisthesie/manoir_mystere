import game.Game;
import game.Player;
import game.rooms.*;
import input.Command;
import input.Parser;

/**
 * Classe principale de l'application.
 */
public class Main {

    /**
     * Fonction principale de l'application.
     * Demande au joueur une confirmation pour commencer la partie.
     */
    public static void main(String[] args) {
        System.out.println("\nBienvenu au Manoir Mystère!\n " +
                "Appuyer sur Entrer pour commencer la partie.\n");

        Parser parser = new Parser();
        parser.getCommand(); // Attend que le joeur appuie sur Entrée


        // Initialisation des variables de la partie (joueur, pièces, etc.)
        // définit les valeurs de bases, pour être facilement modifiables
        int applesInFridge = 3;
        Room[] rooms = new Room[] {
                new Hall(),
                new Chambre(),
                new Bibliotheque(),
                new Cuisine(applesInFridge),
                new SousSol()
        };

        int startApples = 0;
        int startFear = 3;
        int startKeys = 0;
        int maxApples = 3;
        int maxApplesWithBackpack = 6;
        Room startRoom = rooms[0];
        Player player = new Player(startApples, startFear, startKeys, maxApples, maxApplesWithBackpack, startRoom);

        int requiredKeys = 2;
        int maxFear = 5;
        Game game = new Game(player, rooms, maxFear, requiredKeys, parser);
        game.start();

    }
}