package game.rooms;

import game.Player;

public class SousSol extends Room {

    private boolean backpack;
    private boolean spiderTop, spiderRight;
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
    public void roomLoop(Player player) {

        super.setVisited(true);

        if (bat) {
            bat = false;
            player.incrementFear();
            System.out.println("En prenant les escaliers, une chauve-souris passe devant vous. Vous sursautez et prenez un point de peur.");
            System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + player.getGame().getMaxFear() + " points de peur.\n");
            if (player.getFearLevel() >= player.getGame().getMaxFear())
                return;

            player.getGame().waitForKey();
        }

        while (player.getCurrentRoom().equals(this)) {

            printRoom();
            player.getGame().printInterface();
            System.out.println("\nDéplacez-vous vers les élements de la pièce pour les inspecter.");
            System.out.println("(Gauche, Droite, Haut, Bas, Pomme)\n");

            switch (player.getGame().waitForInput()) {
                case GAUCHE:
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
                    player.enterRoom(player.getGame().getHall());
                    return;
                case POMME:
                    player.eatApple();
                    break;
                case DROITE:
                    if (spiderRight) {
                        spiderRight = false;
                        player.incrementFear();
                        System.out.println("Vous avez mis le pied dans une toile d'araignée.");
                        System.out.println("Une araignée vous saute dessus!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + player.getGame().getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= player.getGame().getMaxFear())
                            return;
                    } else {
                        System.out.println("Rien d'intéressant ici.\n");
                    }
                    break;
                case HAUT:
                    if (spiderTop) {
                        spiderTop = false;
                        player.incrementFear();
                        System.out.println("Vous avez mis le pied dans une toile d'araignée.");
                        System.out.println("Une araignée vous saute dessus!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + player.getGame().getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= player.getGame().getMaxFear())
                            return;
                    } else {
                        System.out.println("Rien d'intéressant ici.\n");
                    }
                    break;

            }
            player.getGame().waitForKey();

        }

    }

    @Override
    public void printRoom() {

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
