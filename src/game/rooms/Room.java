package game.rooms;

import game.Game;

/**
 * Classe abstraite représentant une pièce de jeu.
 */
public abstract class Room {

    private String name;
    private String description;
    private String hint;
    private boolean visited;

    /**
     * Constructeur de la classe Room.
     *
     * @param name Le nom de la pièce.
     * @param description Une brève description de la pièce.
     * @param hint Une description visuelle qui aidera le joueur à explorer la pièce.
     */
    public Room(String name, String description, String hint) {
        this.name = name;
        this.description = description;
        this.hint = hint;
        this.visited = false;
    }

    /**
     * Méthode abstraite pour la boucle de jeu de la pièce.
     * Cette méthode sera appelée à chaque fois que le joueur entre dans la pièce.
     * La boucle est implentée dans les classes filles, et est spécifique à chaque pièce.
     * Mais elle suit généralement le même schéma :
     *      tant que le joueur est dans la pièce,
     *          afficher la pièce,
     *          attendre une entrée,
     *          agir en conséquence.
     *
     * @param game L'objet {@link Game} en cours.
     */
    public abstract void roomLoop(Game game);

    /**
     * Méthode abstraite pour afficher visuellement la pièce et ses éléments.
     * Cette méthode est implémentée dans les classes filles, et est spécifique à chaque pièce.
     */
    public abstract void printRoom();

    /**
     * Méthode pour vérifier si la pièce est débloquée.
     * Cette méthode devra être implémentée en fonction des conditions de déblocage de la pièce.
     *
     * @param game L'objet {@link Game} en cours.
     * @return {@code true} si la pièce est débloquée, {@code false} sinon.
     */
    public boolean isLocked(Game game) {
        return false;
    }

    /**
     * @return Le nom de la pièce.
     */
    public String getName() {
        return name;
    }

    /**
     * @return La description de la pièce.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return L'indice visuel pour aider le joueur à explorer la pièce.
     */
    public String getHint() {
        return hint;
    }

    /**
     * @return {@code true} si la pièce a déjà été visitée, {@code false} sinon.
     */
    public boolean beenVisited() {
        return visited;
    }

    /**
     * Marque la pièce comme visitée.
     *
     * @param visited {@code true} si la pièce a été visitée, {@code false} sinon.
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
