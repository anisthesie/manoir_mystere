package input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class input.Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Parser {

    public Parser() {
    }

    public Command getCommand() {
        String inputLine = "";   // will hold the full input line
        String word1 = "";

        System.out.print("> ");     // print prompt

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        } catch (IOException exc) {
            System.out.println("There was an error during reading: "
                    + exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if (tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        return Command.parseCommand(word1);
    }

    /**
     * Print out a list of valid command words.
     */
    public void showCommands() {
        for (Command command : Command.values()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
