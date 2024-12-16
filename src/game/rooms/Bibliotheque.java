package game.rooms;

import game.Game;
import game.Player;
import input.Parser;

/**
 * Classe représentant la pièce bibliothèque.
 */
public class Bibliotheque extends Room {

    /**
     * Indique si la clé est toujours dans la pièce.
     */
    private boolean key;

    /**
     * Indique si l'araignée est toujours dans la pièce.
     */
    private boolean spider;

    /**
     * Indique si la pomme est toujours dans la pièce.
     */
    private boolean apple;

    public Bibliotheque() {
        super("Bibliothèque", "des étagères pleines de livres anciens.",
                "Il y'a à votre gauche et à votre droite deux armoires en bois massif.\n En face de vous, une grande table avec quelque chose dessus.");
        this.key = true;
        this.spider = true;
        this.apple = true;
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
                case GAUCHE: // Armoire avec une clé
                    System.out.println("Vous ouvrez l'armoire de gauche.");
                    if (key) {
                        key = false;
                        System.out.println("Vous avez trouvé une clé!\n");
                        player.addKey();
                        if (player.getKeys() >= game.getRequiredKeys()) {
                            System.out.println("Vous avez trouvé toutes les clés nécessaires pour ouvrir la porte du manoir!");
                            System.out.println("Revenez vers le Hall pour vous échapper!\n");
                        }
                    } else {
                        System.out.println("L'armoire est vide.\n");
                    }
                    break;
                case DROITE: // Armoire avec une araignée
                    System.out.println("Vous ouvrez l'armoire de droite.\n");
                    if (spider) {
                        spider = false;
                        player.incrementFear();
                        System.out.println("Boo! Une araignée vous saute dessus!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + game.getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= game.getMaxFear()) // On arrête tout. Le joueur a perdu.
                            return;
                    } else {
                        System.out.println("L'armoire est vide.\n");
                    }
                    break;
                case HAUT: // Table avec une pomme
                    System.out.println("Vous inspectez la table.");
                    if (apple) {
                        System.out.println("Vous avez trouvé une pomme!");
                        if (player.getAppleCount() < player.getMaxApples()) {
                            apple = false;
                            player.incrementApples();
                            System.out.println("Vous avez maintenant " + player.getAppleCount() + "/" + player.getMaxApples() + " pommes.\n");
                        } else {
                            System.out.println("Vous avez déjà trop de pommes, vous ne pouvez pas en prendre plus.\n");
                        }
                        if (!player.hasBackpack()) // Indice pour indiquer au joueur qu'il peut trouver un sac à dos
                            System.out.println("Vous pouvez trouver un sac à dos pour transporter plus de pommes.\n");

                    } else {
                        System.out.println("La table est vide.\n");
                    }
                    break;
                case BAS:
                    game.enterRoom(game.getHall());
                    return;
                case POMME:
                    game.eatApple();
                    break;
            }
            game.waitForKey();
        }

    }

    @Override
    public void printRoom() {

        Parser.clearScreen();

        System.out.println("Vous êtes dans la bibliothèque :\n");

        System.out.println("           +------------+");
        System.out.println("           |    Table   |");
        System.out.println("           +------------+");
        System.out.println("+----------+------------+------------+");
        System.out.println("| Armoire  |     🏃     |   Armoire  |");
        System.out.println("+----------+------------+------------+");
        System.out.println("           +------------+");
        System.out.println("           |   Revenir  |");
        System.out.println("           +------------+\n");
    }

}
