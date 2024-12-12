package game;

import game.rooms.*;

public class Game {

    private Player player;
    private Room[] rooms;

    public Game() {
        rooms = new Room[]{
                new Hall(),
                new Chambre(),
                new Bibliotheque(),
                new Cuisine(),
                new SousSol()};

        this.player = new Player(0, 0, this.getHall());
    }

    public void start() {
        System.out.println("Vous êtes dans le hall du manoir. " +
                "Il y a une porte à gauche et une porte à droite.\n" +
                "Taper Haut pour aller en haut, Bas pour aller en bas, Gauche pour aller à gauche, " +
                "Droite pour aller à droite.");
    }

    public void printMap() {

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
