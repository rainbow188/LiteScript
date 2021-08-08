package org.litescipt.command;

import org.litescipt.LiteScipt;
import org.litescipt.command.common.LoaderCommand;

import java.util.Scanner;

public class ConsoleSender {

    private LiteScipt sender;

    public ConsoleSender(LiteScipt liteScipt) {
        this.sender = liteScipt;
        Command.commandMap.put("lsp", new LoaderCommand());
        Thread thread = new Thread() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                scanner.useDelimiter("\n");
                while (true) {
                    String command = scanner.next();
                    if (command.length() > 1 && checkCommand(command)) {
                        Command.dispatchCommand(sender, command.split("\\s+"));
                    }
                }
            }
        };
        thread.start();
    }

    public static boolean checkCommand(String command) {
        for (int i = 0; i < command.length(); i++) {
            if (command.charAt(i) != ' ') return true;
        }
        return false;
    }

    public void setSender(LiteScipt sender) {
        this.sender = sender;
    }

    public void call(String message) {
        System.out.print(">>" + message + "\n");
    }

    public LiteScipt getSender() {
        return sender;
    }

    public void debug(String message) {
        if (LiteScipt.debug) call(message);
    }
}
