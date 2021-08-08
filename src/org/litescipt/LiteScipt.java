package org.litescipt;

import org.litescipt.command.ConsoleSender;
import org.litescipt.loader.ClassLoader;

import java.io.File;

public class LiteScipt {

    public static final String VERSION = "BETA-2021-8-6-1";

    public ConsoleSender consoleSender;
    public ClassLoader classLoader;
    public static LiteScipt instance;
    public static boolean debug = false;

    public static void main(String[] args) {
        if (args.length == 0) {
            instance = new LiteScipt();
            instance.init();
        } else if (args.length == 1) {
            switch (args[0]) {
                case "-version":
                    System.out.print("LiteScipt version: " + VERSION + "\nLS Loader(TM) Runtime Environment (build " + ClassLoader.VERSION + ")\n");
                    System.exit(0);
                    break;
                case "-help":
                case "-?":
                    System.out.print("用法: lsp [-options] lapFile [args...]\n" +
                            "           (执行类)\n" +
                            "   或  lsp [-options] -jsp lspPack [args...]\n" +
                            "           (执行 包 文件)");
                    System.exit(0);
                    break;
                default:
                    System.out.print("Unrecognized option: -" + args[0] + "\nError: Could not create the LiteScipt Virtual Machine.\n" +
                            "Error: A fatal exception has occurred. Program will exit.");
                    System.exit(0);
                    break;
            }
        } else if (args.length == 2) {
            if (args[0].equals("-lsp")) {
                String filePath = args[1];
                if (!filePath.contains(File.separator))
                    filePath = System.getProperty("user.dir") + File.separator + filePath;
                instance = new LiteScipt();
                ClassLoader cLoader = new ClassLoader(instance);
                instance.classLoader = cLoader;
                instance.classLoader.load(filePath);
                instance.classLoader.onEnable();
            }
        }
    }

    public void init() {
        System.out.print("LiteScipt version: " + VERSION + "\nLS Loader(TM) Runtime Environment (build " + ClassLoader.VERSION + ")\n");
        instance = this;
        this.consoleSender = new ConsoleSender(this);
    }

}
