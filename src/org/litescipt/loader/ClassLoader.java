package org.litescipt.loader;

import org.litescipt.LiteScipt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class ClassLoader {

    public static final String VERSION = "2021-8-6-1";

    public LiteScipt liteScipt;
    public HashMap<String, Class> classHashMap;

    public ClassLoader(LiteScipt loader) {
        this.liteScipt = loader;
        this.classHashMap = new HashMap<>();
    }

    public void load(String path) {

        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            if (!file.getName().contains(".lsp")) {
                liteScipt.consoleSender.call("Error: Unable to access lspFile " + path);
                return;
            }
            if (this.classHashMap.containsKey(file.getName())) return;
            Class target = new Class(file.getName(), path);
            while ((s = br.readLine()) != null) {
                if (!s.contains("//")) {
                    s.replaceAll(";", "");
                    target.codeBuilder.add(s);
                    //liteScipt.consoleSender.debug("写入类:" + s);
                }
            }
            br.close();
            this.classHashMap.put(file.getName(), target);
        } catch (IOException e) {
            liteScipt.consoleSender.call("org.litescipt.lang.NoClassDefFoundError: " + path
                    + "\nat " + path + ".main(args)[" + path + ".lsp:???]");
        }

    }

    public void onEnable() {
        for (Class target : classHashMap.values()) {
            target.run();
        }
    }

    public void onEnable(String without) {
        for (Class target : classHashMap.values()) {
            if (!target.name.equals(without)) target.run();
        }
    }

}
