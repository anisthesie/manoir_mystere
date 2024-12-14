import game.Game;
import input.Command;
import input.Parser;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nBienvenu au Manoir Mystère!\n " +
                "Commencer la partie? (Oui/Non)");

        Parser parser = new Parser();
        Command commande = parser.getCommand();
        while (commande != Command.OUI && commande != Command.NON) {

            System.out.println("Je ne comprends pas ce que vous voulez faire.\n" +
                    "Taper Oui pour commencer la partie ou Non pour quitter.");
            commande = parser.getCommand();
        }
        if (commande == Command.NON) {
            System.out.println("Au revoir!");
            return;
        }
        System.out.println("La partie commence!");

        Game game = new Game();
        game.start();

    }
}