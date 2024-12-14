package game.rooms;

import game.Player;

public class Hall extends Room {

    public Hall() {
        super("Hall", "l'entrée principale du manoir mystérieux.", "Il y'a 4 pièces autour de vous.");
    }

    @Override
    public void roomLoop(Player player) {

    }

    @Override
    public void printRoom() {

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
