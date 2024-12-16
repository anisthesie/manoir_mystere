package game;

import game.rooms.Room;

/**
 * Objet représentant un joueur.
 */
public class Player {

    private int appleCount;
    private int maxApples;
    private int maxApplesBackpack;
    private int fearLevel;
    private Room currentRoom;
    private int keys;

    /**
     * Constructeur de la classe Player (joueur).
     *
     * @param apples Le nombre de pommes que le joueur commence la partie avec.
     * @param fear_level Le niveau de peur dont le joueur commence la partie avec.
     * @param keys Le nombre de clés que le joueur possède quand la partie commence.
     * @param maxApples Le nombre maximum de pommes que le joueur peut transporter.
     * @param maxApplesBackpack Le nombre maximum de pommes que le joueur peut transporter avec un sac à dos.
     * @param currentRoom La pièce dans laquelle le joueur commence la partie.
     */
    public Player(int apples, int fear_level, int keys, int maxApples, int maxApplesBackpack, Room currentRoom) {
        this.appleCount = apples;
        this.fearLevel = fear_level;
        this.currentRoom = currentRoom;
        this.maxApples = maxApples;
        this.maxApplesBackpack = maxApplesBackpack;
        this.keys = keys;
    }

    /**
     * @return Le nombre de pommes que le joueur possède.
     */
    public int getAppleCount() {
        return appleCount;
    }

    /**
     * Ajoute une pomme au joueur.
     */
    public void incrementApples() {
        appleCount++;
    }

    /**
     * Enlève une pomme au joueur.
     */
    public void decrementApples() {
        if (appleCount > 0)
            appleCount--;
    }

    /**
     * @return {@code true} si le joueur possède un sac à dos, {@code false} sinon.
     */
    public boolean hasBackpack() {
        return maxApples == maxApplesBackpack;
    }

    /**
     * Donne un sac à dos au joueur.
     * Augmente le nombre maximum de pommes que le joueur peut transporter.
     */
    public void giveBackpack() {
        maxApples = maxApplesBackpack;
    }

    /**
     * @return Le nombre maximum de pommes que le joueur peut transporter actuellement.
     */
    public int getMaxApples() {
        return maxApples;
    }

    /**
     * Augmente le niveau de peur du joueur d'un point.
     */
    public void incrementFear() {
        fearLevel++;
    }

    /**
     * Diminue le niveau de peur du joueur d'un point.
     */
    public void decrementFear() {
        if (fearLevel > 0)
            fearLevel--;
    }

    /**
     * @return Le niveau de peur du joueur.
     */
    public int getFearLevel() {
        return fearLevel;
    }

    /**
     * @return La chambre actuelle où se trouve le joueur.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Change la chambre actuelle du joueur.
     *
     * @param currentRoom La nouvelle chambre du joueur.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * @return Le nombre de clés que le joueur possède.
     */
    public int getKeys() {
        return keys;
    }

    /**
     * Ajoute une clé au joueur.
     */
    public void addKey() {
        this.keys++;
    }
}
