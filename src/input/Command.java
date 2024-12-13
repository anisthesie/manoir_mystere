package input;

public enum Command {
    OUI,
    NON,
    HAUT,
    BAS,
    GAUCHE,
    DROITE,
    POMME,
    UNKNOWN_COMMAND;

    public static Command parseCommand(String commandWord) {
        try {
            return Command.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN_COMMAND;
        }
    }
}
