package game.rooms;

import game.Player;

public class Chambre extends Room {

    private boolean announcedNoise;
    private boolean spider;
    private boolean key;

    public Chambre() {
        super("Chambre", "une chambre confortable mais inquiétante."
                , "Il y'a un lit à votre droite, et une commode à votre gauche.");
        this.announcedNoise = false;
        this.key = true;
        this.spider = true;
    }

    @Override
    public void roomLoop(Player player) {

        super.setVisited(true);

        while (player.getCurrentRoom().equals(this)) {
            if (!canAccess(player)) {
                super.setVisited(false);
                System.out.println("\nVous ne pouvez pas accéder à cette pièce pour le moment.\n");
                player.enterRoom(player.getGame().getHall());
                player.getGame().waitForKey();
                return;
            }

            printRoom();
            player.getGame().printInterface();
            System.out.println("\nDéplacez-vous vers les élements de la pièce pour les inspecter.");
            System.out.println("(Gauche, Droite, Haut, Bas, Pomme)\n");

            switch (player.getGame().waitForInput()) {
                case GAUCHE:
                    System.out.println("Vous ouvrez le tiroir de la commode.");
                    if (key) {
                        key = false;
                        System.out.println("Vous avez trouvé une clé!");
                        player.addKey();
                        if (player.getKeys() >= player.getGame().getRequiredKeys()) {
                            System.out.println("Vous avez trouvé toutes les clés nécessaires pour ouvrir la porte du manoir!");
                            System.out.println("Revenez vers le Hall pour vous échapper!\n");
                        }
                    } else {
                        System.out.println("Le tiroir est vide.");
                    }
                    break;
                case DROITE:
                    System.out.println("Vous inspectez le lit.");
                    if (spider) {
                        spider = false;
                        player.incrementFear();
                        System.out.println("Boo! Une araignée se cachait sous le lit!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + player.getGame().getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= player.getGame().getMaxFear())
                            return;
                    } else {
                        System.out.println("Il n'y a rien sur le lit.");
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

        System.out.println("Vous êtes dans la chambre :\n");

        System.out.println("           +------------+");
        System.out.println("           |            |");
        System.out.println("           +------------+");
        System.out.println("+----------+------------+------------+");
        System.out.println("| Commode  |     🏃     |     Lit    |");
        System.out.println("+----------+------------+------------+");
        System.out.println("           +------------+");
        System.out.println("           |   Revenir  |");
        System.out.println("           +------------+\n");

    }

    @Override
    public boolean canAccess(Player player) {
        return player.getGame().getBibliotheque().hasVisited() &&
                player.getGame().getCuisine().hasVisited() &&
                player.getGame().getSousSol().hasVisited();
    }

    public boolean hasAnnouncedNoise() {
        return announcedNoise;
    }

    public void announceNoise() {
        announcedNoise = true;
        System.out.println("\nAttention! Un bruit étrange vient de la chambre. Vous devriez y jeter un coup d'oeil immédiatement.\n");

    }
}
