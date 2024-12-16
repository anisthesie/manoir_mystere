package game.rooms;

import game.Game;
import input.Parser;

/**
 * Classe représentant la pièce "Hall du manoir" du jeu.
 */
public class Hall extends Room {

    public Hall() {
        super("Hall", "l'entrée principale du manoir mystérieux.", "Il y'a 4 pièces autour de vous.");
    }

    @Override
    public void roomLoop(Game game) {
        // Rien à faire ici
        // Les commandes du Hall sont gérées dans la classe Game directement
    }

    @Override
    public void printRoom() {

        Parser.clearScreen();
        System.out.println("Vous êtes dans le hall du manoir :\n");

        System.out.println("               +------------+");
        System.out.println("               |   Chambre  |");
        System.out.println("               +------------+");
        System.out.println("+--------------+------------+-------------+");
        System.out.println("| Bibliothèque |     🏃     |   Cuisine   |");
        System.out.println("+--------------+------------+-------------+");
        System.out.println("               +------------+");
        System.out.println("               |  Sous-sol  |");
        System.out.println("               +------------+\n");
    }
}
