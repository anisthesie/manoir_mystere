package game.rooms;

import game.Player;

public class Chambre extends Room {

    public Chambre() {
        super("Chambre", "une chambre confortable mais inquiétante."
                , "Il y'a un lit à votre droite, et une commode à votre gauche.");
    }

    @Override
    public void roomLoop(Player player) {

        while (player.getCurrentRoom().equals(this)){
            if(!canAccess(player)){
                System.out.println("\nVous ne pouvez pas accéder à cette pièce pour le moment.\n");
                player.enterRoom(player.getGame().getHall());
                player.getGame().waitForKey();
                return;
            }
        }

    }

    @Override
    public void printRoom() {

    }

    @Override
    public boolean canAccess(Player player) {
        return player.getGame().getBibliotheque().hasVisited() && player.getGame().getCuisine().hasVisited() && player.getGame().getSousSol().hasVisited();
    }
}
