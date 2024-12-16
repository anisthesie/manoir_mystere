package game.rooms;

import game.Game;
import game.Player;
import input.Parser;

/**
 * Classe représentant la chambre du manoir.
 */
public class Chambre extends Room {

    /**
     * Indique si le bruit étrange a déjà été annoncé.
     * Pour éviter de spammer le joueur avec des messages.
     */
    private boolean announcedNoise;
    /**
     * Indique si l'araignée est toujours dans la pièce.
     */
    private boolean spider;
    /**
     * Indique si la clé est toujours dans la pièce.
     */
    private boolean key;

    public Chambre() {
        super("Chambre", "une chambre confortable mais inquiétante."
                , "Il y'a un lit à votre droite, et une commode à votre gauche.");
        this.announcedNoise = false;
        this.key = true;
        this.spider = true;
    }

    @Override
    public void roomLoop(Game game) {

        Player player = game.getPlayer();

        while (player.getCurrentRoom().equals(this)) {

            // Si la chambre est verrouillée, on ne permet pas au joueur d'y accéder.
            if (this.isLocked(game)) {
                System.out.println("\nLa porte de la chambre semble bloquée,");
                System.out.println("vous ne pouvez pas entrer pour le moment.\n");
                game.enterRoom(game.getHall());
                game.waitForKey();
                return;
            }
            // Si c'est la première fois que le joueur entre dans la chambre, on lui explique le bruit qu'il a entendu.
            if(!super.beenVisited()) {
                System.out.println("\nVous entrez dans la chambre. Le bruit étrange que vous avez entendu semble bien venir d'ici.");
                System.out.println("C'était sûrement une chauve-souris ou un rat.");
                System.out.println("Peu importe, la chambre est maintenant déverrouillée.\n");
                game.waitForKey();
                super.setVisited(true);
            }

            printRoom();
            game.printInterface();
            System.out.println("\nDéplacez-vous vers les élements de la pièce pour les inspecter.");
            System.out.println("(Gauche, Droite, Haut, Bas, Pomme)\n");

            switch (game.waitForInput()) {
                case GAUCHE: // Commode avec une clé
                    System.out.println("Vous ouvrez le tiroir de la commode.");
                    if (key) {
                        key = false;
                        System.out.println("Vous avez trouvé une clé!\n");
                        player.addKey();
                        if (player.getKeys() >= game.getRequiredKeys()) {
                            System.out.println("Vous avez trouvé toutes les clés nécessaires pour ouvrir la porte du manoir!");
                            System.out.println("Revenez vers le Hall pour vous échapper!\n");
                        }
                    } else {
                        System.out.println("Le tiroir est vide.\n");
                    }
                    break;
                case DROITE: // Lit avec une araignée
                    System.out.println("Vous inspectez le lit.");
                    if (spider) {
                        spider = false;
                        player.incrementFear();
                        System.out.println("Boo! Une araignée se cachait sous le lit!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/" + game.getMaxFear() + " points de peur.\n");
                        if (player.getFearLevel() >= game.getMaxFear()) // On arrête tout. Le joueur a perdu.
                            return;
                    } else {
                        System.out.println("Il n'y a rien sur le lit.\n");
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
    public boolean isLocked(Game game) {
        return !(game.getBibliotheque().beenVisited() &&
                game.getCuisine().beenVisited() &&
                game.getSousSol().beenVisited());
    }

    /**
     * Indique si le bruit étrange a déjà été annoncé, pour ne pas spammer le joueur.
     *
     * @return true si le bruit a déjà été annoncé, false sinon.
     */
    public boolean hasAnnouncedNoise() {
        return announcedNoise;
    }

    /**
     * Annonce un bruit étrange dans la chambre et incite le joueur à y jeter un coup d'oeil.
     */
    public void announceNoise() {
        announcedNoise = true;
        System.out.println("\nAttention! Un bruit étrange vient de la chambre. Vous devriez y jeter un coup d'oeil immédiatement.\n");

    }
}
