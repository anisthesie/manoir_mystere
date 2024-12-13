package game;

import game.rooms.*;
import input.Command;
import input.Parser;

public class Game {

    private Player player;
    private Room[] rooms;
    private int maxFear;
    private Parser parser;
    private int requiredKeys;

    public Game() {
        rooms = new Room[]{
                new Hall(),
                new Chambre(),
                new Bibliotheque(),
                new Cuisine(),
                new SousSol()};

        this.requiredKeys = 1;
        this.parser = new Parser();
        this.maxFear = 5;
        this.player = new Player(0, 3, 0, getHall(), this);
    }

    public Game(Player player, Room[] rooms, int maxFear, Parser parser, int requiredKeys) {
        this.player = player;
        this.rooms = rooms;
        this.maxFear = maxFear;
        this.parser = parser;
        this.requiredKeys = requiredKeys;
    }

    public void start() {
        System.out.println(System.lineSeparator().repeat(50));
        printIntroduction();

        System.out.println("Continuer? (Oui/Non)");
        Command commande = parser.getCommand();
        while (commande != Command.OUI && commande != Command.NON) {
            System.out.println("Je ne comprends pas ce que vous voulez faire.\n" +
                    "Taper Oui pour continuer, Non pour quitter.");
            commande = parser.getCommand();
        }
        if (commande == Command.NON) {
            System.out.println("Au revoir!");
            return;
        }
        gameLoop();
    }

    private void gameLoop() {

        while (getPlayer().getKeys() < requiredKeys && getPlayer().getFearLevel() < maxFear) {

            printMap();
            printInterface();
            System.out.println("Pour vous déplacer, taper la direction souhaitée (Haut, Bas, Gauche, Droite).\n");

            Command commande = waitForDirection();
            switch (commande) {
                case HAUT:
                    getPlayer().enterRoom(getChambre());
                    break;
                case BAS:
                    getPlayer().enterRoom(getSousSol());
                    break;
                case GAUCHE:
                    getPlayer().enterRoom(getBibliotheque());
                    break;
                case DROITE:
                    getPlayer().enterRoom(getCuisine());
                    break;
            }

        }
        if (getPlayer().getKeys() >= requiredKeys) {
            System.out.println("Félicitations! Vous avez trouvé les deux clés et vous avez réussi à sortir du manoir.");

        } else {
            System.out.println("Vous avez eu trop peur et vous êtes mort.");
        }

        System.out.println("Merci d'avoir joué!");


    }

    private void printIntroduction() {
        try {
            System.out.println("Vous venez de vous réveiller dans un manoir mystérieux, vous ne savez pas comment vous êtes arrivé ici.");
            Thread.sleep(4000);
            System.out.println("Vous avez peur, il fait froid et sombre. Vous deveez trouver une sortie.");
            Thread.sleep(3000);
            System.out.println("Ah tiens! Une lettre par terre. Vous la ramassez et lisez : \n");
            Thread.sleep(3000);
            System.out.println("    Je suis Mr. Rumble Scott. Je suis enfermé dans ce manoir depuis maintenant une semaine. \n" +
                    "   Je n'ai pas réussi à trouver la sortie. Je suis terrifié.\n" +
                    "   Je vais laisser cette lettre, espérant aider le prochain malheureux à être coincé comme moi. \n" +
                    "   Pour s'échapper, j'ai cru comprendre qu'il fallait trouver deux clés. \n" +
                    "   Je ne sais pas où elles sont, j'espère tout de même que vous réussirez à les trouver. \n" +
                    "   Il y'a quelques pommes dans le manoir, elles vous aideront à garder votre calme. \n" +
                    "   Mais attention, plus vous restez dans le manoir, plus vous aurez peur. \n" +
                    "   Si votre niveau de peur atteint 5, vous mourrez. \n" +
                    "   Bonne chance.\n");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printMap() {

        Room currentRoom = getPlayer().getCurrentRoom();
        String positionJoueur = currentRoom.getName();

        System.out.println("Carte du Manoir :\n");

        System.out.println("           +------------+");
        System.out.println("           |  " + (positionJoueur.equals(getChambre().getName()) ? "   🏃 " : getChambre().getName()) + "   |");
        System.out.println("           +------------+");
        System.out.println("+------------+------------+------------+");
        System.out.println("| " + (positionJoueur.equals(getBibliotheque().getName()) ? "  🏃  " : getBibliotheque().getName()) + "  |  "
                + (positionJoueur.equals(getHall().getName()) ? "🏃" : getHall().getName()) + "  |   "
                + (positionJoueur.equals(getCuisine().getName()) ? "  🏃  " : getCuisine().getName()) + "   |");
        System.out.println("+------------+------------+------------+");
        System.out.println("           +------------+");
        System.out.println("           |  " + (positionJoueur.equals(getSousSol().getName()) ? "   🏃  " : getSousSol().getName()) + "  |");
        System.out.println("           +------------+\n");


    }

    private void printInterface() {
        System.out.println("Votre niveau de peur : " + getPlayer().getFearLevel() + "/" + maxFear);
        System.out.println("Votre pièce actuelle : " + getPlayer().getCurrentRoom().getName() + ", " + getPlayer().getCurrentRoom().getDescription() + ".");
        System.out.println(player.getCurrentRoom().getCharacteristic());
    }

    public Command waitForDirection() {
        Command commande = parser.getCommand();
        while (commande != Command.HAUT && commande != Command.BAS && commande != Command.GAUCHE && commande != Command.DROITE) {
            System.out.println("Je ne comprends pas ce que vous voulez faire.\n" +
                    "Taper Haut, Bas, Gauche ou Droite pour vous déplacer.");
            commande = parser.getCommand();
        }
        return commande;
    }

    public Room[] getRooms() {
        return rooms;
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
