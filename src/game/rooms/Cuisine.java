package game.rooms;

import game.Game;
import game.Player;
import input.Parser;

/**
 * Classe représentant la pièce "Cuisine" du jeu.
 */
public class Cuisine extends Room {


    private int apples;
    /**
     * Indique si l'araignée est toujours dans la pièce.
     */
    private boolean spider;


    /**
     * Constructeur de la classe Cuisine.
     *
     * @param apples Le nombre de pommes dans le frigo.
     */
    public Cuisine(int apples) {
        super("Cuisine", "une cuisine sombre, mais peut-être utile.",
                "Tout semble vide à priori, à part pour le frigo sur votre droite.");
        this.apples = apples;
        spider = true;
    }

    @Override
    public void roomLoop(Game game) {

        Player player = game.getPlayer();
        super.setVisited(true);

        while (player.getCurrentRoom().equals(this)) {

            printRoom();
            game.printInterface();
            System.out.println("\nDéplacez-vous vers les élements de la pièce pour les inspecter.");
            System.out.println("(Gauche, Droite, Haut, Bas, Pomme)\n");

            switch (game.waitForInput()) {
                case DROITE: // Frigo avec des pommes
                    System.out.println("Vous ouvrez le frigo.");
                    if (apples > 0) {
                        int applesTaken = 0;
                        for (int i = 0; i < apples; i++) {
                            if (player.getAppleCount() < player.getMaxApples()) {
                                player.incrementApples();
                                applesTaken++;
                            }
                        }
                        apples -= applesTaken;
                        if (applesTaken > 0) {
                            System.out.println("Vous avez pris " + applesTaken + " pomme(s) du frigo!");
                        } else {
                            System.out.println("Vous avez déjà trop de pommes sur vous.");
                        }
                        System.out.println("Il reste " + apples + " pomme(s) dans le frigo.\n");
                        if (!player.hasBackpack())
                            System.out.println("Vous pouvez trouver un sac à dos pour transporter plus de pommes.\n");

                    } else {
                        System.out.println("Le frigo est vide.\n");
                    }

                    break;
                case GAUCHE: // Toile d'araignée
                    if (spider) {
                        spider = false;
                        player.incrementFear();
                        System.out.println("Vous avez mis le pied dans une toile d'araignée.");
                        System.out.println("Une araignée vous saute dessus!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + game.getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= game.getMaxFear())
                            return;
                    } else {
                        System.out.println("Rien d'intéressant ici.\n");
                    }
                    break;
                case BAS:
                    game.enterRoom(game.getHall());
                    return;
                case POMME:
                    game.eatApple();
                    break;
                default:
                    System.out.println("Il n'y rien d'intéressant dans cette direction.\n");
                    break;
            }
            game.waitForKey();

        }

    }

    @Override
    public void printRoom() {
        Parser.clearScreen();

        System.out.println("Vous êtes dans la cuisine :\n");

        System.out.println("           +------------+");
        System.out.println("           |            |");
        System.out.println("           +------------+");
        System.out.println("+----------+------------+------------+");
        System.out.println("| *** ***  |     🏃     |    Frigo   |");
        System.out.println("+----------+------------+------------+");
        System.out.println("           +------------+");
        System.out.println("           |   Revenir  |");
        System.out.println("           +------------+\n");
    }
}
