package game.rooms;

import game.Player;

public class Hall extends Room {

    public Hall() {
        super("Hall", "L'entrée principale du manoir mystérieux.");
    }

    @Override
    public String getCharacteristic() {
        return "Il y'a 4 pièces autour de vous.";
    }

    @Override
    public void entryEvent(Player player) {

    }

    @Override
    public void printRoom() {

    }
}
