package game;

import game.rooms.*;
import input.Command;
import input.Parser;

/**
 * Objet représentant une partie de jeu.
 */
public class Game {

    private Player player;
    private Room[] rooms;
    private int maxFear;
    private Parser parser;
    private int requiredKeys;


    /**
     * Constructeur de la classe Game.
     *
     * @param player Le joueur de la partie.
     * @param rooms Un tableau de pièces.
     * @param maxFear Le niveau de peur maximum avant de perdre.
     * @param requiredKeys Le nombre de clés nécessaires pour gagner.
     * @param parser Un objet {@link Parser} pour gérer les entrées du clavier.
     */
    public Game(Player player, Room[] rooms, int maxFear, int requiredKeys, Parser parser) {
        this.player = player;
        this.rooms = rooms;
        this.maxFear = maxFear;
        this.parser = parser;
        this.requiredKeys = requiredKeys;
    }

    /**
     * Méthode pour démarrer la partie.
     * Affiche l'introduction, attend une confirmation, puis lance la boucle de jeu.
     */
    public void start() {
        Parser.clearScreen();
        printIntroduction();
        waitForKey();

        gameLoop();
    }

    /**
     * Boucle principale du jeu.
     */
    private void gameLoop() {

        // Tant que le joueur n'a pas trouvé les clés et n'est pas mort, le jeu continue.
        while (getPlayer().getKeys() < requiredKeys && getPlayer().getFearLevel() < maxFear) {

            // Vérifier si un 'bruit étrange' a été entendu dans la chambre
            // Puisque la dernière clé est dans la chambre, on veut que le joueur visite toutes les pièces avant de la débloquer.
            // La chambre alors est verrouillée en début de partie et n'est accessible que si le joueur a visité toutes les autres pièces.
            if (!getChambre().isLocked(this) && !((Chambre) getChambre()).hasAnnouncedNoise()) {
                ((Chambre) getChambre()).announceNoise();
                waitForKey();
            }

            // Affichage de l'interface du jeu
            player.getCurrentRoom().printRoom();
            printInterface();

            System.out.println("\nPour vous déplacer, taper la direction souhaitée (Haut, Bas, Gauche, Droite).");
            System.out.println("Pour manger une pomme, taper Pomme.\n");

            // Attendre une entrée du joueur et agir en conséquence.
            // La méthode waitForInput() vérifie déjà si l'entrée est valide, donc on prend le résultat directement.
            Command commande = waitForInput();
            switch (commande) {
                case HAUT:
                   enterRoom(getChambre());
                    break;
                case BAS:
                    enterRoom(getSousSol());
                    break;
                case GAUCHE:
                    enterRoom(getBibliotheque());
                    break;
                case DROITE:
                    enterRoom(getCuisine());
                    break;
                case POMME:
                    eatApple();
                    waitForKey();
                    break;
            }

        }
        // On est sorti de la boucle principale, donc la partie est terminée.

        if (getPlayer().getKeys() >= requiredKeys) {
            // Le joueur a trouvé toutes les clés et a gagné
            System.out.println("Félicitations! Vous avez trouvé les deux clés et avez réussi à vous échapper du manoir.");
        } else {
            // Le joueur a atteint le niveau de peur maximum et a perdu
            System.out.println("Vous avez eu trop peur et vous êtes mort.");
        }

        System.out.println("Merci d'avoir joué!\n");
        waitForKey();
    }

