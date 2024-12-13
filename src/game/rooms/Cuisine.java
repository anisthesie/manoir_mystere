package game.rooms;

import game.Player;

public class Cuisine extends Room {

    public Cuisine() {
        super("Cuisine", "Une cuisine sombre, mais peut-être utile.");
    }

    @Override
    public String getCharacteristic() {
        return "Tout semble vide à priori, à part pour le frigo sur votre droite.";
    }

    @Override
    public void entryEvent(Player player) {

    }

    @Override
    public void printRoom() {

    }
}
