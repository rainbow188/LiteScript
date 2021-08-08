package org.litescipt.command;

import org.litescipt.LiteScipt;

import java.util.HashMap;

public class Command {

    public static HashMap<String, Command> commandMap = new HashMap<>();

    public static void dispatchCommand(LiteScipt sender, String[] command) {
        String commandStr = command[0];
        if (commandMap.containsKey(commandStr)) {
            commandMap.get(commandStr).run(sender.consoleSender, command);
        } else {
            System.out.print("Unrecognized option: -" + commandStr + "\nError: Could not create the LiteScipt Virtual Machine.\n" +
                    "Error: A fatal exception has occurred.\n");
        }
    }

    public boolean run(ConsoleSender sender, String[] command) {
        return true;
    }

}
