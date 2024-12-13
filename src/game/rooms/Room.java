package game.rooms;

import game.Player;

public abstract class Room {

    private String name;
    private String description;


    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public abstract String getCharacteristic();

    public abstract void entryEvent(Player player);

    public abstract void printRoom();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
