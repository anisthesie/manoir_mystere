package game;

import game.rooms.Room;
import input.Command;
import input.Parser;

public class Player {

    private int appleCount;
    private int fearLevel;
    private Room currentRoom;
    private int keys;
    private Game game;

    public Player(int apples, int fear_level, int keys, Room currentRoom, Game game) {
        this.appleCount = apples;
        this.fearLevel = fear_level;
        this.currentRoom = currentRoom;
        this.keys = keys;
        this.game = game;
    }

    public void enterRoom(Room room) {
        setCurrentRoom(room);
        room.entryEvent(this);
    }

    public int getAppleCount() {
        return appleCount;
    }

    public void setAppleCount(int apple_count) {
        this.appleCount = apple_count;
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
