package game.rooms;

import game.Player;

public class Chambre extends Room {

    public Chambre() {
        super("Chambre", "une chambre confortable mais inquiétante.");
    }

    @Override
    public String getCharacteristic() {
        return "Il y'a un lit à votre droite, et une commode à votre gauche.";
    }

    @Override
    public void entryEvent(Player player) {

    }

    @Override
    public void printRoom() {

    }
}
