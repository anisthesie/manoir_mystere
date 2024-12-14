package game.rooms;

import game.Player;

public class Cuisine extends Room {

    private int apples;

    public Cuisine() {
        super("Cuisine", "une cuisine sombre, mais peut-être utile.",
                "Tout semble vide à priori, à part pour le frigo sur votre droite.");
        apples = 3;
    }

    @Override
    public void roomLoop(Player player) {

        super.visit();

        while (player.getCurrentRoom().equals(this)) {

            printRoom();
            player.getGame().printInterface();
            System.out.println("\nDéplacez-vous vers les élements de la pièce pour les inspecter.");
            System.out.println("(Gauche, Droite, Haut, Bas, Pomme)\n");

            switch (player.getGame().waitForInput()) {
                case DROITE:
                    System.out.println("Vous ouvrez le frigo.\n");
                    if (apples > 0) {
                        int applesTaken = 0;
                        for (int i = 0; i < apples; i++) {
                            System.out.println(player.getAppleCount());
                            System.out.println(player.getMaxApples());
                            if (player.getAppleCount() < player.getMaxApples()) {
                                player.incrementApples();
                                applesTaken++;
                            }
                        }
                        apples -= applesTaken;
                        if (applesTaken > 0) {
                            System.out.println("Vous avez pu prendre " + applesTaken + " pomme(s)");
                        } else {
                            System.out.println("Vous avez déjà trop de pommes sur vous.");
                        }
                        System.out.println("Il reste " + apples + " pomme(s) dans le frigo.\n");
                        if (!player.hasBackpack())
                            System.out.println("Vous pouvez trouver un sac à dos pour transporter plus de pommes.\n");

                    } else {
                        System.out.println("Il n'y a plus de pommes à manger.\n");
                    }

                    break;
                case BAS:
                    player.enterRoom(player.getGame().getHall());
                    return;
                case POMME:
                    player.eatApple();
                    break;
                default:
                    System.out.println("Il n'y rien d'intéressant dans cette direction.\n");
                    break;
            }
            player.getGame().waitForKey();

        }

    }

    @Override
    public void printRoom() {
        System.out.println("Vous êtes dans la cuisine :\n");

        System.out.println("           +------------+");
        System.out.println("           |            |");
        System.out.println("           +------------+");
        System.out.println("+----------+------------+------------+");
        System.out.println("|          |     🏃     |    Frigo   |");
        System.out.println("+----------+------------+------------+");
        System.out.println("           +------------+");
        System.out.println("           |   Revenir  |");
        System.out.println("           +------------+\n");
    }
}
