package game;

import game.rooms.Room;

public class Player {

    private int apple_count;
    private int fear_level;
    private Room currentRoom;

    public Player(int apples, int fear_level, Room currentRoom) {
        this.apple_count = apples;
        this.fear_level = fear_level;
        this.currentRoom = currentRoom;
    }

    public int getAppleCount() {
        return apple_count;
    }

    public void setAppleCount(int apple_count) {
        this.apple_count = apple_count;
    }

    public int getFearLevel() {
        return fear_level;
    }

    public void setFearLevel(int fear_level) {
        this.fear_level = fear_level;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
