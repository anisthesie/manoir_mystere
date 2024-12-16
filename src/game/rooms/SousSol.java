package game.rooms;

import game.Game;
import game.Player;
import input.Parser;

/**
 * Classe représentant la pièce "Sous-sol" du jeu.
 */
public class SousSol extends Room {

    /**
     * Indique si le sac à dos est toujours dans la pièce.
     */
    private boolean backpack;
    /**
     * Indique si les araignées sont toujours dans la pièce.
     */
    private boolean spiderTop, spiderRight;
    /**
     * Indique si la chauve-souris est toujours dans la pièce.
     */
    private boolean bat;

    public SousSol() {
        super("Sous-sol", "un endroit humide et effrayant.",
                "Il y'a des toiles d'araignées partout.\n Sur votre gauche, un tas de vielles affaires.");
        this.backpack = true;
        this.spiderTop = true;
        this.spiderRight = true;
        this.bat = true;
    }

    @Override
    public void roomLoop(Game game) {

        Player player = game.getPlayer();
        super.setVisited(true);

        // Vérifier si le joueur a déjà pris peur de la chauve-souris
        // pour éviter de lui donner un point de peur à chaque fois qu'il revient dans la pièce.
        if (bat) {
            bat = false;
            player.incrementFear();
            System.out.println("En prenant les escaliers, une chauve-souris passe devant vous. Vous sursautez et prenez un point de peur.");
            System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + game.getMaxFear() + " points de peur.\n");
            if (player.getFearLevel() >= game.getMaxFear())
                return;

            game.waitForKey();
        }

        while (player.getCurrentRoom().equals(this)) {

            printRoom();
            game.printInterface();
            System.out.println("\nDéplacez-vous vers les élements de la pièce pour les inspecter.");
            System.out.println("(Gauche, Droite, Haut, Bas, Pomme)\n");

            switch (game.waitForInput()) {
                case GAUCHE: // Tas d'affaires avec un sac à dos
                    if (backpack) {
                        backpack = false;
                        player.giveBackpack();
                        System.out.println("Vous fouillez dans le tas de vielles affaires.");
                        System.out.println("Vous avez trouvé un sac à dos!");
                        System.out.println("Vous pouvez maintenant transporter jusqu'à " + player.getMaxApples() + " pommes!\n");
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
                case DROITE: // Toile d'araignée à droite
                    if (spiderRight) {
                        spiderRight = false;
                        player.incrementFear();
                        System.out.println("Vous avez mis le pied dans une toile d'araignée.");
                        System.out.println("Une araignée vous saute dessus!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + game.getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= game.getMaxFear()) // On arrête tout. Le joueur a perdu.
                            return;
                    } else {
                        System.out.println("Rien d'intéressant ici.\n");
                    }
                    break;
                case HAUT: // Toile d'araignée en haut
                    if (spiderTop) {
                        spiderTop = false;
                        player.incrementFear();
                        System.out.println("Vous avez mis le pied dans une toile d'araignée.");
                        System.out.println("Une araignée vous saute dessus!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + game.getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= game.getMaxFear()) // On arrête tout. Le joueur a perdu.
                            return;
                    } else {
                        System.out.println("Rien d'intéressant ici.\n");
                    }
                    break;

            }
            game.waitForKey();

        }

    }

    @Override
    public void printRoom() {

        Parser.clearScreen();
        System.out.println("Vous êtes dans le sous-sol :\n");

        System.out.println("                 +------------+");
        System.out.println("                 |   *** ***  |");
        System.out.println("                 +------------+");
        System.out.println("+----------------+------------+---------------+");
        System.out.println("| Tas d'affaires |     🏃     |    *** ***    |");
        System.out.println("+----------------+------------+---------------+");
        System.out.println("                 +------------+");
        System.out.println("                 |   Revenir  |");
        System.out.println("                 +------------+\n");

    }
}
