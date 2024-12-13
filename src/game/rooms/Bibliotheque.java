package game.rooms;

import game.Player;
import input.Command;

public class Bibliotheque extends Room {

    private boolean key, spider, apple;

    public Bibliotheque() {
        super("Bibliothèque", "des étagères pleines de livres anciens.");
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

        while (player.getCurrentRoom().equals(this)){
            printRoom();
            player.getGame().printInterface();
            System.out.println("\nDéplacez-vous vers les élements de la pièce pour les inspecter.");
            System.out.println("(Gauche, Droite, Haut, Bas, Pomme)\n");

            Command direction = player.getGame().waitForInput();
            switch (direction){
                case GAUCHE:
                    System.out.println("Vous ouvrez l'armoire de gauche.");
                    if (!key){
                        key = true;
                        System.out.println("Vous avez trouvé une clé!");
                        player.addKey();
                        if(player.getKeys() >= player.getGame().getRequiredKeys()) {
                            System.out.println("Vous avez trouvé toutes les clés nécessaires pour ouvrir la porte du manoir!");
                            System.out.println("Revenez vers le Hall pour vous échapper!");
                        }
                    } else {
                        System.out.println("L'armoire est vide.");
                    }
                    break;
                case DROITE:
                    System.out.println("Vous ouvrez l'armoire de droite.");
                    if (!spider){
                        spider = true;
                        player.incrementFear();
                        System.out.println("Boo! Une araignée vous saute dessus!");
                        System.out.println("Vous avez maintenant " + player.getFearLevel() + "/"+ player.getGame().getMaxFear() +" points de peur.\n");
                        if(player.getFearLevel() >= player.getGame().getMaxFear())
                            return;
                    } else {
                        System.out.println("L'armoire est vide.");
                    }
                    break;
                case HAUT:
                    System.out.println("Vous inspectez la table.");
                    if (!apple){
                        System.out.println("Vous avez trouvé une pomme!");
                        if(player.getAppleCount() < player.getMaxApples()) {
                            apple = true;
                            player.incrementApples();
                            System.out.println("Vous avez maintenant " + player.getAppleCount() + "/" + player.getMaxApples() + " pommes.\n");
                        } else {
                            System.out.println("Vous avez déjà trop de pommes, vous ne pouvez pas en prendre plus.\n");
                        }
                        if(!player.hasBackpack())
                            System.out.println("Vous pouvez trouver un sac à dos pour transporter plus de pommes.\n");

                    } else {
                        System.out.println("La table est vide.");
                    }
                    break;
                case BAS:
                    player.setCurrentRoom(player.getGame().getHall());
                    return;
                case POMME:
                    player.eatApple();
                    break;
                default:
                    System.out.println("Commande invalide.");
                    break;
            }
            player.getGame().waitForKey();
        }

    }

    @Override
    public void printRoom() {
        System.out.println("Vous êtes dans la bibliothèque :\n");

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
