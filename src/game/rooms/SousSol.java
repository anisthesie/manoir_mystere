package game.rooms;

import game.Player;

public class SousSol extends Room {

    public SousSol() {
        super("Sous-sol", "Un endroit humide et effrayant.");
    }

    @Override
    public String getCharacteristic() {
        return "Il y'a des toiles d'araignées partout. Sur votre gauche, un tas de vieielles affaires.";
    }

    @Override
    public void entryEvent(Player player) {

    }

    @Override
    public void printRoom() {

    }
}