    /**
     * Affiche l'introduction du jeu.
     * Un délai entre les messages pour donner un effet dramatique.
     */
    private void printIntroduction() {
        try {
            System.out.println("Vous venez de vous réveiller dans un manoir mystérieux, vous ne savez pas comment vous êtes arrivé ici.");
            Thread.sleep(4000);
            System.out.println("Vous avez peur, il fait froid et sombre. Vous devez trouver une sortie.");
            Thread.sleep(4000);
            System.out.println("Ah tiens! Une lettre par terre. Vous la ramassez et lisez : \n");
            Thread.sleep(4000);
            System.out.println("    19/11/1994");
            System.out.println("   Je suis Mr. Rumble Scott. Je suis enfermé dans ce manoir depuis maintenant deux semaines. \n" +
                    "   Je n'ai pas réussi à trouver la sortie. Je suis terrifié.\n" +
                    "   Je vais laisser cette lettre, espérant aider le prochain malheureux à être coincé comme moi. \n" +
                    "   Pour s'échapper, j'ai cru comprendre qu'il fallait trouver deux clés. \n" +
                    "   Je ne sais pas où elles sont, j'espère tout de même que vous réussirez à les trouver. \n" +
                    "   Il y'a quelques pommes dans le manoir, elles vous aideront à garder votre calme. \n" +
                    "   Mais attention, il y'a plein d'araignées partout qui vous feront peur. \n" +
                    "   Si votre niveau de peur atteint 5, vous mourrez. \n" +
                    "   Bonne chance à vous.\n");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Affiche l'interface du jeu avec les informations du joueur et son état de jeu actuel.
     */
    public void printInterface() {
        System.out.println("Votre niveau de peur : " + getPlayer().getFearLevel() + "/" + maxFear);
        System.out.println("Nombre de pommes restantes : " + getPlayer().getAppleCount() + "/" + getPlayer().getMaxApples());
        System.out.println("Nombre de clés trouvées : " + getPlayer().getKeys() + "/" + requiredKeys);
        System.out.println("\nVotre pièce actuelle : " + getPlayer().getCurrentRoom().getName() + ", " + getPlayer().getCurrentRoom().getDescription() + ".\n");
        System.out.println(player.getCurrentRoom().getHint());
    }

    /**
     * Met le jeu en pause et attend que le joueur appuie sur la touche d'entrée.
     */
    public void waitForKey() {
        System.out.println("Appuyez sur Entrée pour continuer...");
        parser.getCommand();
    }

    /**
     * Attend une entrée du joueur et vérifie si c'est une commande valide.
     * Si l'entrée n'est pas valide, le jeu demande une nouvelle entrée.
     *
     * @return La {@link Command} valide entrée par le joueur.
     */
    public Command waitForInput() {
        Command commande = parser.getCommand();
        while (commande.equals(Command.UNKNOWN_COMMAND)) {
            System.out.println("Je ne comprends pas ce que vous voulez faire.\n" +
                    "Taper Haut, Bas, Gauche ou Droite pour vous déplacer.\n" +
                    "Taper Pomme pour manger une pomme.\n");
            commande = parser.getCommand();
        }
        return commande;
    }

    /**
     * Change la pièce actuelle du joueur et lance la boucle de la pièce.
     *
     * @param room La nouvelle pièce du joueur.
     */
    public void enterRoom(Room room) {
        player.setCurrentRoom(room);
        room.roomLoop(this);
    }

    /**
     * Permet au joueur de manger une pomme.
     * Si le joueur a des pommes, il en mange une et diminue son niveau de peur.
     * Le jeu affiche un message d'erreur sinon.
     *
     * Le joueur peut manger une pomme même si son niveau de peur est déjà à zéro,
     * comme manger une pomme pour le plaisir.
     */
    public void eatApple() {
        if(player.getAppleCount() > 0){
            player.decrementApples();
            player.decrementFear();
            System.out.println("\nVous mangez une pomme.");
            System.out.println("Vous avez maintenant " + player.getAppleCount() + " pommes et " + player.getFearLevel() + "/"+ this.getMaxFear() +" points de peur.\n");
        } else {
            System.out.println("Vous n'avez pas de pommes à manger.\n");
        }
    }

    /**
     * @return Le niveau de peur maximum avant de perdre.
     */
    public int getMaxFear() {
        return maxFear;
    }

    /**
     * @return Le nombre de clés nécessaires pour gagner.
     */
    public int getRequiredKeys() {
        return requiredKeys;
    }


    public Room getCuisine() {
        return rooms[3];
    }

    public Room getChambre() {
        return rooms[1];
    }

    public Room getBibliotheque() {
        return rooms[2];
    }

    public Room getHall() {
        return rooms[0];
    }

    public Room getSousSol() {
        return rooms[4];
    }

    public Player getPlayer() {
        return player;
    }
}
