package game.rooms;

import game.Player;

public abstract class Room {

    private String name;
    private String description;
    private String hint;
    private boolean visited;


    public Room(String name, String description, String hint) {
        this.name = name;
        this.description = description;
        this.hint = hint;
        this.visited = false;
    }

    public abstract void roomLoop(Player player);

    public abstract void printRoom();

    public boolean canAccess(Player player) {
        return true;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHint() {
        return hint;
    }

    public boolean hasVisited() {
        return visited;
    }

    public void visit() {
        visited = true;
    }

}
