package org.litescipt.command.common;

import org.litescipt.LiteScipt;
import org.litescipt.command.Command;
import org.litescipt.command.ConsoleSender;
import org.litescipt.loader.ClassLoader;

import java.io.File;

public class LoaderCommand extends Command {

    @Override
    public boolean run(ConsoleSender sender, String[] command) {
        if (command.length == 2) {
            String path = command[1];
            if (!path.contains(File.separator)) path = System.getProperty("user.dir") + File.separator + path;
            ClassLoader loader = new ClassLoader(sender.getSender());
            LiteScipt.instance.classLoader = loader;
            LiteScipt.instance.classLoader.load(path);
            LiteScipt.instance.classLoader.onEnable();
        } else {
            System.out.print("Unrecognized option: -" + command[0] + "\nError: Could not create the LiteScipt Virtual Machine.\n" +
                    "Error: A fatal exception has occurred.\n");
        }
        return true;
    }
}
