package game.rooms;

import game.Player;
import input.Command;

public class Bibliotheque extends Room {

    private boolean key, spider, apple;

    public Bibliotheque() {
        super("Bibliothèque", "Des étagères pleines de livres anciens.");
        this.key = false;
        this.spider = false;
        this.apple = false;
    }

    @Override
    public String getCharacteristic() {
        return "Il y'a à votre gauche et à votre droite deux armoires en bois massif.\n " +
                "En face de vous, une grande table avec quelque chose dessus.";
    }

    @Override
    public void entryEvent(Player player) {
        printRoom();
        System.out.println("Vous êtes dans la bibliothèque.");
        System.out.println(getCharacteristic());
        System.out.println("Déplacez-vous vers les élements de la pièce pour les inspecter.\n");

        while (player.getCurrentRoom().equals(this)){
            Command direction = player.getGame().waitForDirection();
            switch (direction){
                case GAUCHE:
                    System.out.println("Vous ouvrez l'armoire de gauche.");
                    if (!key){
                        System.out.println("Vous trouvez une clé.");
                        player.addKey();
                        key = true;
                    } else {
                        System.out.println("L'armoire est vide.");
                    }
                    break;
                case DROITE:
                    System.out.println("Vous ouvrez l'armoire de droite.");
                    if (!spider){
                        System.out.println("Vous trouvez une araignée.");
                        spider = true;
                    } else {
                        System.out.println("L'armoire est vide.");
                    }
                    break;
                case HAUT:
                    System.out.println("Vous inspectez la table.");
                    if (!apple){
                        System.out.println("Vous trouvez une pomme.");
                        apple = true;
                    } else {
                        System.out.println("La table est vide.");
                    }
                    break;
                case BAS:
                    player.setCurrentRoom(player.getGame().getHall());
                    break;
                default:
                    System.out.println("Commande invalide.");
                    break;
            }
        }

    }

    @Override
    public void printRoom() {
        System.out.println("Bibliothèque :\n");

        System.out.println("           +------------+");
        System.out.println("           |    Table   |");
        System.out.println("           +------------+");
        System.out.println("+------------+------------+------------+");
        System.out.println("| Armoire  |     🏃     |   Armoire   |");
        System.out.println("+------------+------------+------------+");
        System.out.println("           +------------+");
        System.out.println("           |   Revenir  |");
        System.out.println("           +------------+\n");
    }

    public boolean hasKey() {
        return key;
    }

    public boolean hasSpider() {
        return spider;
    }

    public boolean hasApple() {
        return apple;
    }
}
