package game;

import game.rooms.Room;

public class Player {

    private int appleCount;
    private int maxApples;
    private int maxApplesBackpack;
    private int fearLevel;
    private Room currentRoom;
    private int keys;
    private Game game;

    public Player(int apples, int fear_level, int keys, int maxApples, int maxApplesBackpack, Room currentRoom, Game game) {
        this.appleCount = apples;
        this.fearLevel = fear_level;
        this.currentRoom = currentRoom;
        this.maxApples = maxApples;
        this.maxApplesBackpack = maxApplesBackpack;
        this.keys = keys;
        this.game = game;
    }

    public void eatApple() {
        if(this.getAppleCount() > 0){
            this.decrementApples();
            this.decrementFear();
            System.out.println("\nVous mangez une pomme.");
            System.out.println("Vous avez maintenant " + this.getAppleCount() + " pommes et " + this.getFearLevel() + "/"+ this.getGame().getMaxFear() +" points de peur.\n");
        } else {
            System.out.println("Vous n'avez pas de pommes à manger.\n");
        }
    }

    public void enterRoom(Room room) {
        setCurrentRoom(room);
        room.roomLoop(this);
    }

    public int getAppleCount() {
        return appleCount;
    }

    public void setAppleCount(int apple_count) {
        this.appleCount = apple_count;
    }

    public void incrementApples() {
        appleCount++;
    }

    public void decrementApples() {
        if (appleCount > 0)
            appleCount--;
    }

    public boolean hasBackpack() {
        return maxApples == maxApplesBackpack;
    }

    public void giveBackpack() {
        maxApples = maxApplesBackpack;
    }

    public int getMaxApples() {
        return maxApples;
    }

    public void setMaxApples(int maxApples) {
        this.maxApples = maxApples;
    }

    public void incrementFear() {
        fearLevel++;
    }

    public void decrementFear() {
        if (fearLevel > 0)
            fearLevel--;
    }

    public int getFearLevel() {
        return fearLevel;
    }

    public void setFearLevel(int fear_level) {
        this.fearLevel = fear_level;
    }

    public Game getGame() {
        return game;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getKeys() {
        return keys;
    }

    public void addKey() {
        this.keys++;
    }
}
