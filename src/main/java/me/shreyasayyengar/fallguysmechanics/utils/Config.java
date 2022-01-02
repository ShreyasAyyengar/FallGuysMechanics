package me.shreyasayyengar.fallguysmechanics.utils;

import me.shreyasayyengar.fallguysmechanics.FallGuysMechanicsPlugin;

import java.util.List;

public class Config {

    private static FallGuysMechanicsPlugin main;

    public static void init(FallGuysMechanicsPlugin main) {
        Config.main = main;
        main.getConfig().options().configuration();
        main.saveDefaultConfig();
    }

    public static int getAmplifier(String path) {
        return main.getConfig().getInt("amplifiers." + path);
    }

    public static double getVelo(String path) {
        return main.getConfig().getDouble("amplifiers." + path);
    }

    public static List<String> getWhitelistedWorlds() {
        return main.getConfig().getStringList("whitelisted-worlds");
    }


}